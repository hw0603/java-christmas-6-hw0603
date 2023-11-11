package christmas.util;

import christmas.constant.EventPlanner;
import christmas.constant.Menu;
import christmas.constant.MenuCategory;
import java.util.Arrays;
import java.util.regex.Pattern;

public class OrderValidator {
    private static final int MIN_QUANTITY = 1;
    private static final int MAX_TOTAL_QUANTITY = 20;
    private static final String ORDER_FORMAT_REGEX = "^([^-]+-\\d+)(,[^-]+-\\d+)*$";

    public static void validateOrder(String userInput) {
        if (!isAcceptableFormat(userInput)) {
            throw new IllegalArgumentException(EventPlanner.INVALID_ORDER_ERROR);
        }
        if (!isAllQuantityMoreThanOrEqualToMinimum(userInput)) {
            throw new IllegalArgumentException(EventPlanner.INVALID_ORDER_ERROR);
        }
        if (!isTotalQuantityLessThanOrEqualToMaximum(userInput)) {
            throw new IllegalArgumentException(EventPlanner.INVALID_ORDER_ERROR);
        }
        if (!isAllMenuExist(userInput)) {
            throw new IllegalArgumentException(EventPlanner.INVALID_ORDER_ERROR);
        }
        if (isAllDrink(userInput)) {
            throw new IllegalArgumentException(EventPlanner.INVALID_ORDER_ERROR);
        }
    }

    private static String parseName(String nameAndQuantity) {
        return nameAndQuantity.split("-")[0];
    }

    private static int parseQuantity(String nameAndQuantity) {
        return Integer.parseInt(nameAndQuantity.split("-")[1]);
    }

    private static boolean isAcceptableFormat(String userInput) {
        return Pattern.matches(ORDER_FORMAT_REGEX, userInput);
    }

    private static boolean isAllQuantityMoreThanOrEqualToMinimum(String userInput) {
        return Arrays.stream(userInput.split(",")).allMatch(OrderValidator::isQuantityMoreThanOrEqualToMinimum);
    }

    private static boolean isQuantityMoreThanOrEqualToMinimum(String nameAndQuantity) {
        return parseQuantity(nameAndQuantity) >= MIN_QUANTITY;
    }

    private static boolean isTotalQuantityLessThanOrEqualToMaximum(String userInput) {
        return Arrays.stream(userInput.split(","))
                .mapToInt(OrderValidator::parseQuantity)
                .sum() <= MAX_TOTAL_QUANTITY;
    }

    private static boolean isAllMenuExist(String userInput) {
        return Arrays.stream(userInput.split(","))
                .map(OrderValidator::parseName)
                .allMatch(OrderValidator::isExist);
    }

    private static boolean isExist(String menuName) {
        for (Menu menu : Menu.values()) {
            if (menu.getName().equals(menuName)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isAllDrink(String userInput) {
        return Arrays.stream(userInput.split(","))
                .map(OrderValidator::parseName)
                .map(Menu::fromName)
                .allMatch(menu -> menu.getCategory().equals(MenuCategory.DRINK));
    }

}
