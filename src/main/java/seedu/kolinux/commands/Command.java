package seedu.kolinux.commands;

import seedu.kolinux.exceptions.KolinuxException;
import seedu.kolinux.module.ModuleDb;
import seedu.kolinux.module.ModuleList;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Logger;

public abstract class Command {

    protected String argument;
    protected static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    protected static ModuleDb moduleDb = new ModuleDb().getPreInitModuleDb();
    protected static ModuleList moduleList = new ModuleList();

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
    public abstract CommandResult executeCommand() throws KolinuxException, IOException;
}
