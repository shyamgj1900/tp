package seedu.duke.commands;

public class CommandResult {

    private String result;

    public CommandResult(String result) {
        this.result = result;
    }

    public String toString() {
        return result;
    }
}
