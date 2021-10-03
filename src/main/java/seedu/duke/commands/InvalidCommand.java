package seedu.duke.commands;

public class InvalidCommand extends Command {

    @Override
    public CommandResult executeCommand() {
        String invalidCommandMessage = "This command is not recognised, please try again...";
        return new CommandResult(invalidCommandMessage);
    }
}
