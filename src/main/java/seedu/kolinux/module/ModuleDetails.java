package seedu.kolinux.module;

import com.google.gson.JsonArray;
import net.gcardone.junidecode.Junidecode;

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
    private JsonArray semesterData;
    private static final int OFFSET = 8;
    private static final int SEMESTER_1 = 0;
    private static final int SEMESTER_2 = 1;
    private static final int WORD_LIMIT = 50;

    public static final String RESET_GRADE = "0";
    public static final String RESET_GRADE_ARGUMENT = "RESET";

    public ModuleDetails(String moduleCode, String moduleCredit, String faculty, String description,
            String title, String department, double[] workload, JsonArray semesterData) {

        /*Attributes read from the NUSmods JSON may contain unicode characters. For proper printing in standard output,
        these characters are converted to their ASCII equivalent.*/
        this.moduleCode = Junidecode.unidecode(moduleCode);
        this.moduleCredit = Junidecode.unidecode(moduleCredit);
        this.faculty = Junidecode.unidecode(faculty);
        this.description = Junidecode.unidecode(description);
        this.title = Junidecode.unidecode(title);
        this.department = Junidecode.unidecode(department);
        this.workload = workload;
        this.semesterData = semesterData;
        this.grade = RESET_GRADE;
        assert Integer.parseInt(this.moduleCredit) > 0 : "Modular Credits must be positive";
    }

    public ModuleDetails(int moduleCredit, String grade) {
        String mc = Integer.toString(moduleCredit);
        
        this.moduleCode = null;
        this.moduleCredit = mc;
        this.faculty = null;
        this.description = null;
        this.title = null;
        this.department = null;
        this.workload = null;
        this.semesterData = null;
        this.grade = grade;
    }
    
    public ModuleDetails(String moduleCode, String grade) {
        this.moduleCode = moduleCode;
        this.moduleCredit = null;
        this.faculty = null;
        this.description = null;
        this.title = null;
        this.department = null;
        this.workload = null;
        this.semesterData = null;
        this.grade = grade;
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
        try {
            tutorialHours = workload[1];
            return tutorialHours;
        } catch (NullPointerException | IndexOutOfBoundsException exception) {
            return -1;
        }
    }

    public double getLectureHours() {
        try {
            lectureHours = workload[0];
            return lectureHours;
        } catch (NullPointerException | IndexOutOfBoundsException exception) {
            return -1;
        }
    }

    public double getLabHours() {
        try {
            labHours = workload[2];
            return labHours;
        } catch (NullPointerException | IndexOutOfBoundsException exception) {
            return -1;
        }
    }

    public double getProjectHours() {
        try {
            projectHours = workload[3];
            return projectHours;
        } catch (NullPointerException | IndexOutOfBoundsException exception) {
            return -1;
        }
    }

    public double getPreparationHours() {
        try {
            preparationHours = workload[4];
            return preparationHours;
        } catch (NullPointerException | IndexOutOfBoundsException exception) {
            return -1;
        }
    }

    public String[] getExamDateTime() {
        try {
            String examDate = semesterData.get(SEMESTER_1).getAsJsonObject().get("examDate").getAsString();
            String newTimeFormat = examDate.replace(":00.000Z", "");
            String[] dateTime = newTimeFormat.split("T");
            return dateTime;
        } catch (NullPointerException | IndexOutOfBoundsException exception) {
            return null;
        }
    }

    public String getDate() {
        try {
            String[] dateTime = getExamDateTime();
            return dateTime[0];
        } catch (NullPointerException | IndexOutOfBoundsException exception) {
            return null;
        }
    }

    public String getStartTime() {
        try {
            String[] dateTime = getExamDateTime();
            String time = dateTime[1];
            String[] timings = time.split(":");
            int offsetTime = Integer.parseInt(timings[0]) + OFFSET;
            String finalTime;
            if (offsetTime < 10) {
                finalTime = "0" + offsetTime + ":00";
            } else {
                finalTime = offsetTime + ":00";
            }
            return finalTime;
        } catch (NullPointerException | IndexOutOfBoundsException exception) {
            return null;
        }
    }

    public String getEndTime() {
        try {
            int examHours = 0;
            examHours = (semesterData.get(SEMESTER_1).getAsJsonObject().get("examDuration").getAsInt()) / 60;
            String time = getStartTime();
            String[] timings = time.split(":");
            int endTiming = Integer.parseInt(timings[0]) + examHours;
            String endTime;
            if (endTiming < 10) {
                endTime = "0" + endTiming + ":00";
            } else {
                endTime = endTiming + ":00";
            }
            return endTime;
        } catch (NullPointerException | IndexOutOfBoundsException exception) {
            return null;
        }
    }

    /**
     * Returns a String object representing the ModuleDetails in a format that can be used to read and write
     * the ModuleDetails from/to file.
     *
     * @return The String representation of the ModuleDetails that be used to read and write from/to file.
     */
    public String getEncodedFormat() {
        return moduleCode + "/" + grade;
    }

    /**
     * Return a grade point corresponding to the grade of this object.
     * 
     * @return Grade point in double type
     */
    public double getGradePoint() {
        switch (grade) {
        case "A+": // Fallthrough, is equivalent grade point to "A"
        case "A":
            return 5.0;
        case "A-":
            return 4.5;
        case "B+":
            return 4.0;
        case "B":
            return 3.5;
        case "B-":
            return 3.0;
        case "C+":
            return 2.5;
        case "C":
            return 2.0;
        case "D+":
            return 1.5;
        case "D":
            return 1.0;
        case "F":
            return 0.0;
        default:
            return -1; // Invalid grade
        }
    }
    
    public boolean containsSuGrade() {
        return grade.equals("S") || grade.equals("CS") || grade.equals("U") || grade.equals("CU");
    }
    
    public boolean containsNullGrade() {
        return grade.equals(RESET_GRADE);
    }
    
    public String resetGrade() {
        if (grade.equals(RESET_GRADE)) {
            return moduleCode + " does not have final grade stored";
        }
        grade = RESET_GRADE;
        return moduleCode + " grade reset";
    }

    /**
     * Returns a String object that is formatted for printing in CLI.
     *
     * @return The String representation of a module's details
     */
    @Override
    public String toString() {
        int i = WORD_LIMIT;
        String newDescription = description.replaceAll("\n", " ");
        StringBuilder descriptionSequence = new StringBuilder(newDescription);
        while (i < newDescription.length()) {
            if ((newDescription.charAt(i) == ' ') && (newDescription.charAt(i + 1) != '\n')) {
                descriptionSequence.setCharAt(i, '\n');
                i += WORD_LIMIT;
            } else {
                i++;
            }
        }
        String formattedDescription = descriptionSequence.toString();
        return moduleCode + ": " + title + "\n" + "Department: " + department + "\n" + "Faculty: " + faculty + "\n"
                + "Credits: " + moduleCredit + "\n" + "Grade: " + (grade.equals(RESET_GRADE) ? "N/A" : grade) + "\n"
                + formattedDescription;
    }

}
