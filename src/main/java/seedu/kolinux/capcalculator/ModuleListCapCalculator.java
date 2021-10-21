package seedu.kolinux.capcalculator;

import seedu.kolinux.exceptions.KolinuxException;
import seedu.kolinux.module.ModuleDetails;
import seedu.kolinux.module.ModuleList;

/**
 * Represents CAP calculator used for calculation from stored modules in module list.
 */
public class ModuleListCapCalculator extends CapCalculatorByCode {

    private static final String UNAVAILABLE_GRADE = "0";
    
    public ModuleListCapCalculator(ModuleList modules) {
        super(modules);
    }

    /**
     * Check if all the stored modules do not have available grade.
     * 
     * @throws KolinuxException if there is no module with available grade stored.
     */
    private void checkAllModulesNotAvailable() throws KolinuxException {
        for (ModuleDetails module : modules.getMyModules()) {
            if (!module.getGrade().equals(UNAVAILABLE_GRADE)) {
                return;
            }
        }
        String errorMessage = "There is no module with available grade at the moment";
        throw new KolinuxException(errorMessage);
    }

    @Override
    public String executeCapCalculator() throws KolinuxException {
        checkModulesNotEmpty();
        checkAllModulesNotAvailable();
        return getCap();
    }
}
