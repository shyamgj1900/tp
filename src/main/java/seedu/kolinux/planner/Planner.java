package seedu.kolinux.planner;

import seedu.kolinux.exceptions.KolinuxException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/** Represents the operations to interact with the user schedule. */
public class Planner {

    private PlannerStorage plannerStorage = new PlannerStorage();

    private static final String DATE_PATTERN = "\\d\\d\\d\\d-\\d\\d-\\d\\d";
    private static final String EMPTY_STRING = "";
    private static final String NO = "n";
    private static ArrayList<Event> scheduleOfAllDates = new ArrayList<>();
    private static final String PLANNER_CORRUPTED_ERROR =
            "Some of the data is corrupted, your planner will be reset...";
    private static final String TIME_CONFLICT_ERROR =
            "You already have an event ongoing for that time period, please try again with another timing.";
    private static final String EMPTY_LIST_MESSAGE = "There are no events planned for this date yet!";
    private static final String INVALID_DATE_MESSAGE = "Please provide a valid date. Format: yyyy-mm-dd";
    private static final String INVALID_ID_ERROR = "Invalid ID given, no events were deleted.";
    private static final String CANCEL_DELETE_ERROR = "Delete cancelled.";

    /**
     * Filters all the events in the planner by a particular date.
     *
     * @param date Date
     * @return List of events happening on the given date
     */
    private ArrayList<Event> filterPlanner(String date) {
        ArrayList<Event> filteredPlanner =
                (ArrayList<Event>) scheduleOfAllDates
                        .stream()
                        .filter((event) -> date.equals(event.getDate()))
                        .sorted(Comparator.comparing(Event::getStartTime))
                        .collect(Collectors.toList());
        return filteredPlanner;
    }

    /**
     * Checks if an event has time conflict with an existing event on the same date.
     *
     * @param eventToBeAdded The event to be added
     * @return true if there is time conflict, false otherwise.
     */
    private boolean hasTimeConflict(Event eventToBeAdded) {
        ArrayList<Event> filteredPlanner = filterPlanner(eventToBeAdded.getDate());
        String startTime = eventToBeAdded.getStartTime();
        String endTime = eventToBeAdded.getEndTime();
        for (Event event : filteredPlanner) {
            if (!(startTime.compareTo(event.getEndTime()) >= 0 || endTime.compareTo(event.getStartTime()) <= 0)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Concatenates an array list of strings into a single string, starting with a newline and with newlines
     * separating consecutive entries.
     *
     * @param strings List of strings to be concatenated
     * @return Concatenated string of the list of strings
     */
    private String concatenateStrings(ArrayList<String> strings) {
        String concatenatedString = EMPTY_STRING;
        for (String string : strings) {
            concatenatedString = concatenatedString.concat("\n" + string);
        }
        return concatenatedString;
    }

    /**
     * Returns a list of data strings generated from the list of events, to be used to rewrite planner.txt.
     *
     * @return List of data strings corresponding to all the events
     */
    private ArrayList<String> returnDataStrings() {
        return (ArrayList<String>) scheduleOfAllDates.stream()
                .map(event -> event.toData())
                .collect(Collectors.toList());
    }

    /**
     * Initializes the planner by loading the previously saved schedule in planner.txt.
     *
     * @throws KolinuxException If the file cannot be read properly due to corruption
     */
    public void initPlanner() throws KolinuxException {
        ArrayList<String> fileLines;
        if ((fileLines = plannerStorage.readFile()) == null) {
            assert scheduleOfAllDates.isEmpty();
            return;
        }

        try {
            Event event;
            for (String fileLine : fileLines) {
                event = new Event(fileLine);
                scheduleOfAllDates.add(event);
            }
        } catch (KolinuxException exception) {
            clearEvents();
            assert scheduleOfAllDates.isEmpty();
            throw new KolinuxException(PLANNER_CORRUPTED_ERROR);
        }
    }

    /**
     * Adds an event to the schedule list.
     *
     * @param event Event
     * @throws KolinuxException If the event to be added has time conflict with an existing event.
     */
    public void addEvent(Event event) throws KolinuxException {
        if (hasTimeConflict(event)) {
            throw new KolinuxException(TIME_CONFLICT_ERROR);
        }
        scheduleOfAllDates.add(event);
        plannerStorage.writeFile(event.toData());
    }

    /**
     * Lists the events on a particular date.
     *
     * @param date Date
     * @param withId true if the list is needed to display the id of the events, false otherwise.
     * @return All the events on the date in a single concatenated string
     * @throws KolinuxException If the date specified is invalid or if there are no events planned
     * on the date specified
     */
    public String listEvents(String date, boolean withId) throws KolinuxException {

        try {
            LocalDate.parse(date);
        } catch (DateTimeParseException exception) {
            throw new KolinuxException(INVALID_DATE_MESSAGE);
        }

        assert Pattern.matches(DATE_PATTERN, date);
        ArrayList<String> filteredEventStrings =
                (ArrayList<String>) filterPlanner(date)
                        .stream()
                        .map((event) -> {
                            if (withId) {
                                return event.toStringWithId();
                            }
                            return event.toString();
                        })
                        .collect(Collectors.toList());

        String eventsInOneString = concatenateStrings(filteredEventStrings);
        if (eventsInOneString.isEmpty()) {
            throw new KolinuxException(EMPTY_LIST_MESSAGE);
        }

        return eventsInOneString;
    }

    /**
     * Deletes an event given its corresponding unique ID.
     *
     * @param id Unique identifier of the event
     * @throws KolinuxException If the id does not match any events or the user cancelled the operation
     */
    public void deleteEvent(String id) throws KolinuxException {
        if (scheduleOfAllDates.removeIf(event -> id.equals(event.getId()))) {
            plannerStorage.rewriteFile(returnDataStrings());
        } else if (id.equalsIgnoreCase(NO)) {
            throw new KolinuxException(CANCEL_DELETE_ERROR);
        } else {
            throw new KolinuxException(INVALID_ID_ERROR);
        }
    }

    /**
     * Clears all events in the schedule.
     */
    public void clearEvents() {
        scheduleOfAllDates.clear();
        plannerStorage.clearFile();
    }
}
