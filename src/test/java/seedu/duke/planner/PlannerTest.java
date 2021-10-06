package seedu.duke.planner;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class PlannerTest {

    private String[] validEventArguments = new String[]{"Pop Quiz 3", "2021-10-26", "15:00", "15:15"};
    private String validList = "\n15:00 - 15:15 Pop Quiz 3";

    @Test
    public void addEvent_validEventInput_eventAdded() throws Exception {
        Planner.clearEvent();
        Event validEvent = new Event(validEventArguments);
        Planner.addEvent(validEvent);
        assertEquals(validList, Planner.listEvents("2021-10-26"));
        Planner.clearEvent();
    }

    
}
