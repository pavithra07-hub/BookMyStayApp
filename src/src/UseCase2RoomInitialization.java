
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
        System.out.println("Number of Beds: " + beds);
        System.out.println("Price per Night: $" + price);
    }
}

// Concrete Room classes
class SingleRoom extends Room {
    public SingleRoom() { super("Single Room", 1, 50.0); }
}

class DoubleRoom extends Room {
    public DoubleRoom() { super("Double Room", 2, 90.0); }
}

class SuiteRoom extends Room {
    public SuiteRoom() { super("Suite Room", 3, 150.0); }
}

// Main application class
public class UseCase2RoomInitialization {
    public static void main(String[] args) {
        System.out.println("=== Book My Stay App v2.1 ===");

        // Static availability
        int singleRoomAvailable = 5;
        int doubleRoomAvailable = 3;
        int suiteRoomAvailable = 2;

        // Initialize room objects
        Room singleRoom = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suiteRoom = new SuiteRoom();

        // Display room details and availability
        System.out.println("\nAvailable Rooms:\n");

        singleRoom.displayDetails();
        System.out.println("Availability: " + singleRoomAvailable + "\n");

        doubleRoom.displayDetails();
        System.out.println("Availability: " + doubleRoomAvailable + "\n");

        suiteRoom.displayDetails();
        System.out.println("Availability: " + suiteRoomAvailable + "\n");

        System.out.println("==================================");
    }
}