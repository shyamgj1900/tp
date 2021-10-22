package seedu.kolinux.timetable.subcommand;

import seedu.kolinux.exceptions.KolinuxException;
import seedu.kolinux.timetable.lesson.Lesson;

import java.util.Objects;

import static seedu.kolinux.timetable.Timetable.lessonStorage;
import static seedu.kolinux.timetable.lesson.Lesson.getIndex;
import static seedu.kolinux.timetable.lesson.Lesson.schoolHours;

public class UpdateSubcommand extends Subcommand {

    private AddSubcommand addSubcommand = new AddSubcommand();
    private DeleteSubcommand deleteSubcommand = new DeleteSubcommand();

    public UpdateSubcommand() {

    }

    public void updateTimetable(String[] parsedArguments) throws KolinuxException {
        try {
            String moduleCode = parsedArguments[0].toUpperCase();
            String lessonType = parsedArguments[1].toUpperCase();
            String oldDay = parsedArguments[2].toLowerCase();
            String newDay = parsedArguments[3].toLowerCase();
            String newStartTiming = parsedArguments[4];
            int startIndex = getIndex(newStartTiming, schoolHours);
            int endIndex = startIndex + getOldLessonHours(moduleCode, lessonType, oldDay);
            String[] oldTimings = getOldTimings(moduleCode, lessonType, oldDay);
            String newEndTiming = schoolHours[endIndex - 1];
            if (Objects.equals(oldDay, newDay) && Objects.equals(oldTimings[0], newStartTiming)
                    && Objects.equals(oldTimings[1], newEndTiming)) {
                throw new KolinuxException(UPDATING_TO_SAME_TIMING);
            }
            String[] parameters = new String[] {moduleCode, lessonType, newDay, newStartTiming, newEndTiming};
            if (isLessonInTimetable(moduleCode, lessonType, oldDay)) {
                deleteSubcommand.deleteLesson(parsedArguments);
                addSubcommand.inputLesson(parameters);
            } else {
                throw new KolinuxException(MISSING_LESSON_TO_UPDATE);
            }
        } catch (ArrayIndexOutOfBoundsException exception) {
            throw new KolinuxException(INVALID_UPDATE_FORMAT);
        }
    }

    private int getOldLessonHours(String moduleCode, String lessonType, String day) {
        for (Lesson lesson : lessonStorage) {
            if (lesson.getModuleCode().equals(moduleCode) && lesson.getLessonType().equals(lessonType)
                    && lesson.getDay().equals(day)) {
                return lesson.getEndTimeIndex() - lesson.getStartTimeIndex();
            }
        }
        return -1;
    }

    private String[] getOldTimings(String moduleCode, String lessonType, String day) {
        String[] timings = new String[2];
        for (Lesson lesson : lessonStorage) {
            if (lesson.getModuleCode().equals(moduleCode) && lesson.getLessonType().equals(lessonType)
                    && lesson.getDay().equals(day)) {
                timings[0] = lesson.getStartTime();
                timings[1] = lesson.getEndTime();
            }
        }
        return timings;
    }

    public boolean isLessonInTimetable(String lessonCode, String lessonType, String day) {
        for (Lesson storedLesson : lessonStorage) {
            String storedCode = storedLesson.getModuleCode();
            String storedType = storedLesson.getLessonType();
            String storedDay = storedLesson.getDay();
            if (storedCode.equals(lessonCode) && storedType.equals(lessonType) && storedDay.equals(day)) {
                return true;
            }
        }
        return false;
    }

}
