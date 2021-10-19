package seedu.kolinux.commands;

import java.util.logging.Level;

/** Represents the command to print the help menu to the user. */
public class HelpCommand extends Command {

    private static final String HELP_MESSAGE = "Here are the list of commands:\n"
            + "1. cap MC_AND_GRADES  - Calculates the total cap for the semester\n"
            + "2. bus /START_POINT /END_POINT - Check for a NUS bus route from stop to another\n"
            + "3. module view MODULE_CODE - View the module details\n"
            + "4. module store MODULE_CODE - Add a module to your module list\n"
            + "5. module delete MODULE_CODE - Delete a module from your module list\n"
            + "6. module list - List all modules stored in your module list\n"
            + "7. module grade CODE/GRADE - Update the module CODE from your module list with a new grade GRADE\n"
            + "8. planner add DESCRIPTION/DATE/START_TIME/END_TIME - Add an event to your schedule\n"
            + "9. planner list DATE - Lists events on a certain date\n"
            + "10. planner delete DATE - Delete an event on a certain date\n"
            + "11. timetable add MODULE_CODE/LESSON_TYPE/DAY/START_TIME/END_TIME - Add lesson to timetable\n"
            + "12. timetable update MODULE_CODE/LESSON_TYPE/OLD_DAY/NEW_DAY/NEW_START_TIME/NEW_END_TIME\n"
            + "    - shift a lesson to another timing\n"
            + "13. timetable delete MODULE_CODE/LESSON_TYPE/DAY - delete a specific lesson\n"
            + "14. timetable clear - Remove all lessons from timetable\n"
            + "15. timetable view - Print the timetable on CLI\n"
            + "16. help - View this menu again\n"
            + "17. bye - Exit Kolinux";

    @Override
    public CommandResult executeCommand() {
        logger.log(Level.INFO, "User needed help");
        return new CommandResult(HELP_MESSAGE);
    }
}
