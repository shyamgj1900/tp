package seedu.kolinux.capcalculator;

import seedu.kolinux.exceptions.KolinuxException;

/**
 * Represents CAP calculator used for calculation from stored modules in module list.
 */
public class ModuleListCapCalculator extends CapCalculatorByCode {

    private static final String UNAVAILABLE_GRADE = "0";

    /**
     * Construct the superclass of this object.
     * 
     * @param input Formatted description of modules and the respective grades from module list.
     */
    public ModuleListCapCalculator(String input) {
        super(input);
    }

    /**
     * Check if all the stored modules do not have available grade.
     * 
     * @throws KolinuxException if there is no module with available grade stored.
     */
    private void checkAllModulesNotAvailable() throws KolinuxException {
        for (String module : modules) {
            String[] moduleDescriptions = module.split("/");
            if (!moduleDescriptions[1].equals(UNAVAILABLE_GRADE)) {
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
