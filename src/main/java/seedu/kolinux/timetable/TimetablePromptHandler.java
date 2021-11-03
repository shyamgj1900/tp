package seedu.kolinux.timetable;

import seedu.kolinux.exceptions.KolinuxException;
import seedu.kolinux.util.PromptHandler;


public class TimetablePromptHandler extends PromptHandler {

    private Timetable timetable;
    private static final String CANCEL_MESSAGE = "Adding to timetable operation cancelled";
    private static final String INVALID_KEY = "Invalid key. Please enter y or n";

    public TimetablePromptHandler(String message, Timetable timetable) {
        super(message);
        this.timetable = timetable;
    }

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
