package seedu.kolinux.capcalculator;

import seedu.kolinux.exceptions.KolinuxException;
import seedu.kolinux.module.ModuleDetails;

/**
 * Represents CAP calculator used when the user's input module descriptions are based on modular credit.
 */
public class CapCalculatorByMc extends CapCalculator {
    
    private static final int MODULE_CREDIT_POSITION = 0;

    /**
     * Construct the superclass of this object.
     * 
     * @param input Command input from user which contains the module descriptions.
     */
    public CapCalculatorByMc(String input) {
        super();
        String[] commandDescriptions = input.split(" ");
        if (commandDescriptions.length <= 2) {
            return;
        }
        int moduleCount = commandDescriptions.length - 2;
        for (int i = 0; i < moduleCount; i++) {
            try {
                String[] moduleDescriptions = commandDescriptions[i + 2].split("/");
                int moduleCredit = Integer.parseInt(moduleDescriptions[0]);
                String grade = moduleDescriptions[1];
                modules.storeModuleMcGrade(moduleCredit, grade);
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException exception) {
                invalidModules.add(commandDescriptions[i + 2]);
            }
        }
    }

    @Override
    protected int getMc(ModuleDetails module) {
        /*String[] moduleDescriptions = module.split("/");
        try {
            return Integer.parseInt(String.valueOf(moduleDescriptions[MODULE_CREDIT_POSITION]));
        } catch (NumberFormatException exception) {
            invalidModules.add(module);
            return INVALID_MC;
        }*/
        return Integer.parseInt(module.getModuleCredit());
    }

    protected String getCap() {
        int totalMc = 0;
        double cap = 0;
        for (ModuleDetails module : modules.getMyModules()) {
            if (module.containsSuGrade()) {
                continue;
            }
            int mc = getMc(module);
            double gradePoint = module.getGradePoint();
            if (gradePoint == INVALID_GRADE || mc < 1) {
                invalidModules.add(module.getModuleCredit() + "/" + module.getGrade());
                continue;
            }
            cap = calculateCurrentCap(totalMc, cap, mc, gradePoint);
            totalMc += mc;
            assert cap <= MAX_CAP;
        }
        return String.format(TWO_DECIMAL_FORMAT, cap);
    }
}
