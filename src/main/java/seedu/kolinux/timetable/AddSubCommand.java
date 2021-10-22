package seedu.kolinux.timetable;

import seedu.kolinux.exceptions.KolinuxException;
import seedu.kolinux.module.ModuleDetails;
import seedu.kolinux.module.ModuleList;
import seedu.kolinux.timetable.lesson.Lab;
import seedu.kolinux.timetable.lesson.Lecture;
import seedu.kolinux.timetable.lesson.Lesson;
import seedu.kolinux.timetable.lesson.Tutorial;

import java.util.Objects;

import static seedu.kolinux.timetable.Timetable.lessonStorage;
import static seedu.kolinux.timetable.Timetable.timetableData;
import static seedu.kolinux.timetable.Timetable.timetableStorage;
import static seedu.kolinux.timetable.Timetable.moduleList;
import static seedu.kolinux.timetable.lesson.Lesson.getIndex;
import static seedu.kolinux.timetable.lesson.Lesson.schoolHours;

public class AddSubCommand extends SubCommand {

    public AddSubCommand() {

    }

    /**
     * Adds lesson to timetable based on the time and day of the lesson and
     * saves the timetable to the local storage by writing to timetable.txt
     * based on the time and day of the lesson.
     *
     * @param lesson Lesson which is to be added to the timetable
     * @throws KolinuxException If the format of user input is incorrect
     */
    public void addLessonToTimetable(Lesson lesson) throws KolinuxException {
        String moduleCode = lesson.getModuleCode();
        String lessonType = lesson.getLessonType();
        String description = moduleCode + " " + lessonType;
        int dayIndex = lesson.getDayIndex();
        int startTimeIndex = lesson.getStartTimeIndex();
        int endTimeIndex = lesson.getEndTimeIndex();
        if (startTimeIndex == -1 || dayIndex == -1 || endTimeIndex == -1 || startTimeIndex >= endTimeIndex) {
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
        lessonStorage.add(lesson);
        timetableStorage.writeToFile();
    }

    public void inputLesson(String[] parsedArguments) throws KolinuxException {
        try {
            String lessonType = parsedArguments[1].toUpperCase();
            String moduleCode = parsedArguments[0].toUpperCase();
            if (!isLessonInModuleList(moduleList, moduleCode)) {
                throw new KolinuxException(moduleCode + " not found in module list");
            }
            int requiredHours = getHours(moduleList, moduleCode, lessonType);
            checkZeroWorkload(requiredHours, moduleCode, lessonType);
            int inputHours = getIndex(parsedArguments[4], schoolHours) - getIndex(parsedArguments[3], schoolHours);
            int storageHours = getStorageHours(moduleCode, lessonType) + inputHours;
            checkExceedingWorkload(requiredHours, storageHours, moduleCode, lessonType);

            if (lessonType.startsWith("TUT")) {
                addLessonToTimetable(new Tutorial(parsedArguments));
            } else if (lessonType.startsWith("LEC")) {
                addLessonToTimetable(new Lecture(parsedArguments));
            } else if (lessonType.startsWith("LAB")) {
                addLessonToTimetable(new Lab(parsedArguments));
            } else {
                throw new KolinuxException(INVALID_ADD_FORMAT);
            }
        } catch (ArrayIndexOutOfBoundsException exception) {
            throw new KolinuxException(INVALID_ADD_FORMAT);
        }
    }

    public int getStorageHours(String moduleCode, String lessonType) {
        int hourCount = 0;
        for (Lesson storedLesson : lessonStorage) {
            if (storedLesson.getModuleCode().equals(moduleCode)
                    && storedLesson.getLessonType().equals(lessonType)) {
                hourCount += storedLesson.getHours();
            }
        }
        return hourCount;
    }

    public int getHours(ModuleList moduleList, String moduleCode, String lessonType) {
        for (ModuleDetails module : moduleList.myModules) {
            if (lessonType.equals("TUT") && module.moduleCode.equals(moduleCode)) {
                return (int) Math.round(module.getTutorialHours());
            } else if (lessonType.equals("LEC") && module.moduleCode.equals(moduleCode)) {
                return (int) Math.round(module.getLectureHours());
            } else if (lessonType.equals("LAB") && module.moduleCode.equals(moduleCode)) {
                return (int) Math.round(module.getLabHours());
            }
        }
        return 0;
    }


    public boolean isLessonInModuleList(ModuleList moduleList, String moduleCode) {
        for (ModuleDetails module : moduleList.myModules) {
            if (Objects.equals(module.moduleCode, moduleCode)) {
                return true;
            }
        }
        return false;
    }

    public void checkZeroWorkload(int requiredHours, String moduleCode, String lessonType)
            throws KolinuxException {
        if (requiredHours == 0) {
            throw new KolinuxException(moduleCode + " has no " + lessonType
                    +
                    ".\nPlease add a different type of lesson.");
        }
    }

    public void checkExceedingWorkload(int requiredHours, int storageHours, String moduleCode,
                                       String lessonType) throws KolinuxException {
        if (storageHours > requiredHours) {
            throw new KolinuxException("Input hours for " + moduleCode + " " + lessonType
                    +
                    " exceeds the total workload\nIt exceeds " + requiredHours + " hours\n"
                    +
                    "Please readjust the input timings or modify timetable to continue\n"
                    +
                    "with adding this lesson to the timetable.");
        }
    }

    public boolean isPeriodFree(int startIndex, int endIndex, int dayIndex) throws KolinuxException {
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

    public boolean isLessonInTimetable(String lessonCode, String lessonType, String day) {
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

}
