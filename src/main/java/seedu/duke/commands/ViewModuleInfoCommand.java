package seedu.duke.commands;

import seedu.duke.module.ModuleDb;
import seedu.duke.module.ModuleDetails;

public class ViewModuleInfoCommand extends Command {
    private ModuleDetails module;

    public ViewModuleInfoCommand(ModuleDb db, String moduleCode) {
        module = db.getModuleInfo(moduleCode);
    }

    @Override
    public CommandResult executeCommand() {
        if (module == null) {
            return new CommandResult("Please enter a valid module code");
        } else {
            return new CommandResult(module.toString());
        }

    }
}