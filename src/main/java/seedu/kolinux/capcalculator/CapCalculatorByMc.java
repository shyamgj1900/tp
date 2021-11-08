package seedu.kolinux.capcalculator;

import seedu.kolinux.module.ModuleDetails;

import static seedu.kolinux.module.Grade.A_PLUS_GRADE;
import static seedu.kolinux.module.Grade.A_MINUS_GRADE;
import static seedu.kolinux.module.Grade.A_GRADE;
import static seedu.kolinux.module.Grade.B_PLUS_GRADE;
import static seedu.kolinux.module.Grade.B_MINUS_GRADE;
import static seedu.kolinux.module.Grade.B_GRADE;
import static seedu.kolinux.module.Grade.C_PLUS_GRADE;
import static seedu.kolinux.module.Grade.C_GRADE;
import static seedu.kolinux.module.Grade.D_PLUS_GRADE;
import static seedu.kolinux.module.Grade.D_GRADE;
import static seedu.kolinux.module.Grade.F_GRADE;
import static seedu.kolinux.module.Grade.S_GRADE;
import static seedu.kolinux.module.Grade.U_GRADE;
import static seedu.kolinux.module.Grade.CS_GRADE;
import static seedu.kolinux.module.Grade.CU_GRADE;
import static seedu.kolinux.module.Grade.EXE_GRADE;
import static seedu.kolinux.module.Grade.IC_GRADE;
import static seedu.kolinux.module.Grade.IP_GRADE;
import static seedu.kolinux.module.Grade.W_GRADE;

/**
 * Represents CAP calculator used when the user's input module descriptions are based on modular credit.
 */
public class CapCalculatorByMc extends CapCalculator {
    
    private boolean isValidGrade(String grade) {
        return grade.equals(A_PLUS_GRADE) || grade.equals(A_GRADE) || grade.equals(A_MINUS_GRADE)
                || grade.equals(B_PLUS_GRADE) || grade.equals(B_GRADE) || grade.equals(B_MINUS_GRADE)
                || grade.equals(C_PLUS_GRADE) || grade.equals(C_GRADE) || grade.equals(D_PLUS_GRADE)
                || grade.equals(D_GRADE) || grade.equals(F_GRADE) || grade.equals(S_GRADE)
                || grade.equals(CS_GRADE) || grade.equals(U_GRADE) || grade.equals(CU_GRADE)
                || grade.equals(EXE_GRADE) || grade.equals(IC_GRADE) || grade.equals(IP_GRADE)
                || grade.equals(W_GRADE);
    }

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
                if (mc < 1) {
                    invalidModules.add(moduleDescription);
                    continue;
                }
                String grade = moduleDescriptions[1];
                if (!isValidGrade(grade)) {
                    invalidModules.add(moduleDescription);
                    continue;
                }
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
            cap = calculateCurrentCap(totalMc, cap, mc, gradePoint);
            totalMc += mc;
            assert cap <= MAX_CAP;
        }
        return String.format(TWO_DECIMAL_FORMAT, cap);
    }
}
