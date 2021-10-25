package seedu.kolinux.commands;

import org.junit.jupiter.api.Test;
import seedu.kolinux.exceptions.KolinuxException;
import seedu.kolinux.planner.Planner;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlannerCommandTest {

    private Planner planner = new Planner();

    private static final String ADD_SUBCOMMAND = "add";
    private static final String LIST_SUBCOMMAND = "list";

    private static final String[][] VALID_EVENT_ARGUMENTS
            = new String[][]{{"Pop Quiz 3", "2021-10-26", "1500", "1515"},
                {"Pop Quiz 4", "2021-10-26", "1530", "2000"},
                {"Pop Quiz 5", "2021-10-26", "2000", "2130"}};
    private static final String[] LIST_EVENT_ARGUMENTS = new String[]{"2021-10-26"};

    private static final String ADD_EVENT_MESSAGE = "An event has been added to your schedule successfully!";
    private static final String LIST_EVENTS_TEST = "2021-10-26\n"
            + "15:00 - 15:15 Pop Quiz 3\n"
            + "15:30 - 20:00 Pop Quiz 4\n"
            + "20:00 - 21:30 Pop Quiz 5";

    @Test
    public void handleAddCommand_validEvent_eventAdded() throws KolinuxException {
        planner.clearEvents();
        PlannerCommand command = new PlannerCommand(ADD_SUBCOMMAND, VALID_EVENT_ARGUMENTS[0]);
        CommandResult result = command.executeCommand();
        assertEquals(ADD_EVENT_MESSAGE, result.getFeedbackToUser());
        planner.clearEvents();
    }

    @Test
    public void handleListCommand_addThreeEventsThenListThem_eventsListed() throws KolinuxException {
        planner.clearEvents();
        PlannerCommand command = new PlannerCommand(ADD_SUBCOMMAND, VALID_EVENT_ARGUMENTS[0]);
        command.executeCommand();
        command = new PlannerCommand(ADD_SUBCOMMAND, VALID_EVENT_ARGUMENTS[1]);
        command.executeCommand();
        command = new PlannerCommand(ADD_SUBCOMMAND, VALID_EVENT_ARGUMENTS[2]);
        command.executeCommand();
        command = new PlannerCommand(LIST_SUBCOMMAND, LIST_EVENT_ARGUMENTS);
        CommandResult result = command.executeCommand();
        assertEquals(LIST_EVENTS_TEST, result.getFeedbackToUser());
        planner.clearEvents();
    }
}
