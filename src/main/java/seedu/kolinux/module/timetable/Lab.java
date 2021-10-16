package seedu.kolinux.module.timetable;

import seedu.kolinux.exceptions.KolinuxException;

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
        return "LAB/" + moduleCode + "/" + day + "/" + startTime + "/" + endTime;
    }

}
