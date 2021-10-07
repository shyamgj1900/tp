package seedu.duke.planner;

import seedu.duke.exceptions.KolinuxException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

public class Event {

    private String description;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;

    private static final String DATETIME_ERROR =
            "Please provide a valid date and time! Format: yyyy-mm-dd";
    private static final String FORMAT_ERROR =
            "Please check the format of your input! Format: planner add DESCRIPTION/DATE/START_TIME/END_TIME";

    public Event(String[] parsedArguments) throws KolinuxException {
        try {
            this.description = parsedArguments[0];
            this.date = LocalDate.parse(parsedArguments[1]);
            this.startTime = LocalTime.parse(parsedArguments[2]);
            this.endTime = LocalTime.parse(parsedArguments[3]);
        } catch (DateTimeParseException e) {
            throw new KolinuxException(DATETIME_ERROR);
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            throw new KolinuxException(FORMAT_ERROR);
        }
    }

    public Event(String data) {
        String[] parsedArguments = data.split("\\|");
        this.description = parsedArguments[0];
        this.date = LocalDate.parse(parsedArguments[1]);
        this.startTime = LocalTime.parse(parsedArguments[2]);
        this.endTime = LocalTime.parse(parsedArguments[3]);
    }

    public String getDate() {
        return date.toString();
    }

    public String toData() {
        return description + "|" + date.toString() + "|" + startTime.toString() + "|" + endTime.toString();
    }

    public String toString() {
        return startTime + " - " + endTime + " " + description;
    }
}
