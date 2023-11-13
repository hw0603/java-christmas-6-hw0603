package christmas.domain.discountpolicy;

import christmas.domain.User;

public interface DiscountPolicy {
    int MINIMUN_PRICE = 10000;

    default int calc(User user) {
        if (isDiscountable(user)) {
            return doCalc(user);
        }
        return 0;
    }

    default boolean isDiscountable(User user) {
        return user.getTotalPrice() >= MINIMUN_PRICE;
    }
    int doCalc(User user);
    String getPolicyName();
}
