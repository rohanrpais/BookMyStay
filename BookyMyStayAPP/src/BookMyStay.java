import java.util.*;

class Service {
    private String serviceName;
    private double cost;
    class Reservation {
        private String guestName;
        private String roomType;

        public Service(String serviceName, double cost) {
            this.serviceName = serviceName;
            this.cost = cost;
    public Reservation(String guestName, String roomType) {
                this.guestName = guestName;
                this.roomType = roomType;
            }

            public String getServiceName() {
                return serviceName;
                public String getGuestName() {
                    return guestName;
                }

                public double getCost() {
                    return cost;
                    public String getRoomType() {
                        return roomType;
                    }
                }

                class AddOnServiceManager {
                    private Map<String, List<Service>> servicesByReservation;
                    class BookingHistory {
                        private List<Reservation> confirmedReservations;

                        public AddOnServiceManager() {
                            servicesByReservation = new HashMap<>();
    public BookingHistory() {
                                confirmedReservations = new ArrayList<>();
                            }

                            public void addService(String reservationId, Service service) {
                                servicesByReservation
                                        .computeIfAbsent(reservationId, k -> new ArrayList<>())
                                        .add(service);
                                public void addReservation(Reservation reservation) {
                                    confirmedReservations.add(reservation);
                                }

                                public double calculateTotalServiceCost(String reservationId) {
                                    List<Service> services = servicesByReservation.get(reservationId);
                                    if (services == null) return 0.0;
                                    public List<Reservation> getConfirmedReservations() {
                                        return confirmedReservations;
                                    }
                                }

                                double total = 0.0;
                                for (Service s : services) {
                                    total += s.getCost();
                                    class BookingReportService {
                                        public void generateReport(BookingHistory history) {
                                            System.out.println("Booking History Report\n");
                                            for (Reservation r : history.getConfirmedReservations()) {
                                                System.out.println("Guest: " + r.getGuestName() + ", Room Type: " + r.getRoomType());
                                            }
                                            return total;
                                        }
                                    }

                                    public class UseCase7AddOnServiceSelection {
                                        public class UseCase8BookingHistoryReport {
                                            public static void main(String[] args) {

                                                System.out.println("Add-On Service Selection");

                                                String reservationId = "Single-1";

                                                AddOnServiceManager manager = new AddOnServiceManager();

                                                Service s1 = new Service("Breakfast", 500.0);
                                                Service s2 = new Service("Spa", 1000.0);
                                                System.out.println("Booking History and Reporting\n");

                                                manager.addService(reservationId, s1);
                                                manager.addService(reservationId, s2);
                                                BookingHistory history = new BookingHistory();

                                                double totalCost = manager.calculateTotalServiceCost(reservationId);
                                                history.addReservation(new Reservation("Abhi", "Single"));
                                                history.addReservation(new Reservation("Subha", "Double"));
                                                history.addReservation(new Reservation("Vannathi", "Suite"));

                                                System.out.println("Reservation ID: " + reservationId);
                                                System.out.println("Total Add-On Cost: " + totalCost);
                                                BookingReportService reportService = new BookingReportService();
                                                reportService.generateReport(history);
                                            }
                                        }