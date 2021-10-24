package seedu.kolinux.timetable;

import seedu.kolinux.exceptions.KolinuxException;
import seedu.kolinux.module.ModuleList;
import seedu.kolinux.timetable.lesson.Lab;
import seedu.kolinux.timetable.lesson.Lecture;
import seedu.kolinux.timetable.lesson.Lesson;
import seedu.kolinux.timetable.lesson.Tutorial;
import seedu.kolinux.timetable.subcommand.AddSubCommand;
import seedu.kolinux.timetable.subcommand.ViewSubCommand;
import seedu.kolinux.timetable.subcommand.DeleteSubCommand;
import seedu.kolinux.timetable.subcommand.UpdateSubCommand;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Timetable class that represents the methods to interact with the 2D timetable array and Array list for storage.
 */
public class Timetable {

    public static ModuleList moduleList;
    private static final int ROW_SIZE = 16;
    private static final int COLUMN_SIZE = 6;
    public static String [][] timetableData = new String[ROW_SIZE][COLUMN_SIZE];
    public static ArrayList<Lesson> lessonStorage = new ArrayList<>();
    public static TimetableStorage timetableStorage = new TimetableStorage(lessonStorage);
    private ViewSubCommand viewSubCommand = new ViewSubCommand();
    private AddSubCommand addSubCommand = new AddSubCommand();
    private DeleteSubCommand deleteSubCommand = new DeleteSubCommand();
    private UpdateSubCommand updateSubCommand = new UpdateSubCommand();
    public static String filePath = "./data/timetable.txt";
    public static File file = new File(filePath);
    public static final String CORRUPT_STORAGE = "Your timetable storage file is corrupted, "
            +
            "it will be reset and cleared";

    public Timetable(ModuleList moduleList) {
        this.moduleList = moduleList;
    }

    /**
     * Initializes the timetable with the data from timetable.txt when Kolinux starts up.
     *
     * @throws KolinuxException If the format of the data in the file is incorrect
     */
    public void initTimetable() throws KolinuxException {
        ArrayList<String> fileContents = new ArrayList<>();
        try {
            Scanner s = new Scanner(file);
            while (s.hasNext()) {
                fileContents.add(s.nextLine());
            }
            loadContent(fileContents);
        } catch (FileNotFoundException exception) {
            timetableStorage.createFilePath(filePath);
        } catch (ArrayIndexOutOfBoundsException exception) {
            clearTimetable();
            timetableStorage.clearFile();
            throw new KolinuxException(CORRUPT_STORAGE);
        }
    }

    /**
     * Loads the content of the timetable.txt file onto the 2D timetable array and lessonStorage array list
     * to carry out any timetable commands given by user.
     *
     * @param fileContents Array list of the contents of the timetable text file
     * @throws KolinuxException If the format of the file content for timetable inputting is incorrect
     */
    private void loadContent(ArrayList<String> fileContents)
            throws KolinuxException {
        AddSubCommand addSubCommand = new AddSubCommand();
        for (String fileContent : fileContents) {
            String[] content = fileContent.split("/");
            switch (content[1]) {
            case "TUT":
                addSubCommand.addToTimetable(new Tutorial(content));
                break;
            case "LEC":
                addSubCommand.addToTimetable(new Lecture(content));
                break;
            case "LAB":
                addSubCommand.addToTimetable(new Lab(content));
                break;
            default:
                timetableStorage.clearFile();
                throw new KolinuxException(CORRUPT_STORAGE);
            }
        }
    }

    public void executeView() {
        viewSubCommand.viewTimetable();
    }

    public void executeAdd(String[] parsedArguments) throws KolinuxException {
        AddSubCommand addCommand = new AddSubCommand();
        addCommand.inputLesson(parsedArguments);
    }

    public void executeDelete(String[] parsedArguments) throws KolinuxException {
        deleteSubCommand.deleteLesson(parsedArguments);
    }

    public void executeUpdate(String[] parsedArguments) throws KolinuxException {
        updateSubCommand.updateTimetable(parsedArguments);
    }

    public void deleteByModuleList(String moduleCode) {
        for (int i = 0; i < ROW_SIZE; i++) {
            for (int j = 0; j < COLUMN_SIZE; j++) {
                if (timetableData[i][j] != null && timetableData[i][j].contains(moduleCode)) {
                    timetableData[i][j] = null;
                }
            }
        }
        lessonStorage.removeIf(lesson -> lesson.getModuleCode().equals(moduleCode));
        timetableStorage.writeToFile();
    }

    /**
     * Clears all the entries of the timetable, ending up with an empty timetable.
     */
    public void clearTimetable() {
        for (int i = 0; i < ROW_SIZE; i++) {
            for (int j = 0; j < COLUMN_SIZE; j++) {
                timetableData[i][j] = null;
            }
        }
        lessonStorage.clear();
        timetableStorage.clearFile();
    }

}
