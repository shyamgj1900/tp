package seedu.kolinux.capcalculator;

import seedu.kolinux.commands.CommandResult;
import seedu.kolinux.exceptions.KolinuxException;
import seedu.kolinux.module.ModuleDetails;
import seedu.kolinux.module.ModuleList;

public class ModuleListCapCalculator {
    
    private String moduleDescriptionList;
    
    public ModuleListCapCalculator(ModuleList moduleList) {
        moduleDescriptionList = "cap code";
        for (ModuleDetails module : moduleList.getMyModules()) {
            String moduleDescription = module.getModuleCode() + "/" + module.getGrade();
            moduleDescriptionList += " " + moduleDescription;
        }
    }
    
    public CommandResult executeCapCalculator() throws KolinuxException {
        CapCalculator calculator = new CapCalculatorByCode(moduleDescriptionList);
        String cap = calculator.executeCapCalculator();
        return new CommandResult(cap);
    }
}
