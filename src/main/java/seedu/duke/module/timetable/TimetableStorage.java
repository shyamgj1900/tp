package seedu.duke.module.timetable;

import seedu.duke.exceptions.KolinuxException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import static seedu.duke.module.timetable.Timetable.days;
import static seedu.duke.module.timetable.Timetable.storageTimetable;
import static seedu.duke.module.timetable.Timetable.INACCESSIBLE_PERIOD;
import static seedu.duke.module.timetable.Timetable.INVALID_ADD_ARGUMENT;
import static seedu.duke.module.timetable.Timetable.filePath;
import static seedu.duke.module.timetable.Timetable.getIndex;
import static seedu.duke.module.timetable.Timetable.timings;

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
            assert startIndex < endIndex : "Starting time should be earlier than ending time";
            if (startIndex == -1 || dayIndex == -1 || endIndex == -1 || startIndex > endIndex) {
                throw new KolinuxException(INVALID_ADD_ARGUMENT);
            }
            storageTimetable.add(day + "/" + description + "/" + start + "/" + end);
            for (int j = startIndex; j < endIndex; j++) {
                if (timetable[j][dayIndex] == null) {
                    timetable[j][dayIndex] = description;
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

    public static void createFilePath(String filePath) {
        File folder = new File(filePath);
        if (!folder.exists()) {
            folder.getParentFile().mkdirs();
        }
    }

    public static void saveToFile() {
        createFilePath(filePath);
        writeToFile();
    }
}
