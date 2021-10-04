package seedu.duke.commands;

public class HelpCommand extends Command {

    @Override
    public CommandResult executeCommand() {
        String helpMessage = "Here are the list of commands:\n"
                + "cap - Opens the CAP calculator\n"
                + "view [module code] - View the module details\n"
                + "info [su/modreg/plagiarism] - View information about SU, ModReg, or the Plagiarism Policy\n";
        return new CommandResult(helpMessage);
    }
}
