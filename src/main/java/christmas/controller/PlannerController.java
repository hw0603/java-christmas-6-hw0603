package christmas.controller;

import christmas.view.OutputView;

public class PlannerController {

    public void start() {
        OutputView.printWelconeMessage();
        receiveValidatedDate();
    }

    private int receiveValidatedDate() {
        OutputView.printDateInputDescription();
        return 0;
    }
}
