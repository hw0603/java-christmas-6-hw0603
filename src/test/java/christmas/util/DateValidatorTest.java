package christmas.util;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class DateValidatorTest {
    @Test
    @DisplayName("예상 방문 날짜 - 정상 입력")
    void normalDateTest() {
        int input = 3;
        assertThatCode(() -> DateValidator.validateDate(input))
                .doesNotThrowAnyException();
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 32})
    @DisplayName("예상 방문 날짜 - 범위 밖 입력")
    void outOfRangeDate(int input) {
        assertThatThrownBy(() -> DateValidator.validateDate(input))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
