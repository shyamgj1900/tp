package seedu.kolinux.module.timetable;

import seedu.kolinux.exceptions.KolinuxException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import static seedu.kolinux.module.timetable.Timetable.days;
import static seedu.kolinux.module.timetable.Timetable.storageTimetable;
import static seedu.kolinux.module.timetable.Timetable.getIndex;
import static seedu.kolinux.module.timetable.Timetable.timings;
import static seedu.kolinux.module.timetable.Timetable.INVALID_ADD_ARGUMENT;
import static seedu.kolinux.module.timetable.Timetable.INACCESSIBLE_PERIOD;
import static seedu.kolinux.module.timetable.Timetable.filePath;

public class TimetableStorage {

    public static void loadContent(String[][] timetable, ArrayList<String> fileContents) throws KolinuxException {
        for (String fileContent : fileContents) {
            String[] content = fileContent.split("/");
            String day = content[0];
            String description = content[1];
            String start = content[2];
            String end = content[3];
            int dayIndex = getIndex(day, days);
            int startIndex = getIndex(start, timings);
            int endIndex = getIndex(end, timings);
            if (startIndex == -1 || dayIndex == -1 || endIndex == -1 || startIndex >= endIndex) {
                throw new KolinuxException(INVALID_ADD_ARGUMENT);
            }
            storageTimetable.add(day + "/" + description + "/" + start + "/" + end);
            for (int i = startIndex; i < endIndex; i++) {
                assert dayIndex <= 6;
                assert i <= 16;
                if (timetable[i][dayIndex] == null) {
                    timetable[i][dayIndex] = description;
                } else {
                    throw new KolinuxException(INACCESSIBLE_PERIOD);
                }
            }
        }
    }

    public static void writeToFile() {
        try {
            FileWriter fw = new FileWriter(filePath);
            for (String s : storageTimetable) {
                String formattedLessonTiming = null;
                formattedLessonTiming = s;
                fw.write(formattedLessonTiming + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void createFilePath(String filePath)  {
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveToFile() {
        writeToFile();
    }

    public static void clearFile() {
        try {
            FileWriter fw = new FileWriter(filePath);
            fw.write("");
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
