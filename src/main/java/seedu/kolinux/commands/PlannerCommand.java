package seedu.kolinux.commands;

import seedu.kolinux.exceptions.KolinuxException;
import seedu.kolinux.planner.Event;
import seedu.kolinux.planner.Planner;

import java.util.logging.Level;

/** Represents the command that interacts with the Planner. */
public class PlannerCommand extends Command {

    private Planner planner = new Planner(moduleList);

    private String subCommand;
    private String[] parsedArguments;

    private static final String YES = "y";
    private static final String NO = "n";

    private static final String ADD_SUBCOMMAND = "add";
    private static final String LIST_SUBCOMMAND = "list";
    private static final String DELETE_SUBCOMMAND = "delete";
    private static final String CLEAR_SUBCOMMAND = "clear";

    private static final String ADD_EVENT_MESSAGE = "An event has been added to your schedule successfully: ";
    private static final String DELETE_EVENT_MESSAGE = "An event has been deleted from your schedule successfully: ";
    private static final String CLEAR_EVENT_MESSAGE = "All the events in your schedule has been cleared.";

    private static final String TIME_CONFLICT_PROMPT =
            "You already have an event ongoing for that time period, do you still want to add?\n"
                    + "You may enter 'n' to cancel and proceed to list the events on the date to see what you already "
                    + "planned on that day\n"
                    + "Or you may enter 'y' to add the event";
    private static final String ENTER_ID_PROMPT =
            "Please enter the ID of the event you wish to delete (Enter 'n' to terminate this operation):";

    private static final String INVALID_ARGUMENT_MESSAGE =
            "This command is not recognised, you can try:\n"
                    + "planner add DESCRIPTION/DATE/START_TIME/END_TIME\n"
                    + "planner list DATE\n"
                    + "planner delete DATE";
    private static final String CANCEL_ADD_ERROR = "Operation cancelled, no events were added.";
    private static final String INVALID_REPLY_ERROR = "Invalid key entered! Please enter 'y' or 'n'.";
    private static final String CANCEL_DELETE_ERROR = "Operation cancelled, no events were deleted.";

    public PlannerCommand(String subCommand, String[] parsedArguments) {
        this.subCommand = subCommand;
        this.parsedArguments = parsedArguments;
    }

    /**
     * Infinite loop of posting prompt to the user to handle time conflicts in events when adding.
     * The loop only exits if the user provides a valid answer 'y' or 'n'.
     *
     * @param event Conflicted event
     * @throws KolinuxException If the user cancels the add operation
     */
    private void handleEventConflict(Event event) throws KolinuxException {
        String reply = getReplyFromPrompt(TIME_CONFLICT_PROMPT);
        while (true) {
            if (reply.equalsIgnoreCase(YES)) {
                planner.addEvent(event, true);
                break;
            } else if (reply.equalsIgnoreCase(NO)) {
                logger.log(Level.INFO, "User cancelled the planner add operation.");
                throw new KolinuxException(CANCEL_ADD_ERROR);
            } else {
                reply = getReplyFromPrompt(INVALID_REPLY_ERROR);
            }
        }
    }

    /**
     * Invoked if the subcommand is "add". This method tries to add the event, and if a time conflict
     * occurs, it will ask the user if the addition should still proceed. If approval is given by the
     * user, the event will be added. If the user cancels, an exception is thrown. Else, the prompt will
     * continue seeking for a valid answer.
     *
     * @return Result containing message
     * @throws KolinuxException If the event cannot be created due to incorrect arguments, or the user
     *     cancels the operation.
     */
    private CommandResult handleAddCommand() throws KolinuxException {
        Event event = new Event(parsedArguments);
        try {
            planner.addEvent(event, false);
        } catch (KolinuxException exception) {
            assert exception.getMessage().equals(TIME_CONFLICT_PROMPT);
            handleEventConflict(event);
        }
        logger.log(Level.INFO, "User added an event to planner: " + event);
        return new CommandResult(ADD_EVENT_MESSAGE + event.getDate() + " " + event);
    }

    /**
     * Invoked if the subcommand is "list". This method lists the events happening on a particular date.
     *
     * @return Result containing the list of events
     * @throws KolinuxException If the arguments are invalid or there are no events happening on the date
     */
    private CommandResult handleListCommand() throws KolinuxException {
        String eventList = planner.listEvents(parsedArguments[0], false);
        logger.log(Level.INFO, "User listed events on " + parsedArguments[0]);
        return new CommandResult(parsedArguments[0] + eventList);
    }

    /**
     * Invoked if the subcommand is "delete". This method deletes an event on a particular date. It starts by
     * displaying the list of events with their id to the user, prompting the user to key in the id of the event
     * to be deleted. If the id is not recognised, an exception is thrown.
     *
     * @return Result containing the message
     * @throws KolinuxException If the id is not recognised or the user cancels the operation
     */
    private CommandResult handleDeleteCommand() throws KolinuxException {
        String idList = planner.listEvents(parsedArguments[0], true);
        String id = getReplyFromPrompt(ENTER_ID_PROMPT + idList);
        if (id.equalsIgnoreCase(NO)) {
            logger.log(Level.INFO, "User cancelled the planner delete operation.");
            throw new KolinuxException(CANCEL_DELETE_ERROR);
        }
        Event deletedEvent = planner.deleteEvent(id);
        logger.log(Level.INFO, "User deleted an event on " + parsedArguments[0]);
        return new CommandResult(DELETE_EVENT_MESSAGE + deletedEvent.getDate() + " " + deletedEvent);
    }

    /**
     * Clears all events stored in scheduleOfAllDates in Planner. This is only used when the subcommand is "clear",
     * which is not known to the user. This is only used for convenience while developing this feature.
     *
     * @return Result stating all events have been cleared.
     */
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
