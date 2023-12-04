package christmas.constant;

import java.time.DayOfWeek;
import java.time.LocalDate;

public enum Week {
    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY;

    public static Week of(int date) {
        DayOfWeek weekIdx = LocalDate.of(EventCalendar.YEAR, EventCalendar.MONTH, date).getDayOfWeek();
        return Week.values()[weekIdx.getValue()-1];
    }

    public boolean isWeekday() {
        return !this.isWeekend();
    }

    public boolean isWeekend() {
        return this == FRIDAY || this == SATURDAY;
    }
}
