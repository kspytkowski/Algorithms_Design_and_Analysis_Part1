package week4;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class ComputingStronglyConnectedComponentsInGraph {

    private int[] sizesOfFiveLargestSCCs = { 0, 0, 0, 0, 0 };
    private int time = 0;

    private void DepthFirstSearch(DirectedGraph graph, int edge, boolean[] explored, int[] begin, int[] end) {
        explored[edge] = true;
        time++;
        begin[edge] = time;
        int i = 0;
        Iterator<Integer> iter = graph.getAdjacencyList(edge).iterator();
        if (iter != null) {
            while (iter.hasNext()) {
                i = iter.next();
                if (explored[i] == false) {
                    DepthFirstSearch(graph, i, explored, begin, end);
                }
            }
        }
        time++;
        end[edge] = time;
    }

    private int findMaxVertexNumber(String filename) {
        int maxVertexNumber = -1;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filename))) {
            String line;
            int firstVertexNumber = 0;
            int secondVertexNumber = 0;
            while ((line = bufferedReader.readLine()) != null) {
                String[] elementsOfLine = line.split(" ");
                firstVertexNumber = Integer.parseInt(elementsOfLine[0]);
                secondVertexNumber = Integer.parseInt(elementsOfLine[1]);
                if (firstVertexNumber > maxVertexNumber) {
                    maxVertexNumber = firstVertexNumber;
                }
                if (secondVertexNumber > maxVertexNumber) {
                    maxVertexNumber = secondVertexNumber;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("There in no such file");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("For some reason cannot read from file");
            e.printStackTrace();
        }
        return maxVertexNumber;
    }

    private void fillGraphsWithEdges(String filename, DirectedGraph directedGraph, DirectedGraph transposedDirectedGraph) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filename))) {
            String line;
            int firstVertexNumber = 0;
            int secondVertexNumber = 0;
            while ((line = bufferedReader.readLine()) != null) {
                String[] elementsOfLine = line.split(" ");
                firstVertexNumber = Integer.parseInt(elementsOfLine[0]);
                secondVertexNumber = Integer.parseInt(elementsOfLine[1]);
                directedGraph.addEdge(firstVertexNumber, secondVertexNumber);
                transposedDirectedGraph.addEdge(secondVertexNumber, firstVertexNumber);
            }
        } catch (FileNotFoundException e) {
            System.out.println("There in no such file");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("For some reason cannot read from file");
            e.printStackTrace();
        }
    }

    public void solveProblem() {
        String filename = "/home/krzysztof/workspace/Algorithms_Design_and_Analysis_Part1/src/week4/SCC.txt";
        int maxVertexNumber = findMaxVertexNumber(filename);
        DirectedGraph directedGraph = new DirectedGraph(maxVertexNumber + 1);
        DirectedGraph transposedDirectedGraph = new DirectedGraph(maxVertexNumber + 1);
        boolean[] explored = new boolean[maxVertexNumber + 1];
        boolean[] transposedExplored = new boolean[maxVertexNumber + 1];
        int[] begin = new int[maxVertexNumber + 1];
        int[] end = new int[maxVertexNumber + 1];
        List<VertexTime> times = new ArrayList<VertexTime>(maxVertexNumber + 1);
        fillGraphsWithEdges(filename, directedGraph, transposedDirectedGraph);

        for (int i = 0; i < maxVertexNumber + 1; i++) {
            if (explored[i] == false) {
                DepthFirstSearch(directedGraph, i, explored, begin, end);
            }
        }
        for (int i = 0; i < maxVertexNumber + 1; i++) {
            times.add(new VertexTime(i, end[i]));
        }
        Collections.sort(times);
        time = 0;
        List<Integer> finalTimesList = new LinkedList<Integer>();
        Iterator<VertexTime> iter = times.iterator();
        int i;
        while (iter.hasNext()) {
            i = iter.next().getVertex();
            if (transposedExplored[i] == false) {
                DepthFirstSearch(transposedDirectedGraph, i, transposedExplored, begin, end);
            }
            finalTimesList.add(time);
            time = 0;
        }
        Collections.sort(finalTimesList);
        for(i = 0; i < 5; i++) {
            sizesOfFiveLargestSCCs[i] = finalTimesList.get(finalTimesList.size() - i - 1) / 2;
        }
        for(i = 0; i < 5; i++) {
            System.out.println(sizesOfFiveLargestSCCs[i]);
        }
    }

    public static void main(String[] args) {
        Thread thread = new Thread(null, null, "My Thread", 10000000) {
            @Override
            public void run() {
                {
                    new ComputingStronglyConnectedComponentsInGraph().solveProblem();
                }
            }
        };
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}