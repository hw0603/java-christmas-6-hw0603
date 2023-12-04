package christmas.service;

import christmas.constant.Badge;
import christmas.domain.badgepolicy.BadgePolicy;
import java.util.Optional;

public class BadgeService {
    public Optional<Badge> findBadge(BadgePolicy policy, int benefitAmount) {
        return Optional.ofNullable(policy.findBadge(benefitAmount));
    }
}
