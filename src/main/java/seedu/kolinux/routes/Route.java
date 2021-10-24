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
    public static final String FILEPATH_A1 = "/routeA1.txt";
    public static final String FILEPATH_A2 = "/routeA2.txt";
    public static final String FILEPATH_D1 = "/routeD1.txt";
    public static final String FILEPATH_D2 = "/routeD2.txt";
    public static final String FILEPATH_E = "/routeE.txt";
    public static final String FILEPATH_K = "/routeK.txt";
    public static final int TOTAL_VERTICES_A1 = 13;
    public static final int TOTAL_VERTICES_A2 = 14;
    public static final int TOTAL_VERTICES_D1 = 13;
    public static final int TOTAL_VERTICES_D2 = 12;
    public static final int TOTAL_VERTICES_E = 7;
    public static final int TOTAL_VERTICES_K = 16;
    public static final int STOP_NUMBER_RAFFLES_HALL = 6;

    private String[] busStops;
    private String startLocation;
    private String endLocation;
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
    private Location location;
    private ArrayList<String> verticesA1;
    private ArrayList<String> verticesA2;
    private ArrayList<String> verticesD1;
    private ArrayList<String> verticesD2;
    private ArrayList<String> verticesE;
    private ArrayList<String> verticesK;
    private String[][] oppositeStops = {{"KR MRT", "OPP KR MRT"}, {"LT27", "S 17"}, {"UHALL", "OPP UHALL"},
            {"OPP UHC", "UHC"}, {"YIH", "OPP YIH"}, {"CLB", "IT"}, {"EA", "JAPANESE PRI SCHOOL"}, {"PGP", "PGPR"},
            {"SDE 3", "OPP SDE 3"}, {"VENTUS", "LT13"}, {"OPP HSSML", "BIZ 2"}, {"TCOMS", "OPP TCOMS"}};

    public Route(String[] splitInput) throws KolinuxException, IOException {
        busStops = new String[2];
        System.arraycopy(splitInput, 1, busStops, 0, 2);
        startLocation = busStops[0].trim().toUpperCase();
        endLocation = busStops[1].trim().toUpperCase();
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
        location = new Location();
        verticesA1 = new ArrayList<>();
        verticesA2 = new ArrayList<>();
        verticesD1 = new ArrayList<>();
        verticesD2 = new ArrayList<>();
        verticesE = new ArrayList<>();
        verticesK = new ArrayList<>();
        readTextFromFile(verticesA1, FILEPATH_A1);
        readTextFromFile(verticesA2, FILEPATH_A2);
        readTextFromFile(verticesD1, FILEPATH_D1);
        readTextFromFile(verticesD2, FILEPATH_D2);
        readTextFromFile(verticesE, FILEPATH_E);
        readTextFromFile(verticesK, FILEPATH_K);
        setRoute(verticesA1, graphA1);
        setRoute(verticesA2, graphA2);
        setRoute(verticesD1, graphD1);
        setRoute(verticesD2, graphD2);
        setRoute(verticesE, graphE);
        setRoute(verticesK, graphK);
    }

    /**
     * Reads the contents from the file which consists of the path
     * of the graph.
     *
     * @param lines contains the nodes which connect the graph
     * @param filePath the path of the input file
     * @throws KolinuxException if the user command is not in the correct format
     * @throws IOException      if the there any IO errors
     */
    public static void readTextFromFile(ArrayList<String> lines, String filePath) throws KolinuxException, IOException {
        try {
            InputStream inputStream = Main.class.getResourceAsStream(filePath);
            if (inputStream == null) {
                throw new KolinuxException("File not found.");
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
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
    private void setRoute(ArrayList<String> vertices, Graph graph) {
        assert vertices.size() != 0;
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
    public void getBusStopNumber() throws KolinuxException {
        for (int i = 0; i < 2; i++) {
            vertexCodeA1[i] = location.getStopNumberAOne(busStops[i]);
            vertexCodeA2[i] = location.getStopNumberATwo(busStops[i]);
            vertexCodeD1[i] = location.getStopNumberDOne(busStops[i]);
            vertexCodeD2[i] = location.getStopNumberDTwo(busStops[i]);
            vertexCodeE[i] = location.getStopNumberE(busStops[i]);
            vertexCodeK[i] = location.getStopNumberK(busStops[i]);
            if (vertexCodeA1[i] < 0 && vertexCodeA2[i] < 0 && vertexCodeD1[i] < 0
                    && vertexCodeE[i] < 0 && vertexCodeD2[i] < 0 && vertexCodeK[i] < 0) {
                throw new KolinuxException(busStops[i].trim() + " is not a valid bus stop name.");
            }
        }
    }

    /**
     * Finds if there are bus routes between the starting and end locations.
     *
     * @return Message which specifies if any routes are found
     * @throws KolinuxException if the user command is not in the correct format
     */
    public String checkRoutes() throws KolinuxException {
        getBusStopNumber();
        ArrayList<String> busNumbers = new ArrayList<>();
        boolean[] flag = new boolean[2];
        flag[0] = checkDirectRoutes(busNumbers);
        if (!flag[0]) {
            ArrayList<String> busNumberOne = new ArrayList<>();
            ArrayList<String> busNumberTwo = new ArrayList<>();
            ArrayList<String> midLocation = new ArrayList<>();
            flag[1] = checkIndirectRoutes(busNumberOne, busNumberTwo, midLocation);
            if (!flag[1]) {
                return checkAlternateRoutes(busNumbers);
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
    public boolean checkDirectRoutes(ArrayList<String> busNumbers) {
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
    public boolean checkIndirectRoutes(ArrayList<String> busOne, ArrayList<String> busTwo, ArrayList<String> midLoc) {
        if (vertexCodeA1[0] > 0 && checkIndirectAOne(busOne, busTwo, midLoc)) {
            return true;
        }
        if (vertexCodeA2[0] > 0 && checkIndirectATwo(busOne, busTwo, midLoc)) {
            return true;
        }
        if (vertexCodeD1[0] > 0 && graphD1.isConnected(vertexCodeD1[0], location.getStopNumberDOne(STOP_UTOWN))) {
            if (checkIndirectDOne(busOne, busTwo, midLoc)) {
                return true;
            }
        }
        if (vertexCodeD2[0] > 0 && graphD2.isConnected(vertexCodeD2[0], location.getStopNumberDTwo(STOP_UTOWN))) {
            if (checkIndirectDTwo(busOne, busTwo, midLoc)) {
                return true;
            }
        }
        if (vertexCodeE[0] > 0 && checkIndirectE(busOne, busTwo, midLoc)) {
            return true;
        }
        if (vertexCodeK[0] > 0 && graphK.isConnected(vertexCodeK[0], location.getStopNumberK(STOP_KENT_VALE))) {
            if (checkIndirectK(busOne, busTwo, midLoc)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if there is an alternate route from the opposite bus stop of the
     * starting location.
     *
     * @param busNumbers bus number which connects the two locations
     * @return Message which specifies if any alternate routes are found
     * @throws KolinuxException if the user command is not in the correct format
     */
    private String checkAlternateRoutes(ArrayList<String> busNumbers) throws KolinuxException {
        boolean flag;
        for (int i = 0; i < 12; i++) {
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
        if (graphD2.isConnected(location.getStopNumberDTwo(STOP_PGP), vertexCodeD2[1])) {
            busTwo.add(BUS_D2);
            flag = true;
        }
        if (graphK.isConnected(location.getStopNumberK(STOP_PGP), vertexCodeK[1])) {
            busTwo.add(BUS_K);
            flag = true;
        }
        if (flag) {
            busOne.add(BUS_A1);
            midLoc.add(STOP_PGP);
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
        if (graphD1.isConnected(location.getStopNumberDOne(STOP_IT), vertexCodeD1[1])) {
            busTwo.add(BUS_D1);
            flag = true;
        }
        if (graphE.isConnected(location.getStopNumberE(STOP_IT), vertexCodeE[1])) {
            busTwo.add(BUS_E);
            flag = true;
        }
        if (flag) {
            busOne.add(BUS_A2);
            midLoc.add(STOP_IT);
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
        if (graphD2.isConnected(location.getStopNumberDTwo(STOP_UTOWN), vertexCodeD2[1])) {
            busTwo.add(BUS_D2);
            flag = true;
        }
        if (graphE.isConnected(location.getStopNumberE(STOP_UTOWN), vertexCodeE[1])) {
            busTwo.add(BUS_E);
            flag = true;
        }
        if (flag) {
            busOne.add(BUS_D1);
            midLoc.add(STOP_UTOWN);
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
        if (graphD1.isConnected(location.getStopNumberDOne(STOP_UTOWN), vertexCodeD1[1])) {
            busTwo.add(BUS_D1);
            flag = true;
        }
        if (graphE.isConnected(location.getStopNumberE(STOP_UTOWN), vertexCodeE[1])) {
            busTwo.add(BUS_E);
            flag = true;
        }
        if (flag) {
            busOne.add(BUS_D2);
            midLoc.add(STOP_UTOWN);
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
        if (vertexCodeE[0] == STOP_NUMBER_RAFFLES_HALL) {
            if (graphK.isConnected(location.getStopNumberK(STOP_KENT_VALE), vertexCodeK[1])) {
                busTwo.add(BUS_K);
                flag = true;
            }
            if (flag) {
                busOne.add(BUS_E);
                midLoc.add(STOP_KENT_VALE);
            }
        } else {
            if (graphD1.isConnected(location.getStopNumberDOne(STOP_UTOWN), vertexCodeD1[1])) {
                busTwo.add(BUS_D1);
                flag = true;
            }
            if (graphD2.isConnected(location.getStopNumberDTwo(STOP_UTOWN), vertexCodeD2[1])) {
                busTwo.add(BUS_D2);
                flag = true;
            }
            if (flag) {
                busOne.add(BUS_E);
                midLoc.add(STOP_UTOWN);
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
        if (graphE.isConnected(location.getStopNumberE(STOP_KENT_VALE), vertexCodeE[1])) {
            busTwo.add(BUS_E);
            flag = true;
        }
        return flag;
    }
}


