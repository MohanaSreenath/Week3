import java.util.*;

public class DS {

    public static void main(String[] args) {
        int datasetSize = 1_000_000; // Change this value to test different dataset sizes
        int target = datasetSize + 1; // Element not in the dataset to simulate worst-case search

        // Generate dataset
        int[] array = new int[datasetSize];
        HashSet<Integer> hashSet = new HashSet<>();
        TreeSet<Integer> treeSet = new TreeSet<>();
        for (int i = 0; i < datasetSize; i++) {
            array[i] = i;
            hashSet.add(i);
            treeSet.add(i);
        }

        // Measure time for Array (Linear Search)
        long startArray = System.currentTimeMillis();
        boolean foundInArray = false;
        for (int num : array) {
            if (num == target) {
                foundInArray = true;
                break;
            }
        }
        long endArray = System.currentTimeMillis();
        System.out.println("Array search time: " + (endArray - startArray) + "ms");

        // Measure time for HashSet (O(1) Search)
        long startHashSet = System.currentTimeMillis();
        boolean foundInHashSet = hashSet.contains(target);
        long endHashSet = System.currentTimeMillis();
        System.out.println("HashSet search time: " + (endHashSet - startHashSet) + "ms");

        // Measure time for TreeSet (O(log N) Search)
        long startTreeSet = System.currentTimeMillis();
        boolean foundInTreeSet = treeSet.contains(target);
        long endTreeSet = System.currentTimeMillis();
        System.out.println("TreeSet search time: " + (endTreeSet - startTreeSet) + "ms");
    }
}