package christmas.controller;

import christmas.util.DateValidator;
import christmas.util.OrderValidator;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class PlannerController {

    public void start() {
        OutputView.printWelcomeMessage();
        int userDate = receiveValidatedDate();
        Map<String, Integer> userOrder = receiveValidatedOrder();
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
