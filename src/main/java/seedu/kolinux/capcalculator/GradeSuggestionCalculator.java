package seedu.kolinux.capcalculator;

import seedu.kolinux.exceptions.KolinuxException;
import seedu.kolinux.module.ModuleDetails;
import seedu.kolinux.module.ModuleList;

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
    }

    /**
     * Get the total number of MC of modules which already have their grade stored.
     * 
     * @return Total MC.
     */
    private double getMcModulesWithGrade() {
        double totalMc = 0.0;
        for (ModuleDetails module : modules.getMyModules()) {
            if (module.containsSuGrade() || module.containsNullGrade()) {
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
            double mc = Double.parseDouble(moduleDb.getModuleInfo(moduleCode).getModuleCredit());
            totalMc += mc;
        }
        if (totalMc == 0) {
            String errorMessage = "Grade suggestion is not available as " +
                    "every modules already have their grade available";
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
            return "F";
        } else if (gradePoint <= 1.0) {
            return "D";
        } else if (gradePoint <= 1.5) {
            return "D+";
        } else if (gradePoint <= 2.0) {
            return "C";
        } else if (gradePoint <= 2.5) {
            return "C+";
        } else if (gradePoint <= 3.0) {
            return "B-";
        } else if (gradePoint <= 3.5) {
            return "B";
        } else if (gradePoint <= 4.0) {
            return "B+";
        } else if (gradePoint <= 4.5) {
            return "A-";
        } else if (gradePoint <= 5.0) {
            return "A";
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
