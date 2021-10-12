package seedu.duke;

import seedu.duke.commands.CalculateCapCommand;
import seedu.duke.commands.Command;
import seedu.duke.commands.ExitCommand;
import seedu.duke.commands.HelpCommand;
import seedu.duke.commands.InvalidCommand;
import seedu.duke.commands.PlannerCommand;
import seedu.duke.commands.BusRouteCommand;
import seedu.duke.commands.ViewModuleInfoCommand;
import seedu.duke.commands.StoreModuleCommand;
import seedu.duke.commands.DeleteModuleCommand;
import seedu.duke.commands.TimetableCommand;
import seedu.duke.exceptions.KolinuxException;

/** Represents the operations to parse information needed for the execution of a command. */
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
        default:
            return new InvalidCommand();
        }
    }

    /**
     * Processes the arguments by separating the first word (sub-command) from the input.
     * The rest of the input is separated into a String array using the "/" delimiter.
     *
     * @param subInput User input without the command word
     * @param commandWord User commandWord
     * @return Command class according to commandWord
     */
    public static Command parseSubCommand(String subInput, String commandWord) throws KolinuxException {
        String subCommand = subInput.split(" ", 2)[0];
        String argument = subInput.replaceFirst(subCommand, "").trim();
        String[] parsedArguments = trimAllElementsOfArray(argument.split("/"));
        switch (commandWord) {
        case COMMAND_PLANNER:
            return new PlannerCommand(subCommand,parsedArguments);
        case COMMAND_TIMETABLE:
            return new TimetableCommand(subCommand,parsedArguments);
        default:
            throw new KolinuxException("Invalid command");
        }
    }

}
