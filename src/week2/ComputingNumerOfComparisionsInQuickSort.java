package week2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class ComputingNumerOfComparisionsInQuickSort {

    private enum PivotType {
        BEGINNING, MEDIAN, ENDING
    }

    private long numberOfComparisionsMadeByQuickSort = 0;

    private int choosePivot(LinkedList<Integer> array, int beginIndex, int endIndex, PivotType pivotType) {
        int pivotIndex = 0;
        switch (pivotType) {
        case BEGINNING:
            pivotIndex = beginIndex;
            break;
        case MEDIAN:
            int first = array.get(beginIndex);
            int last = array.get(endIndex);
            int middle = array.get((endIndex - beginIndex) / 2 + beginIndex);
            pivotIndex = (first > middle) ? ((first > last) ? ((middle > last) ? (endIndex - beginIndex) / 2 + beginIndex : endIndex) : beginIndex) : ((first > last) ? beginIndex : ((middle > last) ? endIndex : (endIndex - beginIndex) / 2 + beginIndex));
            break;
        case ENDING:
            pivotIndex = endIndex;
            break;
        }
        return pivotIndex;
    }

    private void swap(LinkedList<Integer> array, int firstIndex, int secondIndex) {
        int firstNumber = array.get(firstIndex);
        int secondNumber = array.get(secondIndex);
        array.set(firstIndex, secondNumber);
        array.set(secondIndex, firstNumber);
    }

    private int makePartition(LinkedList<Integer> array, int beginIndex, int endIndex) {
        numberOfComparisionsMadeByQuickSort += endIndex - beginIndex;
        int pivot = array.get(beginIndex);
        int i = beginIndex + 1;
        for (int j = beginIndex + 1; j <= endIndex; j++) {
            if (array.get(j) < pivot) {
                swap(array, j, i);
                i++;
            }
        }
        swap(array, beginIndex, i - 1);
        return i - 1;
    }

    private void quickSort(LinkedList<Integer> array, int beginIndex, int endIndex, PivotType pivotType) {
        if (endIndex - beginIndex == 0)
            return;
        int pivotIndexInArray = choosePivot(array, beginIndex, endIndex, pivotType);
        if (pivotIndexInArray != beginIndex)
            swap(array, beginIndex, pivotIndexInArray);
        int newPivotindex = makePartition(array, beginIndex, endIndex);
        if (newPivotindex > beginIndex)
            quickSort(array, beginIndex, newPivotindex - 1, pivotType);
        if (newPivotindex < endIndex)
            quickSort(array, newPivotindex + 1, endIndex, pivotType);
    }

    public void solveProblem() {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("/home/krzysztof/workspace/Algorithms_Design_and_Analysis_Part1/src/week2/QuickSort.txt"))) {
            LinkedList<Integer> array1 = new LinkedList<>();
            String numberString;
            while ((numberString = bufferedReader.readLine()) != null) {
                int numberInt = Integer.parseInt(numberString);
                array1.addLast(numberInt);
            }
            LinkedList<Integer> array2 = (LinkedList<Integer>) array1.clone();
            LinkedList<Integer> array3 = (LinkedList<Integer>) array1.clone();

            quickSort(array1, 0, array1.size() - 1, PivotType.BEGINNING);
            System.out.println(numberOfComparisionsMadeByQuickSort);

            numberOfComparisionsMadeByQuickSort = 0;
            quickSort(array2, 0, array1.size() - 1, PivotType.MEDIAN);
            System.out.println(numberOfComparisionsMadeByQuickSort);

            numberOfComparisionsMadeByQuickSort = 0;
            quickSort(array3, 0, array1.size() - 1, PivotType.ENDING);
            System.out.println(numberOfComparisionsMadeByQuickSort);

        } catch (FileNotFoundException e) {
            System.out.println("There in no such file");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("For some reason cannot read from file");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new ComputingNumerOfComparisionsInQuickSort().solveProblem();
    }
}
