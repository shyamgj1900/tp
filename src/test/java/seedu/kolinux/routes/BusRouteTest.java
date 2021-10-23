package seedu.kolinux.routes;

import org.junit.jupiter.api.Test;
import seedu.kolinux.exceptions.KolinuxException;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;


public class BusRouteTest {
    private static final String[] VALID_STOP_NAME = {"LT13", "Opp YIH", "Ventus", "Opp UHC", "UTown", "CLB"};
    private static final String INVALID_STOP_NAME = "BusStop";
    private int[][] vertices = {{0, 1}, {1, 2}, {2, 3}, {3, 4}, {4, 2}};

    @Test
    public void enterStopName_checkStopName_validStopName() {
        Location location = new Location();
        assertEquals(1, location.getStopNumberAOne(VALID_STOP_NAME[0]));
        assertEquals(2, location.getStopNumberATwo(VALID_STOP_NAME[1]));
        assertEquals(3, location.getStopNumberDOne(VALID_STOP_NAME[2]));
        assertEquals(4, location.getStopNumberDTwo(VALID_STOP_NAME[3]));
        assertEquals(5, location.getStopNumberE(VALID_STOP_NAME[4]));
        assertEquals(6, location.getStopNumberK(VALID_STOP_NAME[5]));
    }

    @Test
    public void enterStopName_checkStopName_invalidStopName() {
        Location location = new Location();
        assertEquals(-1, location.getStopNumberDOne(INVALID_STOP_NAME));
    }

    @Test
    public void enterEdges_testPath_validPath() {
        Graph graph = new Graph(5);
        for (int i = 0; i < 5; i++) {
            graph.addEdge(vertices[i][0], vertices[i][1]);
        }
        assertEquals(true, graph.isConnected(0, 3));
    }

    @Test
    public void enterEdges_testPath_invalidPath() {
        Graph graph = new Graph(5);
        for (int i = 0; i < 5; i++) {
            graph.addEdge(vertices[i][0], vertices[i][1]);
        }
        assertEquals(false, graph.isConnected(3, 1));
    }

    @Test
    public void enterLocation_checkDirectRoute_validRoute() throws KolinuxException, IOException {
        String[] input = {"bus", "kr mrt", "utown"};
        ArrayList<String> busNumbers = new ArrayList<>();
        Route route = new Route(input);
        route.getBusStopNumber();
        assertTrue(route.checkDirectRoutes(busNumbers));
        assertEquals("[D2]", busNumbers.toString());
    }

    @Test
    public void enterLocation_checkDirectRoute_invalidRoute() throws KolinuxException, IOException {
        String[] input = {"bus", "lt13", "utown"};
        ArrayList<String> busNumbers = new ArrayList<>();
        Route route = new Route(input);
        route.getBusStopNumber();
        assertFalse(route.checkDirectRoutes(busNumbers));
    }

    @Test
    public void enterLocation_checkIndirectRoute_validRoute() throws KolinuxException, IOException {
        String[] input = {"bus", "tcoms", "kent vale"};
        ArrayList<String> busNumberOne = new ArrayList<>();
        ArrayList<String> busNumberTwo = new ArrayList<>();
        ArrayList<String> midLocation = new ArrayList<>();
        Route route = new Route(input);
        route.getBusStopNumber();
        assertTrue(route.checkIndirectRoutes(busNumberOne, busNumberTwo, midLocation));
        assertEquals("[A2]", busNumberOne.toString());
        assertEquals("[E]", busNumberTwo.toString());
        assertEquals("IT", midLocation.get(0));
    }

    @Test
    public void enterLocation_checkIndirectRoute_invalidRoute() throws KolinuxException, IOException {
        String[] input = {"bus", "utown", "kr mrt"};
        ArrayList<String> busNumberOne = new ArrayList<>();
        ArrayList<String> busNumberTwo = new ArrayList<>();
        ArrayList<String> midLocation = new ArrayList<>();
        Route route = new Route(input);
        route.getBusStopNumber();
        assertFalse(route.checkIndirectRoutes(busNumberOne, busNumberTwo, midLocation));
    }

}
