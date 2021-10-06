package seedu.duke.module;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import seedu.duke.Main;

public class JsonReader {

    public static InputStream inputStream = Main.class.getResourceAsStream("/moduleDetails.json");

    public static ArrayList<ModuleDetails> readJsonData() {
        Gson gson = new Gson();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        ArrayList<ModuleDetails> modules = gson.fromJson(reader, new TypeToken<ArrayList<ModuleDetails>>() {
        }.getType());

        return modules;
    }

    public Map<String, ModuleDetails> readModuleDb() {

        ArrayList<ModuleDetails> modules = readJsonData();

        Map<String, ModuleDetails> moduleDetailsMap = new HashMap<>();
        if (modules != null) {
            for (ModuleDetails module : modules) {
                moduleDetailsMap.put(module.getModuleCode(), module);
            }
        }
        return moduleDetailsMap;
    }

}
