package seedu.kolinux.timetable.lesson;

import seedu.kolinux.exceptions.KolinuxException;

/**
 * Represents a {@code Lesson} of type Lab.
 */
public class Lab extends Lesson {

    public Lab(String[] parsedArguments) throws KolinuxException {
        super(parsedArguments);
    }

    @Override
    public String getLessonType() {
        return "LAB";
    }

    @Override
    public String getFileContent() {
        return moduleCode + "/LAB/" + day + "/" + startTime + "/" + endTime;
    }

}
