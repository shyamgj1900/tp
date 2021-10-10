package seedu.duke.commands;

import java.util.logging.Level;
import java.util.logging.Logger;

public class InvalidCommand extends Command {

    private static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    private static final String INVALID_COMMAND_MESSAGE =
            "This command is not recognised, please enter \"help\" for the list of commands...";

    @Override
    public CommandResult executeCommand() {
        logger.log(Level.INFO, "User entered an invalid command");
        return new CommandResult(INVALID_COMMAND_MESSAGE);
    }
}
