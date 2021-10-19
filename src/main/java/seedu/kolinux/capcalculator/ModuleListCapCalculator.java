package seedu.kolinux.capcalculator;

import seedu.kolinux.exceptions.KolinuxException;

public class ModuleListCapCalculator extends CapCalculatorByCode {

    private static final String UNAVAILABLE_GRADE = "0";
    
    public ModuleListCapCalculator(String input) {
        super(input);
    }
    
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

    public String executeCapCalculator() throws KolinuxException {
        checkModulesNotEmpty();
        checkAllModulesNotAvailable();
        return getCap();
    }
}
