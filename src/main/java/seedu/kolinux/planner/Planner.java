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
     */
    public void addEvent(Event event) {
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

        ArrayList<String> filteredSchedule =
                (ArrayList<String>) scheduleOfAllDates
                        .stream()
                        .filter((event) -> date.equals(event.getDate()))
                        .sorted(Comparator.comparing(Event::getTime))
                        .map((event) -> event.toString())
                        .collect(Collectors.toList());

        if (filteredSchedule.isEmpty()) {
            return EMPTY_LIST_MESSAGE;
        }

        String filteredScheduleInString = EMPTY_STRING;
        for (String event : filteredSchedule) {
            filteredScheduleInString = filteredScheduleInString.concat("\n" + event);
        }
        return filteredScheduleInString;
    }

    /**
     * Clears all events in the schedule.
     */
    public void clearEvents() {
        scheduleOfAllDates.clear();
        plannerStorage.clearFile();
    }
}
