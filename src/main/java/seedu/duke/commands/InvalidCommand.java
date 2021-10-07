package seedu.duke.commands;

public class InvalidCommand extends Command {

    private static final String INVALID_COMMAND_MESSAGE =
            "This command is not recognised, please enter \"help\" for the list of commands...";

    @Override
    public CommandResult executeCommand() {
        return new CommandResult(INVALID_COMMAND_MESSAGE);
    }
}
