import java.time.LocalDateTime;

class Ticket {
    int ticketId;
    String customerName;
    String movieName;
    String seatNumber;
    LocalDateTime bookingTime;
    Ticket next;

    public Ticket(int ticketId, String customerName, String movieName, String seatNumber, LocalDateTime bookingTime) {
        this.ticketId = ticketId;
        this.customerName = customerName;
        this.movieName = movieName;
        this.seatNumber = seatNumber;
        this.bookingTime = bookingTime;
        this.next = null;
    }
}

public class TicketReservationSystem {
    private Ticket head = null;

    public void addTicket(int ticketId, String customerName, String movieName, String seatNumber, LocalDateTime bookingTime) {
        Ticket newTicket = new Ticket(ticketId, customerName, movieName, seatNumber, bookingTime);
        if (head == null) {
            head = newTicket;
            head.next = head;
        } else {
            Ticket temp = head;
            while (temp.next != head) {
                temp = temp.next;
            }
            temp.next = newTicket;
            newTicket.next = head;
        }
    }

    public void removeTicket(int ticketId) {
        if (head == null) return;
        if (head.ticketId == ticketId && head.next == head) {
            head = null;
            return;
        }
        Ticket current = head, prev = null;
        do {
            if (current.ticketId == ticketId) {
                if (prev != null) {
                    prev.next = current.next;
                } else {
                    Ticket temp = head;
                    while (temp.next != head) {
                        temp = temp.next;
                    }
                    head = head.next;
                    temp.next = head;
                }
                return;
            }
            prev = current;
            current = current.next;
        } while (current != head);
    }

    public void displayTickets() {
        if (head == null) return;
        Ticket temp = head;
        do {
            System.out.println("Ticket ID: " + temp.ticketId + ", Customer Name: " + temp.customerName +
                    ", Movie Name: " + temp.movieName + ", Seat Number: " + temp.seatNumber +
                    ", Booking Time: " + temp.bookingTime);
            temp = temp.next;
        } while (temp != head);
    }

    public void searchTicket(String query) {
        if (head == null) return;
        Ticket temp = head;
        do {
            if (temp.customerName.equalsIgnoreCase(query) || temp.movieName.equalsIgnoreCase(query)) {
                System.out.println("Ticket ID: " + temp.ticketId + ", Customer Name: " + temp.customerName +
                        ", Movie Name: " + temp.movieName + ", Seat Number: " + temp.seatNumber +
                        ", Booking Time: " + temp.bookingTime);
            }
            temp = temp.next;
        } while (temp != head);
    }

    public int totalTickets() {
        if (head == null) return 0;
        int count = 0;
        Ticket temp = head;
        do {
            count++;
            temp = temp.next;
        } while (temp != head);
        return count;
    }

    public static void main(String[] args) {
        TicketReservationSystem system = new TicketReservationSystem();
        system.addTicket(1, "Alice", "Inception", "A1", LocalDateTime.now());
        system.addTicket(2, "Bob", "Interstellar", "B2", LocalDateTime.now());
        system.addTicket(3, "Charlie", "Dunkirk", "C3", LocalDateTime.now());

        system.displayTickets();
        System.out.println("Total Tickets: " + system.totalTickets());

        system.searchTicket("Alice");
        system.removeTicket(2);

        system.displayTickets();
        System.out.println("Total Tickets: " + system.totalTickets());
    }
}