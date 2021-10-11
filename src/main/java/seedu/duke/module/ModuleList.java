package seedu.duke.module;

import java.util.ArrayList;

public class ModuleList {
    private static ArrayList<ModuleDetails> myModules = new ArrayList<>();
    private static final String storeAcknowledgeMessage = "Successfully stored module: ";
    private static final String storeErrorMessage = "Please enter a valid module code";

    public static int getMyModuleListSize() {
        return myModules.size();
    }

    public static void storeModuleByCode(String code) {
        ModuleDetails mod = ModuleDb.getModuleInfo(code);
    }

}
