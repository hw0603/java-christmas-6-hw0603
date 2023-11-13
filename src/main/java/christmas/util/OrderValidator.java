package christmas.util;

import christmas.constant.EventPlannerConstants;
import christmas.constant.Menu;
import christmas.constant.MenuCategory;
import java.util.Map;
import java.util.NoSuchElementException;

public class OrderValidator {
    private static final int MIN_QUANTITY = 1;
    private static final int MAX_TOTAL_QUANTITY = 20;

    public static void validateOrder(Map<String, Integer> userOrder) {
        if (!isAllQuantityMoreThanOrEqualToMinimum(userOrder)) {
            throw new IllegalArgumentException(EventPlannerConstants.INVALID_ORDER_ERROR);
        }
        if (!isTotalQuantityLessThanOrEqualToMaximum(userOrder)) {
            throw new IllegalArgumentException(EventPlannerConstants.INVALID_ORDER_ERROR);
        }
        if (!isAllMenuExist(userOrder)) {
            throw new IllegalArgumentException(EventPlannerConstants.INVALID_ORDER_ERROR);
        }
        if (isAllDrink(userOrder)) {
            throw new IllegalArgumentException(EventPlannerConstants.INVALID_ORDER_ERROR);
        }
    }

    private static boolean isAllQuantityMoreThanOrEqualToMinimum(Map<String, Integer> userOrder) {
        return userOrder.values().stream()
                .allMatch(OrderValidator::isQuantityMoreThanOrEqualToMinimum);
    }

    private static boolean isQuantityMoreThanOrEqualToMinimum(int nameAndQuantity) {
        return nameAndQuantity >= MIN_QUANTITY;
    }

    private static boolean isTotalQuantityLessThanOrEqualToMaximum(Map<String, Integer> userOrder) {
        return userOrder.values().stream()
                .mapToInt(Integer::intValue)
                .sum() <= MAX_TOTAL_QUANTITY;
    }

    private static boolean isAllMenuExist(Map<String, Integer> userOrder) {
        return userOrder.keySet().stream()
                .allMatch(OrderValidator::isExist);
    }

    private static boolean isExist(String menuName) {
        try {
            Menu.fromName(menuName);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private static boolean isAllDrink(Map<String, Integer> userOrder) {
        return userOrder.keySet().stream()
                .map(Menu::fromName)
                .allMatch(menu -> menu.getCategory().equals(MenuCategory.DRINK));
    }

}
