package seedu.kolinux.routes;

import seedu.kolinux.Main;
import seedu.kolinux.exceptions.KolinuxException;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;

public class Route {
    public static final String BUS_A1 = "A1";
    public static final String BUS_A2 = "A2";
    public static final String BUS_D1 = "D1";
    public static final String BUS_D2 = "D2";
    public static final String BUS_E = "E";
    public static final String BUS_K = "K";
    public static final String STOP_PGP = "PGP";
    public static final String STOP_IT = "IT";
    public static final String STOP_UTOWN = "UTOWN";
    public static final String STOP_KENT_VALE = "KENT VALE";
    private static final String FILEPATH_A1 = "/routeA1.txt";
    private static final String FILEPATH_A2 = "/routeA2.txt";
    private static final String FILEPATH_D1 = "/routeD1.txt";
    private static final String FILEPATH_D2 = "/routeD2.txt";
    private static final String FILEPATH_E = "/routeE.txt";
    private static final String FILEPATH_K = "/routeK.txt";
    public static final String FILEPATH_STOP_NAMES = "/busStopNames.txt";
    public static final String COMMAND_LIST_STOPS = "bus stop list";
    public static final String USER_COMMAND_DELIMITER = " /";
    public static final int TOTAL_VERTICES_A1 = 13;
    public static final int TOTAL_VERTICES_A2 = 14;
    public static final int TOTAL_VERTICES_D1 = 13;
    public static final int TOTAL_VERTICES_D2 = 12;
    public static final int TOTAL_VERTICES_E = 7;
    public static final int TOTAL_VERTICES_K = 16;

    private String[] splitInput;
    private String[] location;
    private int[] vertexCodeA1;
    private int[] vertexCodeA2;
    private int[] vertexCodeD1;
    private int[] vertexCodeD2;
    private int[] vertexCodeE;
    private int[] vertexCodeK;
    private Graph graphA1;
    private Graph graphA2;
    private Graph graphD1;
    private Graph graphD2;
    private Graph graphE;
    private Graph graphK;
    private ArrayList<String> verticesA1;
    private ArrayList<String> verticesA2;
    private ArrayList<String> verticesD1;
    private ArrayList<String> verticesD2;
    private ArrayList<String> verticesE;
    private ArrayList<String> verticesK;

    public Route(String input) {
        location = new String[2];
        splitInput = input.split(USER_COMMAND_DELIMITER);
        vertexCodeA1 = new int[2];
        vertexCodeA2 = new int[2];
        vertexCodeD1 = new int[2];
        vertexCodeD2 = new int[2];
        vertexCodeE = new int[2];
        vertexCodeK = new int[2];
        graphA1 = new Graph(TOTAL_VERTICES_A1);
        graphA2 = new Graph(TOTAL_VERTICES_A2);
        graphD1 = new Graph(TOTAL_VERTICES_D1);
        graphD2 = new Graph(TOTAL_VERTICES_D2);
        graphE = new Graph(TOTAL_VERTICES_E);
        graphK = new Graph(TOTAL_VERTICES_K);
        verticesA1 = new ArrayList<>();
        verticesA2 = new ArrayList<>();
        verticesD1 = new ArrayList<>();
        verticesD2 = new ArrayList<>();
        verticesE = new ArrayList<>();
        verticesK = new ArrayList<>();
    }

    /**
     * Reads the contents from the file which consists of the path
     * of the graph.
     *
     * @param vertices contains the nodes which connect the graph
     * @param filePath the path of the input file
     * @throws KolinuxException if the user command is not in the correct format
     * @throws IOException if the there any IO errors
     */
    private void readNodesFromFile(ArrayList<String> vertices, String filePath) throws KolinuxException, IOException {
        try {
            InputStream inputStream = Main.class.getResourceAsStream(filePath);
            if (inputStream == null) {
                throw new KolinuxException("File not found.");
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                vertices.add(line);
            }
        } catch (IOException e) {
            throw new IOException();
        }
    }

    /**
     * Forms the bus route. This is done in the form of an unweighted graph.
     *
     * @param vertices contains the nodes which connect the graph
     * @param graph is the graph which forms the bus route
     * @throws KolinuxException if the user command is not in the correct format
     */
    private void setRoute(ArrayList<String> vertices, Graph graph) throws KolinuxException {
        if (vertices.size() == 0) {
            throw new KolinuxException("Route doesn't exist. Please make sure text file has vertices of expected graph.");
        }
        for (String v : vertices) {
            String[] vertex = v.split(" ");
            graph.addEdge(Integer.parseInt(vertex[0]), Integer.parseInt((vertex[1])));
        }
    }

    /**
     * Converts the bus stop locations into bus stop numbers.
     *
     * @throws KolinuxException if the user command is not in the correct format
     */
    private void getLocations() throws KolinuxException {
        for (int i = 0; i < 2; i++) {
            if (splitInput.length < 3) {
                throw new KolinuxException("Enter starting point and final destination.");
            }
            location[i] = splitInput[i + 1];
            vertexCodeA1[i] = getStopNumberAOne(location[i]);
            vertexCodeA2[i] = getStopNumberATwo(location[i]);
            vertexCodeD1[i] = getStopNumberDOne(location[i]);
            vertexCodeD2[i] = getStopNumberDTwo(location[i]);
            vertexCodeE[i] = getStopNumberE(location[i]);
            vertexCodeK[i] = getStopNumberK(location[i]);
            if (vertexCodeA1[i] < 0 && vertexCodeA2[i] < 0 && vertexCodeD1[i] < 0
                    && vertexCodeE[i] < 0 && vertexCodeD2[i] < 0 && vertexCodeK[i] < 0) {
                throw new KolinuxException(location[i].trim() + " is not a valid bus stop name.");
            }
        }
    }

    /**
     * Reads the list of bus stops from the file and returns the contents
     * of the file.
     *
     * @return the bus stop names present in the file
     * @throws KolinuxException if the user command is not in the correct format
     * @throws IOException if the there any IO errors
     */
    private String getBusStopNames() throws KolinuxException, IOException {
        ArrayList<String> lines = new ArrayList<>();
        try {
            InputStream inputStream = Main.class.getResourceAsStream(FILEPATH_STOP_NAMES);
            if (inputStream == null) {
                throw new KolinuxException("File not found.");
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
            return String.join("\n", lines);
        } catch (IOException e) {
            throw new IOException();
        }
    }

    /**
     * Finds if there are bus routes between the starting and end locations.
     *
     * @return Message which specifies if any routes are found
     * @throws KolinuxException if the user command is not in the correct format
     * @throws IOException if the there any IO errors
     */
    public String checkRoutes() throws KolinuxException, IOException {
        if (splitInput[0].equalsIgnoreCase(COMMAND_LIST_STOPS)) {
            return getBusStopNames();
        }
        readNodesFromFile(verticesA1, FILEPATH_A1);
        readNodesFromFile(verticesA2, FILEPATH_A2);
        readNodesFromFile(verticesD1, FILEPATH_D1);
        readNodesFromFile(verticesD2, FILEPATH_D2);
        readNodesFromFile(verticesE, FILEPATH_E);
        readNodesFromFile(verticesK, FILEPATH_K);
        setRoute(verticesA1, graphA1);
        setRoute(verticesA2, graphA2);
        setRoute(verticesD1, graphD1);
        setRoute(verticesD2, graphD2);
        setRoute(verticesE, graphE);
        setRoute(verticesK, graphK);
        getLocations();
        String startLocation = location[0].trim().toUpperCase();
        String endLocation = location[1].trim().toUpperCase();
        ArrayList<String> busNumbers = new ArrayList<>();
        boolean[] flag = new boolean[2];
        flag[0] = checkDirectRoutes(busNumbers);
        if (!flag[0]) {
            ArrayList<String> busNumberOne = new ArrayList<>();
            ArrayList<String> busNumberTwo = new ArrayList<>();
            ArrayList<String> midLocation = new ArrayList<>();
            flag[1] = checkIndirectRoutes(busNumberOne, busNumberTwo, midLocation);
            if (!flag[1]) {
                return "There are no viable bus services from " + startLocation + " to " + endLocation;
            }
            return "Take bus " + busNumberOne + " then change to bus " + busNumberTwo + " at " + midLocation.get(0);
        }
        return "Bus " + busNumbers + " goes from " + startLocation + " to " + endLocation;
    }

    /**
     * Checks if 2 vertices are directly connected by the same route.
     *
     * @param busNumbers buses of the connected bus stops
     * @return true if connected, false otherwise
     */
    private boolean checkDirectRoutes(ArrayList<String> busNumbers) {
        boolean flag = false;
        if (graphA1.isConnected(vertexCodeA1[0], vertexCodeA1[1])) {
            busNumbers.add(BUS_A1);
            flag = true;
        }
        if (graphA2.isConnected(vertexCodeA2[0], vertexCodeA2[1])) {
            busNumbers.add(BUS_A2);
            flag = true;
        }
        if (graphD1.isConnected(vertexCodeD1[0], vertexCodeD1[1])) {
            busNumbers.add(BUS_D1);
            flag = true;
        }
        if (graphD2.isConnected(vertexCodeD2[0], vertexCodeD2[1])) {
            busNumbers.add(BUS_D2);
            flag = true;
        }
        if (graphE.isConnected(vertexCodeE[0], vertexCodeE[1])) {
            busNumbers.add(BUS_E);
            flag = true;
        }
        if (graphK.isConnected(vertexCodeK[0], vertexCodeK[1])) {
            busNumbers.add(BUS_K);
            flag = true;
        }
        return flag;
    }

    /**
     * Checks if any 2 vertices are connected by an indirect path which
     * requires a change of bus.
     *
     * @param busOne bus number which connects to the intermediate bus stop
     * @param busTwo bus number which connects from the intermediate bus stop
     *               to the final location
     * @param midLoc is the intermediate bus stop
     * @return true if connected, false otherwise
     */
    private boolean checkIndirectRoutes(ArrayList<String> busOne, ArrayList<String> busTwo, ArrayList<String> midLoc) {
        boolean flag = false;
        if (vertexCodeA1[0] > 0) {
            flag = checkIndirectAOne(busOne, busTwo, midLoc);
        } else if (vertexCodeA2[0] > 0) {
            flag = checkIndirectATwo(busOne, busTwo, midLoc);
        } else if (vertexCodeD1[0] > 0) {
            if (graphD1.isConnected(vertexCodeD1[0], getStopNumberDOne(STOP_UTOWN))) {
                flag = checkIndirectDOne(busOne, busTwo, midLoc);
            }
        } else if (vertexCodeD2[0] > 0) {
            if (graphD2.isConnected(vertexCodeD2[0], getStopNumberDTwo(STOP_UTOWN))) {
                flag = checkIndirectDTwo(busOne, busTwo, midLoc);
            }
        } else if (vertexCodeE[0] > 0) {
            flag = checkIndirectE(busOne, busTwo, midLoc);
        } else if (vertexCodeK[0] > 0) {
            if (graphK.isConnected(vertexCodeK[0], getStopNumberK(STOP_KENT_VALE))) {
                flag = checkIndirectK(busOne, busTwo, midLoc);
            }
        }
        return flag;
    }

    /**
     * Checks for indirect route in bus route A1.
     *
     * @param busOne bus number which connects to the intermediate bus stop
     * @param busTwo bus number which connects from the intermediate bus stop
     *               to the final location
     * @param midLoc is the intermediate bus stop
     * @return true if connected, false otherwise
     */
    private boolean checkIndirectAOne(ArrayList<String> busOne, ArrayList<String> busTwo, ArrayList<String> midLoc) {
        boolean flag = false;
        busOne.add(BUS_A1);
        midLoc.add(STOP_PGP);
        if (graphD2.isConnected(getStopNumberDTwo(STOP_PGP), vertexCodeD2[1])) {
            busTwo.add(BUS_D2);
            flag = true;
        }
        if (graphK.isConnected(getStopNumberK(STOP_PGP), vertexCodeK[1])) {
            busTwo.add(BUS_K);
            flag = true;
        }
        return flag;
    }

    /**
     * Checks for indirect route in bus route A2.
     *
     * @param busOne bus number which connects to the intermediate bus stop
     * @param busTwo bus number which connects from the intermediate bus stop
     *               to the final location
     * @param midLoc is the intermediate bus stop
     * @return true if connected, false otherwise
     */
    private boolean checkIndirectATwo(ArrayList<String> busOne, ArrayList<String> busTwo, ArrayList<String> midLoc) {
        boolean flag = false;
        busOne.add(BUS_A2);
        midLoc.add(STOP_IT);
        if (graphD1.isConnected(getStopNumberDOne(STOP_IT), vertexCodeD1[1])) {
            busTwo.add(BUS_D1);
            flag = true;
        }
        if (graphE.isConnected(getStopNumberE(STOP_IT), vertexCodeE[1])) {
            busTwo.add(BUS_E);
            flag = true;
        }
        return flag;
    }

    /**
     * Checks for indirect route in bus route D1.
     *
     * @param busOne bus number which connects to the intermediate bus stop
     * @param busTwo bus number which connects from the intermediate bus stop
     *               to the final location
     * @param midLoc is the intermediate bus stop
     * @return true if connected, false otherwise
     */
    private boolean checkIndirectDOne(ArrayList<String> busOne, ArrayList<String> busTwo, ArrayList<String> midLoc) {
        boolean flag = false;
        busOne.add(BUS_D1);
        midLoc.add(STOP_UTOWN);
        if (graphD2.isConnected(getStopNumberDTwo(STOP_UTOWN), vertexCodeD2[1])) {
            busTwo.add(BUS_D2);
            flag = true;
        }
        if (graphE.isConnected(getStopNumberE(STOP_UTOWN), vertexCodeE[1])) {
            busTwo.add(BUS_E);
            flag = true;
        }
        return flag;
    }

    /**
     * Checks for indirect route in bus route D2.
     *
     * @param busOne bus number which connects to the intermediate bus stop
     * @param busTwo bus number which connects from the intermediate bus stop
     *               to the final location
     * @param midLoc is the intermediate bus stop
     * @return true if connected, false otherwise
     */
    private boolean checkIndirectDTwo(ArrayList<String> busOne, ArrayList<String> busTwo, ArrayList<String> midLoc) {
        boolean flag = false;
        busOne.add(BUS_D2);
        midLoc.add(STOP_UTOWN);
        if (graphD1.isConnected(getStopNumberDOne(STOP_UTOWN), vertexCodeD1[1])) {
            busTwo.add(BUS_D1);
            flag = true;
        }
        if (graphE.isConnected(getStopNumberE(STOP_UTOWN), vertexCodeE[1])) {
            busTwo.add(BUS_E);
            flag = true;
        }
        return flag;
    }

    /**
     * Checks for indirect route in bus route E.
     *
     * @param busOne bus number which connects to the intermediate bus stop
     * @param busTwo bus number which connects from the intermediate bus stop
     *               to the final location
     * @param midLoc is the intermediate bus stop
     * @return true if connected, false otherwise
     */
    private boolean checkIndirectE(ArrayList<String> busOne, ArrayList<String> busTwo, ArrayList<String> midLoc) {
        boolean flag = false;
        busOne.add(BUS_E);
        if (vertexCodeE[0] == 6) {
            if (graphK.isConnected(getStopNumberK(STOP_KENT_VALE), vertexCodeK[1])) {
                midLoc.add(STOP_KENT_VALE);
                busTwo.add(BUS_K);
                flag = true;
            }
        } else {
            midLoc.add(STOP_UTOWN);
            if (graphD1.isConnected(getStopNumberDOne(STOP_UTOWN), vertexCodeD1[1])) {
                busTwo.add(BUS_D1);
                flag = true;
            }
            if (graphD2.isConnected(getStopNumberDTwo(STOP_UTOWN), vertexCodeD2[1])) {
                busTwo.add(BUS_D2);
                flag = true;
            }
        }
        return flag;
    }

    /**
     * Checks for indirect route in bus route K.
     *
     * @param busOne bus number which connects to the intermediate bus stop
     * @param busTwo bus number which connects from the intermediate bus stop
     *               to the final location
     * @param midLoc is the intermediate bus stop
     * @return true if connected, false otherwise
     */
    private boolean checkIndirectK(ArrayList<String> busOne, ArrayList<String> busTwo, ArrayList<String> midLoc) {
        boolean flag = false;
        busOne.add(BUS_K);
        midLoc.add(STOP_KENT_VALE);
        if (graphE.isConnected(getStopNumberE(STOP_KENT_VALE), vertexCodeE[1])) {
            busTwo.add(BUS_E);
            flag = true;
        }
        return flag;
    }

    /**
     * Checks the given bus stop name with the corresponding bus stop
     * number in the graph A1 if any.
     *
     * @param command the bus stop name
     * @return the corresponding bus stop number
     */
    public int getStopNumberAOne(String command) {
        assert command != null;
        switch (command.trim().toLowerCase()) {
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
        case "pgp":
            return 6;
        case "kr mrt":
            return 7;
        case "lt27":
            return 8;
        case "uhall":
            return 9;
        case "opp uhc":
            return 10;
        case "yih":
            return 11;
        case "clb":
            return 12;
        default:
            return -1;
        }
    }

    /**
     * Checks the given bus stop name with the corresponding bus stop
     * number in the graph A2 if any.
     *
     * @param command the bus stop name
     * @return the corresponding bus stop number
     */
    public int getStopNumberATwo(String command) {
        assert command != null;
        switch (command.trim().toLowerCase()) {
        case "kr bus terminal":
            return 0;
        case "it":
            return 1;
        case "opp yih":
            return 2;
        case "museum":
            return 3;
        case "uhc":
            return 4;
        case "opp uhall":
            return 5;
        case "s 17":
            return 6;
        case "opp kr mrt":
            return 7;
        case "pgpr":
            return 8;
        case "tcoms":
            return 9;
        case "opp hssml":
            return 10;
        case "opp nuss":
            return 11;
        case "com 2":
            return 12;
        case "ventus":
            return 13;
        default:
            return -1;
        }
    }

    /**
     * Checks the given bus stop name with the corresponding bus stop
     * number in the graph D1 if any.
     *
     * @param command the bus stop name
     * @return the corresponding bus stop number
     */
    public int getStopNumberDOne(String command) {
        assert command != null;
        switch (command.trim().toLowerCase()) {
        case "opp hssml":
            return 0;
        case "opp nuss":
            return 1;
        case "com 2":
            return 2;
        case "ventus":
            return 3;
        case "it":
            return 4;
        case "opp yih":
            return 5;
        case "museum":
            return 6;
        case "utown":
            return 7;
        case "yih":
            return 8;
        case "clb":
            return 9;
        case "lt13":
            return 10;
        case "as 5":
            return 11;
        case "biz 2":
            return 12;
        default:
            return -1;
        }
    }

    /**
     * Checks the given bus stop name with the corresponding bus stop
     * number in the graph D2 if any.
     *
     * @param command the bus stop name
     * @return the corresponding bus stop number
     */
    public int getStopNumberDTwo(String command) {
        assert command != null;
        switch (command.trim().toLowerCase()) {
        case "pgp":
            return 0;
        case "kr mrt":
            return 1;
        case "lt27":
            return 2;
        case "uhall":
            return 3;
        case "opp uhc":
            return 4;
        case "museum":
            return 5;
        case "utown":
            return 6;
        case "uhc":
            return 7;
        case "opp uhall":
            return 8;
        case "s 17":
            return 9;
        case "opp kr mrt":
            return 10;
        case "pgpr":
            return 11;
        default:
            return -1;
        }
    }

    /**
     * Checks the given bus stop name with the corresponding bus stop
     * number in the graph E if any.
     *
     * @param command the bus stop name
     * @return the corresponding bus stop number
     */
    public int getStopNumberE(String command) {
        assert command != null;
        switch (command.trim().toLowerCase()) {
        case "kent vale":
            return 0;
        case "ea":
            return 1;
        case "sde 3":
            return 2;
        case "it":
            return 3;
        case "opp yih":
            return 4;
        case "utown":
            return 5;
        case "raffles hall":
            return 6;
        default:
            return -1;
        }
    }

    /**
     * Checks the given bus stop name with the corresponding bus stop
     * number in the graph K if any.
     *
     * @param command the bus stop name
     * @return the corresponding bus stop number
     */
    public int getStopNumberK(String command) {
        assert command != null;
        switch (command.trim().toLowerCase()) {
        case "pgp":
            return 0;
        case "kr mrt":
            return 1;
        case "lt27":
            return 2;
        case "uhall":
            return 3;
        case "opp uhc":
            return 4;
        case "yih":
            return 5;
        case "clb":
            return 6;
        case "opp sde 3":
            return 7;
        case "japanese pri school":
            return 8;
        case "kent vale":
            return 9;
        case "museum":
            return 10;
        case "uhc":
            return 11;
        case "opp uhall":
            return 12;
        case "s 17":
            return 13;
        case "opp kr mrt":
            return 14;
        case "pgpr":
            return 15;
        default:
            return -1;
        }
    }
}
