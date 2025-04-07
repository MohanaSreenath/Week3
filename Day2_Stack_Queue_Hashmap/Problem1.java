class Problem1 {
    static class Pair {
        int key;
        int value;
        Pair(int k, int v) {
            key = k;
            value = v;
        }
    }

    static void findSubarrays(int[] arr) {
        int n = arr.length;
        int[] sum = new int[n + 1];
        for (int i = 0; i < n; i++) sum[i + 1] = sum[i] + arr[i];

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j <= n; j++) {
                if (sum[j] - sum[i] == 0)
                    System.out.println("Subarray: [" + i + "..." + (j - 1) + "]");
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {3, 4, -7, 3, 1, 3, -4, -2, -2};
        findSubarrays(arr);
    }
}
