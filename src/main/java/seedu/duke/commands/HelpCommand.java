package seedu.duke.commands;

public class HelpCommand extends Command {

    @Override
    public CommandResult executeCommand() {
        String helpMessage = "Here are the list of commands:\n"
                + "cap [MC and grade]  - Calculates the total cap for the semester\n"
                + "view [enter module code] - View the module details\n"
                + "bus routes - Check for a NUS bus route from stop to another\n"
                + "info [su/modreg/plagiarism] - View information about SU, ModReg, or the Plagiarism Policy";
        return new CommandResult(helpMessage);
    }
}
