package christmas.domain.discountpolicy;

import christmas.domain.User;

public class SpecialDiscountPolicy implements DiscountPolicy {
    public static final String POLICY_NAME = "특별 할인";
    private static final int DISCOUNT_AMOUNT = 1000;

    @Override
    public int doCalc(User user) {
        if (!user.getVisitDate().isSpecialDay()) {
            return 0;
        }
        return DISCOUNT_AMOUNT;
    }

    @Override
    public String getPolicyName() {
        return POLICY_NAME;
    }
}
