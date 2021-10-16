package seedu.kolinux.module.timetable;

import org.junit.jupiter.api.Test;

import seedu.kolinux.exceptions.KolinuxException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.kolinux.module.timetable.Lesson.days;
import static seedu.kolinux.module.timetable.Timetable.timetableData;
import static seedu.kolinux.module.timetable.Lesson.getIndex;
import static seedu.kolinux.module.timetable.Lesson.schoolHours;
import static seedu.kolinux.module.timetable.Timetable.INVALID_ADD_ARGUMENT;
import static seedu.kolinux.module.timetable.Timetable.INACCESSIBLE_PERIOD;


public class TimetableTest {

    private static final String[] VALID_ADD_ARGUMENTS = new String[] {"TUT","CS1010", "monday", "1200", "1300"};
    private static final String[] INVALID_ADD_TIMING = new String[] {"TUT","CS1010", "monday", "1300", "1200"};
    private static final String[] INACCESSIBLE_ADD_PERIOD = new String[] {"TUT","CS1231", "monday", "1200", "1300"};

    @Test
    public void addLesson_validInput_lessonAdded() throws KolinuxException {
        Timetable.clearTimetable();
        Lesson lesson = new Lesson(VALID_ADD_ARGUMENTS);
        Timetable.addLesson(lesson);
        assertEquals(timetableData[getIndex("1200", schoolHours)][getIndex("monday", days)],
                "CS1010 TUT");
        Timetable.clearTimetable();
    }

    @Test
    public void addLesson_invalidTiming_lessonNotAdded() {
        try {
            Timetable.clearTimetable();
            Lesson lesson = new Lesson(INVALID_ADD_TIMING);
            Timetable.addLesson(lesson);
            Timetable.clearTimetable();
        } catch (KolinuxException e) {
            assertEquals(INVALID_ADD_ARGUMENT, e.getMessage());
        }
    }

    @Test
    public void addLesson_inaccessiblePeriod_lessonNotAdded() {
        try {
            Timetable.clearTimetable();
            Lesson firstLesson = new Lesson(VALID_ADD_ARGUMENTS);
            Lesson secondLesson = new Lesson(INACCESSIBLE_ADD_PERIOD);
            Timetable.addLesson(firstLesson);
            Timetable.addLesson(secondLesson);
            Timetable.clearTimetable();
        } catch (KolinuxException e) {
            assertEquals(INACCESSIBLE_PERIOD, e.getMessage());
        }
    }

}
