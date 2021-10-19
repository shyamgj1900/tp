package seedu.kolinux.commands;

import seedu.kolinux.capcalculator.ModuleListCapCalculator;
import seedu.kolinux.exceptions.KolinuxException;
import seedu.kolinux.module.ModuleDetails;

import java.util.logging.Level;

public class CalculateModuleListCapCommand extends Command {
    
    private static final String UNAVAILABLE_GRADE = "0";
    
    private ModuleListCapCalculator calculator;
    private String moduleDescriptionList;
    
    public CalculateModuleListCapCommand() {
        moduleDescriptionList = "cap code";
        for (ModuleDetails module : moduleList.getMyModules()) {
            String moduleCode = module.getModuleCode();
            String moduleGrade = module.getGrade();
            if (moduleGrade == null) {
                moduleGrade = UNAVAILABLE_GRADE;
            }
            String moduleDescription = moduleCode + "/" + moduleGrade;
            moduleDescriptionList += " " + moduleDescription;
        }
        this.calculator = new ModuleListCapCalculator(moduleDescriptionList);
    }
    
    @Override
    public CommandResult executeCommand() throws KolinuxException {
        String cap = calculator.executeCapCalculator();
        String capMessage = "Based on your available grade, your cap for this semester is " + cap;
        logger.log(Level.INFO, "CAP is calculated from module list");
        return new CommandResult(capMessage);
    }
}
