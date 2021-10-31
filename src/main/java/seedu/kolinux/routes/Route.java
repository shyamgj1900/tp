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
    public static final String STOP_PGPR = "PGPR";
    public static final String FILE_NOT_FOUND_MESSAGE = "File not found.";
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
    public static final int STOP_NUMBER_RAFFLES_HALL_E = 6;
    public static final int STOP_NUMBER_UTOWN_D2 = 6;
    public static final int STOP_NUMBER_KENT_VALE_K = 9;

    protected String[] busStops;
    protected String startLocation;
    protected String endLocation;
    protected int[] vertexCodeA1;
    protected int[] vertexCodeA2;
    protected int[] vertexCodeD1;
    protected int[] vertexCodeD2;
    protected int[] vertexCodeE;
    protected int[] vertexCodeK;
    protected Graph[] graph;
    protected Location location;
    protected ArrayList<String>[] vertices;
    protected String[][] oppositeStops = {{"KR MRT", "OPP KR MRT"}, {"LT27", "S 17"}, {"UHALL", "OPP UHALL"},
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
        graph = new Graph[6];
        graph[0] = new Graph(TOTAL_VERTICES_A1);
        graph[1] = new Graph(TOTAL_VERTICES_A2);
        graph[2] = new Graph(TOTAL_VERTICES_D1);
        graph[3] = new Graph(TOTAL_VERTICES_D2);
        graph[4] = new Graph(TOTAL_VERTICES_E);
        graph[5] = new Graph(TOTAL_VERTICES_K);
        location = new Location();
        vertices = new ArrayList[6];
        for (int i = 0; i < 6; i++) {
            vertices[i] = new ArrayList<>();
        }
        readFromFile(vertices[0], FILEPATH_A1);
        readFromFile(vertices[1], FILEPATH_A2);
        readFromFile(vertices[2], FILEPATH_D1);
        readFromFile(vertices[3], FILEPATH_D2);
        readFromFile(vertices[4], FILEPATH_E);
        readFromFile(vertices[5], FILEPATH_K);
        for (int i = 0; i < 6; i++) {
            setRoute(vertices[i], graph[i]);
        }
    }

    /**
     * Reads the contents from the file which consists of the path
     * of the graph.
     *
     * @param lines contains the nodes which connect the graph
     * @param filePath the path of the input file
     * @throws KolinuxException if the user command is not in the correct format
     * @throws IOException if the there any IO errors
     */
    protected static void readFromFile(ArrayList<String> lines, String filePath) throws KolinuxException, IOException {
        try {
            InputStream inputStream = Main.class.getResourceAsStream(filePath);
            if (inputStream == null) {
                throw new KolinuxException(FILE_NOT_FOUND_MESSAGE);
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
}


