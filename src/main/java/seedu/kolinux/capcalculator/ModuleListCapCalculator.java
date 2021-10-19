package seedu.kolinux.capcalculator;

import seedu.kolinux.commands.CommandResult;
import seedu.kolinux.exceptions.KolinuxException;
import seedu.kolinux.module.ModuleDetails;
import seedu.kolinux.module.ModuleList;

public class ModuleListCapCalculator extends CapCalculatorByCode {
    
    public static final String UNAVALIABLE_GRADE = "0";
    
    /*private String moduleDescriptionList;
    
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
    }*/
    
    public ModuleListCapCalculator(String input) {
        super(input);
    }

    @Override
    protected void checkInvalidModules() throws KolinuxException {
        if (invalidModules.isEmpty()) {
            return;
        }
        StringBuilder errorMessage = new StringBuilder("Invalid module info found: ");
        int invalidModuleCount = 0;
        for (String module : invalidModules) {
            String[] moduleDescriptions = module.split("/");
            if (!moduleDescriptions[1].equals(UNAVALIABLE_GRADE)) {
                errorMessage.append(module).append(" ");
                invalidModuleCount++;
            }
        }
        if (invalidModuleCount > 0) {
            throw new KolinuxException(errorMessage.toString());
        }
    }
}
