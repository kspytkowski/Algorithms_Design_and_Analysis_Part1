package week6;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Hashtable;

public class TwoSumAlgorithm {

    private int numberOfTargetValuesInIntervalT = 0;
    private int INTERVAL_BEGIN = -10000;
    private int INTERVAL_END = 10000;

    public void solveProblem() {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("/home/krzysztof/workspace/Algorithms_Design_and_Analysis_Part1/src/week6/algo1_programming_prob_2sum.txt"))) {
            Hashtable<Long, Long> myHashtable = new Hashtable<Long, Long>();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                myHashtable.put(Long.parseLong(line), Long.parseLong(line));
            }
            long x;
            Enumeration<Long> keysSet = myHashtable.keys();
            for (int t = INTERVAL_BEGIN; t <= INTERVAL_END; t++) {
                keysSet = myHashtable.keys();
                while (keysSet.hasMoreElements()) {
                    x = (long) keysSet.nextElement();
                    if (myHashtable.containsKey(t - x)) {
                        numberOfTargetValuesInIntervalT++;
                        break;
                    }
                }
            }
            System.out.println(numberOfTargetValuesInIntervalT);
        } catch (FileNotFoundException e) {
            System.out.println("There in no such file");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("For some reason cannot read from file");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new TwoSumAlgorithm().solveProblem();
    }
}