package seedu.kolinux.timetable;

import seedu.kolinux.exceptions.KolinuxException;
import seedu.kolinux.util.PromptHandler;

/** Represents the operations related to prompting the user while adding to the timetable. */
public class TimetablePromptHandler extends PromptHandler {

    private Timetable timetable;
    private static final String CANCEL_MESSAGE = "Adding to timetable operation cancelled";
    private static final String INVALID_KEY = "Invalid key. Please enter y or n";

    public TimetablePromptHandler(String message, Timetable timetable) {
        super(message);
        this.timetable = timetable;
    }

    /**
     * Sends a confirmation prompt to user to check if they want to continue adding the lesson despite exceeding the
     * stipulated workload, where the lesson gets added if the user confirms with "y" but cancels the operation if
     * user enters "n".
     *
     * @param lessonDetails Details of the lesson to be added
     * @throws KolinuxException If it fails any of the checks in addSubCommand before performing add operation
     */
    public void handleExceedWorkload(String[] lessonDetails) throws KolinuxException {
        while (true) {
            String reply = getReplyFromPrompt();
            if (reply.equalsIgnoreCase("y")) {
                timetable.executeAdd(lessonDetails, true);
                break;
            } else if (reply.equalsIgnoreCase("n")) {
                throw new KolinuxException(CANCEL_MESSAGE);
            } else {
                this.changeMessage(INVALID_KEY);
            }
        }
    }

}
