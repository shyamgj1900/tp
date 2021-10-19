package seedu.kolinux.commands;

import seedu.kolinux.exceptions.KolinuxException;

// May change command name later
public class SetModuleGradeCommand extends Command {
    
    private String moduleCode;
    private String moduleGrade;
    
    // Command format: set CS2113T/A (assume CS2113T exists in moduleList)
    public SetModuleGradeCommand(String input) {
        String[] commandDescriptions = input.split(" ");
        moduleCode = commandDescriptions[0];
        moduleGrade = commandDescriptions[1];
    }
    
    @Override
    public CommandResult executeCommand() throws KolinuxException {
        moduleList.setModuleGrade(moduleCode, moduleGrade);
        String message = "Grade of " + moduleCode + " is " + moduleGrade;
        return new CommandResult(message);
    }
}
