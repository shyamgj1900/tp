package seedu.duke.commands;

public class InvalidCommand extends Command {

    @Override
    public CommandResult executeCommand() {
        String invalidCommandMessage =
                "This command is not recognised, please enter \"help\" for the list of commands...";
        return new CommandResult(invalidCommandMessage);
    }
}
