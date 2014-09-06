package week6;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Hashtable;

public class TwoSumAlgorithm{
	
	private int numberOfTargetValuesInIntervalT = 0;

    public void solveProblem() {
    	Hashtable<Long, Long> myHashtable = new Hashtable<Long, Long>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("/home/krzysztof/workspace/Algorithms_Design_and_Analysis_Part1/src/week6/algo1_programming_prob_2sum.txt"))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                myHashtable.put(Long.parseLong(line), Long.parseLong(line));
        	}
        } catch (FileNotFoundException e) {
            System.out.println("There in no such file");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("For some reason cannot read from file");
            e.printStackTrace();
        }
        long x;
		Enumeration<Long> keysSet = myHashtable.keys();
		for (int t = -10000; t < 10001; t++) {
			keysSet = myHashtable.keys();
		    while(keysSet.hasMoreElements()) {
		    	x = (long)keysSet.nextElement();
		    	if (myHashtable.containsKey(t - x)) {
		    		numberOfTargetValuesInIntervalT++;
		    		break;
		    	}
		    }
		}
		System.out.println(numberOfTargetValuesInIntervalT);
    }
    
    public static void main(String[] args) {
        new TwoSumAlgorithm().solveProblem();
    }
}