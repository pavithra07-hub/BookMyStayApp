import java.util.HashMap;
import java.util.Map;

abstract class Room {
    private String type;
    private int beds;
    private double price;

    public Room(String type, int beds, double price) {
        this.type = type;
        this.beds = beds;
        this.price = price;
    }

    public String getType() { return type; }
    public int getBeds() { return beds; }
    public double getPrice() { return price; }

    public void displayDetails() {
        System.out.println("Room Type: " + type);
        System.out.println("Beds: " + beds);
        System.out.println("Price per Night: $" + price);
    }
}

class SingleRoom extends Room {
    public SingleRoom() { super("Single Room", 1, 50.0); }
}

class DoubleRoom extends Room {
    public DoubleRoom() { super("Double Room", 2, 90.0); }
}

class SuiteRoom extends Room {
    public SuiteRoom() { super("Suite Room", 3, 150.0); }
}

class RoomInventory {
    private Map<String, Integer> inventory;

    public RoomInventory() {
        inventory = new HashMap<>();
    }

    public void addRoomType(String roomType, int available) {
        inventory.put(roomType, available);
    }

    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    public Map<String, Integer> getInventorySnapshot() {
        return new HashMap<>(inventory);
    }
}

class RoomSearchService {
    private RoomInventory inventory;
    private Room[] rooms;

    public RoomSearchService(RoomInventory inventory, Room[] rooms) {
        this.inventory = inventory;
        this.rooms = rooms;
    }

    public void displayAvailableRooms() {
        System.out.println("\n=== Available Rooms ===");
        for (Room room : rooms) {
            if (inventory.getAvailability(room.getType()) > 0) {
                room.displayDetails();
                System.out.println("Availability: " + inventory.getAvailability(room.getType()) + "\n");
            }
        }
        System.out.println("======================\n");
    }
}

public class UseCase4RoomSearch {
    public static void main(String[] args) {
        System.out.println("=== Book My Stay App v4.1 ===");

        RoomInventory inventory = new RoomInventory();
        inventory.addRoomType("Single Room", 5);
        inventory.addRoomType("Double Room", 0);
        inventory.addRoomType("Suite Room", 2);

        Room[] rooms = { new SingleRoom(), new DoubleRoom(), new SuiteRoom() };

        RoomSearchService searchService = new RoomSearchService(inventory, rooms);
        searchService.displayAvailableRooms();
    }
}