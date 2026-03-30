import java.util.*;

class InvalidBookingException extends Exception {
    public InvalidBookingException(String message) {
        super(message);
    }
}

class RoomInventory {
    private Map<String, Integer> availability = new HashMap<>();

    public RoomInventory() {
        availability.put("Single", 5);
        availability.put("Double", 3);
        availability.put("Suite", 2);
    }

    public boolean isValidRoomType(String type) {
        return availability.containsKey(type);
    }

    public boolean isAvailable(String type) {
        return availability.getOrDefault(type, 0) > 0;
    }
}

class ReservationValidator {
    public void validate(String guestName, String roomType, RoomInventory inventory)
            throws InvalidBookingException {

        if (guestName == null || guestName.trim().isEmpty()) {
            throw new InvalidBookingException("Guest name cannot be empty");
        }

        if (!inventory.isValidRoomType(roomType)) {
            throw new InvalidBookingException("Invalid room type selected");
        }

        if (!inventory.isAvailable(roomType)) {
            throw new InvalidBookingException("Selected room not available");
        }
    }
}

class Reservation {
    private String guestName;
    private String roomType;
    @@ -18,43 +60,43 @@ public String getRoomType() {
    }
}

class BookingHistory {
    private List<Reservation> confirmedReservations;
    class BookingRequestQueue {
        private Queue<Reservation> queue = new LinkedList<>();

        public BookingHistory() {
            confirmedReservations = new ArrayList<>();
            public void addRequest(Reservation r) {
                queue.add(r);
            }
        }

        public void addReservation(Reservation reservation) {
            confirmedReservations.add(reservation);
        }
        public class UseCase9ErrorHandlingValidation {
            public static void main(String[] args) {

                public List<Reservation> getConfirmedReservations() {
                    return confirmedReservations;
                }
            }
        System.out.println("Booking Validation");

            class BookingReportService {
                public void generateReport(BookingHistory history) {
                    System.out.println("Booking History Report\n");
                    for (Reservation r : history.getConfirmedReservations()) {
                        System.out.println("Guest: " + r.getGuestName() + ", Room Type: " + r.getRoomType());
                    }
                }
            }
            Scanner scanner = new Scanner(System.in);

            public class UseCase8BookingHistoryReport {
                public static void main(String[] args) {
                    RoomInventory inventory = new RoomInventory();
                    ReservationValidator validator = new ReservationValidator();
                    BookingRequestQueue bookingQueue = new BookingRequestQueue();

                    try {
                        System.out.print("Enter guest name: ");
                        String guestName = scanner.nextLine();

                        System.out.println("Booking History and Reporting\n");
                        System.out.print("Enter room type (Single/Double/Suite): ");
                        String roomType = scanner.nextLine();

                        BookingHistory history = new BookingHistory();
                        validator.validate(guestName, roomType, inventory);

                        history.addReservation(new Reservation("Abhi", "Single"));
                        history.addReservation(new Reservation("Subha", "Double"));
                        history.addReservation(new Reservation("Vannathi", "Suite"));
                        Reservation reservation = new Reservation(guestName, roomType);
                        bookingQueue.addRequest(reservation);

                        BookingReportService reportService = new BookingReportService();
                        reportService.generateReport(history);
                        System.out.println("Booking request added successfully");

                    } catch (InvalidBookingException e) {
                        System.out.println("Booking failed: " + e.getMessage());
                    } finally {
                        scanner.close();
                    }
                }
            }