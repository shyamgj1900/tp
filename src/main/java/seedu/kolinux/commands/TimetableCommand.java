package seedu.kolinux.commands;

import seedu.kolinux.exceptions.KolinuxException;
import seedu.kolinux.module.timetable.Lesson;
import seedu.kolinux.module.timetable.Timetable;

import java.util.logging.Level;

public class TimetableCommand extends Command {

    private String subCommand;
    private String[] parsedArguments;

    public TimetableCommand(String subCommand, String[] parsedArguments) {
        this.subCommand = subCommand;
        this.parsedArguments = parsedArguments;
    }

    @Override
    public CommandResult executeCommand() throws KolinuxException {
        switch (subCommand) {
        case "add":
            Lesson lesson = new Lesson(parsedArguments);
            Timetable.addLesson(lesson);
            logger.log(Level.INFO, "User added a module to timetable");
            return new CommandResult("Lesson has been added to timetable");
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
            return new CommandResult("Ensure command has one of the following formats:\n"
                    +
                    "1. timetable add DESCRIPTION/DAY/START_TIME/END_TIME");
        }
    }

}
