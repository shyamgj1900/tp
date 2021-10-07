package seedu.duke.planner;

import org.junit.jupiter.api.Test;
import seedu.duke.exceptions.KolinuxException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlannerTest {

    private static final String[] VALID_EVENT_ARGUMENTS
            = new String[]{"Pop Quiz 3", "2021-10-26", "15:00", "15:15"};
    private static final String[] INVALID_EVENT_DATE_ARGUMENTS
            = new String[]{"Something bad", "16/10/2020", "4pm", "7pm"};
    private static final String[] INVALID_EVENT_FORMAT_ARGUMENTS
            = new String[]{"Something worse", "2021-10-26", "07:00"};
    private static final String VALID_LIST
            = "\n15:00 - 15:15 Pop Quiz 3";

    @Test
    public void addEvent_validEventInput_eventAdded() throws KolinuxException {
        Planner.clearEvent();
        Event validEvent = new Event(VALID_EVENT_ARGUMENTS);
        Planner.addEvent(validEvent);
        assertEquals(VALID_LIST, Planner.listEvents("2021-10-26"));
        Planner.clearEvent();
    }

    @Test
    public void addEvent_invalidEventDateInput_eventNotAdded() {
        try {
            Event invalidEvent = new Event(INVALID_EVENT_DATE_ARGUMENTS);
            Planner.addEvent(invalidEvent);
        } catch (KolinuxException e) {
            assertEquals("Please provide a valid date and time!", e.getMessage());
        }
    }

    @Test
    public void addEvent_invalidEventFormatInput_eventNotAdded() {
        try {
            Event invalidEvent = new Event(INVALID_EVENT_FORMAT_ARGUMENTS);
            Planner.addEvent(invalidEvent);
        } catch (KolinuxException e) {
            assertEquals("Please double check the format of your input!", e.getMessage());
        }
    }
}
