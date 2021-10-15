package seedu.kolinux.capcalculator;

import seedu.kolinux.exceptions.KolinuxException;

public class CapCalculatorByMc extends CapCalculator {
    
    public CapCalculatorByMc(String input) {
        super(input);
    }

    @Override
    protected double getGradePoint(String module) throws KolinuxException {
        String[] moduleDescriptions = module.split("/");
        String grade = moduleDescriptions[1];
        switch (grade) {
        case "A+":
        case "A":
            return 5.0;
        case "A-":
            return 4.5;
        case "B+":
            return 4.0;
        case "B":
            return 3.5;
        case "B-":
            return 3.0;
        case "C+":
            return 2.5;
        case "C":
            return 2.0;
        case "D+":
            return 1.5;
        case "D":
            return 1.0;
        case "F":
            return 0.0;
        default:
            String errorMessage = "Invalid module info found: " + module;
            throw new KolinuxException(errorMessage);
        }
    }
    
    @Override
    protected int getMc(String module) throws KolinuxException {
        String[] moduleDescriptions = module.split("/");
        try {
            return Integer.parseInt(String.valueOf(moduleDescriptions[0]));
        } catch (NumberFormatException exception) {
            String errorMessage = "Invalid module info found: " + module;
            throw new KolinuxException(errorMessage);
        }
    }
}
