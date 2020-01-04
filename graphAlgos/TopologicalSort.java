package graphAlgos;

import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class TopologicalSort {
    public static void main(String args[]) {
        MyGraph myGraph = new MyGraph(13);
        myGraph.addEdge(0, 3, 1);
        myGraph.addEdge(1, 3, 1);
        myGraph.addEdge(2, 0, 1);
        myGraph.addEdge(2, 1, 1);
        myGraph.addEdge(3, 6, 1);
        myGraph.addEdge(3, 7, 1);  
        myGraph.addEdge(4, 0, 1);
        myGraph.addEdge(4, 3, 1);
        myGraph.addEdge(4, 5, 1);
        myGraph.addEdge(5, 10, 1);
        myGraph.addEdge(5, 9, 1);
        myGraph.addEdge(6, 8, 1);
        myGraph.addEdge(7, 8, 1);
        myGraph.addEdge(7, 9, 1);
        myGraph.addEdge(8, 11, 1);
        myGraph.addEdge(9, 11, 1);
        myGraph.addEdge(9, 12, 1);
        myGraph.addEdge(10, 9, 1);    
        topSort(myGraph);                  
    }
    public static List<Integer> topSort(MyGraph myGraph) {
        if(myGraph == null || myGraph.vertices == 0)  {
            return new ArrayList<Integer>();
        }
        HashSet<Integer> visited = new HashSet<Integer>();
        List<Integer> topOrder = new ArrayList<Integer>();
        for(int i = myGraph.vertices - 1; i >= 0; i--) {
            if(!visited.contains(i)) {
                dfsUtilityForTopsort(myGraph, i, visited, topOrder);
            }
        }
        Collections.reverse(topOrder);
        System.out.println("Topological Order for the graph : ");
        for(Integer vertex: topOrder) {
            System.out.print(" "+ vertex);
        }
        System.out.println();
        return topOrder;
    }

    public static void dfsUtilityForTopsort(MyGraph myGraph, int startNode, HashSet<Integer> visited, List<Integer> topOrder) {
        visited.add(startNode);
        List<Edge> edges = myGraph.graph.get(startNode);
        if(edges == null || edges.size() == 0) {
            if(topOrder.indexOf(startNode) >= 0) {
                return;
            }
            topOrder.add(startNode);
        } else {
            for(Edge e: edges) {
                if(!visited.contains(e.to)) {
                    dfsUtilityForTopsort(myGraph, e.to, visited, topOrder);
                }
            }
            if(!(topOrder.indexOf(startNode) >= 0)) {
                topOrder.add(startNode);
            }
        }
    }
}