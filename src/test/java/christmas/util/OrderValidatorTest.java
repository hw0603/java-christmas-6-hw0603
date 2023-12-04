package christmas.util;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class OrderValidatorTest {
    Map<String, Integer> convert(String input) {
        return Arrays.stream(input.split(","))
                .map(order -> order.split("-"))
                .collect(Collectors.toMap(order -> order[0], order -> Integer.parseInt(order[1])));
    }

    @ParameterizedTest
    @ValueSource(strings = {"타파스-1", "타파스-1,제로콜라-2"})
    @DisplayName("주문 정보 - 정상 입력")
    void normalOrderTest(String input) {
        assertThatCode(() -> OrderValidator.validateOrder(convert(input)))
                .doesNotThrowAnyException();
    }

    @ParameterizedTest
    @ValueSource(strings = {"타파스-0", "타파스-1,제로콜라-0"})
    @DisplayName("주문 정보 - 1개 미만의 수량")
    void lowerThanMinimunQuantityTest(String input) {
        assertThatThrownBy(() -> OrderValidator.validateOrder(convert(input)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"타파스-21", "타파스-11,제로콜라-10"})
    @DisplayName("주문 정보 - 총 주문 수량 20개 초과")
    void moreThanMaximunQuantityTest(String input) {
        assertThatThrownBy(() -> OrderValidator.validateOrder(convert(input)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"코카콜라-3", "타파스-1,코카콜라-1"})
    @DisplayName("주문 정보 - 메뉴판에 없는 메뉴 입력")
    void notExistedMenuTest(String input) {
        assertThatThrownBy(() -> OrderValidator.validateOrder(convert(input)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"제로콜라-13", "제로콜라-3,레드와인-4"})
    @DisplayName("주문 정보 - 음료만 주문")
    void onlyDrinkTest(String input) {
        assertThatThrownBy(() -> OrderValidator.validateOrder(convert(input)))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
