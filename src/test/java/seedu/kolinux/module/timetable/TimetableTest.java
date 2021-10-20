package seedu.kolinux.module.timetable;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.kolinux.exceptions.KolinuxException;
import seedu.kolinux.module.ModuleDb;
import seedu.kolinux.module.ModuleList;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.kolinux.module.timetable.Lesson.days;
import static seedu.kolinux.module.timetable.Lesson.getIndex;
import static seedu.kolinux.module.timetable.Lesson.schoolHours;
import static seedu.kolinux.module.timetable.Timetable.timetableData;
import static seedu.kolinux.module.timetable.Timetable.INVALID_ADD_FORMAT;
import static seedu.kolinux.module.timetable.Timetable.isLessonInTimetable;
import static seedu.kolinux.module.timetable.Timetable.INACCESSIBLE_PERIOD;
import static seedu.kolinux.module.timetable.Timetable.MISSING_LESSON_TO_DELETE;


public class TimetableTest {

    private static final String[] VALID_ADD_ARGUMENTS = new String[] {"CS1231", "TUT", "monday", "1200", "1300"};
    private static final String[] INVALID_ADD_TIMING = new String[] {"CS1231", "TUT", "monday", "1300", "1200"};
    private static final String[] INACCESSIBLE_ADD_PERIOD = new String[] {"CS1010", "TUT", "monday", "1200", "1300"};
    private static final String[] INVALID_DELETE_ARGUMENT = new String[] {"CS1010", "LESSON", "monday"};
    private static final String[] UPDATE_LESSON_ARGUMENTS = new String[] {"CS1231", "TUT", "monday", "tuesday", "1500"};
    private static final ModuleDb moduleDb = new ModuleDb();
    private static ModuleList moduleList = new ModuleList();


    @BeforeAll
    public static void initModuleDb() {
        moduleDb.initModuleDb();
    }

    @BeforeEach
    public void clear() {
        moduleList.clear();
    }

    @Test
    public void addLesson_validInput_lessonAdded() throws KolinuxException {
        Timetable.clearTimetable();
        Lesson lesson = new Lesson(VALID_ADD_ARGUMENTS);
        Timetable.addLesson(lesson);
        assertEquals(timetableData[getIndex("1200", schoolHours)][getIndex("monday", days)],
                "CS1231 TUT");
        Timetable.clearTimetable();
    }

    @Test
    public void addLesson_invalidTiming_lessonNotAdded() {
        try {
            Timetable.clearTimetable();
            Lesson lesson = new Lesson(INVALID_ADD_TIMING);
            Timetable.addLesson(lesson);
            Timetable.clearTimetable();
        } catch (KolinuxException exception) {
            assertEquals(INVALID_ADD_FORMAT, exception.getMessage());
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
        } catch (KolinuxException exception) {
            assertEquals(INACCESSIBLE_PERIOD, exception.getMessage());
        }
    }

    @Test
    public void inputLesson_validLesson_lessonAdded() throws KolinuxException {
        Timetable.clearTimetable();
        moduleList.storeModuleByCode("CS1231", moduleDb);
        Timetable.inputLesson(VALID_ADD_ARGUMENTS, moduleList);
        assertEquals("CS1231 TUT",
                timetableData[getIndex("1200", schoolHours)][getIndex("monday", days)]);
    }

    @Test
    public void inputLesson_lessonNotInModuleList_lessonNotAdded() {
        try {
            Timetable.clearTimetable();
            Timetable.inputLesson(VALID_ADD_ARGUMENTS, moduleList);
        } catch (KolinuxException exception) {
            assertEquals("CS1231 not found in module list", exception.getMessage());
        }
    }

    @Test
    public void deleteLesson_validLesson_lessonDeleted() throws KolinuxException {
        Timetable.clearTimetable();
        Lesson lesson = new Lesson(VALID_ADD_ARGUMENTS);
        Timetable.addLesson(lesson);
        Timetable.deleteLesson(VALID_ADD_ARGUMENTS);
        assertFalse(isLessonInTimetable("CS1010", "TUT", "monday"));
        Timetable.clearTimetable();
    }

    @Test
    public void deleteLesson_lessonNotInTimetable_lessonNotDeleted() {
        try {
            Timetable.clearTimetable();
            Timetable.deleteLesson(VALID_ADD_ARGUMENTS);
        } catch (KolinuxException e) {
            assertEquals("CS1231 TUT" + Timetable.MISSING_LESSON_TO_DELETE, e.getMessage());

        }
    }

    @Test
    public void deleteLesson_invalidLesson_lessonNotDeleted() {
        try {
            Timetable.clearTimetable();
            Timetable.deleteLesson(INVALID_DELETE_ARGUMENT);
            Timetable.clearTimetable();
        } catch (KolinuxException e) {
            assertEquals("CS1010 LESSON" + MISSING_LESSON_TO_DELETE, e.getMessage());
        }
    }

    @Test
    public void updateLesson_validLesson_lessonUpdated() throws KolinuxException {
        Timetable.clearTimetable();
        moduleList.storeModuleByCode("CS1231", moduleDb);
        Timetable.inputLesson(VALID_ADD_ARGUMENTS, moduleList);
        Timetable.updateTimetable(UPDATE_LESSON_ARGUMENTS, moduleList);
        assertFalse(isLessonInTimetable("CS1231", "TUT", "monday"));
        assertTrue(isLessonInTimetable("CS1231", "TUT", "tuesday"));
        Timetable.clearTimetable();
    }

    @Test
    public void updateLesson_lessonNotFoundInTimetable_lessonNotUpdated() {
        try {
            Timetable.clearTimetable();
            moduleList.storeModuleByCode("CS1231", moduleDb);
            Timetable.updateTimetable(UPDATE_LESSON_ARGUMENTS, moduleList);
            Timetable.clearTimetable();
        } catch (KolinuxException e) {
            assertEquals(Timetable.MISSING_LESSON_TO_UPDATE, e.getMessage());
        }
    }

}
