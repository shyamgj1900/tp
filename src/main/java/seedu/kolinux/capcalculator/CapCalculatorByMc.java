package seedu.kolinux.capcalculator;

public class CapCalculatorByMc extends CapCalculator {

    public CapCalculatorByMc(String input) {
        super(input);
    }

    @Override
    protected int getMc(String module) {
        String[] moduleDescriptions = module.split("/");
        try {
            return Integer.parseInt(String.valueOf(moduleDescriptions[0]));
        } catch (NumberFormatException exception) {
            invalidModules.add(module);
            return -1;
        }
    }
}
