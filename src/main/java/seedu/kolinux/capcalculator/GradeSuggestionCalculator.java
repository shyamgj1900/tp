package seedu.kolinux.capcalculator;

import seedu.kolinux.exceptions.KolinuxException;

/**
 * Represents CAP calculator used for suggesting grade based on stored modules in module list and desired CAP
 * from user input.
 */
public class GradeSuggestionCalculator extends CapCalculatorByCode {
    
    private static final String UNAVAILABLE_GRADE = "0";
    
    private String userDesiredCap;

    /**
     * Construct the superclass of this object and store desired CAP from user.
     * 
     * @param input Formatted description of modules and the respective grades from module list.
     * @param userDesiredCap Desired CAP of user.
     */
    public GradeSuggestionCalculator(String input, String userDesiredCap) {
        super(input);
        this.userDesiredCap = userDesiredCap;
    }

    /**
     * Check if user's desired CAP is invalid or not.
     * 
     * @throws KolinuxException if desired CAP is invalid.
     */
    public void checkInvalidDesiredCap() throws KolinuxException {
        double cap = Double.parseDouble(userDesiredCap);
        if (cap > 5.0) {
            String errorMessage = "CAP cannot exceed 5.0";
            throw new KolinuxException(errorMessage);
        }
    }

    /**
     * Check if the module is needed for CAP calculation or not.
     * 
     * @param module Description of module which include the module code and grade.
     * @return true if the module is not needed for calculation, false otherwise.
     */
    private boolean isInvalidModule(String module) {
        String[] moduleDescriptions = module.split("/");
        String grade = moduleDescriptions[1];
        return grade.equals(SATISFACTORY_GRADE) || grade.equals(UNSATISFACTORY_GRADE) || grade.equals(UNAVAILABLE_GRADE);
    }

    /**
     * Get the total number of MC of modules which already have their grade stored.
     * 
     * @return Total MC.
     */
    private double getMcModulesWithGrade() {
        double totalMc = 0.0;
        for (String module : modules) {
            if (isInvalidModule(module)) {
                continue;
            }
            int mc = getMc(module);
            totalMc += mc;
        }
        return totalMc;
    }

    /**
     * Get the total number of MC of modules which do not have their grade stored.
     * 
     * @return Total MC.
     */
    private double getMcModulesWithoutGrade() {
        double totalMc = 0.0;
        for (String module : invalidModules) {
            int mc = getMc(module);
            totalMc += mc;
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
        double minimumCap = ((desiredCap * mcModuleWithGrade) + (desiredCap * mcModuleWithoutGrade) - (currentCap * mcModuleWithGrade)) / mcModuleWithoutGrade;
        return getGradeLetter(minimumCap);
    }

    /**
     * Get the average minimum grade that the user needs to achieve in order to get the desired CAP.
     * 
     * @return The average minimum grade that the user needs.
     */
    private String getExpectedGrades() {
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
