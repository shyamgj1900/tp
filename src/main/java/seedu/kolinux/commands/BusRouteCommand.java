package seedu.kolinux.commands;

import seedu.kolinux.exceptions.KolinuxException;
import seedu.kolinux.routes.DirectRoute;
import seedu.kolinux.routes.IndirectRoute;
import seedu.kolinux.routes.Location;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;

/** Represents the command which interacts with the bus command. **/
public class BusRouteCommand extends Command {
    public static final String COMMAND_LIST_STOPS = "bus stop list";
    public static final String USER_COMMAND_DELIMITER = " /";
    public static final String INCORRECT_FORMAT_MESSAGE = "Enter starting point and final destination.\n"
            + "In the format \"bus /start_location /end_location\"";
    public static final String SAME_LOCATION_ERROR_MESSAGE = "Start and end location cannot be same.";

    private String input;
    private Location location;
    private String[] splitInput;
    private String startLocation;
    private String endLocation;

    public BusRouteCommand(String input) {
        this.input = input;
        splitInput = input.split(USER_COMMAND_DELIMITER);
    }

    private String getBusRoute() throws KolinuxException, IOException {
        DirectRoute directRoute = new DirectRoute(splitInput);
        IndirectRoute indirectRoute = new IndirectRoute(splitInput);
        ArrayList<String> busNumbers = new ArrayList<>();
        if (directRoute.checkDirectRoutes(busNumbers)) {
            logger.log(Level.INFO, "Found Direct Route");
            return "Bus " + busNumbers + " goes from " + startLocation + " to " + endLocation;
        }
        ArrayList<String> busNumberOne = new ArrayList<>();
        ArrayList<String> busNumberTwo = new ArrayList<>();
        ArrayList<String> midLocation = new ArrayList<>();
        if (indirectRoute.checkIndirectRoutes(busNumberOne, busNumberTwo, midLocation)) {
            logger.log(Level.INFO, "Found Indirect Route");
            return "Take bus " + busNumberOne + " then change to bus " + busNumberTwo + " at " + midLocation.get(0);
        }
        logger.log(Level.INFO, "Found direct alternate route or found no route");
        return directRoute.checkDirectAlternateRoutes(busNumbers);
    }

    @Override
    public CommandResult executeCommand() throws KolinuxException, IOException {
        if (input.equalsIgnoreCase(COMMAND_LIST_STOPS)) {
            location = new Location();
            logger.log(Level.INFO, "Show user bus stop list");
            return new CommandResult(location.getBusStopList());
        }
        if (splitInput.length != 3) {
            logger.log(Level.WARNING, "User entered wrong input format");
            throw new KolinuxException(INCORRECT_FORMAT_MESSAGE);
        }
        if (splitInput[1].equalsIgnoreCase(splitInput[2])) {
            throw new KolinuxException(SAME_LOCATION_ERROR_MESSAGE);
        }
        logger.log(Level.INFO, "Finding bus route");
        startLocation = splitInput[1].trim().toUpperCase();
        endLocation = splitInput[2].trim().toUpperCase();
        String message = getBusRoute();
        return new CommandResult(message);
    }
}
