package seedu.kolinux.timetable.subcommand;

import seedu.kolinux.exceptions.KolinuxException;

import java.util.Objects;

import static seedu.kolinux.timetable.lesson.Lesson.days;
import static seedu.kolinux.timetable.lesson.Lesson.getIndex;
import static seedu.kolinux.timetable.Timetable.lessonStorage;
import static seedu.kolinux.timetable.Timetable.timetableData;
import static seedu.kolinux.timetable.Timetable.timetableStorage;

public class DeleteSubCommand extends SubCommand {

    public DeleteSubCommand() {

    }

    private void deleteFromTimetable(String moduleCode, String lessonType, int dayIndex) {
        String description = moduleCode + " " + lessonType;
        for (int i = 0; i < ROW_SIZE; i++) {
            assert dayIndex < COLUMN_SIZE;
            if (Objects.equals(timetableData[i][dayIndex], description)) {
                timetableData[i][dayIndex] = null;
            }
        }
    }

    public void deleteLesson(String[] parsedArguments) throws KolinuxException {
        try {
            String moduleCode = parsedArguments[0].toUpperCase();
            String lessonType = parsedArguments[1].toUpperCase();
            String day = parsedArguments[2].toLowerCase();
            int dayIndex = getIndex(day, days);
            deleteFromTimetable(moduleCode, lessonType, dayIndex);
            int removeIndex = -1;
            for (int j = 0; j < lessonStorage.size(); j++) {
                String typeInStorage = lessonStorage.get(j).getLessonType();
                String codeInStorage = lessonStorage.get(j).getModuleCode();
                String dayInStorage = lessonStorage.get(j).getDay();
                if (typeInStorage.equals(lessonType) && codeInStorage.equals(moduleCode)
                        && dayInStorage.equals(day)) {
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
        } catch (ArrayIndexOutOfBoundsException exception) {
            throw new KolinuxException(INVALID_DELETE_FORMAT);
        }
    }
}
