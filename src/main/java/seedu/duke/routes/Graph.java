package seedu.duke.routes;

import java.util.ArrayList;
import java.util.Iterator;

public class Graph {
    private int ver;
    private ArrayList<Integer>[] adj;

    public Graph(int ver) {
        this.ver = ver;
        adj = new ArrayList[ver];
        for (int i = 0; i < ver; i++) {
            adj[i] = new ArrayList<>();
        }
    }

    public void addEdge(int v, int w) {
        adj[v].add(w);
    }

    /**
     * Uses BFS to check if 2 nodes in a graph are connected with
     * each other.
     *
     * @param u start vertex
     * @param v final vertex
     * @return true if connected, false otherwise
     */
    public Boolean isConnected(int u, int v) {
        int vertex;
        boolean[] visited = new boolean[this.ver];
        ArrayList<Integer> queue = new ArrayList<>();
        visited[u] = true;
        queue.add(u);
        Iterator<Integer> i;
        while (queue.size() != 0) {
            u = queue.get(0);
            queue.remove(0);
            i = adj[u].listIterator();
            while (i.hasNext()) {
                vertex = i.next();
                if (vertex == v) {
                    return true;
                } else if (!visited[vertex]) {
                    visited[vertex] = true;
                    queue.add(vertex);
                }
            }
        }
        return false;
    }
}
