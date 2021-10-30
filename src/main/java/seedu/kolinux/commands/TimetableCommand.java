package seedu.kolinux.commands;

import seedu.kolinux.exceptions.KolinuxException;
import seedu.kolinux.timetable.Timetable;


import java.util.logging.Level;

/** Represents the command that interacts with the timetable. */
public class TimetableCommand extends Command {

    public static Timetable timetable = new Timetable(moduleList);
    private String subCommand;
    private String[] parsedArguments;
    public static final String INVALID_TIMETABLE_ARGUMENT_MESSAGE = "Ensure command has one of the "
            +
            "following formats:\n1. timetable add LESSON_TYPE/MODULE_CODE/DAY/START_TIME\n"
            +
            "2. timetable view\n"
            +
            "3. timetable update MODULE_CODE/LESSON_TYPE/OLD_DAY/NEW_DAY/NEW_START_TIME\n"
            +
            "4. timetable delete MODULE_CODE/LESSON_TYPE/DAY\n"
            +
            "5. timetable clear";
    private static final String ADD_SUBCOMMAND = "add";
    private static final String CLEAR_SUBCOMMAND = "clear";
    private static final String UPDATE_SUBCOMMAND = "update";
    private static final String DELETE_SUBCOMMAND = "delete";
    private static final String VIEW_SUBCOMMAND = "view";

    public TimetableCommand(String subCommand, String[] parsedArguments) {
        this.subCommand = subCommand;
        this.parsedArguments = parsedArguments;
    }

    private CommandResult addLesson() throws KolinuxException {
        timetable.executeAdd(parsedArguments);
        logger.log(Level.INFO, "User added a module to timetable");
        return new CommandResult(parsedArguments[0].toUpperCase() + " "
                +
                parsedArguments[1].toUpperCase() + " has been added to timetable");
    }

    private CommandResult deleteLesson() throws KolinuxException {
        timetable.executeDelete(parsedArguments);
        logger.log(Level.INFO, "User has deleted" + parsedArguments[0].toUpperCase()
                +
                " from the timetable.");
        return new CommandResult(parsedArguments[0].toUpperCase()
                +
                " " + parsedArguments[1].toUpperCase() + " " + parsedArguments[3] + " "
                +
                parsedArguments[2].toLowerCase()
                +
                " has been deleted from timetable");
    }

    private CommandResult viewTimetable() {
        timetable.executeView();
        logger.log(Level.INFO, "User has printed timetable");
        return new CommandResult("Timetable has been printed above");
    }

    private CommandResult clearAllLessons() {
        timetable.clearTimetable();
        logger.log(Level.INFO, "User has cleared timetable");
        return new CommandResult("Timetable has been cleared completely");
    }

    private CommandResult updateLesson() throws KolinuxException {
        timetable.executeUpdate(parsedArguments);
        logger.log(Level.INFO, "User has updated the timetable.");
        return new CommandResult(parsedArguments[0].toUpperCase() + " "
                +
                parsedArguments[1].toUpperCase() + " has been updated");
    }

    @Override
    public CommandResult executeCommand() throws KolinuxException {
        switch (subCommand) {
        case ADD_SUBCOMMAND:
            return addLesson();
        case CLEAR_SUBCOMMAND:
            return clearAllLessons();
        case VIEW_SUBCOMMAND:
            return viewTimetable();
        case DELETE_SUBCOMMAND:
            return deleteLesson();
        case UPDATE_SUBCOMMAND:
            return updateLesson();
        default:
            logger.log(Level.INFO, "User used invalid subCommand for timetable");
            return new CommandResult(INVALID_TIMETABLE_ARGUMENT_MESSAGE);
        }
    }

}
