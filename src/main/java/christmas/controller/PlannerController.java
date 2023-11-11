package christmas.controller;

import christmas.util.DateValidator;
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
        OutputView.printOrderInputDescription();
        String input = InputView.inputOrder();

        return null;
    }
}
