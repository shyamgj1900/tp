package seedu.kolinux.timetable.lesson;

import seedu.kolinux.exceptions.KolinuxException;

public class Recitation extends Lesson {

    public Recitation(String[] parsedArguments) throws KolinuxException {
        super(parsedArguments);
    }

    @Override
    public String getLessonType() {
        return "REC";
    }

    @Override
    public String getFileContent() {
        return moduleCode + "/REC/" + day + "/" + startTime + "/" + endTime;
    }

}
