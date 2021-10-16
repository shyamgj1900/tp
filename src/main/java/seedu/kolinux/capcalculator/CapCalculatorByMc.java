package seedu.kolinux.capcalculator;

import seedu.kolinux.exceptions.KolinuxException;

public class CapCalculatorByMc extends CapCalculator {
    
    public CapCalculatorByMc(String input) {
        super(input);
    }
    
    @Override
    protected int getMc(String module) throws KolinuxException {
        String[] moduleDescriptions = module.split("/");
        try {
            return Integer.parseInt(String.valueOf(moduleDescriptions[0]));
        } catch (NumberFormatException exception) {
            /*String errorMessage = "Invalid module info found: " + module;
            throw new KolinuxException(errorMessage);*/
            invalidModules.add(module);
            return -1;
        }
    }
}
