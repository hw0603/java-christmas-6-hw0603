package christmas.domain.giveawaypolicy;

import christmas.constant.Menu;
import java.util.Collections;
import java.util.Map;

public class DefaultGiveawayPolicy extends GiveawayPolicy {
    private static final int MINIMUM_PRICE = 120000;
    private static final Map<Menu, Integer> GIFT = Map.of(Menu.fromName("샴페인"), 1);

    @Override
    public Map<Menu, Integer> findGift(int totalPrice) {
        if (!isMatchedCondition(totalPrice)) {
            return Collections.emptyMap();
        }
        return GIFT;
    }

    @Override
    public boolean isMatchedCondition(int totalPrice) {
        return totalPrice >= MINIMUM_PRICE;
    }
}
