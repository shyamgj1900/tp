package seedu.kolinux.commands;

import seedu.kolinux.exceptions.KolinuxException;
import seedu.kolinux.module.ModuleDetails;
import seedu.kolinux.module.ModuleListStorage;
import seedu.kolinux.module.ModuleList;

import java.util.logging.Level;

import static seedu.kolinux.module.Grade.A_PLUS_GRADE;
import static seedu.kolinux.module.Grade.A_MINUS_GRADE;
import static seedu.kolinux.module.Grade.A_GRADE;
import static seedu.kolinux.module.Grade.B_PLUS_GRADE;
import static seedu.kolinux.module.Grade.B_MINUS_GRADE;
import static seedu.kolinux.module.Grade.B_GRADE;
import static seedu.kolinux.module.Grade.C_PLUS_GRADE;
import static seedu.kolinux.module.Grade.C_GRADE;
import static seedu.kolinux.module.Grade.D_PLUS_GRADE;
import static seedu.kolinux.module.Grade.D_GRADE;
import static seedu.kolinux.module.Grade.F_GRADE;
import static seedu.kolinux.module.Grade.S_GRADE;
import static seedu.kolinux.module.Grade.U_GRADE;
import static seedu.kolinux.module.Grade.CS_GRADE;
import static seedu.kolinux.module.Grade.CU_GRADE;
import static seedu.kolinux.module.Grade.EXE_GRADE;
import static seedu.kolinux.module.Grade.IC_GRADE;
import static seedu.kolinux.module.Grade.IP_GRADE;
import static seedu.kolinux.module.Grade.W_GRADE;
import static seedu.kolinux.module.Grade.RESET_GRADE;
import static seedu.kolinux.module.Grade.RESET_GRADE_ARGUMENT;

/**
 * Represents the command that interacts with the module list.
 */
public class ModuleCommand extends Command {
    private String subCommand;
    private String[] parsedArguments;
    private String moduleCode;

    public static final String STORE_SUBCOMMAND = "add";
    public static final String DELETE_SUBCOMMAND = "delete";
    public static final String VIEW_SUBCOMMAND = "view";
    public static final String LIST_SUBCOMMAND = "list";
    public static final String SET_GRADE_SUBCOMMAND = "grade";
    private static final String CAP_SUBCOMMAND = "cap";

    public static final String INVALID_GRADE_FORMAT_MESSAGE = "Please use the format: module grade CODE/GRADE";
    public static final String INVALID_GRADE_LETTER_MESSAGE = "Please enter a valid grade";
    public static final String INVALID_SU_GRADE_MESSAGE = "This module doesn't allow S/U grade";
    public static final String INVALID_CSCU_MODULE_MESSAGE = "This module only accept CS or CU grade";
    public static final String INVALID_ARGUMENT_MESSAGE = "Ensure command has one of the following formats:\n"
            + "1. module add CODE\n"
            + "2. module delete CODE\n"
            + "3. module view CODE\n"
            + "4. module list\n"
            + "5. module grade CODE/GRADE\n"
            + "6. module cap\n"
            + "7. module cap DESIRED_CAP";

    public ModuleCommand(String subCommand, String[] parsedArguments) {
        this.subCommand = subCommand;
        this.parsedArguments = parsedArguments;
    }

    public static ModuleList getModuleList() {
        return moduleList;
    }

    private String containsValidGrade(String moduleCode, String moduleGrade) {
        ModuleDetails module = moduleDb.getModuleInfo(moduleCode);
        if (module.isCsCuModule()) {
            if (moduleGrade.equals(CS_GRADE) || moduleGrade.equals(CU_GRADE)
                    || moduleGrade.equals(RESET_GRADE) || moduleGrade.equals(RESET_GRADE_ARGUMENT)) {
                return null;
            }
            return INVALID_CSCU_MODULE_MESSAGE;
        }
        if (moduleGrade.equals(A_PLUS_GRADE) || moduleGrade.equals(A_GRADE) || moduleGrade.equals(A_MINUS_GRADE)
                || moduleGrade.equals(B_PLUS_GRADE) || moduleGrade.equals(B_GRADE) || moduleGrade.equals(B_MINUS_GRADE)
                || moduleGrade.equals(C_PLUS_GRADE) || moduleGrade.equals(C_GRADE) || moduleGrade.equals(D_PLUS_GRADE)
                || moduleGrade.equals(D_GRADE) || moduleGrade.equals(F_GRADE) || moduleGrade.equals(EXE_GRADE)
                || moduleGrade.equals(IC_GRADE) || moduleGrade.equals(IP_GRADE) || moduleGrade.equals(W_GRADE)
                || moduleGrade.equals(RESET_GRADE) || moduleGrade.equals(RESET_GRADE_ARGUMENT)) {
            return null;
        }
        if (moduleGrade.equals(S_GRADE) || moduleGrade.equals(U_GRADE)) {
            return module.isSuAble() ? null : INVALID_SU_GRADE_MESSAGE;
        }
        return INVALID_GRADE_LETTER_MESSAGE;
    }

    private CommandResult setModuleGrade(String[] parsedArguments) throws KolinuxException {
        String moduleCode;
        String moduleGrade;
        try {
            moduleCode = parsedArguments[0].toUpperCase();
            moduleGrade = parsedArguments[1].toUpperCase();
        } catch (IndexOutOfBoundsException exception) {
            throw new KolinuxException(INVALID_GRADE_FORMAT_MESSAGE);
        }
        String gradeResultMessage = containsValidGrade(moduleCode, moduleGrade);
        if (!(gradeResultMessage == null)) {
            throw new KolinuxException(gradeResultMessage);
        }
        String message = moduleList.setModuleGrade(moduleCode, moduleGrade);
        logger.log(Level.INFO, message);
        ModuleListStorage.writeModulesToFile(moduleList);
        return new CommandResult(message);
    }

    private CommandResult showModuleCap(String[] parsedArguments) throws KolinuxException {
        ModuleListCapCommand command = new ModuleListCapCommand(parsedArguments);
        return command.executeCommand();
    }

    private CommandResult storeModule() {
        String message = moduleList.addModuleByCode(moduleCode, moduleDb);
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
        case CAP_SUBCOMMAND:
            return showModuleCap(parsedArguments);
        default:
            return displayError();
        }
    }
}
