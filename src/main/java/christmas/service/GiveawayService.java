package christmas.service;

import christmas.constant.Menu;
import christmas.domain.giveawaypolicy.GiveawayPolicy;
import java.util.Map;

public class GiveawayService {
    public Map<Menu, Integer> findGift(GiveawayPolicy policy, int totalPrice) {
        return policy.findGift(totalPrice);
    }
}
