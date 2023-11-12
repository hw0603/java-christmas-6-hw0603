package christmas.domain.badgepolicy;

import christmas.constant.Badge;

public class DefaultBadgePolicy implements BadgePolicy {
    private static final int STAR_THRESHOLD = 5000;
    private static final int TREE_THRESHOLD = 10000;
    private static final int SANTA_THRESHOLD = 20000;

    @Override
    public Badge findBadge(int benefitAmount) {
        if (benefitAmount >= SANTA_THRESHOLD) {
            return Badge.SANTA;
        }
        if (benefitAmount >= TREE_THRESHOLD) {
            return Badge.TREE;
        }
        if (benefitAmount >= STAR_THRESHOLD) {
            return Badge.STAR;
        }
        return null;
    }
}
