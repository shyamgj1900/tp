package seedu.kolinux.commands;

import seedu.kolinux.exceptions.KolinuxException;
import seedu.kolinux.planner.Event;
import seedu.kolinux.planner.Planner;
import seedu.kolinux.util.Prompt;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.logging.Level;

/** Represents the command that interacts with the Planner. */
public class PlannerCommand extends Command {

    private Planner planner = new Planner();

    private String subCommand;
    private String[] parsedArguments;

    private static final String ADD_SUBCOMMAND = "add";
    private static final String LIST_SUBCOMMAND = "list";
    private static final String DELETE_SUBCOMMAND = "delete";
    private static final String CLEAR_SUBCOMMAND = "clear";

    private static final String ADD_EVENT_MESSAGE = "An event has been added to your schedule successfully!";
    private static final String CLEAR_EVENT_MESSAGE = "All the events in your schedule has been cleared.";
    private static final String ENTER_ID_PROMPT = "Please enter the ID of the event you wish to delete:";
    private static final String INVALID_ARGUMENT_MESSAGE =
            "This command is not recognised, you can try:\n"
                    + "planner add DESCRIPTION/DATE/START_TIME/END_TIME\n"
                    + "planner list DATE";
    private static final String INVALID_DATE_MESSAGE = "Please provide a valid date. Format: yyyy-mm-dd";

    public PlannerCommand(String subCommand, String[] parsedArguments) {
        this.subCommand = subCommand;
        this.parsedArguments = parsedArguments;
    }

    /**
     * Checks if the date follows the format yyyy-mm-dd.
     *
     * @param date Date
     * @return Same date in string if it follows the format
     * @throws KolinuxException If the date does not follow the format
     */
    private String processDate(String date) throws KolinuxException {
        try {
            LocalDate.parse(date);
        } catch (DateTimeParseException exception) {
            throw new KolinuxException(INVALID_DATE_MESSAGE);
        }
        return date;
    }

    @Override
    public CommandResult executeCommand() throws KolinuxException {
        switch (subCommand) {
        case ADD_SUBCOMMAND:
            Event event = new Event(parsedArguments);
            planner.addEvent(event);
            logger.log(Level.INFO, "User added an event to planner: " + event);
            return new CommandResult(ADD_EVENT_MESSAGE);
        case LIST_SUBCOMMAND:
            String dateToList = processDate(parsedArguments[0]);
            String eventList = planner.listEvents(dateToList);
            logger.log(Level.INFO, "User listed events on " + dateToList);
            return new CommandResult(dateToList + eventList);
        case DELETE_SUBCOMMAND:
            String dateToDelete = processDate(parsedArguments[0]);
            String idList = planner.listEventsWithId(dateToDelete);
            Prompt prompt = new Prompt(ENTER_ID_PROMPT + idList);
            String id = prompt.getReply();
            planner.deleteEvent(id);
            return new CommandResult("You have deleted an event on " + dateToDelete);
        case CLEAR_SUBCOMMAND:
            // Command only for testing purposes, not known to the user.
            planner.clearEvents();
            return new CommandResult(CLEAR_EVENT_MESSAGE);
        default:
            logger.log(Level.INFO, "User entered an invalid sub-command of Planner");
            throw new KolinuxException(INVALID_ARGUMENT_MESSAGE);
        }
    }
}
