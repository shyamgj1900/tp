package seedu.kolinux.timetable.subcommand;

import seedu.kolinux.timetable.lesson.Lesson;

import static seedu.kolinux.timetable.Timetable.lessonStorage;

public class SubCommand {

    public static final int ROW_SIZE = 31;
    public static final int COLUMN_SIZE = 6;
    public static final int COLUMN_LAST_INDEX = 5;
    public static final String UPDATING_TO_SAME_TIMING = "You are updating the lesson to the same "
            +
            "timing as before.\nPlease update lesson to a different timing.";
    public static final String INVALID_UPDATE_FORMAT = "Please check the format of updating timetable:\n"
            +
            "timetable update MODULE_CODE/LESSON_TYPE/OLD_DAY/OLD_START_TIME/NEW_DAY/NEW_START_TIME\n"
            +
            "Please ensure the timing for the "
            +
            "lesson falls within the school hours: 0600 - 2100";
    public static final String INVALID_DELETE_FORMAT = "Please check the format of deleting from timetable:\n"
            +
            "timetable delete MODULE_CODE/LESSON_TYPE/DAY/START_TIME\n"
            +
            "e.g. timetable delete CS1010/TUT/Monday/1800\n"
            +
            "Please ensure the timing for the "
            +
            "lesson falls within the school hours: 0600 - 2100";
    public static final String MISSING_LESSON_TO_DELETE = " does not exist in timetable.\n"
            +
            "Please input valid lesson to remove.";
    public static final String INVALID_ADD_FORMAT = "Please check the format of adding to timetable:\n"
            +
            "timetable add MODULE_CODE/LESSON_TYPE/DAY/START_TIME/END_TIME\n"
            +
            "e.g. timetable add CS1010/TUT/Monday/1200/1400";
    public static final String INACCESSIBLE_PERIOD = "Please choose another slot as the "
            +
            "period is already occupied by another lesson";
    public static final String MISSING_LESSON_TO_UPDATE = "Lesson does not exist in timetable.\n"
            +
            "Try adding lesson to timetable with: timetable add";
    public static final String TIMETABLE_HEADER = "+-------------+--------------------+---------"
            +
            "-----------+--------------------+--------------------+--------------------+\n"
            +
            "|             |       MONDAY       |       TUESDAY      |      WEDNESDAY     "
            +
            "|      THURSDAY      |       FRIDAY       |\n+-------------+-----------------"
            +
            "---+--------------------+--------------------+--------------------+--------------------+";
    public static final String TIMETABLE_ROW_DIVIDER = "+-------------+--------------------+----------"
            +
            "----------+--------------------+--------------------+--------------------+";
    public static final String INVALID_LESSON_FORMAT = "Please ensure the LESSON_TYPE entered is in one of "
            +
            "the following format:\n1. LEC\n2. TUT\n3. LAB\n4. SEC";
    public static final String INVALID_DAY_TIME = "1. Please ensure the days are within Monday to Friday "
            +
            "and spelt fully.\n2. Please ensure the timings are within the school hours: 0600 - 2100\n"
            +
            "3. Please ensure the timings are in multiples of 30 mins.\n"
            +
            "   Timings like 1245 or 1110 are not accepted in order to maintain\n"
            +
            "   readability of timetable view on CLI.\n"
            +
            "4. Please ensure the START_TIME is earlier than the END_TIME";
    public static final int TABLE_COLUMN_WIDTH = 20;
    public static final int TABLE_FIRST_COLUMN_WIDTH = 13;

    public SubCommand() {

    }

    public boolean isLessonInTimetable(String lessonCode, String lessonType, String day, String startTime) {
        for (Lesson storedLesson : lessonStorage) {
            String storedCode = storedLesson.getModuleCode();
            String storedType = storedLesson.getLessonType();
            String storedDay = storedLesson.getDay();
            String storedStartTime = storedLesson.getStartTime();
            if (storedCode.equals(lessonCode) && storedType.equals(lessonType) && storedDay.equals(day)
                    && storedStartTime.equals(startTime)) {
                return true;
            }
        }
        return false;
    }

}
