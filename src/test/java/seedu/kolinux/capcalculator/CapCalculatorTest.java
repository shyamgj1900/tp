package seedu.kolinux.capcalculator;

import org.junit.jupiter.api.Test;
import seedu.kolinux.exceptions.KolinuxException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CapCalculatorTest {
    
    private String validInputByMc = "cap mc 4/A+ 6/A- 2/C 4/U 3/D+";
    private String validInputByCode = "cap code CG2027/A+ CS2102/S ST2334/C CS1231/F";
    private String invalidInputNoDescripitons = "cap";
    private String invalidInputModuleDescriptions = "cap code CS1231/A ABCDE CG2027/Z CS0000/B 12345";
    
    private String validOutputFromMc = "3.70";
    private String validOutputFromCode = "1.80";
    private String blankDescriptionExceptionMessage = "Please enter valid module description. Example: CG2027/A+";
    private String invalidModulesExceptionMessage = "Invalid module info found: ABCDE CG2027/Z CS0000/B 12345 ";

    @Test
    public void executeCapCalculator_validInputByMc_capCalculated() throws KolinuxException {
        CapCalculator calculator = new CapCalculatorByMc(validInputByMc);
        assertEquals(validOutputFromMc, calculator.executeCapCalculator());
    }
    
    @Test
    public void executeCapCalculator_validInputByCode_capCalculated() throws KolinuxException {
        CapCalculator calculator = new CapCalculatorByCode(validInputByCode);
        assertEquals(validOutputFromCode, calculator.executeCapCalculator());
    }
    
    @Test
    public void executeCapCalculator_noDescriptionsProvided_showNoDescriptionMessage() {
        try {
            CapCalculator calculator = new CapCalculatorByCode(invalidInputNoDescripitons);
            calculator.executeCapCalculator();
        } catch (KolinuxException exception) {
            assertEquals(blankDescriptionExceptionMessage, exception.getMessage());
        }
    }
    
    @Test
    public void executeCapCalculator_invalidModuleDescriptionsFound_showInvalidModules() {
        try {
            CapCalculator calculator = new CapCalculatorByCode(invalidInputModuleDescriptions);
            calculator.executeCapCalculator();
        } catch (KolinuxException exception) {
            assertEquals(invalidModulesExceptionMessage, exception.getMessage());
        }
    }
}