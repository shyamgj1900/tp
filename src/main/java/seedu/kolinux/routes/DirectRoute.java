package seedu.kolinux.routes;

import seedu.kolinux.exceptions.KolinuxException;

import java.io.IOException;
import java.util.ArrayList;

public class DirectRoute extends Route {
    public static final int TOTAL_OPP_STOPS = 12;

    public DirectRoute(String[] splitInput) throws KolinuxException, IOException {
        super(splitInput);
        getBusStopNumber();
    }

    /**
     * Checks if 2 vertices are directly connected by the same route.
     *
     * @param busNumbers buses of the connected bus stops
     * @return true if connected, false otherwise
     */
    public boolean checkDirectRoutes(ArrayList<String> busNumbers) {
        boolean flag = false;
        if (graph[0].isConnected(vertexCodeA1[0], vertexCodeA1[1])) {
            busNumbers.add(BUS_A1);
            flag = true;
        }
        if (graph[1].isConnected(vertexCodeA2[0], vertexCodeA2[1])) {
            busNumbers.add(BUS_A2);
            flag = true;
        }
        if (graph[2].isConnected(vertexCodeD1[0], vertexCodeD1[1])) {
            busNumbers.add(BUS_D1);
            flag = true;
        }
        if (graph[3].isConnected(vertexCodeD2[0], vertexCodeD2[1])) {
            busNumbers.add(BUS_D2);
            flag = true;
        }
        if (graph[4].isConnected(vertexCodeE[0], vertexCodeE[1])) {
            busNumbers.add(BUS_E);
            flag = true;
        }
        if (graph[5].isConnected(vertexCodeK[0], vertexCodeK[1])) {
            busNumbers.add(BUS_K);
            flag = true;
        }
        return flag;
    }

    /**
     * Checks if there is an alternate route from the opposite bus stop of the
     * starting location.
     *
     * @param busNumbers bus number which connects the two locations
     * @return Message which specifies if any alternate routes are found
     * @throws KolinuxException if the user command is not in the correct format
     */
    public String checkDirectAlternateRoutes(ArrayList<String> busNumbers) throws KolinuxException {
        boolean flag;
        for (int i = 0; i < TOTAL_OPP_STOPS; i++) {
            if (startLocation.equalsIgnoreCase(oppositeStops[i][0])) {
                busStops[0] = oppositeStops[i][1];
                getBusStopNumber();
                flag = checkDirectRoutes(busNumbers);
                if (flag) {
                    return "There are no viable bus services from " + startLocation + " to " + endLocation
                            + ". But you can take bus " + busNumbers + " from " + oppositeStops[i][1] + " to "
                            + endLocation;
                }
            } else if (startLocation.equalsIgnoreCase(oppositeStops[i][1])) {
                busStops[0] = oppositeStops[i][0];
                getBusStopNumber();
                flag = checkDirectRoutes(busNumbers);
                if (flag) {
                    return "There are no viable bus services from " + startLocation + " to " + endLocation
                            + ". But you can take bus " + busNumbers + " from " + oppositeStops[i][0] + " to "
                            + endLocation;
                }
            }
        }
        return "There are no viable bus services from " + startLocation + " to " + endLocation;
    }
}
