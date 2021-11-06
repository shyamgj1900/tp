package seedu.kolinux.timetable.subcommand;

import seedu.kolinux.exceptions.KolinuxException;

import static seedu.kolinux.timetable.Timetable.lessonStorage;
import static seedu.kolinux.timetable.Timetable.timetableData;
import static seedu.kolinux.timetable.Timetable.timetableStorage;
import static seedu.kolinux.timetable.lesson.Lesson.getIndex;
import static seedu.kolinux.timetable.lesson.Lesson.days;
import static seedu.kolinux.timetable.lesson.Lesson.schoolHours;

public class DeleteSubCommand extends SubCommand {

    public DeleteSubCommand() {

    }

    /**
     * Deletes a lesson from the timetable data.
     *
     * @param day Day of the lesson to be deleted
     * @param startIndex Index of the starting time of the lesson to be deleted
     * @param endIndex Index of the ending time of the lesson to be deleted
     */
    private void deleteFromTimetable(String day, int startIndex, int endIndex) {
        int dayIndex = getIndex(day, days);
        for (int i = startIndex; i < endIndex; i++) {
            assert dayIndex < COLUMN_SIZE;
            timetableData[i][dayIndex] = null;
        }
    }

    /**
     * Deletes the lesson from the storage and calls the deleteFromTimetable method as well.
     *
     * @param moduleCode Module code of the lesson to be deleted
     * @param lessonType Lesson type of the lesson to be deleted
     * @param day Day of the lesson to be deleted
     * @param startTime Starting time of the lesson to be deleted
     * @throws KolinuxException If the lesson specified to be deleted is not found in the timetable
     */
    public void deleteLesson(String moduleCode, String lessonType, String day, String startTime)
            throws KolinuxException {
        int removeIndex = -1;
        int endIndex = -1;
        checkLessonType(lessonType, INVALID_DELETE_FORMAT);
        checkStartTimeAndDay(day,startTime,INVALID_DELETE_FORMAT);
        for (int j = 0; j < lessonStorage.size(); j++) {
            String typeInStorage = lessonStorage.get(j).getLessonType();
            String codeInStorage = lessonStorage.get(j).getModuleCode();
            String dayInStorage = lessonStorage.get(j).getDay();
            String startTimeInStorage = lessonStorage.get(j).getStartTime();
            if (typeInStorage.equals(lessonType) && codeInStorage.equals(moduleCode)
                    && dayInStorage.equals(day) && startTimeInStorage.equals(startTime)) {
                removeIndex = j;
                endIndex = lessonStorage.get(j).getEndTimeIndex();
            }
        }
        if (removeIndex != -1) {
            assert endIndex != -1;
            int startIndex = getIndex(startTime, schoolHours);
            deleteFromTimetable(day, startIndex, endIndex);
            lessonStorage.remove(removeIndex);
            timetableStorage.writeToFile();
        } else {
            throw new KolinuxException(MISSING_LESSON_TO_DELETE);
        }
    }

}
