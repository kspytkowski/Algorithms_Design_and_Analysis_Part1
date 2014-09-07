package week5;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class DijkstraShortestPath {

    private int INFINITY = 1000000;
    private int SOURCE_VERTEX = 1;
    
    private int[] dijkstra(Graph graph, int vertexIndex) {
        int[] d = new int[graph.getVertexAmount()];
        d[vertexIndex] = 0;
        for (int v = 1; v < graph.getVertexAmount(); v++) {
            d[v] = INFINITY;
        }
        Queue<Pair> priorityQueue = new PriorityQueue<Pair>(1);
        for (int i = 0; i < graph.getAdjacencyList(vertexIndex).size(); i++) {
            priorityQueue.add(graph.getAdjacencyList(vertexIndex).get(i));
            d[graph.getAdjacencyList(vertexIndex).get(i).getIndex()] = graph.getAdjacencyList(vertexIndex).get(i).getWeight();
        }
        Pair u;
        while (!priorityQueue.isEmpty()) {
            u = priorityQueue.poll();
            List<Pair> V = graph.getAdjacencyList(u.getIndex());
            for (Pair v : V) {
                if (d[u.getIndex()] + graph.getDistance(u.getIndex(), v.getIndex()) < d[v.getIndex()]) {
                    d[v.getIndex()] = d[u.getIndex()] + graph.getDistance(u.getIndex(), v.getIndex());
                    priorityQueue.add(v);
                }
            }
        }
        return d;
    }
    
    private void fillGraphWithEdges(String filename, Graph graph) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] elementsOfLine = line.split("\t");
                for (int j = 1; j < elementsOfLine.length; j++) {
                    graph.addEdge(Integer.parseInt(elementsOfLine[0]), Integer.parseInt(elementsOfLine[j].split(",")[0]), Integer.parseInt(elementsOfLine[j].split(",")[1]));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("There in no such file");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("For some reason cannot read from file");
            e.printStackTrace();
        }
    }

    private void solveProblem() {
        String filename = "/home/krzysztof/workspace/Algorithms_Design_and_Analysis_Part1/src/week5/dijkstraData.txt";
        String vertexNumber = LastLineInFile.find(new File(filename));
        Graph graph = new Graph(Integer.parseInt(vertexNumber.split("\t")[0]) + 1);
        fillGraphWithEdges(filename, graph);
        int[] shortestPaths = dijkstra(graph, SOURCE_VERTEX);
        System.out.println(shortestPaths[7] + "," + shortestPaths[37] + "," + shortestPaths[59] + "," + shortestPaths[82] + "," + shortestPaths[99] + "," + shortestPaths[115] + "," + shortestPaths[133] + "," + shortestPaths[165] + "," + shortestPaths[188] + "," + shortestPaths[197]);
    }

    public static void main(String[] args) {
        new DijkstraShortestPath().solveProblem();
    }
}
