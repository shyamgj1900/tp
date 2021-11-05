package seedu.kolinux.planner;

import seedu.kolinux.exceptions.KolinuxException;
import seedu.kolinux.util.Parser;

import java.time.LocalDate;
import java.time.LocalTime;

/** Represents an event in the schedule. */
public class Event {

    private static final String COLON = ":";
    private static final String EMPTY_STRING = "";
    // Pipe character padded with any number of white spaces
    private static final String DATA_DELIMITER_REGEX = "\\s*\\|\\s*";
    private static final String PIPE = "|";
    private static final int EVENT_ARGUMENTS_LENGTH = 4;

    private static int currentEventId = 0;

    private String description;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private int id;
    private boolean isIntegratedEvent = false;

    private static final String EMPTY_DESCRIPTION_ERROR =
            "Please provide a description for your event!";
    private static final String TIME_ORDER_ERROR =
            "Please check the format of the time! The end time is earlier than the start time...";
    private static final String TIME_SAME_ERROR =
            "Your event cannot start and end at the same time!";
    private static final String FORMAT_ERROR =
            "Please check the format of your input! Format: planner add DESCRIPTION/DATE/START_TIME/END_TIME";

    /**
     * Constructs an event using the arguments given in the user input.
     *
     * @param parsedArguments Array of information of an event
     * @throws KolinuxException If any of the information required is missing or in incorrect format.
     */
    public Event(String[] parsedArguments) throws KolinuxException {

        if (parsedArguments.length != EVENT_ARGUMENTS_LENGTH) {
            throw new KolinuxException(FORMAT_ERROR);
        }
        if (parsedArguments[0].isEmpty()) {
            throw new KolinuxException(EMPTY_DESCRIPTION_ERROR);
        }

        this.description = parsedArguments[0];
        this.date = Parser.verifyDate(parsedArguments[1]);
        this.startTime = Parser.verifyTime(parsedArguments[2]);
        this.endTime = Parser.verifyTime(parsedArguments[3]);

        if (startTime.compareTo(endTime) > 0) {
            throw new KolinuxException(TIME_ORDER_ERROR);
        }
        if (startTime.equals(endTime)) {
            throw new KolinuxException(TIME_SAME_ERROR);
        }
        this.id = currentEventId++;
    }

    /**
     * Constructs an event using the data read from planner.txt. This constructor is only called to
     * add previously saved events in planner.txt to the current active list.
     *
     * @param data Data line read from the file
     * @throws KolinuxException If the data line is corrupted
     */
    public Event(String data) throws KolinuxException {
        this(data.split(DATA_DELIMITER_REGEX));
    }

    /**
     * This method is called only when constructing an Event from a given lesson or exam information. Otherwise, the
     * default value of isIntegratedEvent is false upon construction of this object.
     */
    public void setIsIntegratedEvent() {
        this.isIntegratedEvent = true;
    }

    public boolean getIsIntegratedEvent() {
        return this.isIntegratedEvent;
    }

    public String getDate() {
        return date.toString();
    }

    public String getStartTime() {
        return startTime.toString().replace(COLON, EMPTY_STRING);
    }

    public String getEndTime() {
        return endTime.toString().replace(COLON, EMPTY_STRING);
    }

    public String getId() {
        return Integer.toString(id);
    }

    /**
     * Converts the event to a data string that is stored in planner.txt.
     * Note: This string is different from the one displayed to the user on the user interface.
     *
     * @return Data string
     */
    public String toData() {
        return description + PIPE + date.toString() + PIPE + getStartTime() + PIPE + getEndTime();
    }

    public String toString() {
        assert startTime.compareTo(endTime) < 0;
        return startTime + " - " + endTime + " " + description;
    }

    /**
     * Prints the string representation of this object with ID.
     *
     * @return String representation with ID
     */
    public String toStringWithId() {
        return this + " (id: " + id + ")";
    }
}
