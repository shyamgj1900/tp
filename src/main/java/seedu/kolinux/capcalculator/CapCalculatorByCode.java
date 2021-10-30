package seedu.kolinux.capcalculator;

import seedu.kolinux.module.ModuleDb;
import seedu.kolinux.module.ModuleDetails;
import seedu.kolinux.module.ModuleList;

/**
 * Represents CAP calculator used when the user's input module descriptions are based on module code.
 */
public class CapCalculatorByCode extends CapCalculator {

    protected ModuleDb moduleDb;

    private boolean isValidGrade(String moduleGrade) {
        return moduleGrade.equals("A+") || moduleGrade.equals("A") || moduleGrade.equals("A-")
                || moduleGrade.equals("B+") || moduleGrade.equals("B") || moduleGrade.equals("B-")
                || moduleGrade.equals("C+") || moduleGrade.equals("C") || moduleGrade.equals("D+")
                || moduleGrade.equals("D") || moduleGrade.equals("F") || moduleGrade.equals("S")
                || moduleGrade.equals("U");
    }

    /**
     * Read and store the modules from user's input into this calculator.
     * 
     * @param input String of module descriptions from user.
     */
    private void getInputModules(String input) {
        String[] commandDescriptions = input.split(" ");
        if (commandDescriptions.length <= 2) {
            return;
        }
        int moduleCount = commandDescriptions.length - 2;
        for (int i = 0; i < moduleCount; i++) {
            String inputModule = commandDescriptions[i + 2].toUpperCase();
            String[] moduleDescriptions = inputModule.split(DIVIDER);
            if (moduleDescriptions.length != 2) {
                invalidModules.add(commandDescriptions[i + 2]);
                continue;
            }
            String moduleCode = moduleDescriptions[0];
            if (moduleDb.getModuleInfo(moduleCode) == null) {
                invalidModules.add(commandDescriptions[i + 2]);
                continue;
            }
            String grade = moduleDescriptions[1];
            if (!isValidGrade(grade)) {
                invalidModules.add(commandDescriptions[i + 2]);
                continue;
            }
            modules.storeModuleCodeGrade(moduleCode, grade);
        }
    }

    /**
     * Get the stored modules from Kolinux's module list and store them in this calculator.
     * 
     * @param moduleList The list of modules stored in Kolinux.
     */
    private void getInputModules(ModuleList moduleList) {
        for (ModuleDetails module : moduleList.getMyModules()) {
            if (module.containsNullGrade()) {
                invalidModules.add(module.getModuleCode() + DIVIDER + module.getGrade());
                continue;
            }
            modules.storeModule(module);
        }
    }
    
    /**
     * Construct the superclass of this object and initialize moduleDb in order to retrieve 
     * module information from the database. Module details are then retrieved from input string.
     * 
     * @param input Command input from user which contains the module codes and their grade.
     */
    public CapCalculatorByCode(String input) {
        super();
        moduleDb = new ModuleDb().getPreInitModuleDb();
        getInputModules(input);
    }

    /**
     * Constructor used when module details are retrieved from moduleList of Kolinux instead of user's input.
     * 
     * @param moduleList List of modules stored in moduleList of Kolinux.
     */
    public CapCalculatorByCode(ModuleList moduleList) {
        super();
        moduleDb = new ModuleDb().getPreInitModuleDb();
        getInputModules(moduleList);
    }

    @Override
    protected String getCap() {
        int totalMc = 0;
        double cap = 0;
        for (ModuleDetails module : modules.getMyModules()) {
            if (module.containsSuGrade()) {
                continue;
            }

            double gradePoint = module.getGradePoint();
            
            String moduleCode = module.getModuleCode();
            ModuleDetails moduleInfo = moduleDb.getModuleInfo(moduleCode);
            String moduleCredit = moduleInfo.getModuleCredit();
            int mc = Integer.parseInt(moduleCredit);
            
            cap = calculateCurrentCap(totalMc, cap, mc, gradePoint);
            totalMc += mc;
            assert cap <= MAX_CAP;
        }
        return String.format(TWO_DECIMAL_FORMAT, cap);
    }
}
