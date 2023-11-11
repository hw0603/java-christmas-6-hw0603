package christmas.controller;

import christmas.util.DateValidator;
import christmas.view.InputView;
import christmas.view.OutputView;

public class PlannerController {

    public void start() {
        OutputView.printWelconeMessage();
        int userDate = receiveValidatedDate();
    }

    private int receiveValidatedDate() {
        while (true) {
            try {
                OutputView.printDateInputDescription();
                String input = InputView.intputVisitDate();
                DateValidator.validateDate(input);
                return Integer.parseInt(input);
            } catch (IllegalArgumentException e) {
                OutputView.printExceptionMessage(e);
            }
        }
    }
}
