public class Counting {
    public static void countingSort(int[] ages) {
        int[] count = new int[19];
        int[] output = new int[ages.length];

        for (int age : ages) {
            count[age]++;
        }

        for (int i = 1; i < count.length; i++) {
            count[i] += count[i - 1];
        }

        for (int i = ages.length - 1; i >= 0; i--) {
            output[count[ages[i]] - 1] = ages[i];
            count[ages[i]]--;
        }

        System.arraycopy(output, 0, ages, 0, ages.length);
    }

    public static void main(String[] args) {
        int[] ages = {12, 14, 13, 12, 15, 10, 18, 17, 11, 10};
        countingSort(ages);
        for (int age : ages) {
            System.out.print(age + " ");
        }
    }
}