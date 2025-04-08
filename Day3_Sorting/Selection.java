public class Selection {
    public static void main(String[] args) {
        int[] scores = {85, 62, 78, 90, 55, 71};
        selectionSort(scores);
        for (int score : scores) {
            System.out.print(score + " ");
        }
    }

    public static void selectionSort(int[] array) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = array[minIndex];
            array[minIndex] = array[i];
            array[i] = temp;
        }
    }
}