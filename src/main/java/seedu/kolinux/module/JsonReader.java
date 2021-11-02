package seedu.kolinux.module;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import seedu.kolinux.Main;

import static seedu.kolinux.module.ModuleDetails.RESET_GRADE;

import java.nio.charset.StandardCharsets;

/**
 * JsonReader class facilitates reading module data from stored JSON files.
 */
public class JsonReader {

    public InputStream inputStream = Main.class.getResourceAsStream("/moduleDetails.json");

    /**
     * Reads module data from a JSON into an ArrayList.
     *
     * @return Returns an ArrayList of ModuleDetails objects
     */
    public ArrayList<ModuleDetails> readJsonData() {

        Gson gson = new Gson();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
        ArrayList<ModuleDetails> modules = gson.fromJson(reader, new TypeToken<ArrayList<ModuleDetails>>() {
        }.getType());

        for (ModuleDetails module : modules) {
            module.setGrade(RESET_GRADE);
        }

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
