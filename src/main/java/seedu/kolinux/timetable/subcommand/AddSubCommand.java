package seedu.kolinux.timetable.subcommand;

import seedu.kolinux.exceptions.KolinuxException;
import seedu.kolinux.module.ModuleDetails;
import seedu.kolinux.module.ModuleList;
import seedu.kolinux.timetable.lesson.*;

import java.util.Objects;

import static seedu.kolinux.timetable.Timetable.lessonStorage;
import static seedu.kolinux.timetable.Timetable.timetableData;
import static seedu.kolinux.timetable.Timetable.timetableStorage;
import static seedu.kolinux.timetable.Timetable.moduleList;
import static seedu.kolinux.timetable.lesson.Lesson.getIndex;
import static seedu.kolinux.timetable.lesson.Lesson.schoolHours;
import static seedu.kolinux.timetable.lesson.Lesson.days;

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
    public void addToTimetable(Lesson lesson) throws KolinuxException {
        String moduleCode = lesson.getModuleCode();
        String lessonType = lesson.getLessonType();
        String description = moduleCode + " " + lessonType;
        int dayIndex = lesson.getDayIndex();
        int startTimeIndex = lesson.getStartTimeIndex();
        int endTimeIndex = lesson.getEndTimeIndex();
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

    public void inputLesson(String[] lessonDetails) throws KolinuxException {
        try {
            String lessonType = lessonDetails[1].toUpperCase();
            String moduleCode = lessonDetails[0].toUpperCase();
            if (!isLessonInModuleList(moduleList, moduleCode)) {
                throw new KolinuxException(moduleCode + " not found in module list");
            }
            checkLessonType(lessonType);
            checkTimeAndDay(lessonDetails[2].toLowerCase(), lessonDetails[3], lessonDetails[4]);
            checkZeroWorkload(moduleCode, lessonType);
            checkExceedingWorkload(moduleCode, lessonType, lessonDetails);
            if (lessonType.equals("TUT")) {
                addToTimetable(new Tutorial(lessonDetails));
            } else if (lessonType.equals("LEC")) {
                addToTimetable(new Lecture(lessonDetails));
            } else if (lessonType.equals("LAB")) {
                addToTimetable(new Lab(lessonDetails));
            } else if (lessonType.equals("SEC")) {
                addToTimetable(new Sectional(lessonDetails));
            } else {
                throw new KolinuxException(INVALID_LESSON_FORMAT);
            }
        } catch (ArrayIndexOutOfBoundsException exception) {
            throw new KolinuxException(INVALID_ADD_FORMAT);
        }
    }

    private int getStorageHours(String moduleCode, String lessonType) {
        int hourCount = 0;
        for (Lesson storedLesson : lessonStorage) {
            if (storedLesson.getModuleCode().equals(moduleCode)
                    && storedLesson.getLessonType().equals(lessonType)) {
                hourCount += storedLesson.getHours();
            }
        }
        return hourCount;
    }

    private double getHours(ModuleList moduleList, String moduleCode, String lessonType) {
        for (ModuleDetails module : moduleList.myModules) {
            if (lessonType.equals("TUT") && module.moduleCode.equals(moduleCode)) {
                return module.getTutorialHours() * 2;
            } else if ((lessonType.equals("LEC") || lessonType.equals("SEC")) &&
                    module.moduleCode.equals(moduleCode)) {
                return module.getLectureHours() * 2;
            } else if (lessonType.equals("LAB") && module.moduleCode.equals(moduleCode)) {
                return module.getLabHours() * 2;
            }
        }
        return 0;
    }


    private boolean isLessonInModuleList(ModuleList moduleList, String moduleCode) {
        for (ModuleDetails module : moduleList.myModules) {
            if (Objects.equals(module.moduleCode, moduleCode)) {
                return true;
            }
        }
        return false;
    }

    private void checkZeroWorkload(String moduleCode, String lessonType)
            throws KolinuxException {
        double requiredHours = getHours(moduleList, moduleCode, lessonType);
        if (requiredHours == 0) {
            throw new KolinuxException(moduleCode + " has no " + lessonType
                    +
                    ".\nPlease add a different type of lesson.");
        }
    }

    private void checkExceedingWorkload(String moduleCode,
            String lessonType, String[] lessonDetails) throws KolinuxException {
        double requiredHours = getHours(moduleList, moduleCode, lessonType);
        double inputHours = getIndex(lessonDetails[4], schoolHours) - getIndex(lessonDetails[3], schoolHours);
        double storageHours = getStorageHours(moduleCode, lessonType) + inputHours;
        if (storageHours > requiredHours) {
            throw new KolinuxException("Input hours for " + moduleCode + " " + lessonType
                    +
                    " exceeds the total workload\nIt exceeds " + requiredHours / 2 + " hours\n"
                    +
                    "Please readjust the input timings or modify timetable to continue\n"
                    +
                    "with adding this lesson to the timetable.");
        }
    }

    private boolean isPeriodFree(int startIndex, int endIndex, int dayIndex) throws KolinuxException {
        try {
            for (int i = startIndex; i < endIndex; i++) {
                if (timetableData[i][dayIndex] != null) {
                    return false;
                }
            }
            return true;
        } catch (ArrayIndexOutOfBoundsException exception) {
            throw new KolinuxException(INVALID_DAY_TIME);
        }
    }

    private void checkTimeAndDay(String day, String startTime, String endTime) throws KolinuxException {
        int dayIndex = getIndex(day, days);
        int startTimeIndex = getIndex(startTime, schoolHours);
        int endTimeIndex = getIndex(endTime, schoolHours);
        if (startTimeIndex == -1 || dayIndex == -1 || endTimeIndex == -1 || startTimeIndex >= endTimeIndex) {
            throw new KolinuxException(INVALID_ADD_FORMAT + "\n\n" + INVALID_DAY_TIME);
        }
    }

    private void checkLessonType(String lessonType) throws KolinuxException {
        if (!(lessonType.equals("TUT") || lessonType.equals("LEC") || lessonType.equals("LAB") ||
                lessonType.equals("SEC"))) {
            throw new KolinuxException(INVALID_ADD_FORMAT + "\n\n" + INVALID_LESSON_FORMAT);
        }
    }

}
