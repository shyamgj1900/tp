package seedu.kolinux.module.timetable;

public class Lesson {

    public String description;
    public String day;
    public String startTime;
    public String endTime;
    public int startTimeIndex;
    public int endTimeIndex;
    public int dayIndex;
    protected static String [] timings = new String [] {"0600", "0700", "0800", "0900", "1000", "1100",
        "1200", "1300", "1400", "1500", "1600", "1700", "1800", "1900", "2000", "2100"};
    protected static String[] days = new String[] {"monday", "tuesday", "wednesday", "thursday", "friday"};

    public Lesson(String[] parsedArguments) {
        this.description = parsedArguments[0];
        this.day = parsedArguments[1].toLowerCase();
        this.startTime = parsedArguments[2];
        this.endTime = parsedArguments[3];
        this.startTimeIndex = getIndex(startTime, timings);
        this.endTimeIndex = getIndex(endTime, timings);
        this.dayIndex = getIndex(day, days);
    }

    public String getDescription() {
        return description;
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
