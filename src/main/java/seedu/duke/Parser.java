package seedu.duke;

import java.io.FileNotFoundException;

import seedu.duke.commands.*;
import seedu.duke.module.ModuleDb;


public class Parser {

    private static String[] trimAllElementsOfArray(String[] strings) {
        String[] trimmedStrings = new String[strings.length];
        for (int i = 0; i < strings.length; i++) {
            trimmedStrings[i] = strings[i].trim();
        }
        return trimmedStrings;
    }

    public static Command parseCommand(ModuleDb db, String input) throws FileNotFoundException {

        String trimmedInput = input.trim();
        String commandWord = trimmedInput.split(" ", 2)[0];
        String argument = trimmedInput.replaceFirst(commandWord, "").trim();

        switch (commandWord.toLowerCase()) {
        case "help":
            return new HelpCommand();
        case "cap":
            return new CalculateCapCommand(input);
        case "bus":
            return new BusRouteCommand();
        case "view":
            return new ViewModuleInfoCommand(db, argument);
        case "planner":
            return parsePlannerArgument(argument);
        case "bye":
            return new ExitCommand();
        case "timetable":
            return parseTimetableArgument(argument);
        default:
            return new InvalidCommand();
        }
    }

    public static Command parsePlannerArgument(String subInput) {

        String subCommand = subInput.split(" ", 2)[0];
        String argument = subInput.replaceFirst(subCommand, "").trim();
        String[] parsedArguments = trimAllElementsOfArray(argument.split("/"));
        return new PlannerCommand(subCommand, parsedArguments);
    }

    public static Command parseTimetableArgument(String input) {
        String subCommand = input.split("", 2)[0];
        String argument = input.replaceFirst(subCommand, "").trim();
        String[] parsedArguments = trimAllElementsOfArray(argument.split("/"));
        return new TimetableCommand(subCommand, parsedArguments);
    }
}
