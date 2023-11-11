package christmas.controller;

import christmas.util.DateValidator;
import christmas.util.OrderValidator;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class PlannerController {

    public void start() {
        OutputView.printWelcomeMessage();
        int userDate = receiveValidatedDate();
        String userOrder = receiveValidatedOrder();
    }

    private int receiveValidatedDate() {
        OutputView.printDateInputDescription();
        return receiveValidatedInput(InputView::inputOrder, DateValidator::validateDate, Integer::parseInt);
    }

    private String receiveValidatedOrder() {
        OutputView.printOrderInputDescription();
        return receiveValidatedInput(InputView::inputOrder, OrderValidator::validateOrder, Function.identity());
    }

    private static <T> T receiveValidatedInput(Supplier<String> inputSupplier, Consumer<String> validator, Function<String, T> converter) {
        while (true) {
            try {
                String input = inputSupplier.get();
                validator.accept(input);
                return converter.apply(input);
            } catch (IllegalArgumentException e) {
                OutputView.printExceptionMessage(e);
            }
        }
    }
}
