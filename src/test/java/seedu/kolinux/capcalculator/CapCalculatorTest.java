package seedu.kolinux.capcalculator;

import org.junit.jupiter.api.Test;
import seedu.kolinux.exceptions.KolinuxException;
import seedu.kolinux.module.ModuleDb;
import seedu.kolinux.module.ModuleList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CapCalculatorTest {

    private static final String[] VALID_INPUTS_BY_MC = {"4/A+", "6/A-", "2/C", "4/U", "3/D+"};
    private static final  String[] VALID_INPUTS_BY_CODE = {"CG2027/A+", "CS2102/S", "ST2334/C", "CS1231/F"};
    private static final String[] INVALID_INPUT_NO_DESCRIPTIONS = {""};
    private static final String[] INVALID_INPUT_MODULE_DESCRIPTIONS = {
            "CS1231/A", "ABCDE", "CG2027/Z", "CS0000/B", "12345"};
    
    private static final String MODULE_1 = "CS2113T";
    private static final String MODULE_2 = "CS2101";
    
    private static final String VALID_OUTPUT_FROM_MC = "3.70";
    private static final String VALID_OUTPUT_FROM_CODE = "1.80";
    private static final String VALID_OUTPUT_FROM_STORED_MODULES = "5.00";
    private static final String VALID_OUTPUT_SUGGEST_GRADE = "B-";
    private static final String BLANK_DESCRIPTION_EXCEPTION_MESSAGE = 
            "Please enter valid module description. Example: CG2027/A+";
    private static final String INVALID_MODULES_EXCEPTION_MESSAGE = 
            "Invalid module info format found: ABCDE CG2027/Z CS0000/B 12345 ";
    private static final String GRADES_NOT_AVAILABLE_MESSAGE = "There is no module with available grade at the moment";
    private static final String CAP_UNACHIEVABLE_MESSAGE = "UNACHIEVABLE";
    private static final String INVALID_CAP_MESSAGE = "CAP cannot exceed 5.0";

    @Test
    public void executeCapCalculator_validInputByMc_capCalculated() throws KolinuxException {
        CapCalculator calculator = new CapCalculatorByMc(VALID_INPUTS_BY_MC);
        assertEquals(VALID_OUTPUT_FROM_MC, calculator.executeCapCalculator());
    }
    
    @Test
    public void executeCapCalculator_validInputByCode_capCalculated() throws KolinuxException {
        CapCalculator calculator = new CapCalculatorByCode(VALID_INPUTS_BY_CODE);
        assertEquals(VALID_OUTPUT_FROM_CODE, calculator.executeCapCalculator());
    }

    @Test
    public void executeCapCalculator_storedModulesHaveGrades_capCalculated() throws KolinuxException {
        ModuleList moduleList = new ModuleList();
        ModuleDb moduleDb = new ModuleDb();
        moduleDb.initModuleDb();

        moduleList.addModuleByCode(MODULE_1, moduleDb);
        moduleList.addModuleByCode(MODULE_2, moduleDb);
        moduleList.setModuleGrade(MODULE_1, "A");
        CapCalculator calculator = new ModuleListCapCalculator(moduleList);
        assertEquals(VALID_OUTPUT_FROM_STORED_MODULES, calculator.executeCapCalculator());
    }

    @Test
    public void executeCapCalculator_validDesiredCap_suggestedGradeShown() throws KolinuxException {
        ModuleList moduleList = new ModuleList();
        ModuleDb moduleDb = new ModuleDb();
        moduleDb.initModuleDb();

        moduleList.addModuleByCode(MODULE_1, moduleDb);
        moduleList.addModuleByCode(MODULE_2, moduleDb);
        moduleList.setModuleGrade(MODULE_1, "A");
        CapCalculator calculator = new GradeSuggestionCalculator(moduleList, "4.0");
        assertEquals(VALID_OUTPUT_SUGGEST_GRADE, calculator.executeCapCalculator());
    }
    
    @Test
    public void executeCapCalculator_noDescriptionsProvided_showNoDescriptionMessage() {
        try {
            CapCalculator calculator = new CapCalculatorByCode(INVALID_INPUT_NO_DESCRIPTIONS);
            calculator.executeCapCalculator();
        } catch (KolinuxException exception) {
            assertEquals(BLANK_DESCRIPTION_EXCEPTION_MESSAGE, exception.getMessage());
        }
    }

    @Test
    public void executeCapCalculator_invalidModuleDescriptionsFound_showInvalidModules() {
        try {
            CapCalculator calculator = new CapCalculatorByCode(INVALID_INPUT_MODULE_DESCRIPTIONS);
            calculator.executeCapCalculator();
        } catch (KolinuxException exception) {
            assertEquals(INVALID_MODULES_EXCEPTION_MESSAGE, exception.getMessage());
        }
    }
    
    @Test
    public void executeCapCalculator_noModuleGradeStored_showGradeNotAvailableMessage() {
        try {
            ModuleList moduleList = new ModuleList();
            ModuleDb moduleDb = new ModuleDb();
            moduleDb.initModuleDb();

            moduleList.addModuleByCode(MODULE_1, moduleDb);
            CapCalculator calculator = new ModuleListCapCalculator(moduleList);
            calculator.executeCapCalculator();
        } catch (KolinuxException exception) {
            assertEquals(GRADES_NOT_AVAILABLE_MESSAGE, exception.getMessage());
        }
    }
    
    @Test
    public void executeCapCalculator_capUnachievable_showCapUnachievableMessage() throws KolinuxException {
        ModuleList moduleList = new ModuleList();
        ModuleDb moduleDb = new ModuleDb();
        moduleDb.initModuleDb();

        moduleList.addModuleByCode(MODULE_1, moduleDb);
        moduleList.addModuleByCode(MODULE_2, moduleDb);
        moduleList.setModuleGrade(MODULE_1, "C");
        CapCalculator calculator = new GradeSuggestionCalculator(moduleList, "5.0");
        assertEquals(CAP_UNACHIEVABLE_MESSAGE, calculator.executeCapCalculator());
    }
    
    @Test
    public void executeCapCalculator_invalidCapProvided_showInvalidCapMessage() {
        try {
            ModuleList moduleList = new ModuleList();
            ModuleDb moduleDb = new ModuleDb();
            moduleDb.initModuleDb();

            moduleList.addModuleByCode(MODULE_1, moduleDb);
            moduleList.addModuleByCode(MODULE_2, moduleDb);
            moduleList.setModuleGrade(MODULE_1, "C");
            CapCalculator calculator = new GradeSuggestionCalculator(moduleList, "20.0");
            calculator.executeCapCalculator();
        } catch (KolinuxException exception) {
            assertEquals(INVALID_CAP_MESSAGE, exception.getMessage());
        }
    }
}