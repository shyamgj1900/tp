package seedu.kolinux.module.timetable;

import seedu.kolinux.exceptions.KolinuxException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import static seedu.kolinux.module.timetable.Lesson.timings;

public class Timetable {

    private static final int ROW_SIZE = 16;
    private static final int COLUMN_SIZE = 6;
    private static final int COLUMN_LAST_INDEX = 5;
    public static String [][] timetableData = new String[ROW_SIZE][COLUMN_SIZE];
    public static ArrayList<Lesson> lessonStorage = new ArrayList<>();
    public static String filePath = "./data/timetable.txt";
    public static File file = new File(filePath);
    public static final String INVALID_ADD_ARGUMENT = "Please check the format of adding to timetable: "
            +
            "timetable add DESCRIPTION/DAY/START_TIME/END_TIME\n"
            +
            "e.g. timetable add CS1010 tut/Monday/1200/1400";
    public static final String INACCESSIBLE_PERIOD = "Please choose another slot as the "
            +
            "period is already occupied by another lesson";
    public static final String CORRUPT_STORAGE = "Your timetable storage file is corrupted, "
            +
            "it will be reset and cleared";
    private static final String TIMETABLE_HEADER = "+-------------+--------------------+---------"
            +
            "-----------+--------------------+--------------------+--------------------+\n"
            +
            "|             |       MONDAY       |       TUESDAY      |      WEDNESDAY     "
            +
            "|      THURSDAY      |       FRIDAY       |\n+-------------+-----------------"
            +
            "---+--------------------+--------------------+--------------------+--------------------+";
    private static final String TIMETABLE_ROW_DIVIDER = "+-------------+--------------------+----------"
            +
            "----------+--------------------+--------------------+--------------------+";
    private static final int TABLE_COLUMN_WIDTH = 20;
    private static final int TABLE_FIRST_COLUMN_WIDTH = 13;


    public static void initTimetable() throws KolinuxException {
        ArrayList<String> fileContents = new ArrayList<>();
        try {
            Scanner s = new Scanner(file);
            while (s.hasNext()) {
                fileContents.add(s.nextLine());
            }
            TimetableStorage.loadContent(timetableData, fileContents, lessonStorage);
        } catch (FileNotFoundException exception) {
            TimetableStorage.createFilePath(filePath);
        } catch (ArrayIndexOutOfBoundsException exception) {
            clearTimetable();
            TimetableStorage.clearFile();
            throw new KolinuxException(CORRUPT_STORAGE);
        }
    }

    public static void addLesson(Lesson lesson) throws KolinuxException {
        addToTimetable(lesson);
        lessonStorage.add(lesson);
        TimetableStorage.writeToFile();
    }

    public static void addToTimetable(Lesson lesson) throws KolinuxException {
        String description = lesson.getDescription();
        int dayIndex = lesson.getDayIndex();
        int startTimeIndex = lesson.getStartTimeIndex();
        int endTimeIndex = lesson.getEndTimeIndex();
        if (startTimeIndex == -1 || dayIndex == -1 || endTimeIndex == -1 || startTimeIndex >= endTimeIndex) {
            throw new KolinuxException(INVALID_ADD_ARGUMENT);
        }
        for (int i = startTimeIndex; i < endTimeIndex; i++) {
            assert dayIndex < COLUMN_SIZE;
            assert i < ROW_SIZE;
            if (timetableData[i][dayIndex] == null) {
                timetableData[i][dayIndex] = description;
            } else {
                throw new KolinuxException(INACCESSIBLE_PERIOD);
            }
        }
    }

    public static void viewTimetable() {
        System.out.println(TIMETABLE_HEADER);
        for (int i = 1; i < ROW_SIZE; i++) {
            String time = timings[i - 1] + " - " + timings[i];
            System.out.print("|" + time + getSpaces((TABLE_FIRST_COLUMN_WIDTH - time.length())) + "|");
            for (int j = 1; j < COLUMN_LAST_INDEX; j++) {
                System.out.print(toPrint(timetableData[i][j]));
            }
            System.out.println(toPrint(timetableData[i][COLUMN_LAST_INDEX]));
            System.out.println(TIMETABLE_ROW_DIVIDER);
        }
    }

    private static String toPrint(String data) {
        if (data != null) {
            int spacesFront = (TABLE_COLUMN_WIDTH - data.length()) / 2;
            int spacesBack = (TABLE_COLUMN_WIDTH - data.length()) / 2 + checkOddOrEven(data);;
            return getSpaces(spacesFront) + data + getSpaces(spacesBack) + "|";
        }
        return getSpaces(TABLE_COLUMN_WIDTH) + "|";
    }

    private static String getSpaces(int number) {
        return String.format("%1$" + number + "s", "");
    }

    private static int checkOddOrEven(String lesson) {
        if (lesson.length() % 2 == 0) {
            return 0;
        }
        return 1;
    }

    public static void clearTimetable() {
        for (int i = 0; i < ROW_SIZE; i++) {
            for (int j = 0; j < COLUMN_SIZE; j++) {
                timetableData[i][j] = null;
            }
        }
        lessonStorage.clear();
        TimetableStorage.writeToFile();
    }

}
