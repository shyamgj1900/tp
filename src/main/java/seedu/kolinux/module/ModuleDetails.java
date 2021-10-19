package seedu.kolinux.module;

/**
 * ModuleDetails class that stores all attributes of each module.
 */
public class ModuleDetails {

    public String moduleCode;
    private String title;
    private String description;
    private String department;
    private String moduleCredit;
    private String faculty;
    private String grade;
    private double[] workload;
    private double lectureHours;
    private double tutorialHours;
    private double labHours;
    private double projectHours;
    private double preparationHours;

    public ModuleDetails(String moduleCode, String moduleCredit, String faculty,
                         String description, String title, String department, double[] workload) {
        this.moduleCode = moduleCode;
        this.moduleCredit = moduleCredit;
        this.faculty = faculty;
        this.description = description;
        this.title = title;
        this.department = department;
        this.workload = workload;
        this.grade = null;

        assert Integer.parseInt(this.moduleCredit) > 0 : "Modular Credits must be positive";
    }

    public void setGrade(String newGrade) {
        grade = newGrade;
    }

    public String getGrade() {
        return grade;
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

    public double getTutorialHours() {
        tutorialHours = workload[1];
        return tutorialHours;
    }

    public double getLectureHours() {
        lectureHours = workload[0];
        return lectureHours;
    }

    public double getLabHours() {
        labHours = workload[2];
        return labHours;
    }

    public double getProjectHours() {
        projectHours = workload[3];
        return projectHours;
    }

    public double getPreparationHours() {
        preparationHours = workload[4];
        return preparationHours;
    }

    /**
     * Returns a String object that is formatted for printing in CLI.
     *
     * @return The String representation of a module's details
     */
    @Override
    public String toString() {
        int i = 50;
        description = description.replaceAll("\n", " ");
        StringBuilder sb = new StringBuilder(description);
        while (i < description.length()) {
            if ((description.charAt(i) == ' ') && (description.charAt(i + 1) != '\n')) {
                sb.setCharAt(i, '\n');
                i += 50;
            } else {
                i++;
            }
            description = sb.toString();
        }
        return moduleCode + ": " + title + "\n" + "Department: " + department + "\n" + "Faculty: " + faculty + "\n"
                + "Credits: " + moduleCredit + "\n" + "Grade: " + grade + "\n" + description;
    }

}
