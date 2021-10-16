package seedu.kolinux.capcalculator;

import seedu.kolinux.exceptions.KolinuxException;
import seedu.kolinux.module.ModuleDb;
import seedu.kolinux.module.ModuleDetails;

public class CapCalculatorByCode extends CapCalculator {

    private ModuleDb moduleDb;
    
    public CapCalculatorByCode(String input) {
        super(input);
        moduleDb = new ModuleDb().getPreInitModuleDb();
    }
    
    @Override
    protected int getMc(String module) throws KolinuxException {
        String[] moduleDescriptions = module.split("/");
        String moduleCode = moduleDescriptions[0];
        ModuleDetails moduleInfo = moduleDb.getModuleInfo(moduleCode);
        if (moduleInfo == null) {
            throw new KolinuxException("Invalid module code found");
        }
        String moduleCredit = moduleDb.getModuleInfo(moduleCode).getModuleCredit();
        return Integer.parseInt(moduleCredit);
    }
}
