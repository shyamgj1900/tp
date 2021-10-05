package seedu.duke.planner;

import java.util.ArrayList;

public class Planner {

    private ArrayList<Event> schedule = new ArrayList<>();

    public void addEvent(Event event) {
        schedule.add(event);
    }
}
