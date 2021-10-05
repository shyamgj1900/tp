package seedu.duke.routes;

import java.util.Iterator;
import java.util.LinkedList;

public class Graph {
    private int ver;
    private LinkedList<Integer>[] adj;

    public Graph(int ver) {
        this.ver = ver;
        adj = new LinkedList[ver];
        for (int i = 0; i < ver; i++) {
            adj[i] = new LinkedList<>();
        }
    }

    public void addEdge(int v, int w) {
        adj[v].add(w);
    }

    public Boolean isConnected(int u, int v) {
        int vertex;
        boolean[] visited = new boolean[this.ver];
        LinkedList<Integer> queue = new LinkedList<>();
        visited[u] = true;
        queue.add(u);
        Iterator<Integer> i;
        while (queue.size() != 0) {
            u = queue.poll();
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
