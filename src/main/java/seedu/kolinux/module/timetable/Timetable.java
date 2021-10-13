package seedu.kolinux.module.timetable;

import seedu.kolinux.exceptions.KolinuxException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Timetable {

    public static String [][] timetableData = new String[17][6];
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
        try {
            addToTimetable(lesson);
            lessonStorage.add(lesson);
            TimetableStorage.writeToFile();
        } catch (IndexOutOfBoundsException | NullPointerException exception) {
            throw new KolinuxException(INVALID_ADD_ARGUMENT);
        }
    }

    public static void addToTimetable(Lesson lesson) throws KolinuxException {
        String description = lesson.description;
        int dayIndex = lesson.dayIndex;
        int startTimeIndex = lesson.startTimeIndex;
        int endTimeIndex = lesson.endTimeIndex;
        if (startTimeIndex == -1 || dayIndex == -1 || endTimeIndex == -1 || startTimeIndex >= endTimeIndex) {
            throw new KolinuxException(INVALID_ADD_ARGUMENT);
        }
        for (int i = startTimeIndex; i < endTimeIndex; i++) {
            assert dayIndex <= 6;
            assert i <= 16;
            if (timetableData[i][dayIndex] == null) {
                timetableData[i][dayIndex] = description;
            } else {
                throw new KolinuxException(INACCESSIBLE_PERIOD);
            }
        }
    }

    public void viewTimetable() {

    }

    public static void clearTimetable() {
        for (int i = 0; i < 17; i++) {
            for (int j = 0; j < 6; j++) {
                timetableData[i][j] = null;
            }
        }
        lessonStorage.clear();
        TimetableStorage.writeToFile();
    }

}
