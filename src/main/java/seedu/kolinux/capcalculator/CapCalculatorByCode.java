package seedu.kolinux.capcalculator;

import seedu.kolinux.exceptions.KolinuxException;
import seedu.kolinux.module.ModuleDb;

public class CapCalculatorByCode extends CapCalculator {

    private ModuleDb moduleDb = new ModuleDb().getPreInitModuleDb();
    
    public CapCalculatorByCode(String input) {
        super(input);
    }
    
    @Override
    protected int getMc(String module) throws KolinuxException {
        String[] moduleDescriptions = module.split("/");
        String moduleCode = moduleDescriptions[0];
        String moduleCredit = moduleDb.getModuleInfo(moduleCode).getModuleCredit();
        return Integer.parseInt(moduleCredit);
    }
}
