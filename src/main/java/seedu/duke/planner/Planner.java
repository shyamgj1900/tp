package seedu.duke.planner;

import seedu.duke.exceptions.KolinuxException;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Planner {

    private static ArrayList<Event> scheduleOfAllDates = new ArrayList<>();
    private static final String PLANNER_CORRUPTED_ERROR =
            "Some of the data is corrupted, your planner will be reset...";

    public static void initPlanner() throws KolinuxException {
        ArrayList<String> fileLines;
        if ((fileLines = PlannerStorage.readFile()) == null) {
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
            throw new KolinuxException(PLANNER_CORRUPTED_ERROR);
        }
    }

    public static void addEvent(Event event) {
        scheduleOfAllDates.add(event);
        PlannerStorage.writeFile(event.toData());
    }

    public static String listEvents(String date) {
        ArrayList<String> filteredSchedule =
                (ArrayList<String>) scheduleOfAllDates
                        .stream()
                        .filter((event) -> date.equals(event.getDate()))
                        .map((event) -> event.toString())
                        .collect(Collectors.toList());

        if (filteredSchedule.isEmpty()) {
            return "\nYou have no events planned for this date, just chill!";
        }

        String filteredScheduleInString = "";
        for (String event : filteredSchedule) {
            filteredScheduleInString = filteredScheduleInString.concat("\n" + event);
        }
        return filteredScheduleInString;
    }

    public static void clearEvents() {
        scheduleOfAllDates.clear();
        PlannerStorage.clearFile();
    }
}
