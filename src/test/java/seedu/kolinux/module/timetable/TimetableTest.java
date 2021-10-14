package seedu.kolinux.module.timetable;

import org.junit.jupiter.api.Test;

import seedu.kolinux.exceptions.KolinuxException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.kolinux.module.timetable.Timetable.days;
import static seedu.kolinux.module.timetable.Timetable.timetableData;
import static seedu.kolinux.module.timetable.Timetable.getIndex;
import static seedu.kolinux.module.timetable.Timetable.timings;
import static seedu.kolinux.module.timetable.Timetable.INVALID_ADD_ARGUMENT;
import static seedu.kolinux.module.timetable.Timetable.INACCESSIBLE_PERIOD;


public class TimetableTest {

    private static final String[] VALID_ADD_ARGUMENTS = new String[] {"CS1010 tut", "monday", "1200", "1300"};
    private static final String[] INVALID_ADD_TIMING = new String[] {"CS1010 tut", "monday", "1300", "1200"};
    private static final String[] INACCESSIBLE_ADD_PERIOD = new String[] {"CS1010 tut", "monday", "1200", "1300"};


    @Test
    public void addLesson_validInput_lessonAdded() throws KolinuxException {
        Timetable.clearTimetable();
        Timetable.addLesson(VALID_ADD_ARGUMENTS);
        assertEquals(timetableData[getIndex("1200", timings)][getIndex("monday", days)],
                "CS1010 tut");
        Timetable.clearTimetable();
    }

    @Test
    public void addLesson_invalidTiming_lessonNotAdded() {
        try {
            Timetable.clearTimetable();
            Timetable.addLesson(INVALID_ADD_TIMING);
            Timetable.clearTimetable();
        } catch (KolinuxException e) {
            assertEquals(INVALID_ADD_ARGUMENT, e.getMessage());
        }
    }

    @Test
    public void addLesson_inaccessiblePeriod_lessonNotAdded() {
        try {
            Timetable.clearTimetable();
            Timetable.addLesson(VALID_ADD_ARGUMENTS);
            Timetable.addLesson(INACCESSIBLE_ADD_PERIOD);
            Timetable.clearTimetable();
        } catch (KolinuxException e) {
            assertEquals(INACCESSIBLE_PERIOD, e.getMessage());
        }
    }
}
