package seedu.kolinux.commands;

import java.util.logging.Level;

/** Represents any command from the user input that is not recognised. */
public class InvalidCommand extends Command {

    private static final String INVALID_COMMAND_MESSAGE =
            "This command is not recognised, please enter \"help\" for the list of commands...";

    @Override
    public CommandResult executeCommand() {
        logger.log(Level.INFO, "User entered an invalid command");
        return new CommandResult(INVALID_COMMAND_MESSAGE);
    }
}
