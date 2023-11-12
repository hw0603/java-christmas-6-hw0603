package christmas.controller;

import christmas.domain.Order;
import christmas.domain.User;
import christmas.domain.VisitDate;
import christmas.service.CalendarService;
import christmas.service.OrderService;
import christmas.service.UserService;
import christmas.util.DateValidator;
import christmas.util.OrderValidator;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class PlannerController {
    CalendarService calendarService = new CalendarService();
    OrderService orderService = new OrderService();
    UserService userService = new UserService();
    User user;

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
}
