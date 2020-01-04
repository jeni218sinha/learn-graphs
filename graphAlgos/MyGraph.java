package graphAlgos;

import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class MyGraph {
    Map<Integer, List<Edge>> graph;
    int vertices;
    MyGraph(int vertices) {
        graph = new HashMap<Integer, List<Edge>>();
        this.vertices = vertices;
    }

    public void addEdge(int from, int to, int cost) {
        List<Edge> nodeEdges = graph.get(from);
        if(nodeEdges == null) {
            // from vertex not added yet
            nodeEdges = new ArrayList<Edge>();
        }
        nodeEdges.add(new Edge(from, to, cost));
        graph.put(from, nodeEdges);
    }
}