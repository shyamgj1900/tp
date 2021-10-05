package seedu.duke;

import seedu.duke.commands.Command;
import seedu.duke.commands.CommandResult;
import seedu.duke.commands.ExitCommand;
import seedu.duke.exceptions.KolinuxException;
import seedu.duke.module.ModuleDb;

import java.util.Scanner;

public class Kolinux {

    private Ui ui = new Ui();

    private void runCommandInLoop() {

        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                String userInput = scanner.nextLine();
                Command command = Parser.parseCommand(userInput);
                CommandResult result = command.executeCommand();
                ui.showResultToUser(result);
                if (command instanceof ExitCommand) {
                    break;
                }
            } catch (KolinuxException exception) {
                ui.showErrorMessage(exception);
            }
        }
    }

    public void run() {
        ui.greetUser();
        ModuleDb.initModuleDb();
        runCommandInLoop();
    }
}
