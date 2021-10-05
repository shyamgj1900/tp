package seedu.duke.module;

import java.util.Map;

public class ModuleDb {
    private Map<String, ModuleDetails> moduleDetailsMap;

    ModuleDb() {
        moduleDetailsMap = JsonReader.readModuleDb();
    }
}
