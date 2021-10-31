package seedu.kolinux.module;

/**
 * Used for storing module list specifically for cap calculation operations.
 */
public class CalculatorModuleList extends ModuleList {

    /**
     * Store a module in myModules.
     * 
     * @param module The module to be stored.
     */
    public void storeModule(ModuleDetails module) {
        myModules.add(module);
    }

    /**
     * Store a module in myModules when only the modular credit and its grade are given.
     * This method is only used CapCalculator object.
     *
     * @param mc Modular credit which will be stored
     * @param grade The corresponding grade to be stored
     */
    public void storeModuleMcGrade(int mc, String grade) {
        ModuleDetails mod = new ModuleDetails(mc, grade);
        myModules.add(mod);
    }

    /**
     * Store a module with grade into myModules.
     * 
     * @param code Module code which will be stored.
     * @param grade The corresponding grade to be stored.
     * @param moduleDb Module database.
     * @return true if the module is successfully stored, false otherwise.
     */
    public boolean storeModuleCodeGrade(String code, String grade, ModuleDb moduleDb) {
        ModuleDetails mod = moduleDb.getModuleInfo(code);
        if (myModules.contains(mod)) {
            return false;
        }
        myModules.add(mod);
        mod.setGrade(grade);
        return true;
    }
}
