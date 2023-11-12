package christmas.domain.discountpolicy;

import christmas.domain.User;

public class ChristmasDiscountPolicy implements DiscountPolicy {
    private static final int DISCOUNT_START_AMOUNT = 1000;
    private static final int DISCOUNT_ACCUMULATE_AMOUNT = 100;

    @Override
    public int doCalc(User user) {
        if (user.getVisitDate().isAfterChristmas()) {
            return 0;
        }
        return DISCOUNT_START_AMOUNT + calcAccumulatedDiscount(user.getVisitDate().getDate());
    }

    private int calcAccumulatedDiscount(int date) {
        return (date-1) * DISCOUNT_ACCUMULATE_AMOUNT;
    }
}
