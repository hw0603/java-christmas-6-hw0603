package christmas.controller;

import christmas.constant.Badge;
import christmas.domain.PlannerResult;
import christmas.domain.badgepolicy.DefaultBadgePolicy;
import christmas.domain.discountpolicy.ChristmasDiscountPolicy;
import christmas.domain.Order;
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

public class PlannerController {
    CalendarService calendarService = new CalendarService();
    OrderService orderService = new OrderService();
    UserService userService = new UserService();
    DiscountService discountService = new DiscountService();
    GiveawayService giveawayService = new GiveawayService();
    BadgeService badgeService = new BadgeService();
    PlannerResult result;
    User user;

    public void start() {
        OutputView.printWelcomeMessage();
        int userDate = receiveValidatedDate();
        Map<String, Integer> userOrder = receiveValidatedOrder();

        VisitDate visitDate = calendarService.createVisitDate(userDate);
        List<Order> orders = orderService.createMultipleOrder(userOrder);
        user = userService.createUser(orders, visitDate);
    }

    public void plan() {
        int totalPrice = user.getTotalPrice();
        int weekDayDiscount = discountService.calcDiscountAmount(new WeekDayDiscountPolicy(), user);
        int weekendDiscount = discountService.calcDiscountAmount(new WeekendDiscountPolicy(), user);
        int specialDiscount = discountService.calcDiscountAmount(new SpecialDiscountPolicy(), user);
        int christmasDiscount = discountService.calcDiscountAmount(new ChristmasDiscountPolicy(), user);
        int totalDiscount = weekDayDiscount + weekendDiscount + specialDiscount + christmasDiscount;

        Optional<Menu> gift = giveawayService.findGift(new DefaultGiveawayPolicy(), totalPrice);
        int totalBenefit = totalDiscount + gift.map(Menu::getPrice).orElse(0);

        Optional<Badge> badge = badgeService.findBadge(new DefaultBadgePolicy(), totalBenefit);

        String giftName = gift.map(Menu::getName).orElse(null);
        String badgeName = badge.map(Badge::getName).orElse(null);

        result = new PlannerResult(
                totalPrice,
                Map.of(
                        "평일 할인", weekDayDiscount,
                        "주말 할인", weekendDiscount,
                        "특별 할인", specialDiscount,
                        "크리스마스 디데이 할인", christmasDiscount
                ),
                giftName,
                gift.map(Menu::getPrice).orElse(0),
                badgeName
        );

        printResult(result);
    }


    public void printResult(PlannerResult result) {
        OutputView.printEventPreviewDescription(user.getVisitDate().getDate());
        OutputView.printOrder(user.getOrders());
        OutputView.printTotalPrice(result.getTotalPrice());
        OutputView.printGift(result.getGiftName());
        OutputView.printBenefitList(result.getAllBenefit());
        OutputView.printBenefitAmount(result.getBenefitAmount());
        OutputView.printDiscountedPrice(result.getDiscountedPrice());
        OutputView.printEventBadge(result.getBadgeName());
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
}
