package seedu.kolinux.capcalculator;

import seedu.kolinux.module.ModuleDetails;

/**
 * Represents CAP calculator used when the user's input module descriptions are based on modular credit.
 */
public class CapCalculatorByMc extends CapCalculator {

    /**
     * Read and store the modules from user's input into this calculator.
     *
     * @param parsedArguments Array of module descriptions from user.
     */
    private void getInputModules(String[] parsedArguments) {
        if (parsedArguments.length == 1 && parsedArguments[0].equals("")) {
            return;
        }
        for (String moduleDescription : parsedArguments) {
            try {
                String inputModule = moduleDescription.toUpperCase();
                String[] moduleDescriptions = inputModule.split(DIVIDER);
                double moduleCredit = Double.parseDouble(moduleDescriptions[0]);
                if (moduleCredit != (int) moduleCredit) {
                    invalidModules.add(moduleDescription);
                    continue;
                }
                int mc = (int) moduleCredit;
                String grade = moduleDescriptions[1];
                modules.storeModuleMcGrade(mc, grade);
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException exception) {
                invalidModules.add(moduleDescription);
            }
        }
    }

    /**
     * Construct the superclass of this object, then store the module details from user input.
     * 
     * @param parsedArguments Array of module descriptions from user which contains the module credits 
     *                        and their respective grade.
     */
    public CapCalculatorByMc(String[] parsedArguments) {
        super();
        getInputModules(parsedArguments);
    }

    @Override
    protected String getCap() {
        int totalMc = 0;
        double cap = 0;
        for (ModuleDetails module : modules.getMyModules()) {
            if (module.containsNonCalculatingGrade()) {
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
