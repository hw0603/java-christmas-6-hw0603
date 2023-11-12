package christmas.domain;

import christmas.constant.MenuCategory;

public class WeekendDiscountPolicy implements DiscountPolicy {
    private static final int DISCOUNT_AMOUNT = 2023;

    @Override
    public int doCalc(User user) {
        if (!user.getVisitDate().isWeekend()) {
            return 0;
        }
        return user.getOrders().stream()
                .filter(order -> order.getMenu().getCategory() == MenuCategory.MAIN_DISH)
                .mapToInt(order -> order.getAmount() * DISCOUNT_AMOUNT)
                .sum();
    }
}
