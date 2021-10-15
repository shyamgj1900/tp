package seedu.kolinux.planner;

import org.junit.jupiter.api.Test;
import seedu.kolinux.exceptions.KolinuxException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlannerTest {

    private Planner planner = new Planner();

    private static final String[] VALID_EVENT_ARGUMENTS
            = new String[]{"Pop Quiz 3", "2021-10-26", "1500", "1515"};
    private static final String[] INVALID_EVENT_DATE_ARGUMENTS
            = new String[]{"Something bad", "16/10/2020", "4pm", "7pm"};
    private static final String[] INVALID_EVENT_FORMAT_ARGUMENTS
            = new String[]{"Something worse", "2021-10-26", "0700"};
    private static final String[] WRONG_TIME_ORDER_ARGUMENTS
            = new String[]{"Go back in time", "2021-04-06", "2000", "1600"};
    private static final String[] CONFLICTED_TIME_ARGUMENTS
            = new String[]{"Do something", "2021-10-26", "1505", "1700"};
    private static final String VALID_LIST
            = "\n15:00 - 15:15 Pop Quiz 3";
    private static final String DATETIME_ERROR =
            "Please provide a valid date and time!\n"
                    + "Date: yyyy-mm-dd\n"
                    + "Time: hhMM";
    private static final String FORMAT_ERROR =
            "Please check the format of your input! Format: planner add DESCRIPTION/DATE/START_TIME/END_TIME";
    private static final String TIME_ORDER_ERROR =
            "Please check the format of the time! The end time is earlier than the start time...";
    private static final String TIME_CONFLICT_ERROR =
            "You already have an event ongoing for that time period, please try again with another timing.";

    @Test
    public void addEvent_validEventInput_eventAdded() throws KolinuxException {
        planner.clearEvents();
        Event validEvent = new Event(VALID_EVENT_ARGUMENTS);
        planner.addEvent(validEvent);
        assertEquals(VALID_LIST, planner.listEvents("2021-10-26"));
        planner.clearEvents();
    }

    @Test
    public void addEvent_invalidEventDateInput_eventNotAdded() {
        try {
            Event invalidEvent = new Event(INVALID_EVENT_DATE_ARGUMENTS);
            planner.addEvent(invalidEvent);
        } catch (KolinuxException exception) {
            assertEquals(DATETIME_ERROR, exception.getMessage());
        }
    }

    @Test
    public void addEvent_invalidEventFormatInput_eventNotAdded() {
        try {
            Event invalidEvent = new Event(INVALID_EVENT_FORMAT_ARGUMENTS);
            planner.addEvent(invalidEvent);
        } catch (KolinuxException exception) {
            assertEquals(FORMAT_ERROR, exception.getMessage());
        }
    }

    @Test
    public void addEvent_wrongTimeOrderInput_eventNotAdded() {
        try {
            Event invalidEvent = new Event(WRONG_TIME_ORDER_ARGUMENTS);
            planner.addEvent(invalidEvent);
        } catch (KolinuxException exception) {
            assertEquals(TIME_ORDER_ERROR, exception.getMessage());
        }
    }

    @Test
    public void addEvent_conflictedTimeInput_eventNotAdded() throws KolinuxException {
        planner.clearEvents();
        Event validEvent = new Event(VALID_EVENT_ARGUMENTS);
        planner.addEvent(validEvent);
        try {
            Event invalidEvent = new Event(CONFLICTED_TIME_ARGUMENTS);
            planner.addEvent(invalidEvent);
        } catch (KolinuxException exception) {
            assertEquals(TIME_CONFLICT_ERROR, exception.getMessage());
        }
        planner.clearEvents();
    }
}
