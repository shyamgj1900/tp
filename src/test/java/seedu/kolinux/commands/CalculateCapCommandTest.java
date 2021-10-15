package seedu.kolinux.commands;

import org.junit.jupiter.api.Test;
import seedu.kolinux.exceptions.KolinuxException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CalculateCapCommandTest {
    
    private String validCapInput = "cap 4/A+ 4/B- 6/A- 4/F 2/C";
    private String invalidBlankInput = "cap";
    private String invalidCapInput = "cap 4/A 4/B 44/C";
    
    private String validCapOutput = "Your CAP for this semester will be 3.15 if you get your desired grades!";
    private String invalidBlankOutput = "Please enter module credits and grades in the command (eg. 4A+)";
    private String invalidCapOutput = "Invalid module info found: 44/C";

    @Test
    public void executeCommand_validInput_capCalculated() throws KolinuxException {
        CalculateCapCommand command = new CalculateCapCommand(validCapInput);
        assertEquals(validCapOutput,command.executeCommand().getFeedbackToUser());
    }
    
    @Test
    public void executeCommand_invalidInput_blankDescription() {
        try {
            CalculateCapCommand command = new CalculateCapCommand(invalidBlankInput);
            command.executeCommand();
        } catch (KolinuxException e) {
            assertEquals(invalidBlankOutput, e.getMessage());
        }
    }
    
    @Test
    public void executeCommand_invalidInput_invalidCap() {
        try {
            CalculateCapCommand command = new CalculateCapCommand(invalidCapInput);
            command.executeCommand();
        } catch (KolinuxException e) {
            assertEquals(invalidCapOutput, e.getMessage());
        }
    }
}
