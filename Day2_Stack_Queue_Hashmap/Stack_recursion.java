public class Stack_recursion {
    public static void sortStack(int[] stack, int top) {
        if (top >= 0) {
            int temp = stack[top];
            top--;
            sortStack(stack, top);
            top = insertInSortedOrder(stack, top, temp);
        }
    }

    private static int insertInSortedOrder(int[] stack, int top, int element) {
        if (top == -1 || element > stack[top]) {
            stack[++top] = element;
            return top;
        } else {
            int temp = stack[top];
            top--;
            top = insertInSortedOrder(stack, top, element);
            stack[++top] = temp;
            return top;
        }
    }

    public static void main(String[] args) {
        int[] stack = new int[10];
        int top = -1;

        stack[++top] = 3;
        stack[++top] = 1;
        stack[++top] = 4;
        stack[++top] = 2;

        sortStack(stack, top);

        while (top >= 0) {
            System.out.println(stack[top--]);
        }
    }
}