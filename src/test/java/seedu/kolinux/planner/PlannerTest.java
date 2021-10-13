package seedu.kolinux.planner;

import org.junit.jupiter.api.Test;
import seedu.kolinux.exceptions.KolinuxException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlannerTest {

    private static final String[] VALID_EVENT_ARGUMENTS
            = new String[]{"Pop Quiz 3", "2021-10-26", "15:00", "15:15"};
    private static final String[] INVALID_EVENT_DATE_ARGUMENTS
            = new String[]{"Something bad", "16/10/2020", "4pm", "7pm"};
    private static final String[] INVALID_EVENT_FORMAT_ARGUMENTS
            = new String[]{"Something worse", "2021-10-26", "07:00"};
    private static final String[] WRONG_TIME_ORDER_ARGUMENTS
            = new String[]{"Go back in time", "2021-04-06", "20:00", "16:00"};
    private static final String VALID_LIST
            = "\n15:00 - 15:15 Pop Quiz 3";
    private static final String DATETIME_ERROR =
            "Please provide a valid date and time! Format: yyyy-mm-dd";
    private static final String FORMAT_ERROR =
            "Please check the format of your input! Format: planner add DESCRIPTION/DATE/START_TIME/END_TIME";
    private static final String TIME_ORDER_ERROR =
            "Please check the format of the time! The end time is earlier than the start time...";

    @Test
    public void addEvent_validEventInput_eventAdded() throws KolinuxException {
        Planner.clearEvents();
        Event validEvent = new Event(VALID_EVENT_ARGUMENTS);
        Planner.addEvent(validEvent);
        assertEquals(VALID_LIST, Planner.listEvents("2021-10-26"));
        Planner.clearEvents();
    }

    @Test
    public void addEvent_invalidEventDateInput_eventNotAdded() {
        try {
            Event invalidEvent = new Event(INVALID_EVENT_DATE_ARGUMENTS);
            Planner.addEvent(invalidEvent);
        } catch (KolinuxException exception) {
            assertEquals(DATETIME_ERROR, exception.getMessage());
        }
    }

    @Test
    public void addEvent_invalidEventFormatInput_eventNotAdded() {
        try {
            Event invalidEvent = new Event(INVALID_EVENT_FORMAT_ARGUMENTS);
            Planner.addEvent(invalidEvent);
        } catch (KolinuxException exception) {
            assertEquals(FORMAT_ERROR, exception.getMessage());
        }
    }

    @Test
    public void addEvent_wrongTimeOrderInput_eventNotAdded() {
        try {
            Event invalidEvent = new Event(WRONG_TIME_ORDER_ARGUMENTS);
            Planner.addEvent(invalidEvent);
        } catch (KolinuxException exception) {
            assertEquals(TIME_ORDER_ERROR, exception.getMessage());
        }
    }
}
