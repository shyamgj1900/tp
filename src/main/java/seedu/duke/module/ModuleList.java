package seedu.duke.module;

import java.util.ArrayList;

public class ModuleList {
    private static ArrayList<ModuleDetails> myModules = new ArrayList<>();

    public static String storeModuleByCode(String code) {
        ModuleDetails mod = ModuleDb.getModuleInfo(code);

        if (mod == null) {
            return "Please enter a valid module code";
        } else if (myModules.contains(mod)) {
            return mod.getModuleCode() + " is already in the module list";
        } else {
            myModules.add(mod);
            return "Successfully stored module: " + mod.getModuleCode();
        }

    }

    public static String deleteModuleByCode(String code) {
        for (int i = 0; i < myModules.size(); i++) {
            if (myModules.get(i).getModuleCode().equals(code)) {
                myModules.remove(i);
                return "Successfully deleted module: " + code;
            }
        }

        return code + " not found in the module list";
    }

}
