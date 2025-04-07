class Problem4 {
    static class Node {
        int key, value;
        Node next;

        Node(int k, int v) {
            key = k;
            value = v;
            next = null;
        }
    }

    static class MyHashMap {
        final int SIZE = 100;
        Node[] table = new Node[SIZE];

        int hash(int key) {
            return key % SIZE;
        }

        void put(int key, int value) {
            int idx = hash(key);
            Node head = table[idx];

            while (head != null) {
                if (head.key == key) {
                    head.value = value;
                    return;
                }
                head = head.next;
            }

            Node newNode = new Node(key, value);
            newNode.next = table[idx];
            table[idx] = newNode;
        }

        int get(int key) {
            int idx = hash(key);
            Node head = table[idx];

            while (head != null) {
                if (head.key == key)
                    return head.value;
                head = head.next;
            }
            return -1;
        }

        void remove(int key) {
            int idx = hash(key);
            Node head = table[idx], prev = null;

            while (head != null) {
                if (head.key == key) {
                    if (prev == null)
                        table[idx] = head.next;
                    else
                        prev.next = head.next;
                    return;
                }
                prev = head;
                head = head.next;
            }
        }
    }

    public static void main(String[] args) {
        MyHashMap map = new MyHashMap();
        map.put(1, 100);
        map.put(2, 200);
        System.out.println(map.get(1)); // 100
        map.remove(1);
        System.out.println(map.get(1)); // -1
    }
}
