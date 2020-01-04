package graphAlgos;

import java.util.HashSet;
import java.util.List;

public class DepthFirstRecursive {
    public static void main(String args[]) {
        MyGraph myGraph = new MyGraph(6);
        myGraph.addEdge(0, 1, 4);
        myGraph.addEdge(0, 2, 5);
        myGraph.addEdge(1, 2, -2);
        myGraph.addEdge(1, 3, 6);
        myGraph.addEdge(2, 3, 1);
        myGraph.addEdge(2, 2, 10); // Self loop      
        dfs(myGraph, 0);
    }

    public static void dfs(MyGraph myGraph, int startNode) {
        if(myGraph == null || myGraph.vertices == 0 || !myGraph.graph.containsKey(startNode)) {
            return;
        }
        HashSet<Integer> visited = new HashSet<Integer>();
        dfsUtil(myGraph, startNode, visited);
    }
    public static void dfsUtil(MyGraph myGraph, int startNode, HashSet<Integer> visited)  {
        System.out.println(startNode);
        visited.add(startNode);
        List<Edge> edges = myGraph.graph.get(startNode);
        if(edges != null && edges.size() > 0) {
            for(Edge edge : edges) {
                if(!visited.contains(edge.to) && edge.to != startNode) {
                    dfsUtil(myGraph, edge.to, visited);
                }
            }
        }
    }
}