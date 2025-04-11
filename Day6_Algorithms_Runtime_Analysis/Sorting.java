//package Time_Complexity;

import java.util.Random;

public class Sorting {

    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        boolean swapped;
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
    }

    public static void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }

    private static void merge(int[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] L = new int[n1];
        int[] R = new int[n2];

        System.arraycopy(arr, left, L, 0, n1);
        System.arraycopy(arr, mid + 1, R, 0, n2);

        int i = 0, j = 0, k = left;

        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k++] = L[i++];
            } else {
                arr[k++] = R[j++];
            }
        }

        while (i < n1) {
            arr[k++] = L[i++];
        }

        while (j < n2) {
            arr[k++] = R[j++];
        }
    }

    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }

    public static int[] generateRandomArray(int size) {
        Random rand = new Random();
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = rand.nextInt(size * 10);
        }
        return arr;
    }

    public static void main(String[] args) {
        int[] datasetSizes = {1000, 10000, 1000000};

        System.out.println("Dataset Size (N) | Bubble Sort Time (ms) | Merge Sort Time (ms) | Quick Sort Time (ms)");
        System.out.println("---------------------------------------------------------------------------------------");

        for (int size : datasetSizes) {
            int[] dataForBubble = null;
            if (size <= 10000) { // Limit bubble sort to 10,000 elements for feasibility
                dataForBubble = generateRandomArray(size);
            }
            int[] dataForMerge = generateRandomArray(size);
            int[] dataForQuick = generateRandomArray(size);

            double bubbleTimeMs = -1;
            if (dataForBubble != null) {
                long startBubble = System.nanoTime();
                bubbleSort(dataForBubble);
                long endBubble = System.nanoTime();
                bubbleTimeMs = (endBubble - startBubble) / 1_000_000.0;
            }

            long startMerge = System.nanoTime();
            mergeSort(dataForMerge, 0, dataForMerge.length - 1);
            long endMerge = System.nanoTime();
            double mergeTimeMs = (endMerge - startMerge) / 1_000_000.0;

            long startQuick = System.nanoTime();
            quickSort(dataForQuick, 0, dataForQuick.length - 1);
            long endQuick = System.nanoTime();
            double quickTimeMs = (endQuick - startQuick) / 1_000_000.0;

            System.out.printf("%15d | %21s | %19.4f | %18.4f%n",
                    size,
                    (bubbleTimeMs < 0 ? "Unfeasible" : String.format("%.4f", bubbleTimeMs)),
                    mergeTimeMs,
                    quickTimeMs);
        }

        System.out.println("\nNote: Bubble Sort is limited to datasets of size 10,000 or less due to performance constraints.");
        System.out.println("Merge Sort and Quick Sort perform well on large datasets.");
    }
}
