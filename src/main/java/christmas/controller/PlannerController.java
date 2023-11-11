package christmas.controller;

import christmas.view.OutputView;

public class PlannerController {

    public void start() {
        OutputView.printWelconeMessage();
        receiveValidatedName();
    }

    public String receiveValidatedName() {
        OutputView.printDateInputDescription();
        return null;
    }
}
