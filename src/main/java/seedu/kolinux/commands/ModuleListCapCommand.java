package seedu.kolinux.commands;

import seedu.kolinux.capcalculator.CapCalculatorByCode;
import seedu.kolinux.capcalculator.GradeSuggestionCalculator;
import seedu.kolinux.capcalculator.ModuleListCapCalculator;
import seedu.kolinux.exceptions.KolinuxException;
import seedu.kolinux.module.ModuleDetails;

import java.util.logging.Level;

/**
 * Represents the command that calculate CAP from stored modules.
 */
public class ModuleListCapCommand extends Command {
    
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
        /*moduleDescriptionList = "cap code";
        for (ModuleDetails module : moduleList.getMyModules()) {
            String moduleCode = module.getModuleCode();
            String moduleGrade = module.getGrade();
            String moduleDescription = moduleCode + "/" + moduleGrade;
            moduleDescriptionList += " " + moduleDescription;
        }*/
        if (!commandDescriptions[0].equals("")) {
            this.calculator = new GradeSuggestionCalculator(moduleList, commandDescriptions[0]);
        } else {
            this.calculator = new ModuleListCapCalculator(moduleList);
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
