package seedu.kolinux.planner;

import seedu.kolinux.exceptions.KolinuxException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/** Represents the operations to interact with the user schedule. */
public class Planner {

    private PlannerStorage plannerStorage = new PlannerStorage();

    private static final String DATE_PATTERN = "\\d\\d\\d\\d-\\d\\d-\\d\\d";
    private static final String EMPTY_LIST_MESSAGE = "\nYou have no events planned for this date, just chill!";
    private static final String EMPTY_STRING = "";
    private static ArrayList<Event> scheduleOfAllDates = new ArrayList<>();
    private static final String PLANNER_CORRUPTED_ERROR =
            "Some of the data is corrupted, your planner will be reset...";
    private static final String TIME_CONFLICT_ERROR =
            "You already have an event ongoing for that time period, please try again with another timing.";

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
     * Concatenates an array list of strings into a single string, with a newline separating consecutive
     * entries.
     *
     * @param strings List of strings to be concatenated
     * @return Concatenated string of the list of strings
     */
    private String concatenateStrings(ArrayList<String> strings) {
        if (strings.isEmpty()) {
            return EMPTY_LIST_MESSAGE;
        }

        String concatenatedString = EMPTY_STRING;
        for (String string : strings) {
            concatenatedString = concatenatedString.concat("\n" + string);
        }
        return concatenatedString;
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
     * @return All the events on the date in a single concatenated string
     */
    public String listEvents(String date) {

        assert Pattern.matches(DATE_PATTERN, date);

        ArrayList<String> filteredEventStrings =
                (ArrayList<String>) filterPlanner(date)
                        .stream()
                        .map((event) -> event.toString())
                        .collect(Collectors.toList());
        String eventsInOneString = concatenateStrings(filteredEventStrings);
        return eventsInOneString;
    }

    /**
     * Clears all events in the schedule.
     */
    public void clearEvents() {
        scheduleOfAllDates.clear();
        plannerStorage.clearFile();
    }
}
