package seedu.kolinux.capcalculator;

import seedu.kolinux.exceptions.KolinuxException;
import seedu.kolinux.module.ModuleDetails;
import seedu.kolinux.module.ModuleList;

import java.util.ArrayList;

/**
 * Abstract representation of CAP calculator based on user input.
 */
public abstract class CapCalculator {

    protected static final String SATISFACTORY_GRADE = "S";
    protected static final String UNSATISFACTORY_GRADE = "U";
    
    private static final int CLASSNAME_POSITION = 3;
    private static final int INFO_TYPE_POSITION = 1;
    
    protected static final int INVALID_GRADE = -1;
    protected static final int INVALID_MC = -1;
    
    protected static final double MAX_CAP = 5.0;
    
    protected static final String TWO_DECIMAL_FORMAT = "%.2f";
    
    //protected ArrayList<String> modules;
    //protected ArrayList<String> invalidModules;
    
    protected ModuleList modules;
    protected ArrayList<String> invalidModules;
    
    /*public CapCalculator(String input) {
        modules = new ArrayList<>();
        invalidModules = new ArrayList<>();
        String[] commandDescriptions = input.split(" ");
        if (commandDescriptions.length <= 2) {
            return;
        }
        int moduleCount = commandDescriptions.length - 2;
        for (int i = 0; i < moduleCount; i++) {
            modules.add(commandDescriptions[i + 2]);
        }
        assert !modules.isEmpty();
    }*/
    
    public CapCalculator() {
        modules = new ModuleList();
        invalidModules = new ArrayList<>();
    }

    /**
     * Check if the modules attribute in this object is empty.
     * 
     * @throws KolinuxException If the modules attribute is empty. Show an error message to the user.
     */
    protected void checkModulesNotEmpty() throws KolinuxException {
        if (modules.getMyModulesSize() == 0 && invalidModules.isEmpty()) {
            String errorMessage;
            String className = this.getClass().getName().split("\\.")[CLASSNAME_POSITION];
            switch (className) {
            case "CapCalculatorByCode":
                errorMessage = "Please enter valid module description. Example: CG2027/A+";
                throw new KolinuxException(errorMessage);
            case "CapCalculatorByMc":
                errorMessage = "Please enter valid module description. Example: 4/A+";
                throw new KolinuxException(errorMessage);
            case "ModuleListCapCalculator":
            case "GradeSuggestionCalculator":
                errorMessage = "Please store modules using module store command";
                throw new KolinuxException(errorMessage);
            default:
                // Should not reach this case
                assert false;
                errorMessage = "Unexpected class name found";
                throw new KolinuxException(errorMessage);
            }
        }
    }

    /**
     * Check if a module has an S/U grade.
     * 
     * @param module The current module that is being checked.
     * @return true if the module has an S/U grade, false otherwise.
     */
    /*protected boolean containsSuGrade(String module) {
        String[] moduleDescriptions = module.split("/");
        if (moduleDescriptions.length == 1) {
            invalidModules.add(module);
            return true; // return true in order for getCap method to skip this module
        }
        String grade = moduleDescriptions[INFO_TYPE_POSITION];
        return grade.equals(SATISFACTORY_GRADE) || grade.equals(UNSATISFACTORY_GRADE);
    }*/

    /**
     * Extracts modular credit from a module description.
     *
     * @param module Description of module which contains modular credit and grade.
     * @return Modular credit.
     */
    protected abstract int getMc(ModuleDetails module);

    /**
     * Extracts grade point from a module description.
     *
     * @param module Description of module which contains modular credit and grade.
     * @return Grade point.
     */
    /*protected double getGradePoint(String module) {
        String[] moduleDescriptions = module.split("/");
        String grade = moduleDescriptions[INFO_TYPE_POSITION];
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
            invalidModules.add(module);
            return INVALID_GRADE;
        }
    }*/

    /**
     * Calculate CAP based on a previously calculated CAP and the current module.
     *
     * @param totalMc The total modular credit of the previously calculated CAP.
     * @param cap The previously calculated CAP.
     * @param mc The modular credit of the current module.
     * @param gradePoint The grade point of the current module to be calculated into CAP.
     * @return The overall CAP up to the current module.
     */
    protected double calculateCurrentCap(int totalMc, double cap, int mc, double gradePoint) {
        return ((cap * totalMc) + (gradePoint * mc)) / (totalMc + mc);
    }

    /**
     * Calculate overall CAP based on input modules from user.
     * 
     * @return The overall CAP formatted to two decimal places.
     */
    /*protected String getCap() {
        int totalMc = 0;
        double cap = 0;
        for (ModuleDetails module : modules.getMyModules()) {
            //if (containsSuGrade(module)) {
            //    continue;
            //}
            if (module.containsSuGrade()) {
                continue;
            }
            int mc = getMc(module);
            //if (mc == INVALID_MC) {
            //    continue;
            //}
            double gradePoint = module.getGradePoint();
            if (gradePoint == INVALID_GRADE) {
                continue;
            }
            cap = getCurrentCap(totalMc, cap, mc, gradePoint);
            totalMc += mc;
            assert cap <= MAX_CAP;
        }
        return String.format(TWO_DECIMAL_FORMAT, cap);
    }*/
    
    protected abstract String getCap();

    /**
     * Check if this object detects any invalid input module description from user.
     * 
     * @throws KolinuxException When invalid module descriptions are found. Show an error message to the user
     *     containing all the invalid module descriptions.
     */
    /*protected void checkInvalidModules() throws KolinuxException {
        if (!(invalidModules.getMyModulesSize() == 0)) {
            StringBuilder errorMessage = new StringBuilder("Invalid module info found: ");
            for (ModuleDetails module : invalidModules.getMyModules()) {
                errorMessage.append(module).append(" ");
            }
            throw new KolinuxException(errorMessage.toString());
        }
    }*/

    protected void checkInvalidModules() throws KolinuxException {
        if (!(invalidModules.isEmpty())) {
            StringBuilder errorMessage = new StringBuilder("Invalid module info found: ");
            for (String module : invalidModules) {
                errorMessage.append(module).append(" ");
            }
            throw new KolinuxException(errorMessage.toString());
        }
    }

    /**
     * Calculate CAP based on modules stored in this calculator object.
     *
     * @return Overall CAP of the modules, formatted to two decimal places.
     * @throws KolinuxException When a module description contains an invalid module or grade.
     */
    public String executeCapCalculator() throws KolinuxException {
        checkModulesNotEmpty();
        String cap = getCap();
        checkInvalidModules();
        return cap;
    }
}
