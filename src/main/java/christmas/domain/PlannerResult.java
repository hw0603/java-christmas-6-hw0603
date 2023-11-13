package christmas.domain;

import christmas.constant.EventPlanner;
import christmas.constant.Menu;
import java.util.HashMap;
import java.util.Map;

public class PlannerResult {
    private final int totalPrice;
    private final Map<String, Integer> discountAmountByEachPolicy;
    private final String giftName;
    private final int giftPrice;
    private final String badgeName;
    private final int totalDiscount;

    public PlannerResult(int totalPrice, Map<String, Integer> discountAmountByEachPolicy,
                         String giftName, String badgeName) {
        this.totalPrice = totalPrice;
        this.discountAmountByEachPolicy = discountAmountByEachPolicy;
        this.giftName = giftName;
        this.badgeName = badgeName;
        this.totalDiscount = discountAmountByEachPolicy.values().stream()
                .mapToInt(Integer::intValue)
                .sum();
        this.giftPrice = initGiftPrice();
    }

    private int initGiftPrice() {
        if (giftName == null) {
            return 0;
        }
        return Menu.fromName(giftName).getPrice();
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
        if (giftPrice == 0) {
            return discountAmountByEachPolicy;
        }
        return new HashMap<>(discountAmountByEachPolicy) {{
            put(EventPlanner.GIVEAWAY_EVENT, giftPrice);
        }};
    }
}
