package seedu.kolinux.util;

import java.util.logging.Logger;

/** Represents the operations related to prompt the user to give additional confirmation. */
public class PromptHandler {

    protected Ui ui = new Ui();
    protected String message;
    protected static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public PromptHandler(String message) {
        this.message = message;
    }

    public String toString() {
        return message;
    }

    /**
     * To change the message on the same object of the PromptHandler.
     *
     * @param message Message to change to
     */
    public void changeMessage(String message) {
        this.message = message;
    }

    /**
     * Gets a reply from the user by posting a prompt on the terminal. This is only used if the program needs
     * the users to confirm their choice or to authenticate themselves.
     *
     * @return Reply entered by the user
     */
    protected String getReplyFromPrompt() {
        ui.promptUser(this);
        return ui.readUserInput().trim();
    }
}
