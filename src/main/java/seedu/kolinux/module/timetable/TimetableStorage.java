package seedu.kolinux.module.timetable;

import seedu.kolinux.exceptions.KolinuxException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import static seedu.kolinux.module.timetable.Timetable.filePath;
import static seedu.kolinux.module.timetable.Timetable.lessonStorage;
import static seedu.kolinux.module.timetable.Timetable.addToTimetable;

public class TimetableStorage {

    public static void loadContent(String[][] timetable, ArrayList<String> fileContents, ArrayList<Lesson> lessons)
            throws KolinuxException {
        for (String fileContent : fileContents) {
            String[] content = fileContent.split("/");
            lessons.add(new Lesson(content));
        }
        for (Lesson lesson : lessons) {
            addToTimetable(lesson);
        }
    }

    public static void writeToFile() {
        try {
            FileWriter fw = new FileWriter(filePath);
            for (Lesson lesson : lessonStorage) {
                String formattedLessonTiming = null;
                formattedLessonTiming = lesson.getFileContent();
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
