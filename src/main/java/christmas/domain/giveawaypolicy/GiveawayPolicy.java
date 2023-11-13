package christmas.domain.giveawaypolicy;

import christmas.constant.Menu;

public abstract class GiveawayPolicy {
    public abstract Menu findGift(int totalPrice);

    abstract boolean isMatchedCondition(int totalPrice);
}
