package christmas.domain.giveawaypolicy;

import christmas.constant.Menu;

public class DefaultGiveawayPolicy extends GiveawayPolicy {
    private static final int MINIMUM_PRICE = 120000;
    private static final Menu GIFT = Menu.fromName("샴페인");

    @Override
    public Menu findGift(int totalPrice) {
        if (!isMatchedCondition(totalPrice)) {
            return null;
        }
        return GIFT;
    }

    @Override
    public boolean isMatchedCondition(int totalPrice) {
        return totalPrice >= MINIMUM_PRICE;
    }
}
