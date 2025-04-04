import java.util.LinkedList;

public class Undo {
    private static class Node {
        String text;
        Node prev, next;

        Node(String text) {
            this.text = text;
        }
    }

    private Node head, tail, current;
    private int size = 0;
    private final int maxSize = 10;

    public void addState(String text) {
        Node newNode = new Node(text);
        if (current != null) {
            current.next = null;
        }
        if (size == maxSize) {
            head = head.next;
            head.prev = null;
            size--;
        }
        if (tail == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        current = tail;
        size++;
    }

    public void undo() {
        if (current != null && current.prev != null) {
            current = current.prev;
        }
    }

    public void redo() {
        if (current != null && current.next != null) {
            current = current.next;
        }
    }

    public String getCurrentState() {
        return current != null ? current.text : "";
    }

    public static void main(String[] args) {
        Undo editor = new Undo();
        editor.addState("State1");
        editor.addState("State2");
        editor.addState("State3");
        System.out.println(editor.getCurrentState());
        editor.undo();
        System.out.println(editor.getCurrentState());
        editor.redo();
        System.out.println(editor.getCurrentState());
        editor.addState("State4");
        System.out.println(editor.getCurrentState());
        editor.undo();
        System.out.println(editor.getCurrentState());
    }
}