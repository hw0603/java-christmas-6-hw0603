package christmas.service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import christmas.constant.Menu;
import christmas.domain.giveawaypolicy.DefaultGiveawayPolicy;
import christmas.domain.giveawaypolicy.GiveawayPolicy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GiveawayServiceTest {
    GiveawayService giveawayService;

    @BeforeEach
    void setUp() {
        this.giveawayService = new GiveawayService();
    }

    @Test
    @DisplayName("기본 증정 정책 - 증정품을 받을 수 있는 경우")
    void defaultGiveawayPolicyWithMatchedConditionTest() {
        int totalPrice = 120000;
        GiveawayPolicy policy = new DefaultGiveawayPolicy();

        assertThat(giveawayService.findGift(policy, totalPrice).orElse(null))
                .isEqualTo(Menu.CHAMPAGNE);
    }

    @Test
    @DisplayName("기본 증정 정책 - 증정품을 받을 수 없는 경우")
    void defaultGiveawayPolicyWithUnmatchedConditionTest() {
        int totalPrice = 119999;
        GiveawayPolicy policy = new DefaultGiveawayPolicy();

        assertThat(giveawayService.findGift(policy, totalPrice).orElse(null))
                .isNull();
    }
}
