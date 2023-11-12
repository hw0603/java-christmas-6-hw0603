package christmas.domain;

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
}
