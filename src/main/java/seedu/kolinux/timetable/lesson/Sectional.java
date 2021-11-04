package seedu.kolinux.timetable.lesson;

import seedu.kolinux.exceptions.KolinuxException;

/**
 * Represents a {@code Lesson} of type Sectional.
 */
public class Sectional extends Lesson {

    public Sectional(String[] parsedArguments) throws KolinuxException {
        super(parsedArguments);
    }

    @Override
    public String getLessonType() {
        return "SEC";
    }

    @Override
    public String getFileContent() {
        return moduleCode + "/SEC/" + day + "/" + startTime + "/" + endTime;
    }

}
