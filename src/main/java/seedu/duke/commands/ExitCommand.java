package seedu.duke.commands;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ExitCommand extends Command {

    private static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    private static final String EXIT_MESSAGE = "Bye! Thank you for using Kolinux";

    @Override
    public CommandResult executeCommand() {
        logger.log(Level.INFO, "User exited Kolinux");
        return new CommandResult(EXIT_MESSAGE);
    }
}
