package seedu.kolinux.util;

import java.util.Scanner;

public class Prompt {

    private Scanner scanner = new Scanner(System.in);

    public Prompt(String prompt) {
        System.out.println(prompt);
    }

    public String getReply() {
        return scanner.nextLine();
    }
}
