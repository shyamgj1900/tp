package seedu.kolinux.commands;

import seedu.kolinux.exceptions.KolinuxException;
import seedu.kolinux.planner.Event;
import seedu.kolinux.planner.Planner;

import java.util.logging.Level;

/** Represents the command that interacts with the Planner. */
public class PlannerCommand extends Command {

    private Planner planner = new Planner();

    private String subCommand;
    private String[] parsedArguments;

    private static final String YES = "y";
    private static final String NO = "n";

    private static final String ADD_SUBCOMMAND = "add";
    private static final String LIST_SUBCOMMAND = "list";
    private static final String DELETE_SUBCOMMAND = "delete";
    private static final String CLEAR_SUBCOMMAND = "clear";

    private static final String ADD_EVENT_MESSAGE = "An event has been added to your schedule successfully!";
    private static final String DELETE_EVENT_MESSAGE = "An event has been deleted from your schedule successfully!";
    private static final String CLEAR_EVENT_MESSAGE = "All the events in your schedule has been cleared.";
    private static final String TIME_CONFLICT_PROMPT =
            "You already have an event ongoing for that time period, do you still want to add? (y/n)";
    private static final String ENTER_ID_PROMPT =
            "Please enter the ID of the event you wish to delete (Enter 'n' to terminate this operation):";
    private static final String INVALID_ARGUMENT_MESSAGE =
            "This command is not recognised, you can try:\n"
                    + "planner add DESCRIPTION/DATE/START_TIME/END_TIME\n"
                    + "planner list DATE";

    private static final String CANCEL_ADD_ERROR = "Operation cancelled, no events were added.";
    private static final String CANCEL_DELETE_ERROR = "Operation cancelled, no events were deleted.";

    public PlannerCommand(String subCommand, String[] parsedArguments) {
        this.subCommand = subCommand;
        this.parsedArguments = parsedArguments;
    }

    private CommandResult handleAddCommand() throws KolinuxException {
        Event event = new Event(parsedArguments);
        try {
            planner.addEvent(event, false);
        } catch (KolinuxException exception) {
            assert exception.getMessage().equals(TIME_CONFLICT_PROMPT);
            String reply = getReplyFromPrompt(exception.getMessage());

            if (reply.equalsIgnoreCase(YES)) {
                planner.addEvent(event, true);
            } else {
                throw new KolinuxException(CANCEL_ADD_ERROR);
            }
        }
        logger.log(Level.INFO, "User added an event to planner: " + event);
        return new CommandResult(ADD_EVENT_MESSAGE);
    }

    private CommandResult handleListCommand() throws KolinuxException {
        String eventList = planner.listEvents(parsedArguments[0], false);
        logger.log(Level.INFO, "User listed events on " + parsedArguments[0]);
        return new CommandResult(parsedArguments[0] + eventList);
    }

    private CommandResult handleDeleteCommand() throws KolinuxException {
        String idList = planner.listEvents(parsedArguments[0], true);
        String id = getReplyFromPrompt(ENTER_ID_PROMPT + idList);
        if (id.equalsIgnoreCase(NO)) {
            throw new KolinuxException(CANCEL_DELETE_ERROR);
        }
        planner.deleteEvent(id);
        logger.log(Level.INFO, "User deleted an event on " + parsedArguments[0]);
        return new CommandResult(DELETE_EVENT_MESSAGE);
    }

    private CommandResult handleClearCommand() {
        planner.clearEvents();
        return new CommandResult(CLEAR_EVENT_MESSAGE);
    }

    @Override
    public CommandResult executeCommand() throws KolinuxException {
        switch (subCommand) {
        case ADD_SUBCOMMAND:
            return handleAddCommand();
        case LIST_SUBCOMMAND:
            return handleListCommand();
        case DELETE_SUBCOMMAND:
            return handleDeleteCommand();
        case CLEAR_SUBCOMMAND:
            // Command only for testing purposes, not known to the user.
            return handleClearCommand();
        default:
            logger.log(Level.INFO, "User entered an invalid sub-command of Planner");
            throw new KolinuxException(INVALID_ARGUMENT_MESSAGE);
        }
    }
}
