package christmas.domain.discountpolicy;

import christmas.constant.MenuCategory;
import christmas.domain.User;

public class WeekDayDiscountPolicy implements DiscountPolicy {
    private static final String POLICY_NAME = "평일 할인";
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

    @Override
    public String getPolicyName() {
        return POLICY_NAME;
    }
}
