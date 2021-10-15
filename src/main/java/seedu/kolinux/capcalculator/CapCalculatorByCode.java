package seedu.kolinux.capcalculator;

import seedu.kolinux.exceptions.KolinuxException;
import seedu.kolinux.module.ModuleDb;

// assume module code contains 6 chars
public class CapCalculatorByCode extends CapCalculator {

    private ModuleDb moduleDb = new ModuleDb().getPreInitModuleDb();
    
    public CapCalculatorByCode(String input) {
        super(input);
    }

    @Override
    protected double getGradePoint(String module) throws KolinuxException {
        String grade = module.substring(6);
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
        String moduleCode = module.substring(0, 6);
        String moduleCredit = moduleDb.getModuleInfo(moduleCode).getModuleCredit();
        return Integer.parseInt(moduleCredit);
    }
}
