package seedu.kolinux.util;

import seedu.kolinux.commands.Command;
import seedu.kolinux.commands.HelpCommand;
import seedu.kolinux.commands.CalculateCapCommand;
import seedu.kolinux.commands.BusRouteCommand;
import seedu.kolinux.commands.StoreModuleCommand;
import seedu.kolinux.commands.DeleteModuleCommand;
import seedu.kolinux.commands.ListModulesCommand;
import seedu.kolinux.commands.InvalidCommand;
import seedu.kolinux.commands.PlannerCommand;
import seedu.kolinux.commands.TimetableCommand;
import seedu.kolinux.commands.ExitCommand;
import seedu.kolinux.commands.ViewModuleInfoCommand;
import seedu.kolinux.exceptions.KolinuxException;

import java.util.ArrayList;

/**
 * Represents the operations to parse information needed for the execution of a command.
 */
public class Parser {

    private static final String COMMAND_HELP = "help";
    private static final String COMMAND_CAP = "cap";
    private static final String COMMAND_BUS = "bus";
    private static final String COMMAND_VIEW_MODULE = "view";
    private static final String COMMAND_STORE_MODULE = "store_module";
    private static final String COMMAND_DELETE_MODULE = "delete_module";
    private static final String COMMAND_PLANNER = "planner";
    private static final String COMMAND_EXIT = "bye";
    private static final String COMMAND_TIMETABLE = "timetable";
    private static final String COMMAND_LIST = "list";
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
        case COMMAND_VIEW_MODULE:
            return new ViewModuleInfoCommand(argument);
        case COMMAND_STORE_MODULE:
            return new StoreModuleCommand(argument);
        case COMMAND_DELETE_MODULE:
            return new DeleteModuleCommand(argument);
        case COMMAND_PLANNER:
            return parseSubCommand(argument, COMMAND_PLANNER);
        case COMMAND_EXIT:
            return new ExitCommand();
        case COMMAND_TIMETABLE:
            return parseSubCommand(argument, COMMAND_TIMETABLE);
        case COMMAND_LIST:
            return new ListModulesCommand();
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
}
