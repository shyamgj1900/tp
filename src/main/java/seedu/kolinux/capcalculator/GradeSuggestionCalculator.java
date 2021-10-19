package seedu.kolinux.capcalculator;

import seedu.kolinux.exceptions.KolinuxException;

public class GradeSuggestionCalculator extends CapCalculatorByCode {
    
    private static final String UNAVAILABLE_GRADE = "0";
    
    private String userDesiredCap;
    
    public GradeSuggestionCalculator(String input, String userDesiredCap) {
        super(input);
        this.userDesiredCap = userDesiredCap;
    }
    
    public void checkInvalidDesiredCap() throws KolinuxException {
        double cap = Double.parseDouble(userDesiredCap);
        if (cap > 5.0) {
            String errorMessage = "CAP cannot exceed 5.0";
            throw new KolinuxException(errorMessage);
        }
    }

    private boolean isInvalidModule(String module) {
        String[] moduleDescriptions = module.split("/");
        String grade = moduleDescriptions[1];
        return grade.equals(SATISFACTORY_GRADE) || grade.equals(UNSATISFACTORY_GRADE) || grade.equals(UNAVAILABLE_GRADE);
    }
    
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
    
    private double getMcModulesWithoutGrade() {
        double totalMc = 0.0;
        for (String module : invalidModules) {
            int mc = getMc(module);
            totalMc += mc;
        }
        return totalMc;
    }

    // return grade letter equal to or higher than cap
    private String getGradeLetter(double cap) {
        if (cap <= 0.0) {
            return "F";
        } else if (cap <= 1.0) {
            return "D";
        } else if (cap <= 1.5) {
            return "D+";
        } else if (cap <= 2.0) {
            return "C";
        } else if (cap <= 2.5) {
            return "C+";
        } else if (cap <= 3.0) {
            return "B-";
        } else if (cap <= 3.5) {
            return "B";
        } else if (cap <= 4.0) {
            return "B+";
        } else if (cap <= 4.5) {
            return "A-";
        } else if (cap <= 5.0) {
            return "A";
        } else {
            return "impossible";
        }
    }
    
    private String getMinimumGrade(double currentCap, double mcModuleWithGrade, double mcModuleWithoutGrade) {
        double desiredCap = Double.parseDouble(userDesiredCap);
        double minimumCap = ((desiredCap * mcModuleWithGrade) + (desiredCap * mcModuleWithoutGrade) - (currentCap * mcModuleWithGrade)) / mcModuleWithoutGrade;
        return getGradeLetter(minimumCap);
    }

    private String getExpectedGrades() {
        double currentCap = Double.parseDouble(getCap());
        double mcModuleWithGrade = getMcModulesWithGrade();
        double mcModuleWithoutGrade = getMcModulesWithoutGrade();
        return getMinimumGrade(currentCap, mcModuleWithGrade, mcModuleWithoutGrade);
    }
     
    public String executeCapCalculator() throws KolinuxException {
        checkInvalidDesiredCap();
        checkModulesNotEmpty();
        return getExpectedGrades();
    }
}
