package seedu.kolinux.routes;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BusRouteTest {
    private static final String VALID_STOP_NAME = "UTown";
    private static final String INVALID_STOP_NAME = "BusStop";
    private int[][] vertices = {{0, 1}, {1, 2}, {2, 3}, {3, 4}, {4, 2}};

    @Test
    public void enterStopName_checkStopName_validStopName() {
        Location location = new Location();
        assertEquals(7, location.getStopNumberDOne(VALID_STOP_NAME));
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

}
