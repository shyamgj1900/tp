package seedu.duke.commands;

import seedu.duke.exceptions.KolinuxException;

public abstract class Command {

    protected String argument;

    public Command() {
    }

    public Command(String argument) {
        this.argument = argument;
    }

    protected boolean isEmptyArgument() {
        return argument.isEmpty();
    }

    public abstract CommandResult executeCommand() throws KolinuxException;
}
