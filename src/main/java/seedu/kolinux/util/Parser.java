package seedu.kolinux.util;

import seedu.kolinux.commands.CalculateModuleListCapCommand;
import seedu.kolinux.commands.Command;
import seedu.kolinux.commands.HelpCommand;
import seedu.kolinux.commands.CalculateCapCommand;
import seedu.kolinux.commands.BusRouteCommand;
import seedu.kolinux.commands.ModuleCommand;
import seedu.kolinux.commands.InvalidCommand;
import seedu.kolinux.commands.PlannerCommand;
import seedu.kolinux.commands.TimetableCommand;
import seedu.kolinux.commands.ExitCommand;
import seedu.kolinux.exceptions.KolinuxException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Represents the operations to parse information needed for the execution of a command.
 */
public class Parser {

    private static final String COMMAND_HELP = "help";
    private static final String COMMAND_CAP = "cap";
    private static final String COMMAND_BUS = "bus";
    private static final String COMMAND_MODULE = "module";
    private static final String COMMAND_PLANNER = "planner";
    private static final String COMMAND_EXIT = "bye";
    private static final String COMMAND_TIMETABLE = "timetable";
    private static final String EMPTY_STRING = "";

    /**
     * Removes leading and trailing white spaces from all the elements in a String array.
     *
     * @param strings Array of strings
     * @return Array of strings with all elements trimmed
     */
    private static String[] trimAllElementsOfArray(String[] strings) {
        String[] trimmedStrings = new String[strings.length];
        for (int i = 0; i < strings.length; i++) {
            trimmedStrings[i] = strings[i].trim();
        }
        return trimmedStrings;
    }

    /**
     * Gets the command word from the user input, and calls the respective Command subsequently for
     * execution.
     *
     * @param input User input
     * @return Command
     */
    public static Command parseCommand(String input) throws KolinuxException {

        String trimmedInput = input.trim();
        String commandWord = trimmedInput.split(" ", 2)[0];
        String argument = trimmedInput.replaceFirst(commandWord, "").trim();

        switch (commandWord.toLowerCase()) {
        case COMMAND_HELP:
            return new HelpCommand();
        case COMMAND_CAP:
            return new CalculateCapCommand(input);
        case COMMAND_BUS:
            return new BusRouteCommand(input);
        case COMMAND_MODULE:
            return parseSubCommand(argument, COMMAND_MODULE);
        case COMMAND_PLANNER:
            return parseSubCommand(argument, COMMAND_PLANNER);
        case COMMAND_EXIT:
            return new ExitCommand();
        case COMMAND_TIMETABLE:
            return parseSubCommand(argument, COMMAND_TIMETABLE);
        default:
            return new InvalidCommand();
        }
    }

    /**
     * Processes the arguments by separating the first word (sub-command) from the input.
     * The rest of the input is separated into a String array using the "/" delimiter.
     * This method should only be called if the commandWord is "timetable" or "planner".
     *
     * @param subInput    User input without the command word
     * @param commandWord User commandWord
     * @return Command class according to commandWord
     */
    public static Command parseSubCommand(String subInput, String commandWord) throws KolinuxException {
        String subCommand = subInput.split(" ", 2)[0];
        String argument = subInput.replaceFirst(subCommand, "").trim();
        String[] parsedArguments = trimAllElementsOfArray(argument.split("/"));
        switch (commandWord) {
        case COMMAND_PLANNER:
            return new PlannerCommand(subCommand, parsedArguments);
        case COMMAND_TIMETABLE:
            return new TimetableCommand(subCommand, parsedArguments);
        case COMMAND_MODULE:
            return new ModuleCommand(subCommand, parsedArguments);
        /*case COMMAND_CAP:
            return new CalculateCapCommand(subCommand, parsedArguments);*/
        default:
            throw new KolinuxException("Internal error occurred, please try again.");
        }
    }

    /**
     * Concatenates an array list of strings into a single string, starting with a newline and with newlines
     * separating consecutive entries.
     *
     * @param strings List of strings to be concatenated
     * @return Concatenated string of the list of strings
     */
    public static String concatenateStrings(ArrayList<String> strings) {
        String concatenatedString = EMPTY_STRING;
        for (String string : strings) {
            concatenatedString = concatenatedString.concat("\n" + string);
        }
        return concatenatedString;
    }

    public static int findDayFromDate(String date) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(date));
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        return day;
    }

    public static String parseDay(int day) {
        assert ((day >= 1) && (day <= 7));
        switch (day) {
        case 1:
            return "SUNDAY";
        case 2:
            return "MONDAY";
        case 3:
            return "TUESDAY";
        case 4:
            return "WEDNESDAY";
        case 5:
            return "THURSDAY";
        case 6:
            return "FRIDAY";
        case 7:
            return "SATURDAY";
        default:
            return "";
        }
    }
}
