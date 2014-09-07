package week3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

public class ComputingNumberOfMinCutsInGraph {

    private final int LOOPS_AMOUNT = 2000;
    private int mininumMinCutsAmount = 0;

    public void solveProblem() throws CloneNotSupportedException {
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

            LinkedList<Integer> verticesTemp = new LinkedList<>();
            LinkedList<Edge> edgesTemp = new LinkedList<>();
            Random rand = new Random();
            
            for (int i = 0; i < LOOPS_AMOUNT; i++) {
                verticesTemp.clear();
                Iterator<Integer> iter1 = vertices.iterator();
                while (iter1.hasNext()) {
                    verticesTemp.add(new Integer(iter1.next()));
                }
                edgesTemp.clear();
                Iterator<Edge> iter2 = edges.iterator();
                while (iter2.hasNext()) {
                    edgesTemp.add((Edge) iter2.next().clone());
                }
                while (verticesTemp.size() != 2) {
                    int randNumber = rand.nextInt(edgesTemp.size());
                    Edge randEdge = edgesTemp.get(randNumber);
                    edgesTemp.remove(randNumber);
                    verticesTemp.remove((Integer) randEdge.getFirstVertex());
                    Iterator<Edge> iter = edgesTemp.iterator();
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
                if (mininumMinCutsAmount == 0 || mininumMinCutsAmount > edgesTemp.size())
                    mininumMinCutsAmount = edgesTemp.size();
            }
            System.out.println(mininumMinCutsAmount);
        } catch (FileNotFoundException e) {
            System.out.println("There in no such file");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("For some reason cannot read from file");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            new ComputingNumberOfMinCutsInGraph().solveProblem();
        } catch (CloneNotSupportedException e) {
            System.out.println("There are some problems with cloning objects");
            e.printStackTrace();
        }
    }
}