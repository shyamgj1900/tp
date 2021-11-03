package seedu.kolinux.planner;

import seedu.kolinux.exceptions.KolinuxException;
import seedu.kolinux.module.ModuleDetails;
import seedu.kolinux.module.ModuleList;

import java.util.ArrayList;

/** Represents the methods to fetch exams data from the module list. */
public class ExamsGetter {

    private ArrayList<Event> exams = new ArrayList<>();
    private ModuleList moduleList;

    private static final int EVENT_ARGUMENTS = 4;
    private static final String FATAL_ERROR = "Fatal error occurred, please restart Kolinux.";
    private static final String COLON = ":";
    private static final String EMPTY_STRING = "";

    /**
     * Constructed when user needs to perform add, list, or delete operations in planner. Hence, the
     * module list will always be updated with the latest version.
     *
     * @param moduleList Module list stored by the user
     */
    public ExamsGetter(ModuleList moduleList) {
        this.moduleList = moduleList;
        retrieveModuleExams();
    }

    /**
     * Iterates through the module list stored by the user to find the exam dates and times of the modules
     * if there are any.
     */
    private void retrieveModuleExams() {
        String[] arguments = new String[EVENT_ARGUMENTS];
        for (ModuleDetails module : moduleList.getMyModules()) {
            if (module.getExamDateTime() == null) {
                continue;
            }
            arguments[0] = module.getModuleCode() + " Exam";
            arguments[1] = module.getDate();
            arguments[2] = module.getStartTime().replaceFirst(COLON, EMPTY_STRING);
            arguments[3] = module.getEndTime().replaceFirst(COLON, EMPTY_STRING);

            try {
                Event event = new Event(arguments);
                event.setIsLesson();
                exams.add(event);
            } catch (KolinuxException exception) {
                // Should not execute this
                System.out.println(FATAL_ERROR);
            }
        }
    }

    public ArrayList<Event> getExams() {
        return exams;
    }
}
