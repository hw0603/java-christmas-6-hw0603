package christmas.domain.giveawaypolicy;

import christmas.constant.Menu;
import christmas.domain.User;

public class DefaultGiveawayPolicy implements GiveawayPolicy {
    private static final int MINIMUN_PRICE = 120000;
    private static final Menu GIFT = Menu.fromName("샴페인");

    @Override
    public Menu findGift(User user) {
        if (!isMatchedCondition(user)) {
            return null;
        }
        return GIFT;
    }

    @Override
    public boolean isMatchedCondition(User user) {
        return user.getTotalPrice() >= MINIMUN_PRICE;
    }
}
