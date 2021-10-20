package seedu.kolinux.util;

import java.util.Scanner;

/** Represents a prompt for the user to give additional confirmation. */
public class Prompt {

    private Scanner scanner = new Scanner(System.in);

    public Prompt(String prompt) {
        System.out.println(prompt);
    }

    public String getReply() {
        return scanner.nextLine().trim();
    }
}
