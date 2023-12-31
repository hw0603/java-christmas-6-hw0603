package christmas.controller;

import christmas.constant.Badge;
import christmas.domain.PlannerResult;
import christmas.domain.badgepolicy.DefaultBadgePolicy;
import christmas.domain.discountpolicy.ChristmasDiscountPolicy;
import christmas.domain.Order;
import christmas.domain.discountpolicy.DiscountPolicy;
import christmas.domain.discountpolicy.SpecialDiscountPolicy;
import christmas.domain.User;
import christmas.domain.VisitDate;
import christmas.domain.discountpolicy.WeekDayDiscountPolicy;
import christmas.domain.discountpolicy.WeekendDiscountPolicy;
import christmas.domain.giveawaypolicy.DefaultGiveawayPolicy;
import christmas.service.BadgeService;
import christmas.service.CalendarService;
import christmas.service.DiscountService;
import christmas.service.GiveawayService;
import christmas.service.OrderService;
import christmas.service.UserService;
import christmas.util.DateValidator;
import christmas.util.OrderValidator;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.List;
import java.util.Map;
import christmas.constant.Menu;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PlannerController {
    CalendarService calendarService;
    OrderService orderService;
    UserService userService;
    DiscountService discountService;
    GiveawayService giveawayService;
    BadgeService badgeService;
    User user;

    public PlannerController(CalendarService calendarService, OrderService orderService, UserService userService,
                             DiscountService discountService, GiveawayService giveawayService,
                             BadgeService badgeService) {
        this.calendarService = calendarService;
        this.orderService = orderService;
        this.userService = userService;
        this.discountService = discountService;
        this.giveawayService = giveawayService;
        this.badgeService = badgeService;
    }

    public void start() {
        OutputView.printWelcomeMessage();
        int userDate = receiveValidatedDate();
        Map<String, Integer> userOrder = receiveValidatedOrder();

        VisitDate visitDate = calendarService.createVisitDate(userDate);
        List<Order> orders = orderService.createMultipleOrder(userOrder);
        user = userService.createUser(orders, visitDate);
    }

    private int receiveValidatedDate() {
        OutputView.printDateInputDescription();
        return receiveValidatedInput(InputView::inputVisitDate, DateValidator::validateDate);
    }

    private Map<String, Integer> receiveValidatedOrder() {
        OutputView.printOrderInputDescription();
        return receiveValidatedInput(InputView::inputOrder, OrderValidator::validateOrder);
    }

    private static <T> T receiveValidatedInput(Supplier<T> inputSupplier, Consumer<T> validator) {
        while (true) {
            try {
                T input = inputSupplier.get();
                validator.accept(input);
                return input;
            } catch (IllegalArgumentException e) {
                OutputView.printExceptionMessage(e);
            }
        }
    }

    public void plan() {
        int totalPrice = user.getTotalPrice();
        Map<String, Integer> discountAmountByEachPolicy = calcDiscountAmount();
        Map<Menu, Integer> gift = giveawayService.findGift(new DefaultGiveawayPolicy(), totalPrice);
        int totalBenefit = calcTotalBenefit(discountAmountByEachPolicy, gift);
        Optional<Badge> badge = badgeService.findBadge(new DefaultBadgePolicy(), totalBenefit);

        printResult(new PlannerResult(
                totalPrice, discountAmountByEachPolicy, gift,
                badge.map(Badge::getName).orElse(null)
        ));
    }

    private Map<String, Integer> calcDiscountAmount() {
        List<DiscountPolicy> discountPolicies = List.of(
                new WeekDayDiscountPolicy(),
                new WeekendDiscountPolicy(),
                new SpecialDiscountPolicy(),
                new ChristmasDiscountPolicy()
        );
        return discountPolicies.stream()
                .map(p -> Map.entry(p.getPolicyName(), discountService.calcDiscountAmount(p, user)))
                .filter(entry -> entry.getValue() > 0)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    private int calcTotalBenefit(Map<String, Integer> discountAmountByEachPolicy, Map<Menu, Integer> gift) {
        return Stream.concat(discountAmountByEachPolicy.values().stream(), gift.values().stream())
                .mapToInt(Integer::intValue).sum();
    }

    private void printResult(PlannerResult result) {
        OutputView.printEventPreviewDescription(user.getVisitDate().getDate());
        OutputView.printOrder(user.getOrders());
        OutputView.printTotalPrice(result.getTotalPrice());
        OutputView.printGift(result.getGiftNameAndQuantity());
        OutputView.printBenefitList(result.getAllBenefit());
        OutputView.printBenefitAmount(result.getBenefitAmount());
        OutputView.printDiscountedPrice(result.getDiscountedPrice());
        OutputView.printEventBadge(result.getBadgeName());
    }
}
