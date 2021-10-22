package seedu.kolinux.timetable;

import seedu.kolinux.exceptions.KolinuxException;
import seedu.kolinux.module.ModuleList;
import seedu.kolinux.timetable.lesson.Lab;
import seedu.kolinux.timetable.lesson.Lecture;
import seedu.kolinux.timetable.lesson.Lesson;
import seedu.kolinux.timetable.lesson.Tutorial;
import seedu.kolinux.timetable.subcommand.AddSubcommand;
import seedu.kolinux.timetable.subcommand.DeleteSubcommand;
import seedu.kolinux.timetable.subcommand.UpdateSubcommand;
import seedu.kolinux.timetable.subcommand.ViewSubcommand;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import static seedu.kolinux.timetable.lesson.Lesson.getIndex;
import static seedu.kolinux.timetable.lesson.Lesson.schoolHours;

/**
 * Timetable class that represents the methods to interact with the 2D timetable array and Array list for storage.
 */
public class Timetable {

    public static TimetableStorage timetableStorage = new TimetableStorage();
    public static ModuleList moduleList;
    public AddSubcommand addSubcommand = new AddSubcommand();
    public DeleteSubcommand deleteSubCommand = new DeleteSubcommand();
    public UpdateSubcommand updateSubcommand = new UpdateSubcommand();
    private static final int ROW_SIZE = 16;
    private static final int COLUMN_SIZE = 6;
    public static String [][] timetableData = new String[ROW_SIZE][COLUMN_SIZE];
    public static ArrayList<Lesson> lessonStorage = new ArrayList<>();
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
        for (String fileContent : fileContents) {
            String[] content = fileContent.split("/");
            switch (content[1]) {
            case "TUT":
                addSubcommand.addLessonToTimetable(new Tutorial(content));
                break;
            case "LEC":
                addSubcommand.addLessonToTimetable(new Lecture(content));
                break;
            case "LAB":
                addSubcommand.addLessonToTimetable(new Lab(content));
                break;
            default:
                timetableStorage.clearFile();
                throw new KolinuxException(CORRUPT_STORAGE);
            }
        }
    }

    public void executeViewTimetable() {
        new ViewSubcommand().viewTimetable();
    }

    public void executeAddSubCommand(String[] parsedArguments) throws KolinuxException {

        try {
            String lessonType = parsedArguments[1].toUpperCase();
            String moduleCode = parsedArguments[0].toUpperCase();
            if (!addSubcommand.isLessonInModuleList(moduleList, moduleCode)) {
                throw new KolinuxException(moduleCode + " not found in module list");
            }
            int requiredHours = addSubcommand.getHours(moduleList, moduleCode, lessonType);
            addSubcommand.checkZeroWorkload(requiredHours, moduleCode, lessonType);
            int inputHours = getIndex(parsedArguments[4], schoolHours) - getIndex(parsedArguments[3], schoolHours);
            int storageHours = addSubcommand.getStorageHours(moduleCode, lessonType) + inputHours;
            addSubcommand.checkExceedingWorkload(requiredHours, storageHours, moduleCode, lessonType);

            if (lessonType.startsWith("TUT")) {
                addSubcommand.addLessonToTimetable(new Tutorial(parsedArguments));
            } else if (lessonType.startsWith("LEC")) {
                addSubcommand.addLessonToTimetable(new Lecture(parsedArguments));
            } else if (lessonType.startsWith("LAB")) {
                addSubcommand.addLessonToTimetable(new Lab(parsedArguments));
            } else {
                throw new KolinuxException(addSubcommand.INVALID_ADD_FORMAT);
            }
        } catch (ArrayIndexOutOfBoundsException exception) {
            throw new KolinuxException(addSubcommand.INVALID_ADD_FORMAT);
        }
    }

    public void executeDeleteSubCommand(String[] parsedArguments) throws KolinuxException {
        deleteSubCommand.deleteLesson(parsedArguments);
    }

    public void executeUpdateSubCommand(String[] parsedArguments) throws KolinuxException {
        updateSubcommand.updateTimetable(parsedArguments);
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
        timetableStorage.writeToFile();
    }

}
