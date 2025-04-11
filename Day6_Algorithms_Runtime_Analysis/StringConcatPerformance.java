public class StringConcatPerformance {
    private static long testStringConcat(int n) {
        long start = System.currentTimeMillis();
        String s = "";
        for (int i = 0; i < n; i++) {
            s += "a";
        }
        return System.currentTimeMillis() - start;
    }

    private static long testStringBuilderConcat(int n) {
        long start = System.currentTimeMillis();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append("a");
        }
        return System.currentTimeMillis() - start;
    }

    private static long testStringBufferConcat(int n) {
        long start = System.currentTimeMillis();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < n; i++) {
            sb.append("a");
        }
        return System.currentTimeMillis() - start;
    }

    public static void main(String[] args) {
        int[] testSizes = {1000, 10000, 1000000};

        System.out.println("Operations Count (N)");
        System.out.println("String (O(N²))");
        System.out.println("StringBuilder (O(N))");
        System.out.println("StringBuffer (O(N))");

        for (int n : testSizes) {
            long stringTime = testStringConcat(n);
            long stringBuilderTime = testStringBuilderConcat(n);
            long stringBufferTime = testStringBufferConcat(n);

            System.out.println(n);
            System.out.println(stringTime + "ms");
            System.out.println(stringBuilderTime + "ms");
            System.out.println(stringBufferTime + "ms");
        }
    }
}
