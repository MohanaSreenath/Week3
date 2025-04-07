class Stock_span {
    public static void main(String[] args) {
        int[] prices = {100, 80, 60, 70, 60, 75, 85};
        int[] span = calculateSpan(prices);
        for (int s : span) {
            System.out.print(s + " ");
        }
    }

    static int[] calculateSpan(int[] prices) {
        int n = prices.length;
        int[] span = new int[n];
        int[] stack = new int[n];
        int top = -1;

        for (int i = 0; i < n; i++) {
            while (top != -1 && prices[stack[top]] <= prices[i]) {
                top--;
            }
            span[i] = (top == -1) ? (i + 1) : (i - stack[top]);
            stack[++top] = i;
        }
        return span;
    }
}