package christmas.domain.giveawaypolicy;

import christmas.constant.Menu;
import java.util.Map;

public abstract class GiveawayPolicy {
    public abstract Map<Menu, Integer> findGift(int totalPrice);

    abstract boolean isMatchedCondition(int totalPrice);
}
