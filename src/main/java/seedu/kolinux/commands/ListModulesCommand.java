package seedu.kolinux.commands;

import java.util.logging.Level;

public class ListModulesCommand extends Command {

    @Override
    public CommandResult executeCommand() {
        moduleList.listMyModules();
        logger.log(Level.INFO, "User queried list of all stored modules");
        return new CommandResult("");
    }
}
