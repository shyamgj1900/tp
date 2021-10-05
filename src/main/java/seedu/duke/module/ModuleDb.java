package seedu.duke.module;

import java.util.Map;

public class ModuleDb {
    private static Map<String, ModuleDetails> moduleDetailsMap;

    public static void initModuleDb() {
        moduleDetailsMap = JsonReader.readModuleDb();
    }

    public static void viewModuleInfo(String code) {
        ModuleDetails module = moduleDetailsMap.get(code);
        if (module == null) {
            System.out.println("Please enter a valid module code");
        } else {
            System.out.println(module);
        }
    }
}
