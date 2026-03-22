import java.util.*;

class InvalidBookingException extends Exception {
    public InvalidBookingException(String message) {
        super(message);
    }
}

class BookingValidator {

    private static Map<String, Integer> inventory = new HashMap<>();

    static {
        inventory.put("Standard", 2);
        inventory.put("Deluxe", 1);
        inventory.put("Suite", 1);
    }

    public static void validateAndBook(String guestName, String roomType) throws InvalidBookingException {

        if (!inventory.containsKey(roomType)) {
            throw new InvalidBookingException("Invalid Room Type: " + roomType);
        }

        int available = inventory.get(roomType);

        if (available <= 0) {
            throw new InvalidBookingException("No rooms available for type: " + roomType);
        }

        inventory.put(roomType, available - 1);

        System.out.println("Booking confirmed for " + guestName + " | Room Type: " + roomType);
        System.out.println("Remaining " + roomType + " rooms: " + inventory.get(roomType));
    }
}

public class UseCase9ErrorHandlingValidation {

    public static void main(String[] args) {

        try {

            BookingValidator.validateAndBook("Arun", "Standard");

            BookingValidator.validateAndBook("Priya", "Deluxe");

            BookingValidator.validateAndBook("Rahul", "Premium");

        } catch (InvalidBookingException e) {

            System.out.println("Booking Failed: " + e.getMessage());

        }

        System.out.println("System continues running safely...");
    }
}