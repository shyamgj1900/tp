package seedu.kolinux.module.timetable;

import seedu.kolinux.exceptions.KolinuxException;
import seedu.kolinux.module.ModuleDetails;
import seedu.kolinux.module.ModuleList;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

import static seedu.kolinux.module.timetable.Lesson.getIndex;
import static seedu.kolinux.module.timetable.Lesson.schoolHours;
import static seedu.kolinux.module.timetable.Lesson.days;

/**
 * Timetable class that represents the methods to interact with the 2D timetable array and Array list for storage.
 */
public class Timetable {

    private static final int ROW_SIZE = 16;
    private static final int COLUMN_SIZE = 6;
    private static final int COLUMN_LAST_INDEX = 5;
    public static String [][] timetableData = new String[ROW_SIZE][COLUMN_SIZE];
    public static ArrayList<Lesson> lessonStorage = new ArrayList<>();
    public static String filePath = "./data/timetable.txt";
    public static File file = new File(filePath);
    private static final String INVALID_HOURS_INPUT = "Please ensure the timing for the " +
            "lesson falls within the school hours: 0600 - 2100";
    public static final String INVALID_UPDATE_FORMAT = "Please check the format of updating timetable:\n"
            +
            "timetable update MODULE_CODE_/LESSON_TYPE/OLD_DAY/NEW_DAY/NEW_START_TIME/NEW_END_TIME";
    public static final String INVALID_DELETE_FORMAT = "Please check the format of deleting from timetable:\n"
            +
            "timetable delete MODULE_CODE/LESSON_TYPE/DAY\n"
            +
            "e.g. timetable delete CS1010/TUT/Monday";
    public static final String MISSING_LESSON_DELETE = " does not exist in timetable.\n"
            +
            "Please input valid lesson to remove.";
    public static final String INVALID_ADD_FORMAT = "Please check the format of adding to timetable:\n"
            +
            "timetable add MODULE_CODE/LESSON_TYPE/DAY/START_TIME/END_TIME\n"
            +
            "e.g. timetable add CS1010/TUT/Monday/1200/1400";
    public static final String INACCESSIBLE_PERIOD = "Please choose another slot as the "
            +
            "period is already occupied by another lesson";
    public static final String MISSING_LESSON_UPDATE = "Lesson does not exist in timetable.\n"
            +
            "Try adding lesson to timetable with: timetable add";
    public static final String CORRUPT_STORAGE = "Your timetable storage file is corrupted, "
            +
            "it will be reset and cleared";
    private static final String TIMETABLE_HEADER = "+-------------+--------------------+---------"
            +
            "-----------+--------------------+--------------------+--------------------+\n"
            +
            "|             |       MONDAY       |       TUESDAY      |      WEDNESDAY     "
            +
            "|      THURSDAY      |       FRIDAY       |\n+-------------+-----------------"
            +
            "---+--------------------+--------------------+--------------------+--------------------+";
    private static final String TIMETABLE_ROW_DIVIDER = "+-------------+--------------------+----------"
            +
            "----------+--------------------+--------------------+--------------------+";
    private static final int TABLE_COLUMN_WIDTH = 20;
    private static final int TABLE_FIRST_COLUMN_WIDTH = 13;

    /**
     * Initializes the timetable with the data from timetable.txt when Kolinux starts up.
     *
     * @throws KolinuxException If the format of the data in the file is incorrect
     */
    public static void initTimetable() throws KolinuxException {
        ArrayList<String> fileContents = new ArrayList<>();
        try {
            Scanner s = new Scanner(file);
            while (s.hasNext()) {
                fileContents.add(s.nextLine());
            }
            TimetableStorage.loadContent(fileContents, lessonStorage);
        } catch (FileNotFoundException exception) {
            TimetableStorage.createFilePath(filePath);
        } catch (ArrayIndexOutOfBoundsException exception) {
            clearTimetable();
            TimetableStorage.clearFile();
            throw new KolinuxException(CORRUPT_STORAGE);
        }
    }

    /**
     * Saves the timetable to the local storage by writing to timetable.txt
     * based on the time and day of the lesson.
     *
     * @param lesson Lesson which is to be added to the timetable
     * @throws KolinuxException If the format of user input is incorrect
     */
    public static void addLesson(Lesson lesson) throws KolinuxException {
        addToTimetable(lesson);
        lessonStorage.add(lesson);
        TimetableStorage.writeToFile();
    }

    /**
     * Adds lesson to timetable based on the time and day of the lesson.
     *
     * @param lesson Lesson which is to be added to the timetable
     * @throws KolinuxException If the format of user input is incorrect
     */
    public static void addToTimetable(Lesson lesson) throws KolinuxException {
        String moduleCode = lesson.getModuleCode();
        String lessonType = lesson.getLessonType();
        String description = moduleCode + " " + lessonType;
        int dayIndex = lesson.getDayIndex();
        int startTimeIndex = lesson.getStartTimeIndex();
        int endTimeIndex = lesson.getEndTimeIndex();
        if (!isValidTiming(startTimeIndex, endTimeIndex, dayIndex)) {
            throw new KolinuxException(INVALID_ADD_FORMAT);
        }
        if (!isPeriodFree(startTimeIndex, endTimeIndex, dayIndex)) {
            throw new KolinuxException(INACCESSIBLE_PERIOD);
        }
        for (int i = startTimeIndex; i < endTimeIndex; i++) {
            assert dayIndex < COLUMN_SIZE;
            assert i < ROW_SIZE;
            timetableData[i][dayIndex] = description;
        }
    }

    /**
     * Prints the timetable to the CLI.
     */
    public static void viewTimetable() {
        System.out.println(TIMETABLE_HEADER);
        for (int i = 1; i < ROW_SIZE; i++) {
            String time = schoolHours[i - 1] + " - " + schoolHours[i];
            System.out.print("|" + time + getSpaces((TABLE_FIRST_COLUMN_WIDTH - time.length())) + "|");
            for (int j = 1; j < COLUMN_LAST_INDEX; j++) {
                System.out.print(toPrint(timetableData[i][j]));
            }
            System.out.println(toPrint(timetableData[i][COLUMN_LAST_INDEX]));
            System.out.println(TIMETABLE_ROW_DIVIDER);
        }
    }

    /**
     * Formats the string of the lesson which is to be printed in each box of the
     * timetable by adding spaces to the front and back of the lesson entry.
     *
     * @param data The lesson information found in the timetableData
     * @return The formatted string to be printed in each entry of the timetable
     */
    private static String toPrint(String data) {
        if (data != null) {
            int spacesFront = (TABLE_COLUMN_WIDTH - data.length()) / 2;
            int spacesBack = (TABLE_COLUMN_WIDTH - data.length()) / 2 + checkOddOrEven(data);
            return getSpaces(spacesFront) + data + getSpaces(spacesBack) + "|";
        }
        return getSpaces(TABLE_COLUMN_WIDTH) + "|";
    }

    /**
     * Adds spaces to format the timetable properly.
     *
     * @param numberOfSpaces The number of spaces to be added in each entry of the timetable
     * @return The string with the spaces to be added to each entry of the timetable
     */
    private static String getSpaces(int numberOfSpaces) {
        return String.format("%1$" + numberOfSpaces + "s", "");
    }

    /**
     * Checks if the length of the description for the timetable entry is even or odd,
     * this is done in order to ensure the description is in the middle each box in the timetable.
     * Ensures the format of the timetable is neat.
     *
     * @param lesson The description of the lesson
     * @return The number of extra spaces to be added to the string to ensure proper formatting
     */
    private static int checkOddOrEven(String lesson) {
        if (lesson.length() % 2 == 0) {
            return 0;
        }
        return 1;
    }

    /**
     * Clears all the entries of the timetable, ending up with an empty timetable.
     */
    public static void clearTimetable() {
        for (int i = 0; i < ROW_SIZE; i++) {
            for (int j = 0; j < COLUMN_SIZE; j++) {
                timetableData[i][j] = null;
            }
        }
        lessonStorage.clear();
        TimetableStorage.writeToFile();
    }

    public static void inputAsLesson(String[] parsedArguments, ModuleList moduleList)
            throws KolinuxException {
        try {
            if (!isLessonInModuleList(moduleList, parsedArguments[0].toUpperCase())) {
                throw new KolinuxException("Module not found in module list");
            }
            String lessonType = parsedArguments[1].toUpperCase();
            String moduleCode = parsedArguments[0].toUpperCase();
            int requiredHours = getHours(moduleList, moduleCode, lessonType);
            if (requiredHours == 0) {
                throw new KolinuxException(moduleCode + " has no " + lessonType
                        +
                        ".\nPlease add a different type of lesson.");

            }
            int inputHours = getIndex(parsedArguments[4], schoolHours)
                    - getIndex(parsedArguments[3], schoolHours);
            int storageHours = getStorageHours(moduleCode, lessonType) + inputHours;
            if (storageHours > requiredHours) {
                throw new KolinuxException("Input hours for " + moduleCode + " " + lessonType
                        +
                        " exceeds the total workload\nIt exceeds " + requiredHours + " hours\n"
                        +
                        "Please readjust the input timings or modify timetable to continue\n"
                        +
                        "with adding this lesson to the timetable.");
            }
            if (lessonType.startsWith("TUT")) {
                Timetable.addLesson(new Tutorial(parsedArguments));
            } else if (lessonType.startsWith("LEC")) {
                Timetable.addLesson(new Lecture(parsedArguments));
            } else if (lessonType.startsWith("LAB")) {
                Timetable.addLesson(new Lab(parsedArguments));
            } else {
                throw new KolinuxException(INVALID_ADD_FORMAT);
            }
        } catch (ArrayIndexOutOfBoundsException exception) {
            throw new KolinuxException(INVALID_ADD_FORMAT);
        }
    }

    public static void deleteFromTimetable(String moduleCode, String lessonType, int dayIndex) {
        String description = moduleCode + " " + lessonType;
        for (int i = 0; i < ROW_SIZE; i++) {
            assert dayIndex < COLUMN_SIZE;
            if (Objects.equals(timetableData[i][dayIndex], description)) {
                timetableData[i][dayIndex] = null;
            }
        }
    }

    public static void deleteLesson(String[] parsedArguments) throws KolinuxException {
        try {
            String moduleCode = parsedArguments[0].toUpperCase();
            String lessonType = parsedArguments[1].toUpperCase();
            String day = parsedArguments[2].toLowerCase();
            int dayIndex = getIndex(day,days);
            deleteFromTimetable(moduleCode, lessonType, dayIndex);
            int removeIndex = -1;
            for (int j = 0; j < lessonStorage.size(); j++) {
                String typeInStorage = lessonStorage.get(j).getLessonType();
                String codeInStorage = lessonStorage.get(j).getModuleCode();
                String dayInStorage = lessonStorage.get(j).getDay();
                if (typeInStorage.equals(lessonType) && codeInStorage.equals(moduleCode)
                        && dayInStorage.equals(day)) {
                    removeIndex = j;
                }
            }
            String description = moduleCode + " " + lessonType;
            if (removeIndex != -1) {
                lessonStorage.remove(removeIndex);
                TimetableStorage.writeToFile();
            } else {
                throw new KolinuxException(description + MISSING_LESSON_DELETE);
            }
        } catch (ArrayIndexOutOfBoundsException exception) {
            throw new KolinuxException(INVALID_DELETE_FORMAT);
        }
    }

    public static boolean isLessonFound(String lessonCode, String lessonType, String day) {
        for (Lesson storedLesson : lessonStorage) {
            String storedCode = storedLesson.getModuleCode();
            String storedType = storedLesson.getLessonType();
            String storedDay = storedLesson.getDay();
            if (storedCode.equals(lessonCode) && storedType.equals(lessonType) && storedDay.equals(day)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isPeriodFree(int startIndex, int endIndex, int dayIndex) throws KolinuxException {
        try {
            for (int i = startIndex; i < endIndex; i++) {
                if (timetableData[i][dayIndex] != null) {
                    return false;
                }
            }
            return true;
        } catch (ArrayIndexOutOfBoundsException exception) {
            throw new KolinuxException(INVALID_HOURS_INPUT);
        }
    }

    private static boolean isValidTiming(int startTimeIndex, int endTimeIndex, int dayIndex) {
        if (startTimeIndex == -1 || dayIndex == -1 || endTimeIndex == -1 || startTimeIndex >= endTimeIndex) {
            return false;
        }
        return true;
    }

    public static void updateTimetable(String[] parsedArguments, ModuleList moduleList) throws KolinuxException {
        try {
            String moduleCode = parsedArguments[0].toUpperCase();
            String lessonType = parsedArguments[1].toUpperCase();
            String oldDay = parsedArguments[2].toLowerCase();
            String newDay = parsedArguments[3].toLowerCase();
            String startTiming = parsedArguments[4];
            String endTiming = parsedArguments[5];
            int startIndex = getIndex(startTiming, schoolHours);
            int endIndex = getIndex(endTiming, schoolHours);
            int newDayIndex = getIndex(newDay, days);
            String[] parameters = new String[] {moduleCode, lessonType, newDay, startTiming, endTiming};
            if (!isValidTiming(startIndex, endIndex, newDayIndex)) {
                throw new KolinuxException(INVALID_UPDATE_FORMAT);
            }
            if (isLessonFound(moduleCode, lessonType, oldDay)) {
                deleteLesson(parsedArguments);
                    inputAsLesson(parameters, moduleList);
            } else {
                throw new KolinuxException(MISSING_LESSON_UPDATE);
            }
        } catch (ArrayIndexOutOfBoundsException exception) {
            throw new KolinuxException(INVALID_UPDATE_FORMAT);
        }
    }

    public static boolean isLessonInModuleList(ModuleList moduleList, String moduleCode) {
        for (ModuleDetails module : moduleList.myModules) {
            if (Objects.equals(module.moduleCode, moduleCode)) {
                return true;
            }
        }
        return false;
    }

    public static int getHours(ModuleList moduleList, String moduleCode, String lessonType) {
        for (ModuleDetails module : moduleList.myModules) {
            if (lessonType.equals("TUT") && module.moduleCode.equals(moduleCode)) {
                return (int) module.getTutorialHours();
            } else if (lessonType.equals("LEC") && module.moduleCode.equals(moduleCode)) {
                return (int) module.getLectureHours();
            } else if (lessonType.equals("LAB") && module.moduleCode.equals(moduleCode)) {
                return (int) module.getLabHours();
            }
        }
        return 0;
    }

    public static int getStorageHours(String moduleCode, String lessonType) {
        int hourCount = 0;
        for (Lesson storedLesson : lessonStorage) {
            String storedCode = storedLesson.getModuleCode();
            String storedType = storedLesson.getLessonType();
            if (storedCode.equals(moduleCode) && storedType.equals(lessonType)) {
                hourCount += storedLesson.getHours();
            }
        }
        return hourCount;
    }

}
