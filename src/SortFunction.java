import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.*;

public class SortFunction {
    public int[] bubbleSort(int[] array) {
        int n = array.length;
        for (int i = 0; i < n-1; i++)
            for (int j = 0; j < n-i-1; j++)
                if (array[j] > array[j+1]) {
                    int temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                }
        return array;
    }

    public int[] selectionSort(int[] array) {
        int n = array.length;
        for (int i = 0; i < n-1; i++) {
            int min_idx = i;
            for (int j = i+1; j < n; j++)
                if (array[j] < array[min_idx])
                    min_idx = j;
            int temp = array[min_idx];
            array[min_idx] = array[i];
            array[i] = temp;
        }
        return array;
    }

    public int[] insertionSort(int[] array) {
        int n = array.length;
        for (int i = 1; i < n; ++i) {
            int key = array[i];
            int j = i - 1;
            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j];
                j = j - 1;
            }
            array[j + 1] = key;
        }
        return array;
    }

    public int[] binarySort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int key = array[i];
            int left = 0;
            int right = i;
            while (left < right) {
                int middle = left + (right - left) / 2;
                if (key < array[middle]) {
                    right = middle;
                } else {
                    left = middle + 1;
                }
            }
            for (int j = i; j > left; j--) {
                array[j] = array[j - 1];
            }
            array[left] = key;
        }
        return array;
    }

    public void writeToFile(String fileName, String content) {
        try (PrintWriter out = new PrintWriter(new FileWriter(fileName, true))) {
            out.println(content);
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
    }

    public void sortAndWriteResults(int[] array, String fileName) {
        long startTime, endTime;
        int[] sortedArray;

        startTime = System.currentTimeMillis();
        sortedArray = bubbleSort(array.clone());
        endTime = System.currentTimeMillis();
        writeToFile(fileName, "Bubble Sort: " + Arrays.toString(sortedArray) + ", Time: " + (endTime - startTime) + " ms");

        startTime = System.currentTimeMillis();
        sortedArray = selectionSort(array.clone());
        endTime = System.currentTimeMillis();
        writeToFile(fileName, "Selection Sort: " + Arrays.toString(sortedArray) + ", Time: " + (endTime - startTime) + " ms");

        startTime = System.currentTimeMillis();
        sortedArray = insertionSort(array.clone());
        endTime = System.currentTimeMillis();
        writeToFile(fileName, "Insertion Sort: " + Arrays.toString(sortedArray) + ", Time: " + (endTime - startTime) + " ms");

        startTime = System.currentTimeMillis();
        sortedArray = binarySort(array.clone());
        endTime = System.currentTimeMillis();
        writeToFile(fileName, "Binary Sort: " + Arrays.toString(sortedArray) + ", Time: " + (endTime - startTime) + " ms");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of elements: ");
        int n = scanner.nextInt();

        int[] array = new int[n];
        System.out.println("Enter " + n + " integers: ");
        for (int i = 0; i < n; i++) {
            array[i] = scanner.nextInt();
        }

        new SortFunction().sortAndWriteResults(array, "SortFunctionResult.txt");
    }
}