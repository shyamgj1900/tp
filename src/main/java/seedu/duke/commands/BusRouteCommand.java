package seedu.duke.commands;

import seedu.duke.Main;
import seedu.duke.exceptions.KolinuxException;
import seedu.duke.routes.Graph;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class BusRouteCommand extends Command {
    private String command = "";
    private String[] location;
    private int[] vertexCode;
    private Graph graph;
    ArrayList<String> vertices;

    public BusRouteCommand() throws FileNotFoundException {
        location = new String[2];
        vertexCode = new int[2];
        graph = new Graph(10);
        vertices = new ArrayList<>();
    }

    private static int getLocations(String command) {
        switch (command.toLowerCase()) {
        case "kr bus terminal":
            return 0;
        case "lt13":
            return 1;
        case "as 5":
            return 2;
        case "com 2":
            return 3;
        case "biz 2":
            return 4;
        case "opp tcoms":
            return 5;
        case "opp pgp":
            return 6;
        case "kr mrt":
            return 7;
        case "lt27":
            return 8;
        case "uhall":
            return 9;
        default:
            return -1;
        }
    }

    @Override
    public CommandResult executeCommand() throws KolinuxException, FileNotFoundException {

        try {
            InputStream inputStream = Main.class.getResourceAsStream("/Edges.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                vertices.add(line);
            }
        } catch (IOException e) {
            throw new FileNotFoundException();
        }
        for (String v : vertices) {
            String[] vertex = v.split(" ");
            graph.addEdge(Integer.parseInt(vertex[0]), Integer.parseInt((vertex[1])));
        }
        for (int i = 0; i < 2; i++) {
            if (i == 0) {
                System.out.println("Enter starting point");
            } else {
                System.out.println("Enter final destination");
            }
            Scanner myCommand = new Scanner(System.in);
            command = myCommand.nextLine();
            location[i] = command;
            vertexCode[i] = getLocations(command);
            if (vertexCode[i] < 0) {
                throw new KolinuxException("Enter valid bus stop name");
            }
        }
        int u = vertexCode[0];
        int v = vertexCode[1];
        if (graph.isConnected(u, v)) {
            return new CommandResult("There is a bus service from " + location[0] + " to " + location[1]);
        } else {
            return new CommandResult("There is no bus service from " + location[0] + " to " + location[1]);
        }
    }
}
