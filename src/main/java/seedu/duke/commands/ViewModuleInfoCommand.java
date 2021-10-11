package seedu.duke.commands;

import seedu.duke.module.ModuleDb;
import seedu.duke.module.ModuleDetails;

import java.util.logging.Level;

/**
 * Represents the command that displays a module's information.
 */
public class ViewModuleInfoCommand extends Command {
    private ModuleDetails module;

    public ViewModuleInfoCommand(String moduleCode) {
        module = ModuleDb.getModuleInfo(moduleCode);
    }

    @Override
    public CommandResult executeCommand() {
        if (module == null) {
            logger.log(Level.INFO, "User queried invalid module code from ModuleDbf for viewing");
            return new CommandResult("Please enter a valid module code");
        } else {
            logger.log(Level.INFO, "User queried valid module code from ModuleDb for viewing");
            return new CommandResult(module.toString());
        }

    }
}