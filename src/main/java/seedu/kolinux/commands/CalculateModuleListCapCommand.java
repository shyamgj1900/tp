package seedu.kolinux.commands;

import seedu.kolinux.capcalculator.ModuleListCapCalculator;
import seedu.kolinux.exceptions.KolinuxException;

public class CalculateModuleListCapCommand extends Command {
    
    private ModuleListCapCalculator calculator;

    // Command format: stored_cap
    public CalculateModuleListCapCommand() {
        this.calculator = new ModuleListCapCalculator(moduleList);
    }
    
    @Override
    public CommandResult executeCommand() throws KolinuxException {
        return calculator.executeCapCalculator();
    }
}
