import java.util.LinkedList;
import java.util.Queue;

class Reservation {
    private String guestName;
    private String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getGuestName() { return guestName; }
    public String getRoomType() { return roomType; }

    public void displayReservation() {
        System.out.println("Guest: " + guestName + ", Requested Room: " + roomType);
    }
}

class BookingRequestQueue {
    private Queue<Reservation> requestQueue;

    public BookingRequestQueue() {
        requestQueue = new LinkedList<>();
    }

    public void addRequest(Reservation reservation) {
        requestQueue.offer(reservation);
    }

    public Reservation pollRequest() {
        return requestQueue.poll();
    }

    public boolean isEmpty() {
        return requestQueue.isEmpty();
    }

    public void displayQueue() {
        System.out.println("\n=== Current Booking Requests ===");
        for (Reservation r : requestQueue) {
            r.displayReservation();
        }
        System.out.println("================================\n");
    }
}

public class UseCase5BookingRequestQueue {
    public static void main(String[] args) {
        System.out.println("=== Book My Stay App v5.1 ===");

        BookingRequestQueue bookingQueue = new BookingRequestQueue();

        Reservation r1 = new Reservation("Alice", "Single Room");
        Reservation r2 = new Reservation("Bob", "Suite Room");
        Reservation r3 = new Reservation("Charlie", "Double Room");

        bookingQueue.addRequest(r1);
        bookingQueue.addRequest(r2);
        bookingQueue.addRequest(r3);

        bookingQueue.displayQueue();

        System.out.println("Processing first request...");
        Reservation processed = bookingQueue.pollRequest();
        processed.displayReservation();

        bookingQueue.displayQueue();
    }
}
