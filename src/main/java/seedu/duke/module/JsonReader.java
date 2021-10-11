package seedu.duke.module;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import seedu.duke.Main;

/**
 * JsonReader class facilitates reading module data from stored JSON files.
 */
public class JsonReader {

    public static InputStream inputStream = Main.class.getResourceAsStream("/moduleDetails.json");

    /**
     * Reads module data from a JSON into an ArrayList.
     *
     * @return Returns an ArrayList of ModuleDetails objects
     */
    public static ArrayList<ModuleDetails> readJsonData() {
        Gson gson = new Gson();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        ArrayList<ModuleDetails> modules = gson.fromJson(reader, new TypeToken<ArrayList<ModuleDetails>>() {
        }.getType());

        return modules;
    }

    /**
     * Reads module data from a JSON into a HashMap where each module's code is a key and its
     * ModuleDetails instance is the corresponding value.
     *
     * @return Returns a HashMap of ModuleDetails objects
     */
    public HashMap<String, ModuleDetails> readModuleDb() {

        ArrayList<ModuleDetails> modules = readJsonData();

        HashMap<String, ModuleDetails> moduleDetailsMap = new HashMap<>();
        if (modules != null) {
            for (ModuleDetails module : modules) {
                moduleDetailsMap.put(module.getModuleCode(), module);
            }
        }
        return moduleDetailsMap;
    }

}
