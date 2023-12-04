package christmas.util;

import christmas.constant.EventPlannerConstants;

public class DateValidator {
    public static final int MIN_DATE = 1;
    public static final int MAX_DATE = 31;

    public static void validateDate(int userDate) {
        if (!isInRange(userDate)) {
            throw new IllegalArgumentException(EventPlannerConstants.INVALID_DATE_ERROR);
        }
    }

    private static boolean isInRange(int userDate) {
        return MIN_DATE <= userDate && userDate <= MAX_DATE;
    }
}
