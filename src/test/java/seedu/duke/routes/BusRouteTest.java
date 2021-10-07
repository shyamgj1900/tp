package seedu.duke.routes;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BusRouteTest {
    private String[] validStopName = {"UTown", "BIZ 2", "LT27"};
    private String invalidStopName = "BusStop";
    private int[][] vertices = {{0, 1}, {1, 2}, {2, 3}, {3, 4}, {4, 2}};

    @Test
    public void enterStopName_checkStopName_validStopName() {
        Route route = new Route();
        assertEquals(7, route.getStopNameDOne(validStopName[0]));
    }

    @Test
    public void enterStopName_checkStopName_invalidStopName() {
        Route route = new Route();
        assertEquals(-1, route.getStopNameDOne(invalidStopName));
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
