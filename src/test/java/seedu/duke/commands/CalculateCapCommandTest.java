package seedu.duke.commands;

import org.junit.jupiter.api.Test;
import seedu.duke.exceptions.KolinuxException;

import static org.junit.jupiter.api.Assertions.*;

class CalculateCapCommandTest {
    
    private final String VALID_CAP_INPUT = "cap 4A+ 4B- 6A- 4F 2C";
    private final String INVALID_BLANK_INPUT = "cap";
    private final String INVALID_CAP_INPUT = "cap 4A 4B 44C";
    
    private final String VALID_CAP_OUTPUT = "Your CAP for this semester will be 3.15 if you get your desired grades!";
    private final String INVALID_BLANK_OUTPUT = "Please enter module credits and grades in the command (eg. 4A+)";
    private final String INVALID_CAP_OUTPUT = "Invalid module info found: 44C";

    @Test
    public void executeCommand_validInput_capCalculated() throws KolinuxException {
        CalculateCapCommand command = new CalculateCapCommand(VALID_CAP_INPUT);
        assertEquals(VALID_CAP_OUTPUT,command.executeCommand().getFeedbackToUser());
    }
    
    @Test
    public void executeCommand_invalidInput_blankDescription() {
        try {
            CalculateCapCommand command = new CalculateCapCommand(INVALID_BLANK_INPUT);
            command.executeCommand();
        } catch (KolinuxException e) {
            assertEquals(INVALID_BLANK_OUTPUT, e.getMessage());
        }
    }
    
    @Test
    public void executeCommand_invalidInput_invalidCap() {
        try {
            CalculateCapCommand command = new CalculateCapCommand(INVALID_CAP_INPUT);
            command.executeCommand();
        } catch (KolinuxException e) {
            assertEquals(INVALID_CAP_OUTPUT, e.getMessage());
        }
    }
}
