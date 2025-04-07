public class Queue_stacks {
    private static class Stack {
        private int[] arr;
        private int top;
        private int capacity;

        public Stack(int size) {
            arr = new int[size];
            top = -1;
            capacity = size;
        }

        public void push(int data) {
            if (top == capacity - 1) {
                throw new RuntimeException("Stack overflow");
            }
            arr[++top] = data;
        }

        public int pop() {
            if (isEmpty()) {
                throw new RuntimeException("Stack underflow");
            }
            return arr[top--];
        }

        public boolean isEmpty() {
            return top == -1;
        }
    }

    private Stack stack1;
    private Stack stack2;

    public Queue_stacks(int size) {
        stack1 = new Stack(size);
        stack2 = new Stack(size);
    }

    public void enqueue(int data) {
        stack1.push(data);
    }

    public int dequeue() {
        if (stack2.isEmpty()) {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }
        if (stack2.isEmpty()) {
            throw new RuntimeException("Queue is empty");
        }
        return stack2.pop();
    }

    public static void main(String[] args) {
        Queue_stacks queue = new Queue_stacks(10);
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        queue.enqueue(4);
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
    }
}