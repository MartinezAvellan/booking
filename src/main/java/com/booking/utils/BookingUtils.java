package com.booking.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingUtils {

    private Long id;
    private LocalDate startDate;
    private LocalDate endDate;

    public boolean bookingCanBeCompleted() {
        return isDateValid() && isNotInAdvance() && isNotLongerThanPermitted();
    }

    private boolean isNotLongerThanPermitted() {
        return startDate.until(endDate, ChronoUnit.DAYS) <= 3;
    }

    private boolean isNotInAdvance() {
        return LocalDate.now().until(startDate, ChronoUnit.DAYS) < 30;
    }

    private boolean isDateValid() {
        return startDate.isBefore(endDate);
    }
}