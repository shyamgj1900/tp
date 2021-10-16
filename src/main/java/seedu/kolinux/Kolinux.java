package seedu.kolinux;

import seedu.kolinux.commands.Command;
import seedu.kolinux.commands.CommandResult;
import seedu.kolinux.commands.ExitCommand;
import seedu.kolinux.exceptions.KolinuxException;
import seedu.kolinux.module.timetable.Timetable;
import seedu.kolinux.planner.Planner;
import seedu.kolinux.util.KolinuxLogger;
import seedu.kolinux.util.Parser;
import seedu.kolinux.util.Storage;
import seedu.kolinux.util.Ui;

import java.io.IOException;
import java.util.Scanner;

/** Represents the operations to start and run Kolinux. */
public class Kolinux {

    private Ui ui = new Ui();
    private Storage storage = new Storage();
    private KolinuxLogger kolinuxLogger = new KolinuxLogger();
    private Planner planner = new Planner();

    /**
     * Initializes Kolinux by starting the module information internal database, logger, timetable, and planner.
     */
    private void initKolinux() {
        try {
            storage.initStorage();
            kolinuxLogger.initLogger();
            planner.initPlanner();
            Timetable.initTimetable();
        } catch (KolinuxException exception) {
            ui.showErrorMessage(exception);
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
            } catch (KolinuxException | IOException exception) {
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
