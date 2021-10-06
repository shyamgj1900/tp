package seedu.duke.planner;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class PlannerStorage {

    private static File file = new File("planner.txt");

    public static void writeFile(String eventData) {
        try {
            FileWriter writer = new FileWriter(file, true);
            writer.append(eventData + "\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
