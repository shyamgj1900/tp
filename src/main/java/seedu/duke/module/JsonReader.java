package seedu.duke.module;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class JsonReader {

    public static String filePath = "data/moduleDetails.json";

    public static Map<String, ModuleDetails> readModuleDb() {
        try {
            Gson gson = new Gson();
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            List<ModuleDetails> modules = gson.fromJson(reader, new TypeToken<List<ModuleDetails>>() {
            }.getType());

            Map<String, ModuleDetails> moduleDetailsMap = new HashMap<>();
            for (ModuleDetails module : modules) {
                moduleDetailsMap.put(module.getModuleCode(), module);
            }

            return moduleDetailsMap;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        readModuleDb();
    }

}
