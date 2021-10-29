package seedu.kolinux.util;

/** Represents a prompt for the user to give additional confirmation. */
public class Prompt {

    private Ui ui = new Ui();
    private String message;

    public Prompt(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void postPrompt() {
        ui.promptUser(this);
    }

    public String getReply() {
        String reply = ui.readUserInput().trim();
        return reply;
    }
}
