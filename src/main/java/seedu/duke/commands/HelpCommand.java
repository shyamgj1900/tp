package seedu.duke.commands;

import java.util.logging.Level;

public class HelpCommand extends Command {

    private static final String HELP_MESSAGE = "Here are the list of commands:\n"
            + "1. cap MC_AND_GRADES  - Calculates the total cap for the semester\n"
            + "2. view MODULE_CODE - View the module details\n"
            + "3. bus - Check for a NUS bus route from stop to another\n"
            + "4. planner add DESCRIPTION/DATE/START_TIME/END_TIME - Add an event to your schedule\n"
            + "5. planner list DATE - Lists events on a certain date\n"
            + "6. help - View this menu again\n"
            + "7. bye - Exit Kolinux";

    @Override
    public CommandResult executeCommand() {
        logger.log(Level.INFO, "User needed help");
        return new CommandResult(HELP_MESSAGE);
    }
}
