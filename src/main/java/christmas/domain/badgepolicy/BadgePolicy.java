package christmas.domain.badgepolicy;

import christmas.constant.Badge;
import christmas.domain.User;

public interface BadgePolicy {
    Badge findBadge(int benefitAmount);
}
