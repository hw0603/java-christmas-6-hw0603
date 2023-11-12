package christmas.domain.giveawaypolicy;

import christmas.constant.Menu;

public interface GiveawayPolicy {
    Menu findGift(int totalPrice);

    boolean isMatchedCondition(int totalPrice);
}
