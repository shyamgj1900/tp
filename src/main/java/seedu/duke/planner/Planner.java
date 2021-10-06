package seedu.duke.planner;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Planner {

    private static ArrayList<Event> schedule = new ArrayList<>();

    public static void addEvent(Event event) {
        schedule.add(event);
        PlannerStorage.writeFile(event.toData());
    }

    public static String listEvents(String date) {
        ArrayList<String> eventList =
                (ArrayList<String>) schedule
                        .stream()
                        .filter((event) -> date.equals(event.getDate()))
                        .map((event) -> event.toString())
                        .collect(Collectors.toList());

        String scheduleList = "";
        for (String event : eventList) {
            scheduleList = scheduleList.concat("\n" + event);
        }
        return scheduleList;
    }
}
