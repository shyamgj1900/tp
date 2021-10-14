package seedu.kolinux.commands;

import java.util.logging.Level;

/** Represents the command to exit from the application. */
public class ExitCommand extends Command {

    private static final String EXIT_MESSAGE = "Bye! Thank you for using Kolinux";

    @Override
    public CommandResult executeCommand() {
        logger.log(Level.INFO, "User exited Kolinux");
        return new CommandResult(EXIT_MESSAGE);
    }
}
