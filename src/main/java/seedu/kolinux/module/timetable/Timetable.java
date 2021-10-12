package seedu.kolinux.module.timetable;

import seedu.kolinux.exceptions.KolinuxException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Timetable {

    public static String [][] timetableData = new String[17][6];
    public static ArrayList<String> storageTimetable = new ArrayList<>();
    protected static String [] timings = new String [] { "0600", "0700", "0800", "0900", "1000", "1100",
        "1200", "1300", "1400", "1500", "1600", "1700", "1800", "1900", "2000", "2100" };
    protected static String[] days = new String[] {"monday", "tuesday", "wednesday", "thursday", "friday"};
    public static String filePath = "./timetable.txt";
    public static File file = new File(filePath);
    public static final String  INVALID_ADD_ARGUMENT = "Please check the format of adding to timetable: "
            +
            "timetable add DESCRIPTION/DAY/START_TIME/END_TIME\n"
            +
            "e.g. timetable add CS1010 tut/Monday/1200/1400";
    public static final String INACCESSIBLE_PERIOD = "Please choose another slot as the "
            +
            "period is already occupied by another lesson";

    public static void initTimetable() throws KolinuxException {
        ArrayList<String> fileContents = new ArrayList<>();
        try {
            Scanner s = new Scanner(file);
            while (s.hasNext()) {
                fileContents.add(s.nextLine());
            }
            TimetableStorage.loadContent(timetableData, fileContents);
        } catch (FileNotFoundException e) {
            TimetableStorage.createFilePath(filePath);
        }
    }

    public static void addLesson(String[] parsedArguments) throws KolinuxException {
        try {
            String description = parsedArguments[0];
            String day = parsedArguments[1].toLowerCase();
            String start = parsedArguments[2];
            String end = parsedArguments[3];
            int dayIndex = getIndex(day, days);
            int startIndex = getIndex(start, timings);
            int endIndex = getIndex(end, timings);
            if (startIndex == -1 || dayIndex == -1 || endIndex == -1 || startIndex >= endIndex) {
                throw new KolinuxException(INVALID_ADD_ARGUMENT);
            }
            for (int i = startIndex; i < endIndex; i++) {
                assert dayIndex <= 6;
                assert i <= 16;
                if (timetableData[i][dayIndex] == null) {
                    timetableData[i][dayIndex] = description;
                } else {
                    throw new KolinuxException(INACCESSIBLE_PERIOD);
                }
            }
            storageTimetable.add(day + "/" + description + "/" + start + "/" + end);
            TimetableStorage.saveToFile();
        } catch (IndexOutOfBoundsException | NullPointerException exception) {
            throw new KolinuxException(INVALID_ADD_ARGUMENT);
        }
    }

    public static void clearTimetable() {
        for (int i = 0; i < 17; i++) {
            for (int j = 0; j < 6; j++) {
                timetableData[i][j] = null;
            }
        }
        storageTimetable.clear();
        TimetableStorage.saveToFile();
    }

    public static int getIndex(String input, String[] array) {
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(input)) {
                return i + 1;
            }
        }
        return -1;
    }

}
