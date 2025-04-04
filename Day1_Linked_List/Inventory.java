import java.util.Scanner;

class Inventory {
    String itemName;
    int itemId;
    int quantity;
    double price;
    Inventory next;

    public Inventory(String itemName, int itemId, int quantity, double price) {
        this.itemName = itemName;
        this.itemId = itemId;
        this.quantity = quantity;
        this.price = price;
        this.next = null;
    }
}

class InventoryManagement {
    private Inventory head;

    public void addItemAtBeginning(String itemName, int itemId, int quantity, double price) {
        Inventory newItem = new Inventory(itemName, itemId, quantity, price);
        newItem.next = head;
        head = newItem;
    }

    public void addItemAtEnd(String itemName, int itemId, int quantity, double price) {
        Inventory newItem = new Inventory(itemName, itemId, quantity, price);
        if (head == null) {
            head = newItem;
            return;
        }
        Inventory temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = newItem;
    }

    public void addItemAtPosition(String itemName, int itemId, int quantity, double price, int position) {
        Inventory newItem = new Inventory(itemName, itemId, quantity, price);
        if (position == 0) {
            newItem.next = head;
            head = newItem;
            return;
        }
        Inventory temp = head;
        for (int i = 0; i < position - 1 && temp != null; i++) {
            temp = temp.next;
        }
        if (temp == null) return;
        newItem.next = temp.next;
        temp.next = newItem;
    }

    public void removeItemById(int itemId) {
        if (head == null) return;
        if (head.itemId == itemId) {
            head = head.next;
            return;
        }
        Inventory temp = head;
        while (temp.next != null && temp.next.itemId != itemId) {
            temp = temp.next;
        }
        if (temp.next == null) return;
        temp.next = temp.next.next;
    }

    public void updateQuantityById(int itemId, int newQuantity) {
        Inventory temp = head;
        while (temp != null) {
            if (temp.itemId == itemId) {
                temp.quantity = newQuantity;
                return;
            }
            temp = temp.next;
        }
    }

    public Inventory searchById(int itemId) {
        Inventory temp = head;
        while (temp != null) {
            if (temp.itemId == itemId) return temp;
            temp = temp.next;
        }
        return null;
    }

    public Inventory searchByName(String itemName) {
        Inventory temp = head;
        while (temp != null) {
            if (temp.itemName.equals(itemName)) return temp;
            temp = temp.next;
        }
        return null;
    }

    public double calculateTotalValue() {
        double totalValue = 0;
        Inventory temp = head;
        while (temp != null) {
            totalValue += temp.quantity * temp.price;
            temp = temp.next;
        }
        return totalValue;
    }

    public void sortByName(boolean ascending) {
        head = mergeSort(head, ascending, true);
    }

    public void sortByPrice(boolean ascending) {
        head = mergeSort(head, ascending, false);
    }

    private Inventory mergeSort(Inventory head, boolean ascending, boolean sortByName) {
        if (head == null || head.next == null) return head;
        Inventory middle = getMiddle(head);
        Inventory nextOfMiddle = middle.next;
        middle.next = null;
        Inventory left = mergeSort(head, ascending, sortByName);
        Inventory right = mergeSort(nextOfMiddle, ascending, sortByName);
        return merge(left, right, ascending, sortByName);
    }

    private Inventory merge(Inventory left, Inventory right, boolean ascending, boolean sortByName) {
        if (left == null) return right;
        if (right == null) return left;
        boolean condition = sortByName
                ? (ascending ? left.itemName.compareTo(right.itemName) <= 0 : left.itemName.compareTo(right.itemName) > 0)
                : (ascending ? left.price <= right.price : left.price > right.price);
        if (condition) {
            left.next = merge(left.next, right, ascending, sortByName);
            return left;
        } else {
            right.next = merge(left, right.next, ascending, sortByName);
            return right;
        }
    }

    private Inventory getMiddle(Inventory head) {
        if (head == null) return head;
        Inventory slow = head, fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public void displayInventory() {
        Inventory temp = head;
        while (temp != null) {
            System.out.println("Item Name: " + temp.itemName + ", Item ID: " + temp.itemId + ", Quantity: " + temp.quantity + ", Price: " + temp.price);
            temp = temp.next;
        }
    }

    public static void main(String[] args) {
        InventoryManagement inventory = new InventoryManagement();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1. Add Item at Beginning");
            System.out.println("2. Add Item at End");
            System.out.println("3. Add Item at Position");
            System.out.println("4. Remove Item by ID");
            System.out.println("5. Update Quantity by ID");
            System.out.println("6. Search by ID");
            System.out.println("7. Search by Name");
            System.out.println("8. Calculate Total Value");
            System.out.println("9. Sort by Name");
            System.out.println("10. Sort by Price");
            System.out.println("11. Display Inventory");
            System.out.println("12. Exit");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Enter Item Name, ID, Quantity, Price:");
                    inventory.addItemAtBeginning(scanner.next(), scanner.nextInt(), scanner.nextInt(), scanner.nextDouble());
                    break;
                case 2:
                    System.out.println("Enter Item Name, ID, Quantity, Price:");
                    inventory.addItemAtEnd(scanner.next(), scanner.nextInt(), scanner.nextInt(), scanner.nextDouble());
                    break;
                case 3:
                    System.out.println("Enter Item Name, ID, Quantity, Price, Position:");
                    inventory.addItemAtPosition(scanner.next(), scanner.nextInt(), scanner.nextInt(), scanner.nextDouble(), scanner.nextInt());
                    break;
                case 4:
                    System.out.println("Enter Item ID to Remove:");
                    inventory.removeItemById(scanner.nextInt());
                    break;
                case 5:
                    System.out.println("Enter Item ID and New Quantity:");
                    inventory.updateQuantityById(scanner.nextInt(), scanner.nextInt());
                    break;
                case 6:
                    System.out.println("Enter Item ID to Search:");
                    Inventory itemById = inventory.searchById(scanner.nextInt());
                    if (itemById != null) {
                        System.out.println("Item Found: " + itemById.itemName);
                    } else {
                        System.out.println("Item Not Found");
                    }
                    break;
                case 7:
                    System.out.println("Enter Item Name to Search:");
                    Inventory itemByName = inventory.searchByName(scanner.next());
                    if (itemByName != null) {
                        System.out.println("Item Found: " + itemByName.itemName);
                    } else {
                        System.out.println("Item Not Found");
                    }
                    break;
                case 8:
                    System.out.println("Total Inventory Value: " + inventory.calculateTotalValue());
                    break;
                case 9:
                    System.out.println("Sort by Name (1 for Ascending, 0 for Descending):");
                    inventory.sortByName(scanner.nextInt() == 1);
                    break;
                case 10:
                    System.out.println("Sort by Price (1 for Ascending, 0 for Descending):");
                    inventory.sortByPrice(scanner.nextInt() == 1);
                    break;
                case 11:
                    inventory.displayInventory();
                    break;
                case 12:
                    scanner.close();
                    return;
            }
        }
    }
}