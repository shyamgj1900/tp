package seedu.kolinux.routes;

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

    /**
     * Forms the edge between to connected vertices.
     *
     * @param u start vertex
     * @param v end vertex
     */
    public void addEdge(int u, int v) {
        adj[u].add(v);
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
        if (u < 0 || v < 0) {
            return false;
        }
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
