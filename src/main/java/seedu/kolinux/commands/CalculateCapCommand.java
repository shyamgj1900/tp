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

    /**
     * Constructs this object and initializes a calculator based on the input keyword.
     * 
     * @param subCommand The type of this calculator object.
     * @param parsedArguments Array of module descriptions from user.
     * @throws KolinuxException when an invalid input format is provided.
     */
    public CalculateCapCommand(String subCommand, String[] parsedArguments) throws KolinuxException {
        if (subCommand.equals("")) {
            String errorMessage = "Please indicate your module description type\n"
                    + 
                    "1. cap mc\n" 
                    +
                    "2. cap code";
            throw new KolinuxException(errorMessage);
        }
        switch (subCommand) {
        case "mc":
            calculator = new CapCalculatorByMc(parsedArguments);
            logger.log(Level.INFO, "User calculate CAP using modular credit");
            break;
        case "code":
            calculator = new CapCalculatorByCode(parsedArguments);
            logger.log(Level.INFO, "User calculate CAP using module code");
            break;
        default:
            String errorMessage = "Invalid module description type found, "
                    + "please use either \"mc\" or \"code\" as module descriptions keyword";
            throw new KolinuxException(errorMessage);
        }
    }
    
    @Override
    public CommandResult executeCommand() throws KolinuxException {
        String cap = calculator.executeCapCalculator();
        String capMessage = "Your overall CAP will be " + cap + " if you get your desired grades!";
        logger.log(Level.INFO, "CAP is calculated from user's input");
        return new CommandResult(capMessage);
    }
}
