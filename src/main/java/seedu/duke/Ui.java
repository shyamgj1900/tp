package seedu.duke;

import seedu.duke.commands.CommandResult;

public class Ui {

    public void greetUser() {

        String logo = "\n"
                + "(_)   | |    | |(_)\n"
                + " _____| |___ | | _ ____  _   _ _   _\n"
                + "|  _   _) _ \\| || |  _ \\| | | ( \\ / )\n"
                + "| |  \\ \\ |_| | || | | | | |_| |) X (\n"
                + "|_|   \\_)___/ \\_)_|_| |_|____/(_/ \\_)";
        String greetMessage = "Welcome to Kolinux! Enter \"help\" to the list of commands";
        System.out.println(logo + "\n" + greetMessage);
    }

    public void showResultToUser(CommandResult result) {
        System.out.println(result);
    }
}
