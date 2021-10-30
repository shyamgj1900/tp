package seedu.kolinux.timetable.subcommand;

import seedu.kolinux.exceptions.KolinuxException;

import java.util.Objects;

import static seedu.kolinux.timetable.Timetable.lessonStorage;
import static seedu.kolinux.timetable.Timetable.timetableData;
import static seedu.kolinux.timetable.Timetable.timetableStorage;
import static seedu.kolinux.timetable.lesson.Lesson.getIndex;
import static seedu.kolinux.timetable.lesson.Lesson.days;
import static seedu.kolinux.timetable.lesson.Lesson.schoolHours;

public class DeleteSubCommand extends SubCommand {

    public DeleteSubCommand() {

    }

    private void deleteFromTimetable(String moduleCode, String lessonType, String day, String startTime) {
        int dayIndex = getIndex(day, days);
        int startIndex = getIndex(startTime, schoolHours);
        String description = moduleCode + " " + lessonType;
        for (int i = startIndex; i < ROW_SIZE; i++) {
            assert dayIndex < COLUMN_SIZE;
            if (Objects.equals(timetableData[i][dayIndex], description)) {
                timetableData[i][dayIndex] = null;
            } else {
                break;
            }
        }
    }

    private void deleteFromStorage(String moduleCode, String lessonType, String day, String startTime)
            throws KolinuxException {
        int removeIndex = -1;
        for (int j = 0; j < lessonStorage.size(); j++) {
            String typeInStorage = lessonStorage.get(j).getLessonType();
            String codeInStorage = lessonStorage.get(j).getModuleCode();
            String dayInStorage = lessonStorage.get(j).getDay();
            String startTimeInStorage = lessonStorage.get(j).getStartTime();
            if (typeInStorage.equals(lessonType) && codeInStorage.equals(moduleCode)
                    && dayInStorage.equals(day) && startTimeInStorage.equals(startTime)) {
                removeIndex = j;
            }
        }
        String description = moduleCode + " " + lessonType;
        if (removeIndex != -1) {
            lessonStorage.remove(removeIndex);
            timetableStorage.writeToFile();
        } else {
            throw new KolinuxException(description + MISSING_LESSON_TO_DELETE);
        }
    }

    public void deleteLesson(String[] lessonDetails) throws KolinuxException {
        try {
            String moduleCode = lessonDetails[0].toUpperCase();
            String lessonType = lessonDetails[1].toUpperCase();
            String day = lessonDetails[2].toLowerCase();
            String startTime = lessonDetails[3];
            deleteFromTimetable(moduleCode, lessonType, day, startTime);
            deleteFromStorage(moduleCode, lessonType, day, startTime);
        } catch (ArrayIndexOutOfBoundsException exception) {
            throw new KolinuxException(INVALID_DELETE_FORMAT);
        }
    }
}
