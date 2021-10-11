package seedu.duke.module.timetable;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Timetable {

    public static String [][] timetable = new String[16][6];
    public static ArrayList<String> moduleTimeline = new ArrayList<>();
    protected static String [] timings = new String [] { "0600", "0700", "0800", "0900", "1000", "1100",
        "1200", "1300", "1400", "1500", "1600", "1700", "1800", "1900", "2000", "2100" };
    protected static String[] days = new String[] {"MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY"};
    public static String filePath = "./timetable.txt";
    public static File file = new File(filePath);

    public static void initTimetable() throws FileNotFoundException {
        ArrayList<String> fileContents = new ArrayList<>();
        Scanner s = new Scanner(file);
        while (s.hasNext()) {
            fileContents.add(s.nextLine());
        }
        TimetableStorage.loadContent(timetable, fileContents);
    }

    public static void addModule(String[] parsedArguments) {
        String description = parsedArguments[0];
        String day = parsedArguments[1];
        String start = parsedArguments[2];
        String end = parsedArguments[3];
        moduleTimeline.add(day + "/" + description + "/" + start + "/" + end);
        TimetableStorage.saveToFile();
        int dayIndex = getIndex(day, days);
        int startIndex = getIndex(parsedArguments[2], timings);
        int endIndex = getIndex(parsedArguments[3], timings);
        assert endIndex > startIndex : "Starting time should be earlier than ending time";
        for (int i = startIndex; i < endIndex; i++) {
            if (timetable[i][dayIndex] == null) {
                timetable[i][dayIndex] = description;
            }
        }
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
