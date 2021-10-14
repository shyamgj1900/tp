package seedu.kolinux.commands;

import seedu.kolinux.module.ModuleList;

import java.util.logging.Level;

/**
 * Represents the command that deletes a module from the myModules list.
 */
public class DeleteModuleCommand extends Command {
    private String code;

    public DeleteModuleCommand(String argument) {
        code = argument;
    }

    @Override
    public CommandResult executeCommand() {
        String message = ModuleList.deleteModuleByCode(code);
        logger.log(Level.INFO, "User deleted module " + code + " from myModules list");
        return new CommandResult(message);
    }
}
