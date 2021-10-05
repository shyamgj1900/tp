package seedu.duke.commands;

import seedu.duke.exceptions.KolinuxException;
import seedu.duke.planner.Event;
import seedu.duke.planner.Planner;

public class PlannerCommand extends Command {

    private Planner planner = new Planner();
    private String subCommand;
    private String[] parsedArguments;

    public PlannerCommand(String subCommand, String[] parsedArguments) {
        this.subCommand = subCommand;
        this.parsedArguments = parsedArguments;
    }

    @Override
    public CommandResult executeCommand() throws KolinuxException {
        String addedEvent = "An event has been added to your schedule successfully!";
        String invalidArgument = "Planner does not recognise this command...";
        switch (subCommand) {
        case "add":
            Event event = new Event(parsedArguments);
            planner.addEvent(event);
            return new CommandResult(addedEvent);
        default:
            throw new KolinuxException(invalidArgument);
        }
    }
}
