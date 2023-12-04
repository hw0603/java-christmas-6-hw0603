package christmas.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import christmas.constant.Week;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class VisitDateTest {
    @Test
    @DisplayName("방문 일자 - 별 없는 날")
    void noStarDayTest() {
        int date = 5;
        VisitDate visitDate = new VisitDate(date, Week.of(date));
        assertThat(visitDate.isSpecialDay()).isEqualTo(false);
    }

    @Test
    @DisplayName("방문 일자 - 별 있는 날")
    void StarDayTest() {
        int date = 25;
        VisitDate visitDate = new VisitDate(date, Week.of(date));
        assertThat(visitDate.isSpecialDay()).isEqualTo(true);
    }
}
