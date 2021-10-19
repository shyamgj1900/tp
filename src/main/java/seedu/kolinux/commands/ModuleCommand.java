package seedu.kolinux.commands;

import seedu.kolinux.exceptions.KolinuxException;
import seedu.kolinux.module.ModuleDetails;
import seedu.kolinux.module.ModuleListStorage;

import java.util.logging.Level;

/**
 * Represents the command that interacts with the module list.
 */
public class ModuleCommand extends Command {
    private String subCommand;
    private String[] parsedArguments;
    private String moduleCode;
    public static final String STORE_SUBCOMMAND = "store";
    public static final String DELETE_SUBCOMMAND = "delete";
    public static final String VIEW_SUBCOMMAND = "view";
    public static final String LIST_SUBCOMMAND = "list";
    public static final String SET_GRADE_SUBCOMMAND = "grade";
    public static final String INVALID_GRADE_MESSAGE = "Please use the format: module grade CODE/GRADE";
    public static final String INVALID_ARGUMENT_MESSAGE = "Ensure command has one of the following formats:\n"
            +
            "1. module store CODE\n"
            +
            "2. module delete CODE\n"
            +
            "3. module view CODE\n"
            +
            "4. module list\n"
            +
            "5. module grade CODE/GRADE\n";


    public ModuleCommand(String subCommand, String[] parsedArguments) {
        this.subCommand = subCommand;
        this.parsedArguments = parsedArguments;
    }

    private CommandResult setModuleGrade(String[] parsedArguments) throws KolinuxException {
        String moduleGrade;
        try {
            moduleGrade = parsedArguments[1].toUpperCase();
        } catch (IndexOutOfBoundsException exception) {
            throw new KolinuxException(INVALID_GRADE_MESSAGE);
        }
        String message = moduleList.setModuleGrade(moduleCode, moduleGrade);
        logger.log(Level.INFO, message);
        ModuleListStorage.writeModulesToFile(moduleList);
        return new CommandResult(message);
    }

    private CommandResult storeModule() {
        String message = moduleList.storeModuleByCode(moduleCode, moduleDb);
        logger.log(Level.INFO, message);

        ModuleListStorage.writeModulesToFile(moduleList);
        return new CommandResult(message);
    }

    private CommandResult deleteModule() {
        String message = moduleList.deleteModuleByCode(moduleCode);
        logger.log(Level.INFO, message);
        ModuleListStorage.writeModulesToFile(moduleList);
        return new CommandResult(message);
    }

    private CommandResult viewModule() {
        ModuleDetails module = moduleDb.getModuleInfo(moduleCode);
        if (module == null) {
            logger.log(Level.INFO, "User queried invalid module code from ModuleDb for viewing");
            return new CommandResult("Please enter a valid module code");
        }
        logger.log(Level.INFO, "User queried valid module code from ModuleDb for viewing");
        return new CommandResult(module.toString());

    }

    private CommandResult listMyModules() {
        moduleList.listMyModules();
        logger.log(Level.INFO, "User queried list of all stored modules");
        return new CommandResult("");
    }

    private CommandResult displayError() {
        logger.log(Level.INFO, "User used invalid subCommand for timetable");
        return new CommandResult(INVALID_ARGUMENT_MESSAGE);
    }

    @Override
    public CommandResult executeCommand() throws KolinuxException {
        moduleCode = parsedArguments[0].toUpperCase();

        switch (subCommand) {
        case STORE_SUBCOMMAND:
            return storeModule();
        case DELETE_SUBCOMMAND:
            return deleteModule();
        case VIEW_SUBCOMMAND:
            return viewModule();
        case LIST_SUBCOMMAND:
            return listMyModules();
        case SET_GRADE_SUBCOMMAND:
            return setModuleGrade(parsedArguments);
        default:
            return displayError();
        }
    }
}
