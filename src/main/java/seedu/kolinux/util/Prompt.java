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

    /**
     * This method is called by other objects that need to display a prompt on the Ui for the user to reply to.
     */
    public void postPrompt() {
        ui.promptUser(this);
    }

    /**
     * Gets the user reply through the Ui.
     *
     * @return User reply
     */
    public String getReply() {
        String reply = ui.readUserInput().trim();
        return reply;
    }
}
