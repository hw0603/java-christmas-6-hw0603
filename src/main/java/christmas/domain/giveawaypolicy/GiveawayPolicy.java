package christmas.domain.giveawaypolicy;

import christmas.constant.Menu;
import christmas.domain.User;

public interface GiveawayPolicy {
    Menu findGift(User user);

    boolean isMatchedCondition(User user);
}
