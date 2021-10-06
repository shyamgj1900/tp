package seedu.duke.commands;

import seedu.duke.exceptions.KolinuxException;
import seedu.duke.planner.Event;
import seedu.duke.planner.Planner;

public class PlannerCommand extends Command {

    private String subCommand;
    private String[] parsedArguments;

    public PlannerCommand(String subCommand, String[] parsedArguments) {
        this.subCommand = subCommand;
        this.parsedArguments = parsedArguments;
    }

    @Override
    public CommandResult executeCommand() throws KolinuxException {
        String addedEvent = "An event has been added to your schedule successfully!";
        String clearEvent = "All the events in your schedule has been cleared.";
        String invalidArgument = "Planner does not recognise this command...";
        switch (subCommand) {
        case "add":
            Event event = new Event(parsedArguments);
            Planner.addEvent(event);
            return new CommandResult(addedEvent);
        case "list":
            String date = parsedArguments[0];
            String scheduleList = Planner.listEvents(date);
            return new CommandResult(date + scheduleList);
        case "clear":
            Planner.clearEvent();
            return new CommandResult(clearEvent);
        default:
            throw new KolinuxException(invalidArgument);
        }
    }
}
