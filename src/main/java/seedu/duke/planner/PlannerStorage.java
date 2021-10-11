package seedu.duke.planner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/** Represents the operations to interact with planner.txt. */
public class PlannerStorage {

    private static File file = new File("./planner.txt");

    /**
     * Creates a file with the name planner.txt
     */
    private static void createFile() {
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
    public static void writeFile(String eventData) {
        try {
            FileWriter writer = new FileWriter(file, true);
            writer.append(eventData + "\n");
            writer.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Reads from the file with the name planner.txt
     *
     * @return Array list where each entry is a line from the file, null if the file does not exist.
     */
    public static ArrayList<String> readFile() {
        ArrayList<String> fileLines = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                fileLines.add(scanner.nextLine());
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
    public static void clearFile() {
        try {
            FileWriter writer = new FileWriter(file);
            writer.write("");
            writer.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
