package seedu.duke.planner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class PlannerStorage {

    private static File file = new File("planner.txt");

    private static void createFile() {
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeFile(String eventData) {
        try {
            FileWriter writer = new FileWriter(file, true);
            writer.append(eventData + "\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<String> readFile() {
        ArrayList<String> fileLines = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                fileLines.add(scanner.nextLine());
            }
            return fileLines;
        } catch (FileNotFoundException e) {
            createFile();
        }
        return null;
    }
}
