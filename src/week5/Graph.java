package week5;

import java.util.LinkedList;
import java.util.List;

public class Graph {
    
    private List<Pair>[] adjacencyList;
    private int vertexAmount;
    
    @SuppressWarnings("unchecked")
    public Graph(int vertexAmount) {
        this.vertexAmount = vertexAmount;
        adjacencyList = (List<Pair>[]) new List[vertexAmount];
        for (int i = 0; i < vertexAmount; i++) {
            adjacencyList[i] = new LinkedList<Pair>();
        }
    }
    
    public int getVertexAmount() {
        return vertexAmount;
    }

    public void addEdge(int firstVertexIndex, int secondVertexIndex, int weight) {
        adjacencyList[firstVertexIndex].add(new Pair(secondVertexIndex, weight));
    }

    public int getDistance(int firstVertexIndex, int secondVertexIndex) {
        int weight = -1;
        for (Pair p : adjacencyList[firstVertexIndex]) {
            if (p.getIndex() == secondVertexIndex) {
                weight =  p.getWeight();
                break;
            }
        }
        return weight;
    }

    public List<Pair> getAdjacencyList(int vertexIndex) {
        return adjacencyList[vertexIndex];
    }

}
