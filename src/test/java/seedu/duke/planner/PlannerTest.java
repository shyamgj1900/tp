package seedu.duke.planner;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlannerTest {

    private String[] validEventArguments = new String[]{"Pop Quiz 3", "2021-10-26", "15:00", "15:15"};
    private String[] invalidEventDateArguments = new String[]{"Something bad", "16/10/2020", "4pm", "7pm"};
    private String[] invalidEventFormatArguments = new String[]{"Something worse", "2021-10-26", "07:00"};
    private String validList = "\n15:00 - 15:15 Pop Quiz 3";

    @Test
    public void addEvent_validEventInput_eventAdded() throws Exception {
        Planner.clearEvent();
        Event validEvent = new Event(validEventArguments);
        Planner.addEvent(validEvent);
        assertEquals(validList, Planner.listEvents("2021-10-26"));
        Planner.clearEvent();
    }

    @Test
    public void addEvent_invalidEventDateInput_eventNotAdded() {
        Planner.clearEvent();
        try {
            Event invalidEvent = new Event(invalidEventDateArguments);
        } catch (Exception e) {
            assertEquals("Please provide a valid date and time!", e.getMessage());
        }
    }

    @Test
    public void addEvent_invalidEventFormatInput_eventNotAdded() {
        Planner.clearEvent();
        try {
            Event invalidEvent = new Event(invalidEventFormatArguments);
        } catch (Exception e) {
            assertEquals("Please double check the format of your input!", e.getMessage());
        }
    }
}
