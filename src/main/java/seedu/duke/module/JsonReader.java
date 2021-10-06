package seedu.duke.module;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import seedu.duke.Main;

public class JsonReader {

    public InputStream inputStream = Main.class.getResourceAsStream("/moduleDetails.json");

    public Map<String, ModuleDetails> readModuleDb() {
        Gson gson = new Gson();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        List<ModuleDetails> modules = gson.fromJson(reader, new TypeToken<List<ModuleDetails>>() {
        }.getType());

        Map<String, ModuleDetails> moduleDetailsMap = new HashMap<>();
        for (ModuleDetails module : modules) {
            moduleDetailsMap.put(module.getModuleCode(), module);
        }

        return moduleDetailsMap;
    }

}
