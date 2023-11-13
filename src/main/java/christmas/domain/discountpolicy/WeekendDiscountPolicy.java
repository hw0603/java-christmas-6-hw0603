package christmas.domain.discountpolicy;

import christmas.constant.MenuCategory;
import christmas.domain.User;

public class WeekendDiscountPolicy extends DiscountPolicy {
    private static final String POLICY_NAME = "주말 할인";
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

    @Override
    public String getPolicyName() {
        return POLICY_NAME;
    }
}
