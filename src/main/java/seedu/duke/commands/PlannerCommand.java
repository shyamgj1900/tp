package seedu.duke.commands;

import seedu.duke.exceptions.KolinuxException;
import seedu.duke.planner.Event;
import seedu.duke.planner.Planner;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.logging.Level;

/** Represents the command that interacts with the Planner. */
public class PlannerCommand extends Command {

    private String subCommand;
    private String[] parsedArguments;

    private static final String ADD_EVENT_MESSAGE = "An event has been added to your schedule successfully!";
    private static final String CLEAR_EVENT_MESSAGE = "All the events in your schedule has been cleared.";
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
        case "add":
            Event event = new Event(parsedArguments);
            Planner.addEvent(event);
            logger.log(Level.INFO, "User added an event to planner: " + event);
            return new CommandResult(ADD_EVENT_MESSAGE);
        case "list":
            String date = processDate(parsedArguments[0]);
            String eventList = Planner.listEvents(date);
            logger.log(Level.INFO, "User listed events on " + date);
            return new CommandResult(date + eventList);
        case "clear":
            // Command only for testing purposes, not known to the user.
            Planner.clearEvents();
            return new CommandResult(CLEAR_EVENT_MESSAGE);
        default:
            logger.log(Level.INFO, "User entered an invalid sub-command of Planner");
            throw new KolinuxException(INVALID_ARGUMENT_MESSAGE);
        }
    }
}
