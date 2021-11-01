package seedu.kolinux.timetable;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.kolinux.exceptions.KolinuxException;
import seedu.kolinux.module.ModuleDb;
import seedu.kolinux.module.ModuleList;
import seedu.kolinux.timetable.lesson.Lesson;
import seedu.kolinux.timetable.lesson.Tutorial;
import seedu.kolinux.timetable.subcommand.AddSubCommand;
import seedu.kolinux.timetable.subcommand.SubCommand;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.kolinux.timetable.lesson.Lesson.days;
import static seedu.kolinux.timetable.lesson.Lesson.getIndex;
import static seedu.kolinux.timetable.lesson.Lesson.schoolHours;


public class TimetableTest {

    private static final String[] VALID_ADD_TUTORIAL_ARGUMENTS = new String[] {"CS1231", "TUT", "monday",
            "0600", "0700"};
    private static final String[] ANOTHER_VALID_ADD_TUTORIAL_ARGUMENTS = new String[] {"CS1231", "TUT", "monday",
            "0700", "0800"};
    private static final String[] INVALID_ADD_TUTORIAL_TIMING = new String[] {"CS1231", "TUT", "monday",
            "1300", "1200"};
    private static final String[] INACCESSIBLE_ADD_PERIOD = new String[] {"CS1010", "TUT", "monday", "0600", "0700"};
    private static final String[] VALID_ADD_LECTURE_ARGUMENTS = new String[] {"CS2113T", "LEC", "friday", "1900",
            "2100"};
    private static final String[] VALID_ADD_LAB_ARGUMENTS = new String[] {"CS2040", "LAB", "monday", "0600",
            "0700"};
    private static final String[] INVALID_DELETE_ARGUMENT = new String[] {"CS1010", "LESSON", "monday","0600"};
    private static final String[] UPDATE_LESSON_ARGUMENTS = new String[] {"CS1231", "TUT", "monday","0600","tuesday",
            "1500"};
    private static final ModuleDb moduleDb = new ModuleDb();
    private ModuleList moduleList = new ModuleList();
    private Timetable timetable = new Timetable(moduleList);
    private AddSubCommand addSubCommand = new AddSubCommand();


    @BeforeAll
    public static void initModuleDb() {
        moduleDb.initModuleDb();
    }

    @BeforeEach
    public void clear() {
        moduleList.clear();
    }

    @Test
    public void addTutorialToTimetable_validInput_tutorialAdded() throws KolinuxException {
        timetable.clearTimetable();
        Lesson lesson = new Tutorial(VALID_ADD_TUTORIAL_ARGUMENTS);
        addSubCommand.addToTimetable(lesson);
        assertEquals(timetable.timetableData[getIndex("0600", schoolHours)][getIndex("monday", days)],
                "CS1231 TUT");
        timetable.clearTimetable();
    }

    @Test
    public void addTutorialToTimetable_invalidTiming_tutorialNotAdded() {
        try {
            timetable.clearTimetable();
            Lesson lesson = new Tutorial(INVALID_ADD_TUTORIAL_TIMING);
            addSubCommand.addToTimetable(lesson);
            timetable.clearTimetable();
        } catch (KolinuxException exception) {
            assertEquals(SubCommand.INVALID_ADD_FORMAT + "\n\n" + SubCommand.INVALID_DAY_TIME,
                    exception.getMessage());
        }
    }

    @Test
    public void addTutorialToTimetable_inaccessiblePeriod_tutorialNotAdded() {
        try {
            timetable.clearTimetable();
            Lesson firstLesson = new Tutorial(VALID_ADD_TUTORIAL_ARGUMENTS);
            Lesson secondLesson = new Tutorial(INACCESSIBLE_ADD_PERIOD);
            addSubCommand.addToTimetable(firstLesson);
            addSubCommand.addToTimetable(secondLesson);
            timetable.clearTimetable();
        } catch (KolinuxException exception) {
            assertEquals(SubCommand.INACCESSIBLE_PERIOD, exception.getMessage());
        }
    }

    @Test
    public void addLectureToTimetable_validLesson_lectureAdded() throws KolinuxException {
        timetable.clearTimetable();
        moduleList.addModuleByCode("CS2113T", moduleDb);
        timetable.executeAdd(VALID_ADD_LECTURE_ARGUMENTS);
        assertEquals(timetable.timetableData[getIndex("2000", schoolHours)][getIndex("friday", days)],
                "CS2113T LEC");
        timetable.clearTimetable();
    }

    @Test
    public void addLabToTimetable_validLesson_labAdded() throws KolinuxException {
        timetable.clearTimetable();
        moduleList.addModuleByCode("CS2040", moduleDb);
        timetable.executeAdd(VALID_ADD_LAB_ARGUMENTS);
        assertEquals(timetable.timetableData[getIndex("0600", schoolHours)][getIndex("monday", days)],
                "CS2040 LAB");
        timetable.clearTimetable();
    }

    @Test
    public void inputLesson_exceedsWorkload_lessonNotAdded() {
        try {
            timetable.clearTimetable();
            moduleList.addModuleByCode("CS1231", moduleDb);
            timetable.executeAdd(VALID_ADD_TUTORIAL_ARGUMENTS);
            timetable.executeAdd(ANOTHER_VALID_ADD_TUTORIAL_ARGUMENTS);
            assertEquals("CS1231 TUT",
                    timetable.timetableData[getIndex("0600", schoolHours)][getIndex("monday", days)]);
            timetable.clearTimetable();
        } catch (KolinuxException e) {
            assertEquals("Input hours for CS1231 TUT exceeds the total workload\nIt exceeds 1.0 hours\n"
                            +
                            "Please readjust the input timings or modify timetable to continue\nwith adding "
                            +
                            "this lesson to the timetable.", e.getMessage());
        }
    }

    @Test
    public void inputLesson_lessonNotInModuleList_lessonNotAdded() {
        try {
            timetable.clearTimetable();
            timetable.executeAdd(VALID_ADD_TUTORIAL_ARGUMENTS);
            timetable.clearTimetable();
        } catch (KolinuxException exception) {
            assertEquals("CS1231 not found in module list", exception.getMessage());
        }
    }

    @Test
    public void deleteLesson_validLesson_lessonDeleted() throws KolinuxException {
        timetable.clearTimetable();
        Lesson lesson = new Tutorial(VALID_ADD_TUTORIAL_ARGUMENTS);
        addSubCommand.addToTimetable(lesson);
        timetable.executeDelete(VALID_ADD_TUTORIAL_ARGUMENTS);
        assertFalse(addSubCommand.isLessonInTimetable("CS1010", "TUT", "monday",
                "0600"));
        timetable.clearTimetable();
    }

    @Test
    public void deleteLesson_lessonNotInTimetable_lessonNotDeleted() {
        try {
            timetable.clearTimetable();
            timetable.executeDelete(VALID_ADD_TUTORIAL_ARGUMENTS);
            timetable.clearTimetable();
        } catch (KolinuxException e) {
            assertEquals("CS1231 TUT" + SubCommand.MISSING_LESSON_TO_DELETE, e.getMessage());
        }
    }

    @Test
    public void deleteLesson_invalidLesson_lessonNotDeleted() {
        try {
            timetable.clearTimetable();
            timetable.executeDelete(INVALID_DELETE_ARGUMENT);
            timetable.clearTimetable();
        } catch (KolinuxException e) {
            assertEquals("CS1010 LESSON" + SubCommand.MISSING_LESSON_TO_DELETE, e.getMessage());
        }
    }

    @Test
    public void updateLesson_validLesson_lessonUpdated() throws KolinuxException {
        timetable.clearTimetable();
        moduleList.addModuleByCode("CS1231", moduleDb);
        timetable.executeAdd(VALID_ADD_TUTORIAL_ARGUMENTS);
        timetable.executeUpdate(UPDATE_LESSON_ARGUMENTS);
        assertFalse(addSubCommand.isLessonInTimetable("CS1231",
                "TUT", "monday", "0600"));
        assertTrue(addSubCommand.isLessonInTimetable("CS1231",
                "TUT", "tuesday", "1500"));
        timetable.clearTimetable();
    }

    @Test
    public void updateLesson_lessonNotFoundInTimetable_lessonNotUpdated() {
        try {
            timetable.clearTimetable();
            moduleList.addModuleByCode("CS1231", moduleDb);
            timetable.executeUpdate(UPDATE_LESSON_ARGUMENTS);
            timetable.clearTimetable();
        } catch (KolinuxException e) {
            assertEquals(SubCommand.MISSING_LESSON_TO_UPDATE, e.getMessage());
        }
    }

    @Test
    public void deleteModule_moduleInModuleList_moduleLessonsDeletedFromTimetable() throws KolinuxException {
        timetable.clearTimetable();
        moduleList.addModuleByCode("CS1231",moduleDb);
        timetable.executeAdd(VALID_ADD_TUTORIAL_ARGUMENTS);
        assertEquals(timetable.timetableData[getIndex("0600", schoolHours)][getIndex("monday", days)],
                "CS1231 TUT");
        timetable.deleteByModuleList("CS1231");
        assertEquals(timetable.timetableData[getIndex("0600", schoolHours)][getIndex("monday", days)],
                null);
        timetable.clearTimetable();
    }

}
