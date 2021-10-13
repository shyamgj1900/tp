package seedu.kolinux.module.timetable;

import seedu.kolinux.exceptions.KolinuxException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import static seedu.kolinux.module.timetable.Timetable.filePath;
import static seedu.kolinux.module.timetable.Timetable.lessonStorage;
import static seedu.kolinux.module.timetable.Timetable.CORRUPT_STORAGE;
import static seedu.kolinux.module.timetable.Timetable.addToTimetable;

public class TimetableStorage {

    /**
     * Loads the content of the timetable.txt file onto the 2D timetable array and lessonStorage array list
     * to carry out any timetable commands given by user.
     *
     * @param fileContents Array list of the contents of the timetable text file
     * @param lessons Array list of the lessons which have been extracted from timetable text file
     * @throws KolinuxException If the format of the file content for timetable inputting is incorrect
     */
    public static void loadContent(ArrayList<String> fileContents, ArrayList<Lesson> lessons)
            throws KolinuxException {
        try {
            for (String fileContent : fileContents) {
                String[] content = fileContent.split("/");
                lessons.add(new Lesson(content));
            }
            for (Lesson lesson : lessons) {
                addToTimetable(lesson);
            }
        } catch (KolinuxException exception) {
            clearFile();
            throw new KolinuxException(CORRUPT_STORAGE);
        }
    }

    /**
     * Saves the new lessons input by user onto the timetable to timetable.txt.
     */
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

    /**
     * Creates the specified filepath with the file if it is not already present in that path.
     *
     * @param filePath The expected file path of timetable text file
     */
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

    /**
     * Clears all the information present in the timetable.txt in case of file corruption.
     */
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
