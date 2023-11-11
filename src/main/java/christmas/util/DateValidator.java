package christmas.util;

import christmas.constant.EventPlanner;

public class DateValidator {
    public static void validateDate(String inputDate) {
        if (!isNumeric(inputDate)) {
            throw new IllegalArgumentException(EventPlanner.INVALID_DATE_ERROR);
        }
        if (!isInRange(inputDate)) {
            throw new IllegalArgumentException(EventPlanner.INVALID_DATE_ERROR);
        }
    }

    private static boolean isNumeric(String inputDate) {
        try {
            Integer.parseInt(inputDate);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    private static boolean isInRange(String inputDate) {
        int date = Integer.parseInt(inputDate);
        return EventPlanner.MIN_DATE <= date && date <= EventPlanner.MAX_DATE;
    }
}
