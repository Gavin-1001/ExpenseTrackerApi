package com.financetracker.Utils;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

public class DateUtils {
    public static LocalDateTime getStartOfWeek(LocalDateTime date) {
        // Calculate the start of the week (Monday) for the given date
        return date.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
    }
}
