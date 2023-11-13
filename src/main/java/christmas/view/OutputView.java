package christmas.view;

import christmas.constant.EventCalendar;
import christmas.domain.Order;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

public class OutputView {
    public static final String ERROR_MSG_PREFIX = "[ERROR] ";
    public static final String WELCOME_MSG = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.";
    public static final String VISIT_DATE_INPUT_DESC = "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)";
    public static final String ORDER_INPUT_DESC = "주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)";
    public static final String EVENT_PREVIEW_HEADER = "에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n";
    public static final String ORDER_LIST_HEADER = "<주문 메뉴>";
    public static final String TOTAL_PRICE_HEADER = "<할인 전 총주문 금액>";
    public static final String GIFT_HEADER = "<증정 메뉴>";
    public static final String BENEFIT_LIST_HEADER = "<혜택 내역>";
    public static final String BENEFIT_AMOUNT_HEADER = "<총혜택 금액>";
    public static final String DISCOUNTED_PRICE_HEADER = "<할인 후 예상 결제 금액>";
    public static final String EVENT_BADGE_HEADER = "<12월 이벤트 배지>";
    public static final String NOTHING = "없음";
    private static final DecimalFormat formatter = new DecimalFormat("###,###원");

    public static void printWelcomeMessage() {
        System.out.println(WELCOME_MSG);
    }

    public static void printDateInputDescription() {
        System.out.println(VISIT_DATE_INPUT_DESC);
    }

    public static void printOrderInputDescription() {
        System.out.println(ORDER_INPUT_DESC);
    }

    public static void printExceptionMessage(Exception e) {
        System.out.println(ERROR_MSG_PREFIX + e.getMessage());
    }

    public static void printEventPreviewDescription(int date) {
        String dateDesc = String.format("%d월 %d일", EventCalendar.MONTH, date);
        System.out.println(dateDesc + EVENT_PREVIEW_HEADER);
    }

    public static void printOrder(List<Order> orders) {
        System.out.println(ORDER_LIST_HEADER);
        StringBuilder sb = new StringBuilder();
        for (Order order : orders) {
            sb.append(order.toString());
            sb.append("\n");
        }
        System.out.println(sb);
    }

    public static void printTotalPrice(int totalPrice) {
        System.out.println(TOTAL_PRICE_HEADER);
        System.out.println(formatter.format(totalPrice));
        System.out.println();
    }

    public static void printGift(Map<String, Integer> gift) {
        System.out.println(GIFT_HEADER);

        if (gift.isEmpty()) {
            System.out.println(NOTHING);
            System.out.println();
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Integer> giftEntry : gift.entrySet()) {
            sb.append(giftEntry.getKey());
            sb.append(" ");
            sb.append(giftEntry.getValue());
            sb.append("개");
            sb.append("\n");
        }
        System.out.println(sb);
    }

    public static void printBenefitList(Map<String, Integer> benefits) {
        System.out.println(BENEFIT_LIST_HEADER);

        if (benefits.isEmpty()) {
            System.out.println(NOTHING);
            System.out.println();
            return;
        }

        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Integer> benefit : benefits.entrySet()) {
            sb.append(benefit.getKey());
            sb.append(": ");
            sb.append(formatter.format(-benefit.getValue()));
            sb.append("\n");
        }
        System.out.println(sb);
    }

    public static void printBenefitAmount(int benefitAmount) {
        System.out.println(BENEFIT_AMOUNT_HEADER);
        System.out.println(formatter.format(-benefitAmount));
        System.out.println();
    }

    public static void printDiscountedPrice(int discountedPrice) {
        System.out.println(DISCOUNTED_PRICE_HEADER);
        System.out.println(formatter.format(discountedPrice));
        System.out.println();
    }

    public static void printEventBadge(String badgeName) {
        System.out.println(EVENT_BADGE_HEADER);
        if (badgeName == null) {
            System.out.println(NOTHING);
            return;
        }
        System.out.println(badgeName);
    }
}
