package christmas.constant;

public class EventPlanner {
    public static final int MIN_DATE = 1;
    public static final int MAX_DATE = 31;
    public static final String ERROR_MSG_PREFIX = "[ERROR] ";
    public static final String WELCOME_MSG = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.";
    public static final String VISIT_DATE_INPUT_DESC = "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)";
    public static final String INVALID_DATE_ERROR = "유효하지 않은 날짜입니다. 다시 입력해 주세요.";
    public static final String ORDER_INPUT_DESC = "주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)";
    public static final String INVALID_ORDER_ERROR = "유효하지 않은 주문입니다. 다시 입력해 주세요.";

    private EventPlanner() {
    }
}
