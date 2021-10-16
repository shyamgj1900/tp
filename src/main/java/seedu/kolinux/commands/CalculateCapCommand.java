package seedu.kolinux.commands;

import seedu.kolinux.capcalculator.CapCalculator;
import seedu.kolinux.capcalculator.CapCalculatorByCode;
import seedu.kolinux.capcalculator.CapCalculatorByMc;
import seedu.kolinux.exceptions.KolinuxException;

import java.util.logging.Level;

/**
 * Represents the command that calculate CAP from user input.
 */
public class CalculateCapCommand extends Command {
    
    private CapCalculator calculator;
    
    /*private boolean isNumeric(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException exception) {
            return false;
        }
    }
    
    private String getModuleInfoFormat(String moduleInfo) {
        String[] moduleDescriptions = moduleInfo.split("/");
        if (isNumeric(moduleDescriptions[0])) {
            return "mc";
        } else {
            return "code";
        }
    }*/
    
    private String getModuleInfoFormat(String input) throws KolinuxException {
        String[] commandDescriptions = input.split(" ");
        if (commandDescriptions.length == 1) {
            throw new KolinuxException("NO MODULE INFO TYPE SPECIFIED");
        }
        String moduleInfoType = commandDescriptions[1];
        switch (moduleInfoType) {
        case "mc":
            return "mc";
        case "code":
            return "code";
        default:
            throw new KolinuxException("INVALID MODULE INFO TYPE");
        }
    }
    
    /**
     * Constructs this object and initializes array to store module information.
     * 
     * @param input Command input from user which contains modular credits and grades.
     */
    public CalculateCapCommand(String input) throws KolinuxException {
        /*String[] commandDescriptions = input.split(" ");
        if (commandDescriptions.length == 1) {
            String errorMessage = "Please enter module credits and grades in the command (eg. 4A+)";
            throw new KolinuxException(errorMessage);
        }
        // need better format checker
        String moduleInfoFormat = getModuleInfoFormat(commandDescriptions[1]);*/
        String moduleInfoFormat = getModuleInfoFormat(input);
        switch (moduleInfoFormat) {
        case "mc":
            calculator = new CapCalculatorByMc(input);
            break;
        case "code":
            calculator = new CapCalculatorByCode(input);
            break;
        default:
            String errorMessage = "Invalid module info found";
            throw new KolinuxException(errorMessage);
        }
    }
    
    @Override
    public CommandResult executeCommand() throws KolinuxException {
        String cap = calculator.executeCapCalculator();
        String capMessage = "Your CAP for this semester will be " + cap + " if you get your desired grades!";
        logger.log(Level.INFO, "User calculated CAP");
        return new CommandResult(capMessage);
    }
}
