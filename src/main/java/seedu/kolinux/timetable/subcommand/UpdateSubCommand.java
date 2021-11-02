package seedu.kolinux.timetable.subcommand;

import seedu.kolinux.exceptions.KolinuxException;
import seedu.kolinux.timetable.lesson.Lesson;

import java.util.Objects;

import static seedu.kolinux.timetable.Timetable.lessonStorage;
import static seedu.kolinux.timetable.lesson.Lesson.getIndex;
import static seedu.kolinux.timetable.lesson.Lesson.schoolHours;

public class UpdateSubCommand extends SubCommand {

    private AddSubCommand addSubcommand = new AddSubCommand();
    private DeleteSubCommand deleteSubcommand = new DeleteSubCommand();

    public UpdateSubCommand() {

    }

    public void updateTimetable(String[] lessonDetails) throws KolinuxException {
        try {
            String moduleCode = lessonDetails[0].toUpperCase();
            String lessonType = lessonDetails[1].toUpperCase();
            String oldDay = lessonDetails[2].toLowerCase();
            String oldStartTiming = lessonDetails[3];
            String newDay = lessonDetails[4].toLowerCase();
            String newStartTiming = lessonDetails[5];
            int startIndex = getIndex(newStartTiming, schoolHours);
            int endIndex = startIndex + getOldLessonHours(moduleCode, lessonType, oldDay, oldStartTiming);
            String newEndTiming = schoolHours[endIndex - 1];
            checkUpdateTiming(moduleCode, lessonType, oldDay, newDay, oldStartTiming, newStartTiming, newEndTiming);
            String[] parameters = new String[] {moduleCode, lessonType, newDay, newStartTiming, newEndTiming};

            if (isLessonInTimetable(moduleCode, lessonType, oldDay, oldStartTiming)) {
                deleteSubcommand.deleteLesson(lessonDetails);
                addSubcommand.inputLesson(parameters);
            } else {
                throw new KolinuxException(MISSING_LESSON_TO_UPDATE);
            }
        } catch (ArrayIndexOutOfBoundsException exception) {
            throw new KolinuxException(INVALID_UPDATE_FORMAT);
        }
    }

    private int getOldLessonHours(String moduleCode, String lessonType, String day, String oldStartTiming) {
        for (Lesson lesson : lessonStorage) {
            if (lesson.getModuleCode().equals(moduleCode) && lesson.getLessonType().equals(lessonType)
                    && lesson.getDay().equals(day) && lesson.getStartTime().equals(oldStartTiming)) {
                return lesson.getEndTimeIndex() - lesson.getStartTimeIndex();
            }
        }
        return -1;
    }

    private String getOldEndTiming(String moduleCode, String lessonType, String day, String startTime) {
        String oldEndTime = null;
        for (Lesson lesson : lessonStorage) {
            if (lesson.getModuleCode().equals(moduleCode) && lesson.getLessonType().equals(lessonType)
                    && lesson.getDay().equals(day) && lesson.getStartTime().equals(startTime)) {
                oldEndTime = lesson.getEndTime();
            }
        }
        return oldEndTime;
    }

    private void checkUpdateTiming(String moduleCode, String lessonType, String oldDay, String newDay,
            String oldStartTiming, String newStartTiming, String newEndTiming) throws KolinuxException {
        String oldEndTiming = getOldEndTiming(moduleCode, lessonType, oldDay, oldStartTiming);
        if (Objects.equals(oldDay, newDay) && Objects.equals(oldStartTiming, newStartTiming)
                && Objects.equals(oldEndTiming, newEndTiming)) {
            throw new KolinuxException(UPDATING_TO_SAME_TIMING);
        }
    }

}
