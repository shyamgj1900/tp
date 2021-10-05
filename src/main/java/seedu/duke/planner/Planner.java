package seedu.duke.planner;

import java.util.ArrayList;

public class Planner {

    private ArrayList<Event> schedule = new ArrayList<>();
    private PlannerStorage storage = new PlannerStorage();

    public void addEvent(Event event) {
        schedule.add(event);
        storage.writeFile(event.toData());
    }
}
