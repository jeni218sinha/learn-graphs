package graphAlgos;

import java.util.List;
import java.util.Arrays;

public class SsspUsingTopSort {
    public static void main(String args[]) {
        MyGraph myGraph = new MyGraph(8);
        myGraph.addEdge(0, 1, 3);
        myGraph.addEdge(0, 2, 6);
        myGraph.addEdge(1, 3, 4);
        myGraph.addEdge(1, 4, 11);
        myGraph.addEdge(1, 2, 4);
        myGraph.addEdge(2, 3, 8);
        myGraph.addEdge(2, 6, 11);
        myGraph.addEdge(3, 4, -4);
        myGraph.addEdge(3, 5, 5);
        myGraph.addEdge(3, 6, 2);
        myGraph.addEdge(4, 7, 9);
        myGraph.addEdge(5, 7, 1);
        myGraph.addEdge(6, 7, 2);
        int source = 1;
        findSsspUsingTopSort(myGraph, source);
    }
 
    public static void findSsspUsingTopSort(MyGraph myGraph, int source) {
        if(myGraph == null || myGraph.vertices == 0 || !myGraph.graph.containsKey(source)) {
            return;
        }
        List<Integer> topOrder = TopologicalSort.topSort(myGraph);
        Integer[] distance = new Integer[myGraph.vertices];
        distance[source] = 0;
        
        for (int i = 0; i < myGraph.vertices; i++) {
            int nodeIndex = topOrder.indexOf(i);
            if (distance[nodeIndex] != null) {
                List<Edge> adjacentEdges = myGraph.graph.get(nodeIndex);
                if (adjacentEdges != null) {
                    for (Edge edge : adjacentEdges) {
                        int newDist = distance[nodeIndex] + edge.cost;
                        if (distance[edge.to] == null) distance[edge.to] = newDist;
                        else distance[edge.to] = Math.min(distance[edge.to], newDist);
                    }
                }
            }
        }       

        System.out.println(" Single Source shortest path ");
        for(int i = 0; i < myGraph.vertices; i++) {
            System.out.print(" "+ distance[i]);
        }
    }
}