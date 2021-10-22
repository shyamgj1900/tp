package seedu.kolinux.planner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/** Represents the operations to interact with planner.txt. */
public class PlannerStorage {

    private static File file = new File("./data/planner.txt");

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
     * Reads from the file with the name planner.txt
     *
     * @return Array list where each entry is a line from the file, null if the file does not exist.
     */
    public ArrayList<String> readFile() {
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
    public void clearFile() {
        try {
            FileWriter writer = new FileWriter(file);
            writer.write("");
            writer.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
