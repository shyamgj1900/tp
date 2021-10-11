package seedu.duke.commands;

import seedu.duke.module.ModuleList;

import java.util.logging.Level;

/**
 * Represents the command that stores a module in the myModules list.
 */
public class StoreModuleCommand extends Command {
    private String code;

    public StoreModuleCommand(String argument) {
        code = argument;
    }

    @Override
    public CommandResult executeCommand() {
        String message = ModuleList.storeModuleByCode(code);
        logger.log(Level.INFO, "User stored module" + code + " in myModules list");
        return new CommandResult(message);
    }
}
