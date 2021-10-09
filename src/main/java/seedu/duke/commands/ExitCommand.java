package seedu.duke.commands;

public class ExitCommand extends Command {

    private static final String EXIT_MESSAGE = "Bye! Thank you for using Kolinux";

    @Override
    public CommandResult executeCommand() {
        return new CommandResult(EXIT_MESSAGE);
    }
}
