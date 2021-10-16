package seedu.kolinux.capcalculator;

import seedu.kolinux.module.ModuleDb;
import seedu.kolinux.module.ModuleDetails;

public class CapCalculatorByCode extends CapCalculator {
    
    private final int MODULE_CODE_POSITION = 0;

    private ModuleDb moduleDb;
    
    public CapCalculatorByCode(String input) {
        super(input);
        moduleDb = new ModuleDb().getPreInitModuleDb();
    }
    
    @Override
    protected int getMc(String module) {
        String[] moduleDescriptions = module.split("/");
        String moduleCode = moduleDescriptions[MODULE_CODE_POSITION];
        ModuleDetails moduleInfo = moduleDb.getModuleInfo(moduleCode);
        if (moduleInfo == null) {
            invalidModules.add(module);
            return INVALID_MC;
        }
        String moduleCredit = moduleDb.getModuleInfo(moduleCode).getModuleCredit();
        return Integer.parseInt(moduleCredit);
    }
}
