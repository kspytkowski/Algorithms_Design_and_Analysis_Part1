package week1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class ComputingNumberOfInversionsInMergeSort {

    private long numerOfInversionsMadeByMergeSort = 0;

    private LinkedList<Integer> merge(LinkedList<Integer> left, LinkedList<Integer> right) {
        LinkedList<Integer> output = new LinkedList<>();
        int i = 0, j = 0;
        for (int k = 0; k < left.size() + right.size() - 1; k++) {
            if (left.get(i) < right.get(j)) {
                output.addLast(left.get(i));
                i++;
            } else {
                numerOfInversionsMadeByMergeSort += left.size() - i;
                output.addLast(right.get(j));
                j++;
            }
            if (i == left.size() || j == right.size())
                break;
        }
        if (i != left.size()) {
            while (i < left.size()) {
                output.add(left.get(i));
                i++;
            }
        }
        if (j != right.size()) {
            while (j < right.size()) {
                output.add(right.get(j));
                j++;
            }
        }
        return output;
    }

    private LinkedList<Integer> mergeSort(LinkedList<Integer> array) {
        if (array.size() > 1) {
            LinkedList<Integer> left = new LinkedList<>();
            LinkedList<Integer> right = new LinkedList<>();
            for (int i = 0; i < array.size(); i++) {
                if (i < array.size() / 2) {
                    left.add(array.get(i));
                } else {
                    right.add(array.get(i));
                }
            }
            LinkedList<Integer> newLeft = mergeSort(left);
            LinkedList<Integer> newRight = mergeSort(right);
            return merge(newLeft, newRight);
        } else {
            return array;
        }
    }

    private void solveProblem() {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("/home/krzysztof/workspace/Algorithms_Design_and_Analysis_Part1/src/week1/IntegerArray.txt"))) {
            LinkedList<Integer> array = new LinkedList<>();
            String numberString;
            while ((numberString = bufferedReader.readLine()) != null) {
                int numberInt = Integer.parseInt(numberString);
                array.addLast(numberInt);
            }
            LinkedList<Integer> result = mergeSort(array);
            System.out.println(numerOfInversionsMadeByMergeSort);
        } catch (FileNotFoundException e) {
            System.out.println("There in no such file");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("For some reason cannot read from file");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new ComputingNumberOfInversionsInMergeSort().solveProblem();
    }
}
