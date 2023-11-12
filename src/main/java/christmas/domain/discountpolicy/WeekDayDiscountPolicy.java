package christmas.domain.discountpolicy;

import christmas.constant.MenuCategory;
import christmas.domain.User;

public class WeekDayDiscountPolicy implements DiscountPolicy {
    private static final int DISCOUNT_AMOUNT = 2023;

    @Override
    public int doCalc(User user) {
        if (!user.getVisitDate().isWeekday()) {
            return 0;
        }
        return user.getOrders().stream()
                .filter(order -> order.getMenu().getCategory() == MenuCategory.DESSERT)
                .mapToInt(order -> order.getAmount() * DISCOUNT_AMOUNT)
                .sum();
    }
}
