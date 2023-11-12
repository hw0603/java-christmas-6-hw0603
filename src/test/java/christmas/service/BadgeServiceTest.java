package christmas.service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import christmas.constant.Badge;
import christmas.domain.badgepolicy.BadgePolicy;
import christmas.domain.badgepolicy.DefaultBadgePolicy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class BadgeServiceTest {
    BadgeService badgeService;

    @BeforeEach
    void setUp() {
        badgeService = new BadgeService();
    }

    @ParameterizedTest
    @CsvSource(value = {"5000:STAR", "10000:TREE", "20000:SANTA"}, delimiter = ':')
    @DisplayName("기본 배지 정책 - 배지를 받을 수 있는 경우")
    void defaultBadgePolicyWithMatchedConditionTest(int benefitAmount, String badgeName) {
        BadgePolicy policy = new DefaultBadgePolicy();
        Badge expected = Badge.valueOf(badgeName);
        assertThat(badgeService.findBadge(policy, benefitAmount).orElse(null))
                .isEqualTo(expected);
    }

    @Test
    @DisplayName("기본 배지 정책 - 배지를 받을 수 없는 경우")
    void defaultBadgePolicyWithUnmatchedConditionTest() {
        BadgePolicy policy = new DefaultBadgePolicy();
        int benefitAmount = 4000;
        assertThat(badgeService.findBadge(policy, benefitAmount).orElse(null))
                .isNull();
    }
}
