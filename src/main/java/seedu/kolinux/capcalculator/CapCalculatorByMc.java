package seedu.kolinux.capcalculator;

/**
 * Represents CAP calculator used when the user's input module descriptions are based on modular credit.
 */
public class CapCalculatorByMc extends CapCalculator {
    
    private final int MODULE_CREDIT_POSITION = 0;

    /**
     * Construct the superclass of this object.
     * 
     * @param input Command input from user which contains the module descriptions.
     */
    public CapCalculatorByMc(String input) {
        super(input);
    }

    @Override
    protected int getMc(String module) {
        String[] moduleDescriptions = module.split("/");
        try {
            return Integer.parseInt(String.valueOf(moduleDescriptions[MODULE_CREDIT_POSITION]));
        } catch (NumberFormatException exception) {
            invalidModules.add(module);
            return INVALID_MC;
        }
    }
}
