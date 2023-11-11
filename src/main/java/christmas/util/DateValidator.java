package christmas.util;

import christmas.constant.EventPlanner;

public class DateValidator {
    public static final int MIN_DATE = 1;
    public static final int MAX_DATE = 31;

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
        return MIN_DATE <= date && date <= MAX_DATE;
    }
}
