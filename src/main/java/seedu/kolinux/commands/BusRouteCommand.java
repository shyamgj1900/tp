package seedu.kolinux.commands;

import seedu.kolinux.exceptions.KolinuxException;
import seedu.kolinux.routes.Graph;
import seedu.kolinux.routes.Route;

import java.io.IOException;
import java.util.ArrayList;

/** Represent the command which interacts with the bus command. **/
public class BusRouteCommand extends Command {
    private String[] splitInput;
    private String[] location;
    private int[] vertexCodeAOne;
    private int[] vertexCodeDOne;
    private int[] vertexCodeDTwo;
    private int[] vertexCodeE;
    private int[] vertexCodeK;
    private Graph graphAOne;
    private Graph graphDOne;
    private Graph graphDTwo;
    private Graph graphE;
    private Graph graphK;
    private Route route;
    ArrayList<String> verticesAOne;
    ArrayList<String> verticesDOne;
    ArrayList<String> verticesDTwo;
    ArrayList<String> verticesE;
    ArrayList<String> verticesK;

    public BusRouteCommand(String input) {
        location = new String[2];
        splitInput = input.split(" /");
        vertexCodeAOne = new int[2];
        vertexCodeDOne = new int[2];
        vertexCodeDTwo = new int[2];
        vertexCodeE = new int[2];
        vertexCodeK = new int[2];
        graphAOne = new Graph(13);
        graphDOne = new Graph(13);
        graphDTwo = new Graph(12);
        graphE = new Graph(7);
        graphK = new Graph(16);
        route = new Route();
        verticesAOne = new ArrayList<>();
        verticesDOne = new ArrayList<>();
        verticesDTwo = new ArrayList<>();
        verticesE = new ArrayList<>();
        verticesK = new ArrayList<>();
    }

    /**
     * Converts the bus stop locations into bus stop numbers.
     *
     * @throws KolinuxException if the user command is not in the correct format
     */
    private void getLocations() throws KolinuxException {
        for (int i = 0; i < 2; i++) {
            if (splitInput.length < 3) {
                throw new KolinuxException("Enter starting point and final destination");
            }
            location[i] = splitInput[i + 1];
            vertexCodeAOne[i] = route.getStopNumberAOne(location[i]);
            vertexCodeDOne[i] = route.getStopNumberDOne(location[i]);
            vertexCodeDTwo[i] = route.getStopNumberDTwo(location[i]);
            vertexCodeE[i] = route.getStopNumberE(location[i]);
            vertexCodeK[i] = route.getStopNumberK(location[i]);
            if (vertexCodeAOne[i] < 0 && vertexCodeDOne[i] < 0
                    && vertexCodeE[i] < 0 && vertexCodeDTwo[i] < 0 && vertexCodeK[i] < 0) {
                throw new KolinuxException(location[i].trim() + " is not a valid bus stop name");
            }
        }
    }

    /**
     * Checks if 2 vertices are connected and gets the particular
     * bus route.
     *
     * @param flag true if connected, false otherwise
     * @param busNumbers buses of the connected bus stops
     */
    private void checkConnection(boolean[] flag, ArrayList<String> busNumbers) {
        if (graphAOne.isConnected(vertexCodeAOne[0], vertexCodeAOne[1])) {
            busNumbers.add("A1");
            flag[0] = true;
        }
        if (graphDOne.isConnected(vertexCodeDOne[0], vertexCodeDOne[1])) {
            busNumbers.add("D1");
            flag[0] = true;
        }
        if (graphDTwo.isConnected(vertexCodeDTwo[0], vertexCodeDTwo[1])) {
            busNumbers.add("D2");
            flag[0] = true;
        }
        if (graphE.isConnected(vertexCodeE[0], vertexCodeE[1])) {
            busNumbers.add("E");
            flag[0] = true;
        }
        if (graphK.isConnected(vertexCodeK[0], vertexCodeK[1])) {
            busNumbers.add("K");
            flag[0] = true;
        }
    }

    @Override
    public CommandResult executeCommand() throws KolinuxException, IOException {
        String[] filePaths = {"/routeA1.txt", "/routeD1.txt", "/routeD2.txt", "/routeE.txt", "/routeK.txt"};
        route.readNodesFromFile(verticesAOne, filePaths[0]);
        route.readNodesFromFile(verticesDOne, filePaths[1]);
        route.readNodesFromFile(verticesDTwo, filePaths[2]);
        route.readNodesFromFile(verticesE, filePaths[3]);
        route.readNodesFromFile(verticesK, filePaths[4]);
        route.setRoute(verticesAOne, graphAOne);
        route.setRoute(verticesDOne, graphDOne);
        route.setRoute(verticesDTwo, graphDTwo);
        route.setRoute(verticesE, graphE);
        route.setRoute(verticesK, graphK);
        getLocations();
        String startLocation = location[0].trim().toUpperCase();
        String endLocation = location[1].trim().toUpperCase();
        boolean[] flag = {false};
        ArrayList<String> busNumbers = new ArrayList<>();
        checkConnection(flag, busNumbers);
        if (!flag[0]) {
            String message = "There is no bus service from " + startLocation + " to " + endLocation;
            return new CommandResult(message);
        }
        String message = "Bus " + busNumbers + " goes from " + startLocation + " to " + endLocation;
        return new CommandResult(message);
    }
}
