package seedu.kolinux.module.timetable;

import seedu.kolinux.exceptions.KolinuxException;

import static seedu.kolinux.module.timetable.Timetable.INVALID_ADD_ARGUMENT;

public class Lesson {

    private String description;
    private String day;
    private String startTime;
    private String endTime;
    private int startTimeIndex;
    private int endTimeIndex;
    private int dayIndex;
    protected static String [] timings = new String [] {"0600", "0700", "0800", "0900", "1000", "1100",
        "1200", "1300", "1400", "1500", "1600", "1700", "1800", "1900", "2000", "2100"};
    protected static String[] days = new String[] {"monday", "tuesday", "wednesday", "thursday", "friday"};

    public Lesson(String[] parsedArguments) throws KolinuxException {
        try {
            this.description = parsedArguments[0];
            this.day = parsedArguments[1].toLowerCase();
            this.startTime = parsedArguments[2];
            this.endTime = parsedArguments[3];
            this.startTimeIndex = getIndex(startTime, timings);
            this.endTimeIndex = getIndex(endTime, timings);
            this.dayIndex = getIndex(day, days);
        } catch (ArrayIndexOutOfBoundsException exception) {
            throw new KolinuxException(INVALID_ADD_ARGUMENT);
        }
    }

    public String getDescription() {
        return description;
    }

    public int getDayIndex() {
        return dayIndex;
    }

    public int getStartTimeIndex() {
        return startTimeIndex;
    }

    public int getEndTimeIndex() {
        return endTimeIndex;
    }

    public String getFileContent() {
        return description + "/" + day + "/" + startTime + "/" + endTime;
    }

    public static int getIndex(String input, String[] array) {
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(input)) {
                return i + 1;
            }
        }
        return -1;
    }

}
