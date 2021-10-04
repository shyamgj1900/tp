package seedu.duke.module;

public class ModuleDetails {

    private String moduleCode;
    private String title;
    private String description;
    private String department;
    private String moduleCredit;
    private String faculty;


    public ModuleDetails(String moduleCode, String moduleCredit, String faculty,
                         String description, String title, String department) {
        this.moduleCode = moduleCode;
        this.moduleCredit = moduleCredit;
        this.faculty = faculty;
        this.description = description;
        this.title = title;
        this.department = department;
    }

    public String getModuleCredit() {
        return moduleCredit;
    }

    public String getModuleCode() {
        return moduleCode;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }

    public String getDepartment() {
        return department;
    }

    public String getFaculty() {
        return faculty;
    }

}
