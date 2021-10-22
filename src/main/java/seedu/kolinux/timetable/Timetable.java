package seedu.kolinux.timetable;

import seedu.kolinux.exceptions.KolinuxException;
import seedu.kolinux.module.ModuleList;
import seedu.kolinux.timetable.lesson.Lab;
import seedu.kolinux.timetable.lesson.Lecture;
import seedu.kolinux.timetable.lesson.Lesson;
import seedu.kolinux.timetable.lesson.Tutorial;

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
    public AddSubCommand addSubCommand = new AddSubCommand();
    public DeleteSubCommand deleteSubCommand = new DeleteSubCommand();
    public UpdateSubCommand updateSubCommand = new UpdateSubCommand();
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
                addSubCommand.addLessonToTimetable(new Tutorial(content));
                break;
            case "LEC":
                addSubCommand.addLessonToTimetable(new Lecture(content));
                break;
            case "LAB":
                addSubCommand.addLessonToTimetable(new Lab(content));
                break;
            default:
                timetableStorage.clearFile();
                throw new KolinuxException(CORRUPT_STORAGE);
            }
        }
    }

    public void executeViewTimetable() {
        new ViewSubCommand().viewTimetable();
    }

    public void executeAddSubCommand(String[] parsedArguments) throws KolinuxException {
        try {
            String lessonType = parsedArguments[1].toUpperCase();
            String moduleCode = parsedArguments[0].toUpperCase();
            if (!addSubCommand.isLessonInModuleList(moduleList, moduleCode)) {
                throw new KolinuxException(moduleCode + " not found in module list");
            }
            int requiredHours = addSubCommand.getHours(moduleList, moduleCode, lessonType);
            addSubCommand.checkZeroWorkload(requiredHours, moduleCode, lessonType);
            int inputHours = getIndex(parsedArguments[4], schoolHours) - getIndex(parsedArguments[3], schoolHours);
            int storageHours = addSubCommand.getStorageHours(moduleCode, lessonType) + inputHours;
            addSubCommand.checkExceedingWorkload(requiredHours, storageHours, moduleCode, lessonType);

            if (lessonType.startsWith("TUT")) {
                addSubCommand.addLessonToTimetable(new Tutorial(parsedArguments));
            } else if (lessonType.startsWith("LEC")) {
                addSubCommand.addLessonToTimetable(new Lecture(parsedArguments));
            } else if (lessonType.startsWith("LAB")) {
                addSubCommand.addLessonToTimetable(new Lab(parsedArguments));
            } else {
                throw new KolinuxException(addSubCommand.INVALID_ADD_FORMAT);
            }
        } catch (ArrayIndexOutOfBoundsException exception) {
            throw new KolinuxException(addSubCommand.INVALID_ADD_FORMAT);
        }
    }

    public void executeDeleteSubCommand(String[] parsedArguments) throws KolinuxException {
        deleteSubCommand.deleteLesson(parsedArguments);
    }

    public void executeUpdateSubCommand(String[] parsedArguments) throws KolinuxException {
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
        timetableStorage.writeToFile();
    }

}
