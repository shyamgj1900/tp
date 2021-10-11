package seedu.duke.commands;

import seedu.duke.exceptions.KolinuxException;

import java.io.FileNotFoundException;
import java.util.logging.Logger;

public abstract class Command {

    protected String argument;
    protected static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public Command() {
    }

    public Command(String argument) {
        this.argument = argument;
    }

    protected boolean isEmptyArgument() {
        return argument.isEmpty();
    }

    /**
     * Executes the command with the arguments given by the user input.
     *
     * @return Result of execution
     * @throws KolinuxException If the arguments given are invalid
     * @throws FileNotFoundException If the file needed for execution is not found
     */
    public abstract CommandResult executeCommand() throws KolinuxException, FileNotFoundException;
}
