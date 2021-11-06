package seedu.kolinux.capcalculator;

import seedu.kolinux.exceptions.KolinuxException;
import seedu.kolinux.module.ModuleDetails;
import seedu.kolinux.module.ModuleList;

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


/**
 * Represents CAP calculator used for suggesting grade based on stored modules in module list and desired CAP
 * from user input.
 */
public class GradeSuggestionCalculator extends CapCalculatorByCode {
    
    private String userDesiredCap;
    
    public GradeSuggestionCalculator(ModuleList modules, String userDesiredCap) {
        super(modules);
        this.userDesiredCap = userDesiredCap;
    }

    /**
     * Check if user's desired CAP is invalid or not.
     * 
     * @throws KolinuxException if desired CAP is invalid.
     */
    private void checkInvalidDesiredCap() throws KolinuxException {
        double cap;
        try {
            cap = Double.parseDouble(userDesiredCap);
        } catch (NumberFormatException exception) {
            String errorMessage = "CAP must be a number";
            throw new KolinuxException(errorMessage);
        }
        if (cap > 5.0) {
            String errorMessage = "CAP cannot exceed 5.0";
            throw new KolinuxException(errorMessage);
        }
        if (cap < 0.0) {
            String errorMessage = "CAP cannot be a negative number";
            throw new KolinuxException(errorMessage);
        }
    }

    /**
     * Get the total number of MC of modules which already have their grade stored.
     * 
     * @return Total MC.
     */
    private double getMcModulesWithGrade() {
        double totalMc = 0.0;
        for (ModuleDetails module : modules.getMyModules()) {
            if (module.containsNonCalculatingGrade() || module.containsNullGrade()) {
                continue;
            }
            totalMc += totalMc += Integer.parseInt(module.getModuleCredit());
        }
        return totalMc;
    }

    /**
     * Get the total number of MC of modules which do not have their grade stored.
     * 
     * @return Total MC.
     */
    private double getMcModulesWithoutGrade() throws KolinuxException {
        double totalMc = 0.0;
        for (String module : invalidModules) {
            String moduleCode = module.split(DIVIDER)[0];
            if (moduleDb.getModuleInfo(moduleCode).isCsCuModule()) {
                continue;
            }
            String moduleCredit = moduleDb.getModuleInfo(moduleCode).getModuleCredit();
            double mc = Double.parseDouble(moduleCredit);
            totalMc += mc;
        }
        if (totalMc == 0) {
            String errorMessage = "Grade suggestion is not available as "
                    + "every valid modules already have their grade available";
            throw new KolinuxException(errorMessage);
        }
        return totalMc;
    }
    
    /**
     * Get the minimum grade that the user needs to achieve in order to get the desired CAP.
     * 
     * @param gradePoint The minimum grade point that the user needs.
     * @return A letter grade which is equal to or higher than the minimum grade point.
     */
    private String getGradeLetter(double gradePoint) {
        if (gradePoint <= 0.0) {
            return F_GRADE;
        } else if (gradePoint <= 1.0) {
            return D_GRADE;
        } else if (gradePoint <= 1.5) {
            return D_PLUS_GRADE;
        } else if (gradePoint <= 2.0) {
            return C_GRADE;
        } else if (gradePoint <= 2.5) {
            return C_PLUS_GRADE;
        } else if (gradePoint <= 3.0) {
            return B_MINUS_GRADE;
        } else if (gradePoint <= 3.5) {
            return B_GRADE;
        } else if (gradePoint <= 4.0) {
            return B_PLUS_GRADE;
        } else if (gradePoint <= 4.5) {
            return A_MINUS_GRADE;
        } else if (gradePoint <= 5.0) {
            return A_GRADE;
        } else {
            return "UNACHIEVABLE";
        }
    }

    /**
     * Calculate and retrieve the average minimum grade that the user needs to achieve in order to get the desired CAP.
     * 
     * @param currentCap CAP of the modules with available grade stored in module list.
     * @param mcModuleWithGrade Total MC of modules with available grade.
     * @param mcModuleWithoutGrade Total MC of modules without available grade.
     * @return The average minimum grade that the user needs.
     */
    private String getMinimumGrade(double currentCap, double mcModuleWithGrade, double mcModuleWithoutGrade) {
        double desiredCap = Double.parseDouble(userDesiredCap);
        double minimumCap = ((desiredCap * mcModuleWithGrade) + (desiredCap * mcModuleWithoutGrade) 
                - (currentCap * mcModuleWithGrade)) / mcModuleWithoutGrade;
        return getGradeLetter(minimumCap);
    }

    /**
     * Get the average minimum grade that the user needs to achieve in order to get the desired CAP.
     * 
     * @return The average minimum grade that the user needs.
     */
    private String getExpectedGrades() throws KolinuxException {
        double currentCap = Double.parseDouble(getCap());
        double mcModuleWithGrade = getMcModulesWithGrade();
        double mcModuleWithoutGrade = getMcModulesWithoutGrade();
        return getMinimumGrade(currentCap, mcModuleWithGrade, mcModuleWithoutGrade);
    }
     
    @Override
    public String executeCapCalculator() throws KolinuxException {
        checkInvalidDesiredCap();
        checkModulesNotEmpty();
        return getExpectedGrades();
    }
}
