package seedu.kolinux.timetable;

import seedu.kolinux.timetable.lesson.Lesson;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import static seedu.kolinux.timetable.Timetable.filePath;


/** Represents the methods to interact with timetable text file. */
public class TimetableStorage {

    private ArrayList<Lesson> lessonStorage;

    public TimetableStorage(ArrayList<Lesson> lessonStorage) {
        this.lessonStorage = lessonStorage;
    }

    /**
     * Saves the new lessons input by user onto the timetable to timetable.txt.
     */
    public void writeToFile() {
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
    public void createFilePath(String filePath)  {
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
    public void clearFile() {
        try {
            FileWriter fw = new FileWriter(filePath);
            fw.write("");
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
