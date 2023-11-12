package christmas.domain.discountpolicy;

import christmas.domain.User;

public class SpecialDiscountPolicy implements DiscountPolicy {
    private static final int DISCOUNT_AMOUNT = 1000;

    @Override
    public int doCalc(User user) {
        if (!user.getVisitDate().isSpecialDay()) {
            return 0;
        }
        return DISCOUNT_AMOUNT;
    }
}
