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

    public abstract CommandResult executeCommand() throws KolinuxException, FileNotFoundException;
}
