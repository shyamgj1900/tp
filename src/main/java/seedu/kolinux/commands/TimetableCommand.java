package seedu.kolinux.commands;

import seedu.kolinux.exceptions.KolinuxException;
import seedu.kolinux.module.timetable.Timetable;


import java.util.logging.Level;

/** Represents the command that interacts with the timetable. */
public class TimetableCommand extends Command {

    private String subCommand;
    private String[] parsedArguments;
    public static final String INVALID_TIMETABLE_ARGUMENT = "Ensure command has one of the following formats:\n"
            +
            "1. timetable add LESSON_TYPE/MODULE_CODE/DAY/START_TIME\n"
            +
            "2. timetable view\n"
            +
            "3. timetable update MODULE_CODE/LESSON_TYPE/OLD_DAY/NEW_DAY/NEW_START_TIME\n"
            +
            "4. timetable delete MODULE_CODE/LESSON_TYPE/DAY\n"
            +
            "5. timetable clear";

    public TimetableCommand(String subCommand, String[] parsedArguments) {
        this.subCommand = subCommand;
        this.parsedArguments = parsedArguments;
    }

    @Override
    public CommandResult executeCommand() throws KolinuxException {
        switch (subCommand) {
        case "add":
            Timetable.inputAsLesson(parsedArguments, moduleList);
            logger.log(Level.INFO, "User added a module to timetable");
            return new CommandResult(parsedArguments[0].toUpperCase() + " "
                    +
                    parsedArguments[1].toUpperCase() + " has been added to timetable");
        case "clear":
            Timetable.clearTimetable();
            logger.log(Level.INFO, "User has cleared timetable");
            return new CommandResult("Timetable has been cleared completely");
        case "view":
            Timetable.viewTimetable();
            logger.log(Level.INFO, "User has printed timetable");
            return new CommandResult("Timetable has been printed above");
        case "delete":
            Timetable.deleteLesson(parsedArguments);
            logger.log(Level.INFO, "User has deleted" + parsedArguments[0].toUpperCase()
                    +
                    " from the timetable.");
            return new CommandResult(parsedArguments[0].toUpperCase()
                    +
                    " " + parsedArguments[1].toUpperCase() + " " + parsedArguments[2].toLowerCase()
                    +
                    " has been deleted from timetable");
        case "update":
            Timetable.updateTimetable(parsedArguments, moduleList);
            logger.log(Level.INFO, "User has updated the timetable.");
            return new CommandResult(parsedArguments[0].toUpperCase() + " "
                    +
                    parsedArguments[1].toUpperCase() + " has been updated");
        default:
            logger.log(Level.INFO, "User used invalid subCommand for timetable");
            return new CommandResult(INVALID_TIMETABLE_ARGUMENT);
        }
    }

}
