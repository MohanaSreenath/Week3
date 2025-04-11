public class Search {

    private static long seed = 123456789;

    private static int nextRandom(int max) {
        seed = (seed * 1103515245 + 12345) & 0x7fffffff;
        return (int)(seed % max);
    }

    public static int linearSearch(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                return i;
            }
        }
        return -1;
    }

    public static int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
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
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = nextRandom(size * 10);
        }
        return arr;
    }

    public static void main(String[] args) {
        int[] datasetSizes = {1000, 10000, 1000000};
        int target = -1;

        System.out.println("Dataset Size (N) | Linear Search Time (ms) | Binary Search Time (ms)");
        System.out.println("---------------------------------------------------------------");

        for (int size : datasetSizes) {
            int[] data = generateRandomArray(size);

            long startLinear = System.nanoTime();
            linearSearch(data, target);
            long endLinear = System.nanoTime();
            double linearTimeMs = (endLinear - startLinear) / 1_000_000.0;

            long startSort = System.nanoTime();
            quickSort(data, 0, data.length - 1);
            long endSort = System.nanoTime();
            double sortTimeMs = (endSort - startSort) / 1_000_000.0;

            long startBinary = System.nanoTime();
            binarySearch(data, target);
            long endBinary = System.nanoTime();
            double binaryTimeMs = (endBinary - startBinary) / 1_000_000.0;

            System.out.printf("%15d | %22.4f | %21.4f (sort: %.4f)%n", size, linearTimeMs, binaryTimeMs, sortTimeMs);
        }

        System.out.println("\nNote: Binary Search time excludes sorting time. Sorting is O(N log N).");
        System.out.println("Expected Result: Binary Search performs much better for large datasets, provided data is sorted.");
    }
}
