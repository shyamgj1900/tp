package seedu.duke;

import seedu.duke.commands.Command;
import seedu.duke.commands.CommandResult;
import seedu.duke.commands.ExitCommand;
import seedu.duke.exceptions.KolinuxException;
import seedu.duke.module.ModuleDb;
import seedu.duke.module.timetable.Timetable;
import seedu.duke.planner.Planner;

import java.io.FileNotFoundException;
import java.util.Scanner;

/** Represents the operations to start and run Kolinux. */
public class Kolinux {

    private Ui ui = new Ui();

    /**
     * Initializes Kolinux by starting the module information internal database, logger, and planner.
     */
    private void initKolinux() {
        try {
            KolinuxLogger.initLogger();
            ModuleDb.initModuleDb();
            Planner.initPlanner();
            Timetable.initTimetable();
        } catch (KolinuxException | FileNotFoundException exception) {
            ui.showErrorMessage((KolinuxException) exception);
        }
    }

    /**
     * Infinite loop that executes user inputs repeatedly until the user prompts to exit the application.
     */
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
            } catch (KolinuxException | FileNotFoundException exception) {
                assert exception instanceof KolinuxException;
                ui.showErrorMessage((KolinuxException) exception);
            }
        }
    }

    /**
     * Greets the user, initializes databases, and runs user inputs in a loop.
     */
    public void run() {
        ui.greetUser();
        initKolinux();
        runCommandInLoop();
    }
}
