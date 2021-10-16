package seedu.kolinux.commands;

import seedu.kolinux.exceptions.KolinuxException;
import seedu.kolinux.module.timetable.Lab;
import seedu.kolinux.module.timetable.Lecture;
import seedu.kolinux.module.timetable.Timetable;
import seedu.kolinux.module.timetable.Tutorial;

import java.util.logging.Level;

/** Represents the command that interacts with the timetable. */
public class TimetableCommand extends Command {

    private String subCommand;
    private String[] parsedArguments;
    public static final String INVALID_TIMETABLE_ARGUMENT = "Ensure command has one of the following formats:\n"
            +
            "1. timetable add LESSON_TYPE/MODULE_CODE/DAY/START_TIME/END_TIME\n"
            +
            "2. timetable view\n"
            +
            "3. timetable clear";
    public static final String INVALID_ADD_ARGUMENT = "Please check the format of adding to timetable: "
            +
            "timetable add LESSON_TYPE/MODULE_CODE/DAY/START_TIME/END_TIME\n"
            +
            "e.g. timetable add TUT/CS1010/Monday/1200/1400";

    public TimetableCommand(String subCommand, String[] parsedArguments) {
        this.subCommand = subCommand;
        this.parsedArguments = parsedArguments;
    }

    public void inputAsLesson(String[] parsedArguments) throws KolinuxException {
        String lessonType = parsedArguments[0].toLowerCase();
        if (lessonType.startsWith("tut")) {
            Timetable.addLesson(new Tutorial(parsedArguments));
        } else if (lessonType.startsWith("lec")) {
            Timetable.addLesson(new Lecture(parsedArguments));
        } else if (lessonType.startsWith("lab")) {
            Timetable.addLesson(new Lab(parsedArguments));
        } else {
            throw new KolinuxException(INVALID_ADD_ARGUMENT);
        }
    }

    @Override
    public CommandResult executeCommand() throws KolinuxException {
        switch (subCommand) {
        case "add":
            inputAsLesson(parsedArguments);
            logger.log(Level.INFO, "User added a module to timetable");
            String moduleCode = parsedArguments[1].toUpperCase();
            String lessonType = parsedArguments[0].toUpperCase();
            return new CommandResult(moduleCode + " " + lessonType + " has been added to timetable");
        case "clear":
            Timetable.clearTimetable();
            logger.log(Level.INFO, "User has cleared timetable");
            return new CommandResult("Timetable has been cleared completely");
        case "view":
            Timetable.viewTimetable();
            logger.log(Level.INFO, "User has printed timetable");
            return new CommandResult("Timetable has been printed above");
        default:
            logger.log(Level.INFO, "User used invalid subCommand for timetable");
            return new CommandResult(INVALID_TIMETABLE_ARGUMENT);
        }
    }

}
