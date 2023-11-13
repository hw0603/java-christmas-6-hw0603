package christmas.domain;

import christmas.constant.EventPlannerConstants;
import christmas.constant.Menu;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class PlannerResult {
    private final int totalPrice;
    private final Map<String, Integer> discountAmountByEachPolicy;
    private final Map<Menu, Integer> gift;
    private final int totalGiftPrice;
    private final String badgeName;
    private final int totalDiscount;

    public PlannerResult(int totalPrice, Map<String, Integer> discountAmountByEachPolicy,
                         Map<Menu, Integer> gift, String badgeName) {
        this.totalPrice = totalPrice;
        this.discountAmountByEachPolicy = discountAmountByEachPolicy;
        this.gift = gift;
        this.badgeName = badgeName;
        this.totalDiscount = discountAmountByEachPolicy.values().stream()
                .mapToInt(Integer::intValue)
                .sum();
        this.totalGiftPrice = gift.values().stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public Map<String, Integer> getGiftNameAndQuantity() {
        return gift.entrySet().stream()
                .collect(Collectors.toMap(e -> e.getKey().getName(), Map.Entry::getValue));
    }

    public String getBadgeName() {
        return badgeName;
    }

    public int getBenefitAmount() {
        return totalGiftPrice + totalDiscount;
    }

    public int getDiscountedPrice() {
        return totalPrice - totalDiscount;
    }

    public Map<String, Integer> getAllBenefit() {
        if (gift.isEmpty()) {
            return discountAmountByEachPolicy;
        }
        return new HashMap<>(discountAmountByEachPolicy) {{
            put(EventPlannerConstants.GIVEAWAY_EVENT, totalGiftPrice);
        }};
    }
}
