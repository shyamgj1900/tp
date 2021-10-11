package seedu.duke.commands;

import seedu.duke.exceptions.KolinuxException;
import seedu.duke.module.timetable.Timetable;

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
            Timetable.addModule(parsedArguments);
            logger.log(Level.INFO, "User added a module to timetable");
            return new CommandResult("Module has been added to timetable");
        default:
            logger.log(Level.INFO, "User used invalid subCommand for timetable");
            return new CommandResult("Ensure command has oen of the following formats:\n"
                    +
                    "1. timetable add DESCRIPTION/DAY/START_TIME/END_TIME\n");
        }
    }

}
