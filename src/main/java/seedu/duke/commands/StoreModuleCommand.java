package seedu.duke.commands;

import seedu.duke.module.ModuleList;

public class StoreModuleCommand extends Command {
    private String code;

    public StoreModuleCommand(String argument) {
        code = argument;
    }

    @Override
    public CommandResult executeCommand() {
        String message = ModuleList.storeModuleByCode(code);
        return new CommandResult(message);
    }
}
