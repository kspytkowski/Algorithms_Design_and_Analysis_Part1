package week6;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class MedianMaintenance {

    private int sumOf10000Medians = 0;

    public void solveProblem() {
        Queue<Integer> minPriorityQueue = new PriorityQueue<Integer>(1, new Comparator<Integer>() {
            @Override
            public int compare(Integer c1, Integer c2) {
                return -(c1 - c2);
            }
        });
        Queue<Integer> maxPriorityQueue = new PriorityQueue<Integer>(1, new Comparator<Integer>() {
            @Override
            public int compare(Integer c1, Integer c2) {
                return c1 - c2;
            }
        });

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("/home/krzysztof/workspace/Algorithms_Design_and_Analysis_Part1/src/week6/Median.txt"))) {
            String line;
            int number;
            minPriorityQueue.add(Integer.parseInt(bufferedReader.readLine()));
            sumOf10000Medians += minPriorityQueue.peek();
            number = Integer.parseInt(bufferedReader.readLine());
            if (number < minPriorityQueue.peek()) {
                int tempNumber = minPriorityQueue.poll();
                minPriorityQueue.add(number);
                maxPriorityQueue.add(tempNumber);
                sumOf10000Medians += minPriorityQueue.peek();
            } else {
                maxPriorityQueue.add(number);
                sumOf10000Medians += minPriorityQueue.peek();
            }
            while ((line = bufferedReader.readLine()) != null) {
                number = Integer.parseInt(line);
                if (number < maxPriorityQueue.peek()) {
                    if (minPriorityQueue.size() < maxPriorityQueue.size()) {
                        minPriorityQueue.add(number);
                        sumOf10000Medians += minPriorityQueue.peek();
                    } else if (minPriorityQueue.size() == maxPriorityQueue.size()) {
                        minPriorityQueue.add(number);
                        sumOf10000Medians += minPriorityQueue.peek();
                    } else {
                        if (number < minPriorityQueue.peek()) {
                            int tempNumber = minPriorityQueue.poll();
                            minPriorityQueue.add(number);
                            maxPriorityQueue.add(tempNumber);
                            sumOf10000Medians += minPriorityQueue.peek();
                        } else {
                            maxPriorityQueue.add(number);
                            sumOf10000Medians += minPriorityQueue.peek();
                        }
                    }
                } else {
                    if (minPriorityQueue.size() > maxPriorityQueue.size()) {
                        maxPriorityQueue.add(number);
                        sumOf10000Medians += minPriorityQueue.peek();
                   } else if (minPriorityQueue.size() == maxPriorityQueue.size()) {
                        maxPriorityQueue.add(number);
                        sumOf10000Medians += maxPriorityQueue.peek();
                    } else {
                        int tempNumber = maxPriorityQueue.poll();
                        maxPriorityQueue.add(number);
                        minPriorityQueue.add(tempNumber);
                        sumOf10000Medians += minPriorityQueue.peek();
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("There in no such file");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("For some reason cannot read from file");
            e.printStackTrace();
        }
        System.out.println(sumOf10000Medians % 10000);
    }

    public static void main(String[] args) {
        new MedianMaintenance().solveProblem();
    }
}