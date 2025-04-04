import java.util.Scanner;

class Book {
    String title;
    String author;
    String genre;
    int bookID;
    boolean isAvailable;
    Book next;
    Book prev;

    public Book(String title, String author, String genre, int bookID, boolean isAvailable) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.bookID = bookID;
        this.isAvailable = isAvailable;
        this.next = null;
        this.prev = null;
    }
}

public class Library {
    private Book head;
    private Book tail;
    private int count;

    public Library() {
        this.head = null;
        this.tail = null;
        this.count = 0;
    }

    public void addBook(String title, String author, String genre, int bookID, boolean isAvailable, int position) {
        Book newBook = new Book(title, author, genre, bookID, isAvailable);
        if (position <= 0 || head == null) {
            newBook.next = head;
            if (head != null) head.prev = newBook;
            head = newBook;
            if (tail == null) tail = newBook;
        } else if (position >= count) {
            newBook.prev = tail;
            if (tail != null) tail.next = newBook;
            tail = newBook;
            if (head == null) head = newBook;
        } else {
            Book current = head;
            for (int i = 0; i < position - 1; i++) current = current.next;
            newBook.next = current.next;
            newBook.prev = current;
            if (current.next != null) current.next.prev = newBook;
            current.next = newBook;
        }
        count++;
    }

    public void removeBook(int bookID) {
        Book current = head;
        while (current != null && current.bookID != bookID) current = current.next;
        if (current == null) return;
        if (current.prev != null) current.prev.next = current.next;
        else head = current.next;
        if (current.next != null) current.next.prev = current.prev;
        else tail = current.prev;
        count--;
    }

    public void searchBook(String query) {
        Book current = head;
        while (current != null) {
            if (current.title.equalsIgnoreCase(query) || current.author.equalsIgnoreCase(query)) {
                System.out.println("Book Found: " + current.title + " by " + current.author);
                return;
            }
            current = current.next;
        }
        System.out.println("Book not found.");
    }

    public void updateAvailability(int bookID, boolean isAvailable) {
        Book current = head;
        while (current != null) {
            if (current.bookID == bookID) {
                current.isAvailable = isAvailable;
                System.out.println("Availability updated.");
                return;
            }
            current = current.next;
        }
        System.out.println("Book not found.");
    }

    public void displayBooks(boolean reverse) {
        if (reverse) {
            Book current = tail;
            while (current != null) {
                System.out.println(current.title + " by " + current.author + " (ID: " + current.bookID + ", Available: " + current.isAvailable + ")");
                current = current.prev;
            }
        } else {
            Book current = head;
            while (current != null) {
                System.out.println(current.title + " by " + current.author + " (ID: " + current.bookID + ", Available: " + current.isAvailable + ")");
                current = current.next;
            }
        }
    }

    public int countBooks() {
        return count;
    }

    public static void main(String[] args) {
        Library library = new Library();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Add Book\n2. Remove Book\n3. Search Book\n4. Update Availability\n5. Display Books\n6. Count Books\n7. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Enter Title, Author, Genre, Book ID, Availability (true/false), and Position:");
                    String title = scanner.nextLine();
                    String author = scanner.nextLine();
                    String genre = scanner.nextLine();
                    int bookID = scanner.nextInt();
                    boolean isAvailable = scanner.nextBoolean();
                    int position = scanner.nextInt();
                    library.addBook(title, author, genre, bookID, isAvailable, position);
                    break;
                case 2:
                    System.out.println("Enter Book ID to remove:");
                    int removeID = scanner.nextInt();
                    library.removeBook(removeID);
                    break;
                case 3:
                    System.out.println("Enter Title or Author to search:");
                    String query = scanner.nextLine();
                    library.searchBook(query);
                    break;
                case 4:
                    System.out.println("Enter Book ID and new Availability (true/false):");
                    int updateID = scanner.nextInt();
                    boolean availability = scanner.nextBoolean();
                    library.updateAvailability(updateID, availability);
                    break;
                case 5:
                    System.out.println("Display in reverse order? (true/false):");
                    boolean reverse = scanner.nextBoolean();
                    library.displayBooks(reverse);
                    break;
                case 6:
                    System.out.println("Total Books: " + library.countBooks());
                    break;
                case 7:
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}