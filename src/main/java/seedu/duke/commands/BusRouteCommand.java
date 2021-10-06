package seedu.duke.commands;

import seedu.duke.exceptions.KolinuxException;
import seedu.duke.routes.Graph;
import seedu.duke.routes.Route;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class BusRouteCommand extends Command {
    private String command = "";
    private String[] location;
    private int[] vertexCodeAOne;
    private int[] vertexCodeDOne;
    private Graph graphAOne;
    private Graph graphDOne;
    private Route route;
    ArrayList<String> verticesAOne;
    ArrayList<String> verticesDOne;

    public BusRouteCommand() {
        location = new String[2];
        vertexCodeAOne = new int[2];
        vertexCodeDOne = new int[2];
        graphAOne = new Graph(13);
        graphDOne = new Graph(13);
        route = new Route();
        verticesAOne = new ArrayList<>();
        verticesDOne = new ArrayList<>();
    }

    private void getLocations(int[] u, int[] v) throws KolinuxException {
        for (int i = 0; i < 2; i++) {
            if (i == 0) {
                System.out.println("Enter starting point");
            } else {
                System.out.println("Enter final destination");
            }
            Scanner myCommand = new Scanner(System.in);
            command = myCommand.nextLine();
            location[i] = command;
            vertexCodeAOne[i] = route.getStopNameAOne(command);
            vertexCodeDOne[i] = route.getStopNameDOne(command);
            if (vertexCodeAOne[i] < 0 && vertexCodeDOne[i] < 0) {
                throw new KolinuxException("Enter valid bus stop name");
            }
        }
        u[0] = vertexCodeAOne[0];
        v[0] = vertexCodeAOne[1];
        u[1] = vertexCodeDOne[0];
        v[1] = vertexCodeDOne[1];
    }

    @Override
    public CommandResult executeCommand() throws KolinuxException, FileNotFoundException {
        String[] filePaths = {"/routeA1.txt", "/routeD1.txt"};
        route.readNodesFromFile(verticesAOne, filePaths[0]);
        route.readNodesFromFile(verticesDOne, filePaths[1]);
        route.setRoute(verticesAOne, graphAOne);
        route.setRoute(verticesDOne, graphDOne);
        int[] u = new int[2];
        int[] v = new int[2];
        getLocations(u, v);
        String startLocation = location[0].toUpperCase();
        String endLocation = location[0].toUpperCase();
        if (u[0] >= 0 && v[0] >= 0 && graphAOne.isConnected(u[0], v[0])) {
            String message = "Bus A1 goes from " + startLocation + " to " + endLocation;
            return new CommandResult(message);
        } else if (u[1] >= 0 && v[1] >= 0 && graphDOne.isConnected(u[1], v[1])) {
            String message = "Bus D1 goes from " + startLocation + " to " + endLocation;
            return new CommandResult(message);
        } else {
            String message = "There is no bus service from " + startLocation + " to " + endLocation;
            return new CommandResult(message);
        }
    }
}
