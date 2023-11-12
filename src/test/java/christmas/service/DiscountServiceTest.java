package christmas.service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import christmas.domain.ChristmasDiscountPolicy;
import christmas.domain.Order;
import christmas.domain.SpecialDiscountPolicy;
import christmas.domain.User;
import christmas.domain.VisitDate;
import christmas.domain.WeekDayDiscountPolicy;
import christmas.domain.WeekendDiscountPolicy;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class DiscountServiceTest {
    DiscountService discountService;
    OrderService orderService;
    UserService userService;
    CalendarService calendarService;

    @BeforeEach
    void setUp() {
        this.discountService = new DiscountService();
        this.orderService = new OrderService();
        this.userService = new UserService();
        this.calendarService = new CalendarService();
    }

    User createTestUser(int date, String userOrder) {
        Map<String, Integer> multiOrder = Arrays.stream(userOrder.split(","))
                .map(order -> order.split("-"))
                .collect(Collectors.toMap(order -> order[0], order -> Integer.parseInt(order[1])));
        List<Order> orders = orderService.createMultipleOrder(multiOrder);
        VisitDate visitDate = calendarService.createVisitDate(date);

        return userService.createUser(orders, visitDate);
    }

    @ParameterizedTest
    @CsvSource(
            value = {
                    "3/티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1/1000",
                    "25/티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1/1000",
            },
            delimiter = '/'
    )
    @DisplayName("특별 할인 정책 테스트 - 특별 할인 적용일인 경우")
    void specialDiscountOnSpecialDayTest(int date, String userOrder, int expected) {
        User user = createTestUser(date, userOrder);
        assertThat(discountService.calcDiscountAmount(new SpecialDiscountPolicy(), user)).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(
            value = {
                    "1/티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1/0",
                    "26/티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1/0",
            },
            delimiter = '/'
    )
    @DisplayName("특별 할인 정책 테스트 - 특별 할인 적용일이 아닐 경우")
    void specialDiscountOnNormalDayTest(int date, String userOrder, int expected) {
        User user = createTestUser(date, userOrder);
        assertThat(discountService.calcDiscountAmount(new SpecialDiscountPolicy(), user)).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(
            value = {
                    "3/양송이수프-1/0",
                    "25/제로콜라-3/0",
            },
            delimiter = '/'
    )
    @DisplayName("특별 할인 정책 테스트 - 최소 주문 금액 미달인 경우")
    void specialDiscountOnSpecialDayBelowMinimumPriceTest(int date, String userOrder, int expected) {
        User user = createTestUser(date, userOrder);
        assertThat(discountService.calcDiscountAmount(new SpecialDiscountPolicy(), user)).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(
            value = {
                    "3/티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1/1200",
                    "25/티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1/3400",
            },
            delimiter = '/'
    )
    @DisplayName("크리스마스 할인 정책 테스트 - 크리스마스 디데이 할인 기간 이내일 경우")
    void christmasDiscountDuringPromotionTest(int date, String userOrder, int expected) {
        User user = createTestUser(date, userOrder);
        assertThat(discountService.calcDiscountAmount(new ChristmasDiscountPolicy(), user)).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(
            value = {
                    "26/티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1/0",
                    "31/티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1/0",
            },
            delimiter = '/'
    )
    @DisplayName("크리스마스 할인 정책 테스트 - 크리스마스 디데이 할인이 종료된 이후인 경우")
    void christmasDiscountAfterPromotionTest(int date, String userOrder, int expected) {
        User user = createTestUser(date, userOrder);
        assertThat(discountService.calcDiscountAmount(new ChristmasDiscountPolicy(), user)).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(
            value = {
                    "3/양송이수프-1/0",
                    "25/제로콜라-3/0",
            },
            delimiter = '/'
    )
    @DisplayName("크리스마스 할인 정책 테스트 - 최소 주문 금액 미달인 경우")
    void christmasDiscountDuringPromotionBelowMinimumPriceTest(int date, String userOrder, int expected) {
        User user = createTestUser(date, userOrder);
        assertThat(discountService.calcDiscountAmount(new ChristmasDiscountPolicy(), user)).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(
            value = {
                    "13/티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1/4046",
                    "20/초코케이크-5,제로콜라-1/10115",
            },
            delimiter = '/'
    )
    @DisplayName("평일 할인 정책 테스트 - 평일에 디저트를 주문하는 경우")
    void weekDayDiscountOnWeekDayWithDessertTest(int date, String userOrder, int expected) {
        User user = createTestUser(date, userOrder);
        assertThat(discountService.calcDiscountAmount(new WeekDayDiscountPolicy(), user)).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(
            value = {
                    "13/타파스-1,바비큐립-1/0",
                    "20/티본스테이크-1,바비큐립-1/0",
            },
            delimiter = '/'
    )
    @DisplayName("평일 할인 정책 테스트 - 평일이지만 디저트 주문이 없는 경우")
    void weekDayDiscountOnWeekDayWithOutDessertTest(int date, String userOrder, int expected) {
        User user = createTestUser(date, userOrder);
        assertThat(discountService.calcDiscountAmount(new WeekDayDiscountPolicy(), user)).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(
            value = {
                    "9/티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1/0",
                    "16/초코케이크-5,제로콜라-1/0",
            },
            delimiter = '/'
    )
    @DisplayName("평일 할인 정책 테스트 - 주말인 경우")
    void weekDayDiscountOnWeekendTest(int date, String userOrder, int expected) {
        User user = createTestUser(date, userOrder);
        assertThat(discountService.calcDiscountAmount(new WeekDayDiscountPolicy(), user)).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(
            value = {
                    "13/아이스크림-1/0",
            },
            delimiter = '/'
    )
    @DisplayName("평일 할인 정책 테스트 - 최소 주문 금액 미달인 경우")
    void weekDayDiscountOnWeekDayWithDessertBelowMinimumPriceTest(int date, String userOrder, int expected) {
        User user = createTestUser(date, userOrder);
        assertThat(discountService.calcDiscountAmount(new WeekDayDiscountPolicy(), user)).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(
            value = {
                    "9/티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1/4046",
                    "16/티본스테이크-10,바비큐립-10/40460",
            },
            delimiter = '/'
    )
    @DisplayName("주말 할인 정책 테스트 - 주말에 메인 메뉴를 주문하는 경우")
    void weekendDiscountOnWeekendWithMainDishTest(int date, String userOrder, int expected) {
        User user = createTestUser(date, userOrder);
        assertThat(discountService.calcDiscountAmount(new WeekendDiscountPolicy(), user)).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(
            value = {
                    "15/아이스크림-1,양송이수프-1/0",
                    "22/샴페인-1,시저샐러드-1/0",
            },
            delimiter = '/'
    )
    @DisplayName("주말 할인 정책 테스트 - 주말이지만 메인 메뉴 주문이 없는 경우")
    void weekendDiscountOnWeekendWithOutMainDishTest(int date, String userOrder, int expected) {
        User user = createTestUser(date, userOrder);
        assertThat(discountService.calcDiscountAmount(new WeekendDiscountPolicy(), user)).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(
            value = {
                    "10/티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1/0",
                    "17/초코케이크-5,제로콜라-1/0",
            },
            delimiter = '/'
    )
    @DisplayName("주말 할인 정책 테스트 - 평일인 경우")
    void weekendDiscountOnWeekdayTest(int date, String userOrder, int expected) {
        User user = createTestUser(date, userOrder);
        assertThat(discountService.calcDiscountAmount(new WeekendDiscountPolicy(), user)).isEqualTo(expected);
    }
}
