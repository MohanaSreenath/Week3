public class Bubble {
    public static void main(String[] args) {
        int[] marks = {78, 45, 89, 32, 56, 99, 12};
        bubbleSort(marks);
        for (int mark : marks) {
            System.out.print(mark + " ");
        }
    }

    public static void bubbleSort(int[] array) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }
}