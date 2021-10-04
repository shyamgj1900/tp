package seedu.duke.commands;

import java.util.ArrayList;

public class CalculateCapCommand extends Command {
    private ArrayList<String> modules;
    
    public CalculateCapCommand(String input) {
        modules = new ArrayList<>();
        String[] commandDescriptions = input.split(" ");
        int moduleCount = Integer.parseInt(commandDescriptions[1]);
        for (int i = 0; i < moduleCount; i++) {
            modules.add(commandDescriptions[i + 2]);
        }
    }
    
    private int getMc(String module) {
        return Integer.parseInt(String.valueOf(module.charAt(0)));
    }
    
    private double getGradePoint(String module) {
        String grade = module.substring(1);
        switch (grade) {
        case "A+":
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
            // Will change to throw an error later
            return 0.0;
        }
    }

    private double getCurrentCap(int totalMc, double cap, int mc, double gradePoint) {
        return ((cap * totalMc) + (gradePoint * mc)) / (totalMc + mc);
    }
    
    private String getCap() {
        int totalMc = 0;
        double cap = 0;
        for (String module : modules) {
            int mc = getMc(module);
            double gradePoint = getGradePoint(module);
            cap = getCurrentCap(totalMc, cap, mc, gradePoint);
            totalMc += mc;
        }
        return String.format("%.2f", cap);
    }    

    @Override
    public CommandResult executeCommand() {
        String capMessage;
        int moduleCount = modules.size();
        if (moduleCount == 0) {
            capMessage = "Please enter modules.";
        } else {
            String cap = getCap();
            capMessage = "Your CAP for this semester will be " + cap + " if you get your desired grades!";
        }
        return new CommandResult(capMessage);
    }
}
