package seedu.kolinux.capcalculator;

import seedu.kolinux.exceptions.KolinuxException;

public class ModuleListCapCalculator extends CapCalculatorByCode {
    
    public static final String UNAVALIABLE_GRADE = "0";
    
    public ModuleListCapCalculator(String input) {
        super(input);
    }

    @Override
    protected void checkInvalidModules() throws KolinuxException {
        if (invalidModules.isEmpty()) {
            return;
        }
        StringBuilder errorMessage = new StringBuilder("Invalid module info found: ");
        int invalidModuleCount = 0;
        for (String module : invalidModules) {
            String[] moduleDescriptions = module.split("/");
            if (!moduleDescriptions[1].equals(UNAVALIABLE_GRADE)) {
                errorMessage.append(module).append(" ");
                invalidModuleCount++;
            }
        }
        if (invalidModuleCount > 0) {
            throw new KolinuxException(errorMessage.toString());
        }
    }
}
