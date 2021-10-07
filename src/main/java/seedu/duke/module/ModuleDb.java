package seedu.duke.module;

import java.util.Map;

public class ModuleDb {
    private JsonReader jsonReader = new JsonReader();
    private Map<String, ModuleDetails> moduleDetailsMap;

    public void initModuleDb() {
        moduleDetailsMap = jsonReader.readModuleDb();
    }

    public ModuleDetails getModuleInfo(String code) {
        return moduleDetailsMap.get(code);
    }
}
