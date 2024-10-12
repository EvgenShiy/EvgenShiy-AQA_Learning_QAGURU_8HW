package data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateOfBirth {
    private final LocalDate date;

    public DateOfBirth(LocalDate date) {
        this.date = date;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getDay() {
        return String.format("%02d", date.getDayOfMonth());
    }

    public String getMonth() {
        return date.getMonth().name();
    }

    public String getYear() {
        return String.valueOf(date.getYear());
    }

    public String getFormattedDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
        return date.format(formatter);
    }

    @Override
    public String toString() {
        return getFormattedDate();
    }
}
