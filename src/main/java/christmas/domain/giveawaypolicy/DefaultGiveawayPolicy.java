package christmas.domain.giveawaypolicy;

import christmas.constant.Menu;

public class DefaultGiveawayPolicy implements GiveawayPolicy {
    private static final int MINIMUN_PRICE = 120000;
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
        return totalPrice >= MINIMUN_PRICE;
    }
}
