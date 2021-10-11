package seedu.duke.commands;

/** Represents the result from the execution of a command. */
public class CommandResult {

    private String feedbackToUser;

    public CommandResult(String feedbackToUser) {
        this.feedbackToUser = feedbackToUser;
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }
}
