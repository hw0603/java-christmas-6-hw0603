package christmas.service;

import christmas.constant.Week;
import christmas.domain.VisitDate;

public class CalendarService {
    public VisitDate createVisitDate(int date) {
        return new VisitDate(date, getWeekday(date));
    }

    private Week getWeekday(int date) {
        return Week.of(date);
    }
}
