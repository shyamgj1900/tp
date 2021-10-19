package seedu.kolinux.module;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.OffsetDateTime;
import java.time.Instant;
import java.util.Arrays;
import java.util.Locale;

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

    public ModuleDetails(String moduleCode, String moduleCredit, String faculty,
                         String description, String title, String department, double[] workload,
                         JsonArray semesterData) {
        this.moduleCode = moduleCode;
        this.moduleCredit = moduleCredit;
        this.faculty = faculty;
        this.description = description;
        this.title = title;
        this.department = department;
        this.workload = workload;
        this.semesterData = semesterData;
        this.grade = "0";
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

    public String[] getExamDateTime() {
        try {
            String examDate = null;
            for (int i = 0; i < semesterData.size(); i++) {
                examDate = semesterData.get(i).getAsJsonObject().get("examDate").getAsString();
            }
            String newTimeFormat = examDate.replace(":00.000Z", "");
            String[] dateTime = newTimeFormat.split("T");
            return dateTime;
        } catch (NullPointerException e) {
            return null;
        }
    }

    public String getDate() {
        try {
            String[] dateTime = getExamDateTime();
            return dateTime[0];
        } catch (NullPointerException exception) {
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
        } catch (NullPointerException exception) {
            return null;
        }
    }

    public String getEndTime() {
        try {
            int examHours = 0;
            for (int i = 0; i < semesterData.size(); i++) {
                examHours = (semesterData.get(i).getAsJsonObject().get("examDuration").getAsInt()) / 60;
            }
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
        } catch (NullPointerException exception) {
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
