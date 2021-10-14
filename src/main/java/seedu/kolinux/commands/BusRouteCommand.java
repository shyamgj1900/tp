package seedu.kolinux.commands;

import seedu.kolinux.exceptions.KolinuxException;
import seedu.kolinux.routes.Route;

import java.io.IOException;

/** Represent the command which interacts with the bus command. **/
public class BusRouteCommand extends Command {
    private Route route;

    public BusRouteCommand(String input) {
        route = new Route(input);
    }

    @Override
    public CommandResult executeCommand() throws KolinuxException, IOException {
        String message = route.checkRoutes();
        return new CommandResult(message);
    }
}
