package seedu.duke;

import seedu.duke.commands.CalculateCapCommand;
import seedu.duke.commands.Command;
import seedu.duke.commands.ExitCommand;
import seedu.duke.commands.HelpCommand;
import seedu.duke.commands.InvalidCommand;
import seedu.duke.commands.BusRouteCommand;
import java.io.FileNotFoundException;
import seedu.duke.commands.ViewModuleInfoCommand;


public class Parser {

    public static Command parseCommand(String input) throws FileNotFoundException {

        String trimmedInput = input.trim();
        String commandWord = trimmedInput.split(" ")[0];
        String argument = trimmedInput.replaceFirst(commandWord, "").trim();

        switch (commandWord.toLowerCase()) {
        case "help":
            return new HelpCommand();
        case "cap":
            return new CalculateCapCommand(input);
        case "bus":
            return new BusRouteCommand();
        case "view":
            return new ViewModuleInfoCommand(argument);
        case "bye":
            return new ExitCommand();
        default:
            return new InvalidCommand();
        }
    }
}
