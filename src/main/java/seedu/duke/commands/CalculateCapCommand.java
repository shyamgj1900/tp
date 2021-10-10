package seedu.duke.commands;

import seedu.duke.exceptions.KolinuxException;

import java.util.ArrayList;
import java.util.logging.Level;

public class CalculateCapCommand extends Command {
    private ArrayList<String> modules;
    
    public CalculateCapCommand(String input) {
        modules = new ArrayList<>();
        String[] commandDescriptions = input.split(" ");
        if (commandDescriptions.length == 1) {
            return;
        }
        int moduleCount = commandDescriptions.length - 1;
        for (int i = 0; i < moduleCount; i++) {
            modules.add(commandDescriptions[i + 1]);
        }
    }
    
    private int getMc(String module) throws KolinuxException {
        try {
            return Integer.parseInt(String.valueOf(module.charAt(0)));
        } catch (NumberFormatException exception) {
            String errorMessage = "Invalid module info found: " + module;
            throw new KolinuxException(errorMessage);
        }
    }
    
    private double getGradePoint(String module) throws KolinuxException {
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
            String errorMessage = "Invalid module info found: " + module;
            throw new KolinuxException(errorMessage);
        }
    }

    private double getCurrentCap(int totalMc, double cap, int mc, double gradePoint) {
        return ((cap * totalMc) + (gradePoint * mc)) / (totalMc + mc);
    }
    
    private String getCap() throws KolinuxException {
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
    public CommandResult executeCommand() throws KolinuxException {
        int moduleCount = modules.size();
        if (moduleCount == 0) {
            String errorMessage = "Please enter module credits and grades in the command (eg. 4A+)";
            logger.log(Level.INFO, "User entered an invalid CAP calculation command");
            throw new KolinuxException(errorMessage);
        }
        String cap = getCap();
        String capMessage = "Your CAP for this semester will be " + cap + " if you get your desired grades!";
        logger.log(Level.INFO, "User calculated CAP");
        return new CommandResult(capMessage);
    }
}
