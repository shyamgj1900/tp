package seedu.duke.module.timetable;

import seedu.duke.exceptions.KolinuxException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class TimetableStorage extends Timetable {

    public static void loadContent(String[][] timetable, ArrayList<String> fileContents) throws KolinuxException {
        for (int i = 0; i < fileContents.size(); i++) {
            String[] content = fileContents.get(i).split("/");
            String day = content[0];
            String description = content[1];
            String start = content[2];
            String end = content[3];
            int dayIndex = getIndex(day, days);
            int startIndex = getIndex(start, timings);
            int endIndex = getIndex(end, timings);
            if (startIndex == -1 || dayIndex == -1 || endIndex == -1 || startIndex > endIndex) {
                throw new KolinuxException(INVALID_ADD_ARGUMENT);
            }
            assert startIndex < endIndex;
            moduleTimeline.add(day + "/" + description + "/" + start + "/" + end);
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
            for (int i = 0; i < moduleTimeline.size(); i++) {
                String formattedTiming = null;
                formattedTiming = moduleTimeline.get(i);
                fw.write(formattedTiming + System.lineSeparator());
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
