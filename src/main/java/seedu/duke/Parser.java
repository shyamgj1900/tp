package seedu.duke;

import seedu.duke.commands.Command;
import seedu.duke.commands.ExitCommand;
import seedu.duke.commands.InvalidCommand;

public class Parser {

    public static Command parseCommand(String input) {

        String commandWord = input.split(" ")[0];
        switch (commandWord) {
        case "bye":
            return new ExitCommand();
        default:
            return new InvalidCommand();
        }
    }
}
