package seedu.kolinux.commands;

import seedu.kolinux.exceptions.KolinuxException;
import seedu.kolinux.routes.Location;
import seedu.kolinux.routes.Route;

import java.io.IOException;

/** Represents the command which interacts with the bus command. **/
public class BusRouteCommand extends Command {
    public static final String COMMAND_LIST_STOPS = "bus stop list";
    public static final String USER_COMMAND_DELIMITER = " /";

    private Route route;
    private String input;
    private Location location;
    private String[] splitInput;

    public BusRouteCommand(String input) {
        this.input = input;
        splitInput = input.split(USER_COMMAND_DELIMITER);
    }

    @Override
    public CommandResult executeCommand() throws KolinuxException, IOException {
        if (input.equalsIgnoreCase(COMMAND_LIST_STOPS)) {
            location = new Location();
            return new CommandResult(location.getBusStopList());
        }
        if (splitInput.length != 3) {
            throw new KolinuxException("Enter starting point and final destination.\n"
                    + "In the format \"bus /start_location /end_location\"");
        }
        route = new Route(splitInput);
        String message = route.checkRoutes();
        return new CommandResult(message);
    }
}
