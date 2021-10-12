package seedu.duke;

import seedu.duke.commands.CommandResult;
import seedu.duke.exceptions.KolinuxException;

/** Represents the user interface. */
public class Ui {

    private static final String LOGO = "\n"
            + "(_)   | |    | |(_)\n"
            + " _____| |___ | | _ ____  _   _ _   _\n"
            + "|  _   _) _ \\| || |  _ \\| | | ( \\ / )\n"
            + "| |  \\ \\ |_| | || | | | | |_| |) X (\n"
            + "|_|   \\_)___/ \\_)_|_| |_|____/(_/ \\_)";
    private static final String GREET_MESSAGE = "Welcome to Kolinux! Enter \"help\" to view the list of commands";
    private static final String DIVIDER = "....................................................................";

    private void printDivider() {
        System.out.println(DIVIDER);
    }

    /**
     * Prints the logo and greet message upon start-up of Kolinux.
     */
    public void greetUser() {
        System.out.println(LOGO + "\n" + GREET_MESSAGE);
        printDivider();
    }

    /**
     * Prints the feedback from the command execution to the user.
     *
     * @param result Feedback after command execution
     */
    public void showResultToUser(CommandResult result) {
        System.out.println(result.getFeedbackToUser());
        printDivider();
    }

    /**
     * Prints the error message from any exceptions encountered during command executions.
     *
     * @param exception Exception from command execution
     */
    public void showErrorMessage(KolinuxException exception) {
        System.out.println(exception.getMessage());
        printDivider();
    }
}
