package seedu.duke.commands;

import seedu.duke.exceptions.KolinuxException;
import seedu.duke.routes.Graph;
import seedu.duke.routes.Route;

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
    private Graph graphAOne;
    private Graph graphDOne;
    private Graph graphDTwo;
    private Graph graphE;
    private Route route;
    ArrayList<String> verticesAOne;
    ArrayList<String> verticesDOne;
    ArrayList<String> verticesDTwo;
    ArrayList<String> verticesE;

    public BusRouteCommand(String input) {
        location = new String[2];
        splitInput = input.split(" /");
        vertexCodeAOne = new int[2];
        vertexCodeDOne = new int[2];
        vertexCodeDTwo = new int[2];
        vertexCodeE = new int[2];
        graphAOne = new Graph(13);
        graphDOne = new Graph(13);
        graphDTwo = new Graph(12);
        graphE = new Graph(7);
        route = new Route();
        verticesAOne = new ArrayList<>();
        verticesDOne = new ArrayList<>();
        verticesDTwo = new ArrayList<>();
        verticesE = new ArrayList<>();
    }

    /**
     * Converts the bus stop locations into bus stop numbers.
     *
     * @param u start vertex
     * @param v final vertex
     * @throws KolinuxException if the user command is not in the correct format
     */
    private void getLocations(int[] u, int[] v) throws KolinuxException {
        for (int i = 0; i < 2; i++) {
            if (splitInput.length < 3) {
                throw new KolinuxException("Enter starting point and final destination");
            }
            location[i] = splitInput[i + 1];
            vertexCodeAOne[i] = route.getStopNumberAOne(location[i]);
            vertexCodeDOne[i] = route.getStopNumberDOne(location[i]);
            vertexCodeDTwo[i] = route.getStopNumberDTwo(location[i]);
            vertexCodeE[i] = route.getStopNumberE(location[i]);
            if (vertexCodeAOne[i] < 0 && vertexCodeDOne[i] < 0 && vertexCodeE[i] < 0 && vertexCodeDTwo[i] < 0) {
                throw new KolinuxException(location[i].trim() + " is not a valid bus stop name");
            }
        }
        u[0] = vertexCodeAOne[0];
        v[0] = vertexCodeAOne[1];
        u[1] = vertexCodeDOne[0];
        v[1] = vertexCodeDOne[1];
        u[2] = vertexCodeDTwo[0];
        v[2] = vertexCodeDTwo[1];
        u[3] = vertexCodeE[0];
        v[3] = vertexCodeE[1];
    }

    /**
     * Checks if 2 vertices are connected and gets the particular
     * bus route.
     *
     * @param u start vertex
     * @param v final vertex
     * @param flag true if connected, false otherwise
     * @param busNumbers buses of the connected bus stops
     */
    private void checkConnection(int[] u, int []v, boolean[] flag, ArrayList<String> busNumbers) {
        if (u[0] >= 0 && v[0] >= 0 && graphAOne.isConnected(u[0], v[0])) {
            busNumbers.add("A1");
            flag[0] = true;
        }
        if (u[1] >= 0 && v[1] >= 0 && graphDOne.isConnected(u[1], v[1])) {
            busNumbers.add("D1");
            flag[0] = true;
        }
        if (u[2] >= 0 && v[2] >= 0 && graphDTwo.isConnected(u[2], v[2])) {
            busNumbers.add("D2");
            flag[0] = true;
        }
        if (u[3] >= 0 && v[3] >= 0 && graphE.isConnected(u[3], v[3])) {
            busNumbers.add("E");
            flag[0] = true;
        }
    }

    @Override
    public CommandResult executeCommand() throws KolinuxException, IOException {
        String[] filePaths = {"/routeA1.txt", "/routeD1.txt", "/routeD2.txt", "/routeE.txt"};
        route.readNodesFromFile(verticesAOne, filePaths[0]);
        route.readNodesFromFile(verticesDOne, filePaths[1]);
        route.readNodesFromFile(verticesDTwo, filePaths[2]);
        route.readNodesFromFile(verticesE, filePaths[3]);
        route.setRoute(verticesAOne, graphAOne);
        route.setRoute(verticesDOne, graphDOne);
        route.setRoute(verticesDTwo, graphDTwo);
        route.setRoute(verticesE, graphE);
        int[] u = new int[4];
        int[] v = new int[4];
        getLocations(u, v);
        String startLocation = location[0].trim().toUpperCase();
        String endLocation = location[1].trim().toUpperCase();
        boolean[] flag = {false};
        ArrayList<String> busNumbers = new ArrayList<>();
        checkConnection(u, v, flag, busNumbers);
        if (!flag[0]) {
            String message = "There is no bus service from " + startLocation + " to " + endLocation;
            return new CommandResult(message);
        }
        String message = "Bus " + busNumbers + " goes from " + startLocation + " to " + endLocation;
        return new CommandResult(message);
    }
}
