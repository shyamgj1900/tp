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

    public Event(String[] parsedArguments) throws KolinuxException {
        try {
            this.description = parsedArguments[0];
            this.date = LocalDate.parse(parsedArguments[1]);
            this.startTime = LocalTime.parse(parsedArguments[2]);
            this.endTime = LocalTime.parse(parsedArguments[3]);
        } catch (DateTimeParseException e) {
            throw new KolinuxException("Please provide a valid date and time!");
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            throw new KolinuxException("Please double check the format of your input!");
        }
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
