package christmas.domain;

import christmas.constant.EventCalendar;
import christmas.constant.Week;
import christmas.util.DateValidator;

public class VisitDate {
    private final int date;
    private final Week weekday;

    public VisitDate(int date, Week weekday) {
        DateValidator.validateDate(date);
        this.date = date;
        this.weekday = weekday;
    }

    public boolean isSpecialDay() {
        return EventCalendar.specialDate.contains(this.date);
    }

    public boolean isWeekday() {
        return weekday.isWeekday();
    }

    public boolean isWeekend() {
        return weekday.isWeekend();
    }

    public boolean isAfterChristmas() {
        return this.date > EventCalendar.CHRISTMAS_DATE;
    }

    public int getDate() {
        return this.date;
    }
}
