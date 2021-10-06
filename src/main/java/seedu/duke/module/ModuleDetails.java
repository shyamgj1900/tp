package seedu.duke.module;

public class ModuleDetails {

    private String moduleCode;
    private String title;
    private String description;
    private String department;
    private String moduleCredit;
    private String faculty;
    private final int CHARACTER_LIMIT = 50;


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

    @Override
    public String toString() {
        int i = CHARACTER_LIMIT;
        description = description.replaceAll("\n"," ");
        StringBuilder sb = new StringBuilder(description);
        while (i < description.length()) {
            if (description.charAt(i) == ' ' && (description.charAt(i+1) != '\n' )) {
                sb.setCharAt(i,'\n');
                i += CHARACTER_LIMIT;
            } else {
                i++;
            }
            description = sb.toString();
        }
        return moduleCode + ": " + title + "\n" + "Department: " + department + "\n" + "Faculty: " + faculty + "\n"
                + "Credits: " + moduleCredit + "\n" + description;
    }

}
