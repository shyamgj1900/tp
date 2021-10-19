package seedu.kolinux.planner;

import seedu.kolinux.exceptions.KolinuxException;
import seedu.kolinux.module.ModuleDetails;
import seedu.kolinux.module.ModuleList;

import java.util.ArrayList;

public class ExamsGetter {

    private ArrayList<Event> exams = new ArrayList<>();
    private ModuleList moduleList;

    private static final String FATAL_ERROR = "Fatal error occurred, please restart Kolinux.";

    public ExamsGetter(ModuleList moduleList) {
        this.moduleList = moduleList;
        retrieveModuleExams();
    }

    private void retrieveModuleExams() {
        String[] arguments = new String[4];
        for (ModuleDetails module : moduleList.getMyModules()) {
            if (module.getExamDateTime() == null) {
                continue;
            }
            arguments[0] = module.getModuleCode() + " Exam";
            arguments[1] = module.getDate();
            arguments[2] = module.getStartTime().replaceFirst(":", "");
            arguments[3] = module.getEndTime().replaceFirst(":", "");

            try {
                Event event = new Event(arguments);
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
