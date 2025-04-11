public class Fibinacci {

    public static int fibonacciRecursive(int n) {
        if (n <= 1) return n;
        return fibonacciRecursive(n - 1) + fibonacciRecursive(n - 2);
    }

    public static int fibonacciIterative(int n) {
        if (n <= 1) return n;
        int a = 0, b = 1, sum = 0;
        for (int i = 2; i <= n; i++) {
            sum = a + b;
            a = b;
            b = sum;
        }
        return b;
    }

    public static void main(String[] args) {
        int n = 30; // Change this value to test different Fibonacci numbers

        // Measure time for recursive approach
        long startRecursive = System.currentTimeMillis();
        int resultRecursive = fibonacciRecursive(n);
        long endRecursive = System.currentTimeMillis();
        System.out.println("Recursive result: " + resultRecursive + ", Time: " + (endRecursive - startRecursive) + "ms");

        // Measure time for iterative approach
        long startIterative = System.currentTimeMillis();
        int resultIterative = fibonacciIterative(n);
        long endIterative = System.currentTimeMillis();
        System.out.println("Iterative result: " + resultIterative + ", Time: " + (endIterative - startIterative) + "ms");
    }
}