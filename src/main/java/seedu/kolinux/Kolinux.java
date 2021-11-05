package seedu.kolinux;

import seedu.kolinux.commands.Command;
import seedu.kolinux.commands.CommandResult;
import seedu.kolinux.commands.ExitCommand;
import seedu.kolinux.exceptions.KolinuxException;
import seedu.kolinux.module.ModuleListStorage;
import seedu.kolinux.planner.Planner;
import seedu.kolinux.util.KolinuxLogger;
import seedu.kolinux.util.Parser;
import seedu.kolinux.util.DirectoryCreator;
import seedu.kolinux.util.Ui;

import java.io.IOException;

import static seedu.kolinux.commands.TimetableCommand.timetable;

/** Represents the operations to start and run Kolinux. */
public class Kolinux {

    private Ui ui = new Ui();
    private DirectoryCreator directoryCreator = new DirectoryCreator();
    private KolinuxLogger kolinuxLogger = new KolinuxLogger();
    private Planner planner = new Planner();

    /**
     * Initializes Kolinux by starting the module information internal database, logger, timetable, and planner.
     * It also initializes the "/data" directory in which user data files are stored.
     */
    private void initKolinux() {
        directoryCreator.initDirectory();
        kolinuxLogger.initLogger();
        ModuleListStorage.setupStorage();

        try {
            timetable.initTimetable();
        } catch (KolinuxException exception) {
            ui.showErrorMessage(exception);
        }

        try {
            planner.initPlanner();
        } catch (KolinuxException exception) {
            ui.showErrorMessage(exception);
        }
    }

    /**
     * Infinite loop that executes user inputs repeatedly until the user prompts to exit the application.
     */
    private void runCommandInLoop() {

        while (true) {
            try {
                String userInput = ui.readUserInput();
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
     * Greets the user, initializes databases and features, and runs user inputs in a loop.
     */
    public void run() {
        ui.greetUser();
        initKolinux();
        runCommandInLoop();
    }
}
