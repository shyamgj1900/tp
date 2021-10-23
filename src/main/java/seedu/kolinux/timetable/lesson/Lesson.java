package seedu.kolinux.timetable.lesson;

import seedu.kolinux.exceptions.KolinuxException;
import seedu.kolinux.timetable.subcommand.SubCommand;

/** Lesson class which stores all the attributes of a lesson to be input into the timetable. */
public class Lesson {

    protected String lessonType;
    protected String moduleCode;
    protected String day;
    protected String startTime;
    protected String endTime;
    protected int startTimeIndex;
    protected int endTimeIndex;
    protected int dayIndex;
    public static String [] schoolHours = new String [] {"0600", "0700", "0800", "0900", "1000", "1100",
            "1200", "1300", "1400", "1500", "1600", "1700", "1800", "1900", "2000", "2100"};
    public static String[] days = new String[] {"monday", "tuesday", "wednesday", "thursday", "friday"};

    public Lesson(String[] parsedArguments) throws KolinuxException {
        try {
            this.moduleCode = parsedArguments[0].toUpperCase();
            this.lessonType = parsedArguments[1].toUpperCase();
            this.day = parsedArguments[2].toLowerCase();
            this.startTime = parsedArguments[3];
            this.endTime = parsedArguments[4];
            this.startTimeIndex = getIndex(startTime, schoolHours);
            this.endTimeIndex = getIndex(endTime, schoolHours);
            this.dayIndex = getIndex(day, days);
        } catch (ArrayIndexOutOfBoundsException exception) {
            throw new KolinuxException(SubCommand.INVALID_ADD_FORMAT);
        }
    }

    public String getLessonType() {
        return lessonType;
    }

    /**
     * Gets the description of the lesson.
     *
     * @return The description of the lesson
     */
    public String getModuleCode() {
        return moduleCode;
    }

    /**
     * Gets the index of the day of the lesson in the days array.
     *
     * @return The index of the day of the lesson in the days array.
     */
    public int getDayIndex() {
        return dayIndex;
    }

    /**
     * Gets the index of the starting time of the lesson in the schoolHours array.
     *
     * @return The index of the starting time of the lesson in the schoolHours array
     */
    public int getStartTimeIndex() {
        return startTimeIndex;
    }

    public int getEndTimeIndex() {
        return endTimeIndex;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getDay() {
        return day;
    }

    /**
     * Formats the information about the lesson with accordance to format to be saved in the timetable.txt.
     *
     * @return Formatted information about the lesson
     */
    public String getFileContent() {
        return moduleCode + "/" + day + "/" + startTime + "/" + endTime;
    }

    /**
     * Finds the index of a given string in one of the given arrays, days or schoolHours.
     *
     * @param input The input string by user for either the day or the timings of their lesson
     * @param array The arrays, days or schoolHours, containing the days and school hours for a week
     * @return The index of the string in the array
     */
    public static int getIndex(String input, String[] array) {
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(input)) {
                return i + 1;
            }
        }
        return -1;
    }

    public int getHours() {
        return endTimeIndex - startTimeIndex;
    }
}
