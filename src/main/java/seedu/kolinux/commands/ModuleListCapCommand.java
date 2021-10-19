package seedu.kolinux.commands;

import seedu.kolinux.capcalculator.CapCalculatorByCode;
import seedu.kolinux.capcalculator.GradeSuggestionCalculator;
import seedu.kolinux.capcalculator.ModuleListCapCalculator;
import seedu.kolinux.exceptions.KolinuxException;
import seedu.kolinux.module.ModuleDetails;

/**
 * Represents the command that calculate CAP from stored modules.
 */
import java.util.logging.Level;

public class ModuleListCapCommand extends Command {
    
    private static final String UNAVAILABLE_GRADE = "0";
    
    private CapCalculatorByCode calculator;
    private String moduleDescriptionList;

    /**
     * Check if a string is numeric or not.
     * 
     * @param input The string being checked.
     * @return true if the string can be converted to numerical value, false otherwise.
     */
    private boolean isNumeric(String input) {
        try {
            Double.parseDouble(input);
        } catch (NumberFormatException exception) {
            return false;
        }
        return true;
    }

    /**
     * Constructor of this object. Format the modules and grades stored in module list and pass it to the
     * respective calculator.
     * 
     * @param commandDescriptions Command input from user which is used to determine the calculator type.
     */
    public ModuleListCapCommand(String[] commandDescriptions) {
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
        String result = calculator.executeCapCalculator();
        String message;
        if (calculator instanceof GradeSuggestionCalculator) {
            if (result.equals("UNACHIEVABLE")) {
                message = "It is impossible to achieve your desired CAP with the current modules";
            } else {
                message = "Based on your modules, you have to get an average grade of " + result 
                        + "\nor higher in order to achieve your desired CAP";
            }
            logger.log(Level.INFO, "Suggested grade is calculated from module list");
        } else {
            message = "Based on your available grade, your cap for this semester is " + result;
            logger.log(Level.INFO, "CAP is calculated from module list");
        }
        return new CommandResult(message);
    }
}
