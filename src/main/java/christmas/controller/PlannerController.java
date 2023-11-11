package christmas.controller;

import christmas.util.DateValidator;
import christmas.util.OrderValidator;
import christmas.view.InputView;
import christmas.view.OutputView;

public class PlannerController {

    public void start() {
        OutputView.printWelcomeMessage();
        int userDate = receiveValidatedDate();
        String userOrder = receiveValidatedOrder();
    }

    private int receiveValidatedDate() {
        while (true) {
            try {
                OutputView.printDateInputDescription();
                String input = InputView.inputVisitDate();
                DateValidator.validateDate(input);
                return Integer.parseInt(input);
            } catch (IllegalArgumentException e) {
                OutputView.printExceptionMessage(e);
            }
        }
    }

    private String receiveValidatedOrder() {
        while (true) {
            try {
                OutputView.printOrderInputDescription();
                String input = InputView.inputOrder();
                OrderValidator.validateOrder(input);
                return input;
            } catch (IllegalArgumentException e) {
                OutputView.printExceptionMessage(e);
            }

        }
    }
}
