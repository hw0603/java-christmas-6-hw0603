package christmas.domain;

import christmas.constant.EventPlanner;
import java.util.HashMap;
import java.util.Map;

public class PlannerResult {
    private final int totalPrice;
    private final Map<String, Integer> discountAmountByEachPolicy;
    private final String giftName;
    private final int giftPrice;
    private final String badgeName;
    private final int totalDiscount;

    public PlannerResult(int totalPrice, Map<String, Integer> discountAmountByEachPolicy, String giftName,
                         int giftPrice, String badgeName) {
        this.totalPrice = totalPrice;
        this.discountAmountByEachPolicy = discountAmountByEachPolicy;
        this.giftName = giftName;
        this.giftPrice = giftPrice;
        this.badgeName = badgeName;
        this.totalDiscount = discountAmountByEachPolicy.values().stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public String getGiftName() {
        return giftName;
    }

    public String getBadgeName() {
        return badgeName;
    }

    public int getBenefitAmount() {
        return giftPrice + totalDiscount;
    }

    public int getDiscountedPrice() {
        return totalPrice - totalDiscount;
    }

    public Map<String, Integer> getAllBenefit() {
        return new HashMap<>(discountAmountByEachPolicy) {{
            put(EventPlanner.GIVEAWAY_EVENT, giftPrice);
        }};
    }
}
