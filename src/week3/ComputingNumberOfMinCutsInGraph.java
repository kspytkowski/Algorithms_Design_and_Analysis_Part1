package week3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

public class ComputingNumberOfMinCutsInGraph {

    private int mininumNumberOfMinCuts = 0;

    public void solveProblem() {
        for (int i = 0; i < 2000; i++) {
            try (BufferedReader bufferedReader = new BufferedReader(new FileReader("/home/krzysztof/workspace/Algorithms_Design_and_Analysis_Part1/src/week3/kargerMinCut.txt"))) {
                LinkedList<Integer> vertices = new LinkedList<>();
                LinkedList<Edge> edges = new LinkedList<>();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    String[] elementsOfLine = line.split("\t");
                    vertices.add(Integer.parseInt(elementsOfLine[0]));
                    for (int j = 1; j < elementsOfLine.length; j++) {
                        if (Integer.parseInt(elementsOfLine[j]) > Integer.parseInt(elementsOfLine[0]))
                            edges.add(new Edge(Integer.parseInt(elementsOfLine[0]), Integer.parseInt(elementsOfLine[j])));
                    }
                }
                Random rand = new Random();
                while (vertices.size() != 2) {
                    int randNumber = rand.nextInt(edges.size());
                    Edge randEdge = edges.get(randNumber);
                    edges.remove(randNumber);
                    vertices.remove((Integer) randEdge.getFirstVertex());
                    Iterator<Edge> iter = edges.iterator();
                    while (iter.hasNext()) {
                        Edge e = iter.next();
                        if (randEdge.getFirstVertex() == e.getFirstVertex()) {
                            e.setFirstVertex(randEdge.getSecondVertex());
                        }
                        if (randEdge.getFirstVertex() == e.getSecondVertex()) {
                            e.setSecondVertex(randEdge.getSecondVertex());
                        }
                        if (e.isLoop()) {
                            iter.remove();
                        }
                    }
                }
                if (mininumNumberOfMinCuts == 0 || mininumNumberOfMinCuts > edges.size())
                    mininumNumberOfMinCuts = edges.size();
            } catch (FileNotFoundException e) {
                System.out.println("There in no such file");
                e.printStackTrace();
            } catch (IOException e) {
                System.out.println("For some reason cannot read from file");
                e.printStackTrace();
            }
        }
        System.out.println(mininumNumberOfMinCuts);
    }

    public static void main(String[] args) {
        new ComputingNumberOfMinCutsInGraph().solveProblem();
    }
}
