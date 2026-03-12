import java.util.*;

class BookingRequest {
    String customerName;
    String roomType;

    BookingRequest(String customerName, String roomType) {
        this.customerName = customerName;
        this.roomType = roomType;
    }
}

public class UseCase6RoomAllocationService {

    // Queue to store booking requests (FIFO)
    private static Queue<BookingRequest> bookingQueue = new LinkedList<>();

    // Inventory of room types
    private static Map<String, Integer> inventory = new HashMap<>();

    // Map of roomType -> allocated room IDs
    private static Map<String, Set<String>> allocatedRooms = new HashMap<>();

    // Set to ensure room IDs are unique
    private static Set<String> usedRoomIds = new HashSet<>();

    public static void main(String[] args) {

        // Initialize inventory
        inventory.put("Standard", 3);
        inventory.put("Deluxe", 2);
        inventory.put("Suite", 1);

        // Initialize allocated rooms map
        allocatedRooms.put("Standard", new HashSet<>());
        allocatedRooms.put("Deluxe", new HashSet<>());
        allocatedRooms.put("Suite", new HashSet<>());

        // Add booking requests to queue
        bookingQueue.add(new BookingRequest("Arun", "Standard"));
        bookingQueue.add(new BookingRequest("Priya", "Deluxe"));
        bookingQueue.add(new BookingRequest("Rahul", "Standard"));
        bookingQueue.add(new BookingRequest("Meena", "Suite"));
        bookingQueue.add(new BookingRequest("Kiran", "Standard"));

        processBookings();
    }

    public static void processBookings() {

        while (!bookingQueue.isEmpty()) {

            BookingRequest request = bookingQueue.poll();

            String roomType = request.roomType;

            if (inventory.containsKey(roomType) && inventory.get(roomType) > 0) {

                String roomId = generateRoomId(roomType);

                usedRoomIds.add(roomId);
                allocatedRooms.get(roomType).add(roomId);

                inventory.put(roomType, inventory.get(roomType) - 1);

                System.out.println("Reservation Confirmed");
                System.out.println("Customer: " + request.customerName);
                System.out.println("Room Type: " + roomType);
                System.out.println("Allocated Room ID: " + roomId);
                System.out.println();

            } else {
                System.out.println("Reservation Failed for " + request.customerName + " - No " + roomType + " rooms available\n");
            }
        }
    }

    private static String generateRoomId(String roomType) {

        String prefix = roomType.substring(0, 2).toUpperCase();
        String roomId;

        do {
            int number = new Random().nextInt(900) + 100;
            roomId = prefix + number;
        } while (usedRoomIds.contains(roomId));

        return roomId;
    }
}