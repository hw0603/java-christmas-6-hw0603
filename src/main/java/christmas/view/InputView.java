package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.constant.EventPlanner;
import java.util.Arrays;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class InputView {
    private static final String ORDER_FORMAT_REGEX = "^([^-]+-\\d+)(,[^-]+-\\d+)*$";

    public static int inputVisitDate() {
        try {
            return Integer.parseInt(Console.readLine());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(EventPlanner.INVALID_ORDER_ERROR);
        }
    }

    public static Map<String, Integer> inputOrder() {
        String userInput = Console.readLine();
        if (!isAcceptableOrderFormat(userInput)) {
            throw new IllegalArgumentException(EventPlanner.INVALID_ORDER_ERROR);
        }

        return Arrays.stream(userInput.split(","))
                .map(order -> order.split("-"))
                .collect(Collectors.toMap(order -> order[0], order -> Integer.parseInt(order[1])));
    }

    private static boolean isAcceptableOrderFormat(String userInput) {
        return Pattern.matches(ORDER_FORMAT_REGEX, userInput);
    }
}
