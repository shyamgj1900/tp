package seedu.kolinux.timetable.lesson;

import seedu.kolinux.exceptions.KolinuxException;

/**
 * Represents a {@code Lesson} of type Tutorial.
 */
public class Tutorial extends Lesson {

    public Tutorial(String[] parsedArguments) throws KolinuxException {
        super(parsedArguments);
    }

    @Override
    public String getLessonType() {
        return "TUT";
    }

    @Override
    public String getFileContent() {
        return moduleCode + "/TUT/" + day + "/" + startTime + "/" + endTime;
    }

}
