package seedu.kolinux.commands;

import org.junit.jupiter.api.Test;
import seedu.kolinux.exceptions.KolinuxException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculateCapCommandTest {
    
    private String validInputByMc = "cap mc 4/A 4/B 4/C";
    private String validInputByCode = "cap code CS1010/C MA1511/B+ CS1231/S MA1508E/A";
    private String invalidCalculatorTypeInput = "cap invalid_type 4/A 4/B";
    private String blankCalculatorTypeInput = "cap";
    
    private String validOutputFromMc = "Your CAP for this semester will be 3.50 if you get your desired grades!";
    private String validOutputFromCode = "Your CAP for this semester will be 3.60 if you get your desired grades!";
    private String invalidCalculatorTypeMessage = "Invalid module description type found, "
            + "please use either \"mc\" or \"code\" as module descriptions keyword";
    private String blankCalculatorTypeMessage = "Please indicate your module description type";

    @Test
    public void executeCommand_validInputByMc_capCalculated() throws KolinuxException {
        CalculateCapCommand command = new CalculateCapCommand(validInputByMc);
        assertEquals(validOutputFromMc, command.executeCommand().getFeedbackToUser());
    }
    
    @Test
    public void executeCapCommand_validInputByCode_capCalculated() throws KolinuxException {
        CalculateCapCommand command = new CalculateCapCommand(validInputByCode);
        assertEquals(validOutputFromCode, command.executeCommand().getFeedbackToUser());
    }
    
    @Test
    public void testConstructor_invalidCalculatorType_showErrorMessage() {
        try {
            CalculateCapCommand command = new CalculateCapCommand(invalidCalculatorTypeInput);
        } catch (KolinuxException exception) {
            assertEquals(invalidCalculatorTypeMessage, exception.getMessage());
        }
    }
    
    @Test
    public void testConstructor_noCalculatorTypeProvided_showErrorMessage() {
        try {
            CalculateCapCommand command = new CalculateCapCommand(blankCalculatorTypeInput);
        } catch (KolinuxException exception) {
            assertEquals(blankCalculatorTypeMessage, exception.getMessage());
        }
    }
}
