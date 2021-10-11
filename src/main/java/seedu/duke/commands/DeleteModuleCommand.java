package seedu.duke.commands;

import seedu.duke.module.ModuleList;

public class DeleteModuleCommand extends Command {
    private String code;

    public DeleteModuleCommand(String argument) {
        code = argument;
    }

    @Override
    public CommandResult executeCommand() {
        String message = ModuleList.deleteModuleByCode(code);
        return new CommandResult(message);
    }
}
