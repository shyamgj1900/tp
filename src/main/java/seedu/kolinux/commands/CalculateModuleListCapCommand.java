package seedu.kolinux.commands;

import seedu.kolinux.capcalculator.CapCalculatorByCode;
import seedu.kolinux.capcalculator.GradeSuggestionCalculator;
import seedu.kolinux.capcalculator.ModuleListCapCalculator;
import seedu.kolinux.exceptions.KolinuxException;
import seedu.kolinux.module.ModuleDetails;

import java.util.logging.Level;

public class CalculateModuleListCapCommand extends Command {
    
    private static final String UNAVAILABLE_GRADE = "0";
    
    private CapCalculatorByCode calculator;
    private String moduleDescriptionList;

    private boolean isNumeric(String input) {
        try {
            Double.parseDouble(input);
        } catch (NumberFormatException exception) {
            return false;
        }
        return true;
    }
    
    public CalculateModuleListCapCommand(String[] commandDescriptions) {
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
        if (isNumeric(commandDescriptions[0])) {
            this.calculator = new GradeSuggestionCalculator(moduleDescriptionList, commandDescriptions[0]);
        } else {
            this.calculator = new ModuleListCapCalculator(moduleDescriptionList);
        } 
    }
    
    @Override
    public CommandResult executeCommand() throws KolinuxException {
        // if isNumeric
        String cap = calculator.executeCapCalculator();
        String capMessage = "Based on your available grade, your cap for this semester is " + cap;
        logger.log(Level.INFO, "CAP is calculated from module list");
        
        // else isString (grade letter)
        
        
        return new CommandResult(capMessage);
    }
}
