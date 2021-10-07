package seedu.duke;

import seedu.duke.commands.CommandResult;
import seedu.duke.exceptions.KolinuxException;

public class Ui {

    private static final String LOGO = "\n"
            + "(_)   | |    | |(_)\n"
            + " _____| |___ | | _ ____  _   _ _   _\n"
            + "|  _   _) _ \\| || |  _ \\| | | ( \\ / )\n"
            + "| |  \\ \\ |_| | || | | | | |_| |) X (\n"
            + "|_|   \\_)___/ \\_)_|_| |_|____/(_/ \\_)";
    private static final String GREET_MESSAGE = "Welcome to Kolinux! Enter \"help\" to view the list of commands";

    public void greetUser() {
        System.out.println(LOGO + "\n" + GREET_MESSAGE);
    }

    public void showResultToUser(CommandResult result) {
        System.out.println(result.getFeedbackToUser());
    }

    public void showErrorMessage(KolinuxException exception) {
        System.out.println(exception.getMessage());
    }
}
