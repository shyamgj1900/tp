package seedu.duke;

import seedu.duke.commands.Command;
import seedu.duke.commands.CommandResult;
import seedu.duke.commands.ExitCommand;

import java.util.Scanner;

public class Kolinux {

    private Ui ui = new Ui();

    private void runCommandInLoop() {

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String userInput = scanner.nextLine();
            Command command = Parser.parseCommand(userInput);
            CommandResult result = command.executeCommand();
            ui.showResultToUser(result);
            if (command instanceof ExitCommand) {
                break;
            }
        }
    }

    public void run() {
        ui.greetUser();
        runCommandInLoop();
    }
}
