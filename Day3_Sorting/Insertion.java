public class Insertion {
    public static void main(String[] args) {
        int[] employeeIds = {45, 12, 78, 34, 89, 23, 56};
        insertionSort(employeeIds);
        for (int id : employeeIds) {
            System.out.print(id + " ");
        }
    }

    public static void insertionSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int key = array[i];
            int j = i - 1;
            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = key;
        }
    }
}