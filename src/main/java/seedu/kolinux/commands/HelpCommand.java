package seedu.kolinux.commands;

import java.util.logging.Level;

/** Represents the command to print the help menu to the user. */
public class HelpCommand extends Command {

    private static final String HELP_MESSAGE = "Here are the list of commands:\n"
            + "1. cap mc MC/GRADE - Calculates the overall cap from MC\n"
            + "2. cap code MODULE_CODE/GRADE - Calculates the overall cap from MODULE_CODE\n"
            + "3. bus /START_POINT /END_POINT - Check for a NUS bus route from stop to another\n"
            + "4. bus stop list - List all the bus stop names\n"
            + "5. module view MODULE_CODE - View the module details\n"
            + "6. module add MODULE_CODE - Add a module to your module list\n"
            + "7. module delete MODULE_CODE - Delete a module from your module list\n"
            + "8. module list - List all modules stored in your module list\n"
            + "9. module grade CODE/GRADE - Update the module CODE from your module list with a new grade GRADE\n"
            + "10. module cap - Calculate the overall CAP of modules stored in your module list\n"
            + "11. module cap DESIRED_CAP - Calculate the average minimum grade for the other modules needed\n"
            + "    to achieve DESIRED_CAP\n"
            + "12. planner add DESCRIPTION/DATE/START_TIME/END_TIME - Add an event to your schedule\n"
            + "13. planner list DATE - Lists events on a certain date\n"
            + "14. planner delete DATE - Delete an event on a certain date\n"
            + "15. timetable add MODULE_CODE/LESSON_TYPE/DAY/START_TIME/END_TIME - Add lesson to timetable\n"
            + "16. timetable update MODULE_CODE/LESSON_TYPE/OLD_DAY/OLD_START_TIME/NEW_DAY/NEW_START_TIME\n"
            + "    - shift a lesson to another timing with the same duration\n"
            + "17. timetable delete MODULE_CODE/LESSON_TYPE/DAY/START_TIME - delete a specific lesson\n"
            + "18. timetable view - Print the timetable on CLI\n"
            + "19. help - View this menu again\n"
            + "20. bye - Exit Kolinux";

    @Override
    public CommandResult executeCommand() {
        logger.log(Level.INFO, "User needed help");
        return new CommandResult(HELP_MESSAGE);
    }
}
