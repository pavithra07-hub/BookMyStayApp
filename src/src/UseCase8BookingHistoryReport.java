import java.util.*;

class Reservation {
    String reservationId;
    String guestName;
    String roomType;

    Reservation(String reservationId, String guestName, String roomType) {
        this.reservationId = reservationId;
        this.guestName = guestName;
        this.roomType = roomType;
    }
}

class BookingHistory {
    private List<Reservation> history = new ArrayList<>();

    public void addReservation(Reservation r) {
        history.add(r);
    }

    public List<Reservation> getHistory() {
        return history;
    }
}

class BookingReportService {

    public void displayAllBookings(List<Reservation> list) {

        System.out.println("Booking History:");

        for (Reservation r : list) {
            System.out.println(
                    "Reservation ID: " + r.reservationId +
                            ", Guest: " + r.guestName +
                            ", Room Type: " + r.roomType
            );
        }
    }

    public void generateSummary(List<Reservation> list) {

        Map<String, Integer> roomTypeCount = new HashMap<>();

        for (Reservation r : list) {
            roomTypeCount.put(
                    r.roomType,
                    roomTypeCount.getOrDefault(r.roomType, 0) + 1
            );
        }

        System.out.println("\nBooking Summary Report:");

        for (String type : roomTypeCount.keySet()) {
            System.out.println(type + " Rooms Booked: " + roomTypeCount.get(type));
        }
    }
}

public class UseCase8BookingHistoryReport {

    public static void main(String[] args) {

        BookingHistory history = new BookingHistory();

        history.addReservation(new Reservation("RES101", "Arun", "Standard"));
        history.addReservation(new Reservation("RES102", "Priya", "Deluxe"));
        history.addReservation(new Reservation("RES103", "Rahul", "Suite"));
        history.addReservation(new Reservation("RES104", "Meena", "Standard"));

        BookingReportService reportService = new BookingReportService();

        reportService.displayAllBookings(history.getHistory());

        reportService.generateSummary(history.getHistory());
    }
}
