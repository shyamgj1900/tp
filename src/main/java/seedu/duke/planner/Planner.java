package seedu.duke.planner;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Planner {

    private static ArrayList<Event> scheduleOfAllDates = new ArrayList<>();

    public static void initPlanner() {
        ArrayList<String> fileLines;
        if ((fileLines = PlannerStorage.readFile()) == null) {
            return;
        }

        Event event;
        for (String fileLine : fileLines) {
            event = new Event(fileLine);
            scheduleOfAllDates.add(event);
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
