package graphAlgos;

import java.util.Queue;
import java.util.LinkedList;
import java.util.HashSet;
import java.util.List;
import java.util.Arrays;

public class BreadthFirstGraph {
    public static void main(String args[]) {
        MyGraph myGraph = new MyGraph(6);
        myGraph.addEdge(0, 1, 4);
        myGraph.addEdge(0, 2, 5);
        myGraph.addEdge(1, 2, -2);
        myGraph.addEdge(1, 3, 6);
        myGraph.addEdge(2, 3, 1);
        myGraph.addEdge(2, 2, 10); // Self loop      
        myGraph.addEdge(3, 4, 90);
        myGraph.addEdge(4, 5, 0);
        bfs(myGraph, 0);
        getShortestPathFrom(myGraph, 1, 0);
    }

    public static int[] bfs(MyGraph myGraph, int startNode) {
        if(myGraph.vertices == 0) {
            return new int[0];
        }
        if(!myGraph.graph.containsKey(startNode)) {
            return new int[0];
        }
        int[] path = new int[myGraph.vertices];
        Arrays.fill(path, -1);
        Queue<Integer> q = new LinkedList<Integer>();
        HashSet<Integer> visited = new HashSet<Integer>();
        q.offer(startNode);
        visited.add(startNode);
        System.out.println(" BFS Starting  from node " + startNode);
        while(!q.isEmpty()) {
            int size = q.size();
            while(size > 0) {
                Integer node = q.poll();
                System.out.println(node);
                size--;
                List<Edge> edges = myGraph.graph.get(node);
                if(edges != null && edges.size() > 0) {
                    for(int i = 0;  i < edges.size(); i++) {
                        if(!visited.contains(edges.get(i).to)) {
                            path[edges.get(i).to] = node;
                            q.offer(edges.get(i).to);
                            visited.add(edges.get(i).to);
                        }
                    }
                }
            }
        }
        return path;
    }

    public static void getShortestPathFrom(MyGraph myGraph, int startNode, int endNode) {
        if(myGraph.graph == null || !myGraph.graph.containsKey(startNode) || !myGraph.graph.containsKey(endNode)) {
            System.out.println("Nodes not present in the graph");
            return;
        }
        int[] path = bfs(myGraph, startNode);
        int currNode = endNode;
        System.out.println(" Printing shortest path from " + startNode + " to " + endNode);
        if(path[currNode] == -1) {
            System.out.println(" No Path exists");
            return;
        }
        while(currNode != startNode) {
            System.out.println(currNode);
            currNode = path[currNode];
        }
        System.out.println(startNode);
    }
}