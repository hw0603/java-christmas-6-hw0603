package christmas.domain;

import christmas.constant.EventCalendar;
import christmas.constant.Week;

public class VisitDate {
    private final int date;
    private final Week weekday;

    public VisitDate(int date, Week weekday) {
        this.date = date;
        this.weekday = weekday;
    }

    public boolean isSpecialDay() {
        return EventCalendar.specialDate.contains(this.date);
    }
}
