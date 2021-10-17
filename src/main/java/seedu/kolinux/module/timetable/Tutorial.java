package seedu.kolinux.module.timetable;

import seedu.kolinux.exceptions.KolinuxException;

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
        return moduleCode + "/TUT/" + day + "/" + startTime;
    }

}
