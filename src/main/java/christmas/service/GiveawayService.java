package christmas.service;

import christmas.constant.Menu;
import christmas.domain.User;
import christmas.domain.giveawaypolicy.GiveawayPolicy;
import java.util.Optional;

public class GiveawayService {
    public Optional<Menu> findGift(GiveawayPolicy policy, User user) {
        return Optional.ofNullable(policy.findGift(user));
    }
}
