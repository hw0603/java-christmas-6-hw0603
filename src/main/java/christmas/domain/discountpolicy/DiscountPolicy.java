package christmas.domain.discountpolicy;

import christmas.domain.User;

public abstract class DiscountPolicy {
    int MINIMUM_PRICE = 10000;

    public int calc(User user) {
        if (isDiscountable(user)) {
            return doCalc(user);
        }
        return 0;
    }

    private boolean isDiscountable(User user) {
        return user.getTotalPrice() >= MINIMUM_PRICE;
    }
    abstract int doCalc(User user);
    public abstract String getPolicyName();
}
