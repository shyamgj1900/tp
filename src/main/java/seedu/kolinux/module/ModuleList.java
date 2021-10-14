package seedu.kolinux.module;

import java.util.ArrayList;

/**
 * ModuleList class contains and facilitate operations on the myModules list.
 */
public class ModuleList {
    private static ArrayList<ModuleDetails> myModules = new ArrayList<>();

    /**
     * Stores the moduleDetails corresponding to a given module code in the myModules list.
     *
     * @param code Module code whose details will be stored
     * @return Returns an acknowledgement message if store is successful. Returns an error message if the code is
     *      invalid, or if it already exists in the list
     */
    public static String storeModuleByCode(String code, ModuleDb moduleDb) {
        ModuleDetails mod = moduleDb.getModuleInfo(code);

        if (mod == null) {
            return "Please enter a valid module code";
        } else if (myModules.contains(mod)) {
            return mod.getModuleCode() + " is already in the module list";
        } else {
            myModules.add(mod);
            return "Successfully stored module: " + mod.getModuleCode();
        }

    }

    /**
     * Deletes the moduleDetails corresponding to a given module code from the myModules list.
     *
     * @param code Module code whose details will be deleted
     * @return Returns an acknowledgement message if deletion is successful. Returns an error message if the code is
     *      invalid, or if it is not found in the list
     */
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
