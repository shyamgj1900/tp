package seedu.kolinux.capcalculator;

import seedu.kolinux.exceptions.KolinuxException;

import java.util.ArrayList;

public abstract class CapCalculator {
    
    protected ArrayList<String> modules;
    protected ArrayList<String> invalidModules;

    public CapCalculator(String input) {
        modules = new ArrayList<>();
        invalidModules = new ArrayList<>();
        String[] commandDescriptions = input.split(" ");
        if (commandDescriptions.length == 1) {
            return;
        }
        int moduleCount = commandDescriptions.length - 1;
        for (int i = 0; i < moduleCount; i++) {
            modules.add(commandDescriptions[i + 1]);
        }
        assert !modules.isEmpty();
    }
    
    protected boolean hasSuGrade(String module) throws KolinuxException {
        String[] moduleDescriptions = module.split("/");
        if (moduleDescriptions.length == 1) {
            /*String errorMessage = "Invalid module info found: " + module;
            throw new KolinuxException(errorMessage);*/
            invalidModules.add(module);
            return true;
        }
        String grade = moduleDescriptions[1];
        return grade.equals("S") || grade.equals("U");
    }

    /**
     * Extracts modular credit from a module description.
     *
     * @param module Description of module which contains modular credit and grade.
     * @return Modular credit.
     * @throws KolinuxException When the module contains invalid credit.
     */
    protected abstract int getMc(String module) throws KolinuxException;

    /**
     * Extracts grade point from a module description.
     *
     * @param module Description of module which contains modular credit and grade.
     * @return Grade point.
     * @throws KolinuxException When the module contains invalid grade.
     */
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
            /*String errorMessage = "Invalid module info found: " + module;
            throw new KolinuxException(errorMessage);*/
            invalidModules.add(module);
            return -1;
        }
    }

    /**
     * Calculate CAP based on a previously calculated CAP and the current module.
     *
     * @param totalMc The total modular credit of the previously calculated CAP.
     * @param cap The previously calculated CAP.
     * @param mc The modular credit of the current module.
     * @param gradePoint The grade point of the current module to be calculated into CAP.
     * @return The overall CAP.
     */
    protected double getCurrentCap(int totalMc, double cap, int mc, double gradePoint) {
        return ((cap * totalMc) + (gradePoint * mc)) / (totalMc + mc);
    }

    /**
     * Calculate CAP based on modules stored in this command object.
     *
     * @return Overall CAP of the modules, formatted to two decimal places.
     * @throws KolinuxException When a module description contains an invalid modular credit or grade.
     */
    public String getCap() throws KolinuxException {
        int totalMc = 0;
        double cap = 0;
        for (String module : modules) {
            if (hasSuGrade(module)) {
                continue;
            }
            int mc = getMc(module);
            double gradePoint = getGradePoint(module);
            cap = getCurrentCap(totalMc, cap, mc, gradePoint);
            totalMc += mc;
            assert cap <= 5.0;
        }
        if (!invalidModules.isEmpty()) {
            StringBuilder errorMessage = new StringBuilder("Invalid module info found: ");
            for (String module : invalidModules) {
                errorMessage.append(module).append(" ");
            }
            throw new KolinuxException(errorMessage.toString());
        }
        return String.format("%.2f", cap);
    }
}
