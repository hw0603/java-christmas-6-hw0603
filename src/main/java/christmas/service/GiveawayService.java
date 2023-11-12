package christmas.service;

import christmas.constant.Menu;
import christmas.domain.giveawaypolicy.GiveawayPolicy;
import java.util.Optional;

public class GiveawayService {
    public Optional<Menu> findGift(GiveawayPolicy policy, int totalPrice) {
        return Optional.ofNullable(policy.findGift(totalPrice));
    }
}
