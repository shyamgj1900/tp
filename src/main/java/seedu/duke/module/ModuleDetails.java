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

        assert Integer.parseInt(this.moduleCredit) > 0 : "Modular Credits must be positive";
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

    @Override
    public String toString() {
        int i = 50;
        description = description.replaceAll("\n"," ");
        StringBuilder sb = new StringBuilder(description);
        while (i < description.length()) {
            if ((description.charAt(i) == ' ') && (description.charAt(i + 1) != '\n')) {
                sb.setCharAt(i,'\n');
                i += 50;
            } else {
                i++;
            }
            description = sb.toString();
        }
        return moduleCode + ": " + title + "\n" + "Department: " + department + "\n" + "Faculty: " + faculty + "\n"
                + "Credits: " + moduleCredit + "\n" + description;
    }

}
