package seedu.duke.planner;

import java.time.LocalDate;
import java.time.LocalTime;

public class Event {

    private String description;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;

    public Event(String[] parsedArguments) {
        this.description = parsedArguments[0];
        this.date = LocalDate.parse(parsedArguments[1]);
        this.startTime = LocalTime.parse(parsedArguments[2]);
        this.endTime = LocalTime.parse(parsedArguments[3]);
    }
}
