package seedu.duke.commands;

public class ExitCommand extends Command {

    @Override
    public CommandResult executeCommand() {
        String exitMessage = "Bye! Thank you for using Kolinux";
        return new CommandResult(exitMessage);
    }
}
