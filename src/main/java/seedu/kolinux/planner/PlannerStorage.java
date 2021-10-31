package seedu.kolinux.planner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/** Represents the operations to interact with planner.txt. */
public class PlannerStorage {

    private static final String PLANNER_FILE_PATH = "./data/planner.txt";
    private static final String EMPTY_STRING = "";
    private static File file = new File(PLANNER_FILE_PATH);

    private ArrayList<String> fileLines = new ArrayList<>();

    /**
     * Adds the content of planner.txt into an array list of strings, if the string is not empty. Empty strings
     * are ignored.
     *
     * @param fileLine String in planner.txt to be added
     */
    private void addToFileLines(String fileLine) {
        if (!fileLine.isEmpty()) {
            fileLines.add(fileLine);
        }
    }

    /**
     * Creates a file with the name planner.txt
     */
    private void createFile() {
        try {
            file.createNewFile();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Writes to the file with the name planner.txt.
     *
     * @param eventData String to write to the file
     */
    public void writeFile(String eventData) {
        try {
            FileWriter writer = new FileWriter(file, true);
            writer.append(eventData + "\n");
            writer.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Rewrites the planner.txt file with the list of planned events.
     *
     * @param eventDataStrings Arraylist of data strings to write to the file
     */
    public void rewriteFile(ArrayList<String> eventDataStrings) {
        clearFile();
        try {
            FileWriter writer = new FileWriter(file, true);
            for (String eventDataString : eventDataStrings) {
                writer.append(eventDataString + "\n");
            }
            writer.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Reads from the file with the name planner.txt. Empty lines will be ignored and skipped.
     *
     * @return Array list where each entry is a line from the file, null if the file does not exist.
     */
    public ArrayList<String> readFile() {
        String fileLine;
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                fileLine = scanner.nextLine();
                addToFileLines(fileLine);
            }
            return fileLines;
        } catch (FileNotFoundException exception) {
            createFile();
        }
        return null;
    }

    /**
     * Clears the contents of the file with the name planner.txt.
     */
    public void clearFile() {
        try {
            FileWriter writer = new FileWriter(file);
            writer.write(EMPTY_STRING);
            writer.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
