package seedu.duke.module;

import java.util.Map;

public class ModuleDb {
    private static Map<String, ModuleDetails> moduleDetailsMap;

    public static void initModuleDb() {
        moduleDetailsMap = JsonReader.readModuleDb();
    }

    public static ModuleDetails getModuleInfo(String code) {
        return moduleDetailsMap.get(code);
    }
}
