package christmas.service;

import christmas.domain.discountpolicy.DiscountPolicy;
import christmas.domain.User;

public class DiscountService {
    public int calcDiscountAmount(DiscountPolicy policy, User user) {
        return policy.calc(user);
    }
}
