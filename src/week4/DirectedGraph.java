package week4;

import java.util.ArrayList;
import java.util.List;

public class DirectedGraph {

    private List<Integer>[] adjacencyList;

    @SuppressWarnings("unchecked")
    public DirectedGraph(int edgesAmount) {
        adjacencyList = (List<Integer>[]) new List[edgesAmount];
        for (int i = 0; i < edgesAmount; i++) {
            adjacencyList[i] = new ArrayList<Integer>();
        }
    }

    public void addEdge(int firstEdge, int secondEdge) {
        adjacencyList[firstEdge].add(secondEdge);
    }
    
    public List<Integer> getAdjacencyList(int v) {
        return adjacencyList[v];
    }

}