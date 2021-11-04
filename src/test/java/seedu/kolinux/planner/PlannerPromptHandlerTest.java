package seedu.kolinux.planner;

import org.junit.jupiter.api.Test;
import seedu.kolinux.exceptions.KolinuxException;
import seedu.kolinux.module.ModuleList;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlannerPromptHandlerTest {

    private ModuleList moduleList = new ModuleList();
    private Planner planner = new Planner(moduleList);
    private PlannerPromptHandler plannerPromptHandler;

    private static final String TIME_CONFLICT_PROMPT =
            "You already have an event ongoing for that time period, do you still want to add?\n"
                    + "You may enter 'n' to cancel and proceed to list the events on the date to see what you already "
                    + "planned on that day\n"
                    + "Or you may enter 'y' to add the event";
    private static final String CANCEL_ADD_ERROR = "Operation cancelled, no events were added.";
    private static final String INVALID_REPLY_ERROR = "Invalid key entered! Please enter 'y' or 'n'.";
    private static final String ENTER_ID_PROMPT =
            "Please enter the ID of the event you wish to delete (Enter 'n' to terminate this operation):";
    private static final String CANCEL_DELETE_ERROR = "Operation cancelled, no events were deleted.";

    private static final String[][] VALID_EVENT_ARGUMENTS
            = new String[][]{{"Pop Quiz 3", "2021-10-26", "1500", "1515"},
                {"Pop Quiz 4", "2021-10-26", "1530", "2000"},
                {"Pop Quiz 5", "2021-10-26", "2000", "2130"}};

    private static final String VALID_LIST_1 = "\n15:00 - 15:15 Pop Quiz 3";

    @Test
    public void handleEventConflict_validEventArguments_eventAddedWithConflictAllowed() throws KolinuxException {
        planner.clearEvents();
        String input = "y";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        plannerPromptHandler = new PlannerPromptHandler(planner, TIME_CONFLICT_PROMPT);
        Event event = new Event(VALID_EVENT_ARGUMENTS[0]);
        plannerPromptHandler.handleEventConflict(event);

        assertEquals(TIME_CONFLICT_PROMPT, plannerPromptHandler.toString());
        assertEquals(VALID_LIST_1, planner.listEvents("2021-10-26", false));
        planner.clearEvents();
    }

    @Test
    public void handleEventConflict_validEventArguments_eventConflictDenied() {
        String input = "n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        try {
            plannerPromptHandler = new PlannerPromptHandler(planner, TIME_CONFLICT_PROMPT);
            Event event = new Event(VALID_EVENT_ARGUMENTS[0]);
            plannerPromptHandler.handleEventConflict(event);
        } catch (KolinuxException exception) {
            assertEquals(CANCEL_ADD_ERROR, exception.getMessage());
        }
    }

    @Test
    public void handleEventConflict_validEventArguments_invalidReplyButAddedEventually() throws KolinuxException {
        planner.clearEvents();
        String input = "you found a secret message, congrats!" + System.getProperty("line.separator") + "y";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        plannerPromptHandler = new PlannerPromptHandler(planner, TIME_CONFLICT_PROMPT);
        Event event = new Event(VALID_EVENT_ARGUMENTS[0]);
        plannerPromptHandler.handleEventConflict(event);

        assertEquals(INVALID_REPLY_ERROR, plannerPromptHandler.toString());
        assertEquals(VALID_LIST_1, planner.listEvents("2021-10-26", false));
        planner.clearEvents();
    }

    @Test
    public void promptForEventId_validEventList_operationCancelled() {
        String input = "n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        try {
            plannerPromptHandler = new PlannerPromptHandler(planner, ENTER_ID_PROMPT);
            plannerPromptHandler.promptForEventId();
        } catch (KolinuxException exception) {
            assertEquals(CANCEL_DELETE_ERROR, exception.getMessage());
        }
    }

    @Test
    public void promptForEventId_validEventList_idReturned() throws KolinuxException {
        planner.clearEvents();
        planner.addEvent(new Event(VALID_EVENT_ARGUMENTS[0]), false);
        planner.addEvent(new Event(VALID_EVENT_ARGUMENTS[1]), false);

        Event eventToBeDeleted = new Event(VALID_EVENT_ARGUMENTS[2]);
        planner.addEvent(eventToBeDeleted, false);

        String input = eventToBeDeleted.getId();
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        plannerPromptHandler = new PlannerPromptHandler(planner, ENTER_ID_PROMPT);
        assertEquals(eventToBeDeleted.getId(), plannerPromptHandler.promptForEventId());

        planner.clearEvents();
    }
}
