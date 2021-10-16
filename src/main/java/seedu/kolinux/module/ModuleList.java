package seedu.kolinux.module;

import java.util.ArrayList;

/**
 * ModuleList class contains and facilitate operations on the myModules list.
 */
public class ModuleList {
    public static final String HORIZONTAL_LINE = "....................................................................";
    private ArrayList<ModuleDetails> myModules = new ArrayList<>();

    public ArrayList<ModuleDetails> getMyModules() {
        return myModules;
    }

    public int getMyModulesSize() {
        return myModules.size();
    }

    public void clear() {
        myModules.clear();
    }

    /**
     * Stores the moduleDetails corresponding to a given module code in the myModules list.
     *
     * @param code Module code whose details will be stored
     * @return Returns an acknowledgement message if store is successful. Returns an error message if the code is
     *      invalid, or if it already exists in the list
     */
    public String storeModuleByCode(String code, ModuleDb moduleDb) {
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
    public String deleteModuleByCode(String code) {
        for (int i = 0; i < myModules.size(); i++) {
            if (myModules.get(i).getModuleCode().equals(code)) {
                myModules.remove(i);
                return "Successfully deleted module: " + code;
            }
        }

        return code + " not found in the module list";
    }

    /**
     * Prints codes and titles of each module stored in the myModules list.
     */
    public void listMyModules() {
        for (ModuleDetails module : myModules) {
            String code = module.getModuleCode();
            String title = module.getTitle();
            double lectureHours = module.getLectureHours();
            System.out.println(code + " " + title + "\n\nWorkload:");
            if (lectureHours != 0) {
                System.out.println("Lecture: " + lectureHours + " hours");
            }
            double tutorialHours = module.getTutorialHours();
            if (tutorialHours != 0) {
                System.out.println("Tutorial: " + tutorialHours + " hours");
            }
            double labHours = module.getLabHours();
            if (labHours != 0) {
                System.out.println("Lab: " + labHours + " hours");
            }
            double projectHours = module.getProjectHours();
            if (projectHours != 0) {
                System.out.println("Project Work: " + projectHours + " hours");
            }
            double preparationHours = module.getPreparationHours();
            if (preparationHours != 0) {
                System.out.println("Preparation: " + preparationHours + " hours");
            }
            System.out.println(HORIZONTAL_LINE);
        }
        System.out.print("Remember to add the module's lessons to the timetable based on the workload");
    }

}
