package seedu.duke.commands;

import seedu.duke.module.ModuleList;

import java.util.logging.Level;

public class DeleteModuleCommand extends Command {
    private String code;

    public DeleteModuleCommand(String argument) {
        code = argument;
    }

    @Override
    public CommandResult executeCommand() {
        String message = ModuleList.deleteModuleByCode(code);
        logger.log(Level.INFO, "User deleted module" + code + " from myModules list");
        return new CommandResult(message);
    }
}
