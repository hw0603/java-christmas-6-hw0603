package christmas.view;

import christmas.constant.EventCalendar;
import christmas.constant.EventPlanner;
import christmas.domain.Order;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

public class OutputView {
    private static final DecimalFormat formatter = new DecimalFormat("###,###원");

    public static void printWelcomeMessage() {
        System.out.println(EventPlanner.WELCOME_MSG);
    }

    public static void printDateInputDescription() {
        System.out.println(EventPlanner.VISIT_DATE_INPUT_DESC);
    }

    public static void printOrderInputDescription() {
        System.out.println(EventPlanner.ORDER_INPUT_DESC);
    }

    public static void printExceptionMessage(Exception e) {
        System.out.println(EventPlanner.ERROR_MSG_PREFIX + e.getMessage());
    }

    public static void printEventPreviewDescription(int date) {
        String dateDesc = String.format("%d월 %d일", EventCalendar.MONTH, date);
        System.out.println(dateDesc + EventPlanner.EVENT_PREVIEW_HEADER);
    }

    public static void printOrder(List<Order> orders) {
        System.out.println(EventPlanner.ORDER_LIST_HEADER);
        StringBuilder sb = new StringBuilder();
        for (Order order : orders) {
            sb.append(order.toString());
            sb.append("\n");
        }
        System.out.println(sb);
    }

    public static void printTotalPrice(int totalPrice) {
        System.out.println(EventPlanner.TOTAL_PRICE_HEADER);
        System.out.println(formatter.format(totalPrice));
        System.out.println();
    }

    public static void printGift(String giftName) {
        System.out.println(EventPlanner.GIFT_HEADER);

        if (giftName == null) {
            System.out.println(EventPlanner.NOTHING);
            System.out.println();
            return;
        }
        System.out.println(giftName + " 1개"); // TODO: 증정품이 다수일 수 있음
        System.out.println();
    }

    public static void printBenefitList(Map<String, Integer> benefits) {
        System.out.println(EventPlanner.BENEFIT_LIST_HEADER);

        if (benefits.isEmpty()) {
            System.out.println(EventPlanner.NOTHING);
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
        System.out.println(EventPlanner.BENEFIT_AMOUNT_HEADER);
        System.out.println(formatter.format(-benefitAmount));
        System.out.println();
    }

    public static void printDiscountedPrice(int discountedPrice) {
        System.out.println(EventPlanner.DISCOUNTED_PRICE_HEADER);
        System.out.println(formatter.format(discountedPrice));
        System.out.println();
    }

    public static void printEventBadge(String badgeName) {
        System.out.println(EventPlanner.EVENT_BADGE_HEADER);
        if (badgeName == null) {
            System.out.println(EventPlanner.NOTHING);
            return;
        }
        System.out.println(badgeName);
    }
}
