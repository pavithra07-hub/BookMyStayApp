import java.util.*;

class Service {
    String name;
    double cost;

    Service(String name, double cost) {
        this.name = name;
        this.cost = cost;
    }
}

public class UseCase7AddOnServiceSelection {

    // reservationId -> list of services
    private static Map<String, List<Service>> reservationServices = new HashMap<>();

    public static void addService(String reservationId, Service service) {

        reservationServices.putIfAbsent(reservationId, new ArrayList<>());

        reservationServices.get(reservationId).add(service);

        System.out.println(service.name + " added to reservation " + reservationId);
    }

    public static double calculateAdditionalCost(String reservationId) {

        double total = 0;

        List<Service> services = reservationServices.get(reservationId);

        if (services != null) {
            for (Service s : services) {
                total += s.cost;
            }
        }

        return total;
    }

    public static void showServices(String reservationId) {

        List<Service> services = reservationServices.get(reservationId);

        if (services == null || services.isEmpty()) {
            System.out.println("No services selected.");
            return;
        }

        System.out.println("Services for reservation " + reservationId + ":");

        for (Service s : services) {
            System.out.println(s.name + " - $" + s.cost);
        }

        System.out.println("Total Additional Cost: $" + calculateAdditionalCost(reservationId));
    }

    public static void main(String[] args) {

        String reservationId = "RES101";

        addService(reservationId, new Service("Breakfast", 15));
        addService(reservationId, new Service("Airport Pickup", 25));
        addService(reservationId, new Service("Spa Access", 40));

        showServices(reservationId);
    }
}
