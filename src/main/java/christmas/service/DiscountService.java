package christmas.service;

import christmas.domain.DiscountPolicy;
import christmas.domain.User;

public class DiscountService {
    public int calcDiscountAmount(DiscountPolicy policy, User user) {
        return policy.calc(user);
    }
}
