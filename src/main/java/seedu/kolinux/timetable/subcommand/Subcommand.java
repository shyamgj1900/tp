package seedu.kolinux.timetable.subcommand;

public class Subcommand {

    public static final int ROW_SIZE = 16;
    public static final int COLUMN_SIZE = 6;
    public static final int COLUMN_LAST_INDEX = 5;
    public static final String UPDATING_TO_SAME_TIMING = "You are updating the lesson to the same "
            +
            "timing as before.\nPlease update lesson to a different timing.";
    public static final String INVALID_HOURS_INPUT = "Please ensure the timing for the "
            +
            "lesson falls within the school hours: 0600 - 2100";
    public static final String INVALID_UPDATE_FORMAT = "Please check the format of updating timetable:\n"
            +
            "timetable update MODULE_CODE_/LESSON_TYPE/OLD_DAY/NEW_DAY/NEW_START_TIME\n"
            +
            "Please ensure the timing for the "
            +
            "lesson falls within the school hours: 0600 - 2100";
    public static final String INVALID_DELETE_FORMAT = "Please check the format of deleting from timetable:\n"
            +
            "timetable delete MODULE_CODE/LESSON_TYPE/DAY\n"
            +
            "e.g. timetable delete CS1010/TUT/Monday\n"
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
    public static final int TABLE_COLUMN_WIDTH = 20;
    public static final int TABLE_FIRST_COLUMN_WIDTH = 13;

    public Subcommand() {

    }

}
