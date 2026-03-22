import java.util.*;

// Booking class to store booking details
class Booking {
    String bookingId;
    String roomType;
    String roomId;
    boolean isActive;

    Booking(String bookingId, String roomType, String roomId) {
        this.bookingId = bookingId;
        this.roomType = roomType;
        this.roomId = roomId;
        this.isActive = true;
    }
}

// Main class
public class UseCase10BookingCancellation {

    // Inventory: Room Type → Available Count
    static Map<String, Integer> inventory = new HashMap<>();

    // Room allocation pool: Room Type → Available Room IDs
    static Map<String, Queue<String>> roomPool = new HashMap<>();

    // Booking records: Booking ID → Booking Object
    static Map<String, Booking> bookings = new HashMap<>();

    // Stack for rollback (LIFO)
    static Stack<String> rollbackStack = new Stack<>();

    public static void main(String[] args) {

        // Initialize inventory
        inventory.put("Single", 2);
        inventory.put("Double", 2);

        // Initialize room pool
        roomPool.put("Single", new LinkedList<>(Arrays.asList("S1", "S2")));
        roomPool.put("Double", new LinkedList<>(Arrays.asList("D1", "D2")));

        // Simulate booking
        bookRoom("B101", "Single");
        bookRoom("B102", "Double");

        System.out.println("\n--- Before Cancellation ---");
        displayState();

        // Perform cancellation
        cancelBooking("B101");

        System.out.println("\n--- After Cancellation ---");
        displayState();
    }

    // Booking method
    public static void bookRoom(String bookingId, String roomType) {

        if (!inventory.containsKey(roomType) || inventory.get(roomType) == 0) {
            System.out.println("No rooms available for type: " + roomType);
            return;
        }

        String roomId = roomPool.get(roomType).poll();
        inventory.put(roomType, inventory.get(roomType) - 1);

        Booking booking = new Booking(bookingId, roomType, roomId);
        bookings.put(bookingId, booking);

        System.out.println("Booked: " + bookingId + " -> Room " + roomId);
    }

    // Cancellation method (Core Logic)
    public static void cancelBooking(String bookingId) {

        System.out.println("\nAttempting cancellation for: " + bookingId);

        // Validation
        if (!bookings.containsKey(bookingId)) {
            System.out.println("Cancellation failed: Booking does not exist.");
            return;
        }

        Booking booking = bookings.get(bookingId);

        if (!booking.isActive) {
            System.out.println("Cancellation failed: Already cancelled.");
            return;
        }

        // Step 1: Push room ID to rollback stack
        rollbackStack.push(booking.roomId);

        // Step 2: Restore inventory
        inventory.put(booking.roomType, inventory.get(booking.roomType) + 1);

        // Step 3: Release room back to pool
        roomPool.get(booking.roomType).offer(booking.roomId);

        // Step 4: Update booking status
        booking.isActive = false;

        // Step 5: Log cancellation
        System.out.println("Cancellation successful for booking: " + bookingId);
        System.out.println("Room released: " + booking.roomId);
    }

    // Display system state
    public static void displayState() {

        System.out.println("\nInventory:");
        for (String type : inventory.keySet()) {
            System.out.println(type + " → " + inventory.get(type));
        }

        System.out.println("\nBookings:");
        for (Booking b : bookings.values()) {
            System.out.println(b.bookingId + " | Room: " + b.roomId + " | Active: " + b.isActive);
        }

        System.out.println("\nRollback Stack:");
        System.out.println(rollbackStack);
    }
}