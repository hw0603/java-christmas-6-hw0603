package christmas.domain.badgepolicy;

import christmas.constant.Badge;
import christmas.domain.User;

public abstract class BadgePolicy {
    public abstract Badge findBadge(int benefitAmount);
}
