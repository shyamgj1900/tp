package seedu.kolinux.capcalculator;

import seedu.kolinux.module.ModuleDetails;

/**
 * Represents CAP calculator used when the user's input module descriptions are based on modular credit.
 */
public class CapCalculatorByMc extends CapCalculator {

    private void getInputModules(String input) {
        String[] commandDescriptions = input.split(" ");
        if (commandDescriptions.length <= 2) {
            return;
        }
        int moduleCount = commandDescriptions.length - 2;
        for (int i = 0; i < moduleCount; i++) {
            try {
                String inputModule = commandDescriptions[i + 2].toUpperCase();
                String[] moduleDescriptions = inputModule.split(DIVIDER);
                double moduleCredit = Double.parseDouble(moduleDescriptions[0]);
                if (moduleCredit != (int) moduleCredit) {
                    invalidModules.add(commandDescriptions[i + 2]);
                    continue;
                }
                int mc = (int) moduleCredit;
                String grade = moduleDescriptions[1];
                modules.storeModuleMcGrade(mc, grade);
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException exception) {
                invalidModules.add(commandDescriptions[i + 2]);
            }
        }
    }
    
    /**
     * Construct the superclass of this object, then store the module details from user input.
     * 
     * @param input Command input from user which contains the modular credits and their respective grade.
     */
    public CapCalculatorByMc(String input) {
        super();
        getInputModules(input);
    }

    @Override
    protected String getCap() {
        int totalMc = 0;
        double cap = 0;
        for (ModuleDetails module : modules.getMyModules()) {
            if (module.containsSuGrade()) {
                continue;
            }
            int mc = Integer.parseInt(module.getModuleCredit());
            double gradePoint = module.getGradePoint();
            if (gradePoint == INVALID_GRADE || mc < 1) {
                invalidModules.add(module.getModuleCredit() + DIVIDER + module.getGrade());
                continue;
            }
            cap = calculateCurrentCap(totalMc, cap, mc, gradePoint);
            totalMc += mc;
            assert cap <= MAX_CAP;
        }
        return String.format(TWO_DECIMAL_FORMAT, cap);
    }
}
