package christmas.view;

import christmas.constant.EventPlanner;

public class OutputView {
    public static void printWelconeMessage() {
        System.out.println(EventPlanner.WELCOME_MSG);
    }
    public static void printDateInputDescription() {
        System.out.println(EventPlanner.VISIT_DATE_INPUT_DESC);
    }
    public static void printExceptionMessage(Exception e) {
        System.out.println(EventPlanner.ERROR_MSG_PREFIX + e.getMessage());
    }
}
