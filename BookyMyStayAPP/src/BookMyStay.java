import java.util.*;

class InvalidBookingException extends Exception {
    public InvalidBookingException(String message) {
        super(message);
    }
}

class RoomInventory {
    private Map<String, Integer> availability = new HashMap<>();

    @@ -15,88 +9,67 @@ public RoomInventory() {
        availability.put("Suite", 2);
    }

    public boolean isValidRoomType(String type) {
        return availability.containsKey(type);
        public void increaseRoom(String type) {
            availability.put(type, availability.get(type) + 1);
        }

        public boolean isAvailable(String type) {
            return availability.getOrDefault(type, 0) > 0;
            public int getAvailableRooms(String type) {
                return availability.getOrDefault(type, 0);
            }
        }

        class ReservationValidator {
            public void validate(String guestName, String roomType, RoomInventory inventory)
                    throws InvalidBookingException {

                if (guestName == null || guestName.trim().isEmpty()) {
                    throw new InvalidBookingException("Guest name cannot be empty");
                }
                class CancellationService {
                    private Stack<String> releasedRoomIds;
                    private Map<String, String> reservationRoomTypeMap;

        if (!inventory.isValidRoomType(roomType)) {
                        throw new InvalidBookingException("Invalid room type selected");
                    }
                    public CancellationService() {
                        releasedRoomIds = new Stack<>();
                        reservationRoomTypeMap = new HashMap<>();
                    }

        if (!inventory.isAvailable(roomType)) {
                        throw new InvalidBookingException("Selected room not available");
                    }
                    public void registerBooking(String reservationId, String roomType) {
                        reservationRoomTypeMap.put(reservationId, roomType);
                    }
                }

                class Reservation {
                    private String guestName;
                    private String roomType;
                    public void cancelBooking(String reservationId, RoomInventory inventory) {
                        if (!reservationRoomTypeMap.containsKey(reservationId)) {
                            System.out.println("Invalid reservation ID");
                            return;
                        }

    public Reservation(String guestName, String roomType) {
                            this.guestName = guestName;
                            this.roomType = roomType;
                        }
                        String roomType = reservationRoomTypeMap.get(reservationId);

                        public String getGuestName() {
                            return guestName;
                        }
                        inventory.increaseRoom(roomType);
                        releasedRoomIds.push(reservationId);

                        public String getRoomType() {
                            return roomType;
                            System.out.println("Booking cancelled successfully. Inventory restored for room type: " + roomType);
                        }
                    }

                    class BookingRequestQueue {
                        private Queue<Reservation> queue = new LinkedList<>();

                        public void addRequest(Reservation r) {
                            queue.add(r);
                            public void showRollbackHistory() {
                                System.out.println("\nRollback History (Most Recent First):");
                                while (!releasedRoomIds.isEmpty()) {
                                    System.out.println("Released Reservation ID: " + releasedRoomIds.pop());
                                }
                            }
                        }

                        public class UseCase9ErrorHandlingValidation {
                            public class UseCase10BookingCancellation {
                                public static void main(String[] args) {

                                    System.out.println("Booking Validation");

                                    Scanner scanner = new Scanner(System.in);
                                    System.out.println("Booking Cancellation");

                                    RoomInventory inventory = new RoomInventory();
                                    ReservationValidator validator = new ReservationValidator();
                                    BookingRequestQueue bookingQueue = new BookingRequestQueue();
                                    CancellationService service = new CancellationService();

                                    try {
                                        System.out.print("Enter guest name: ");
                                        String guestName = scanner.nextLine();
                                        String reservationId = "Single-1";
                                        String roomType = "Single";

                                        System.out.print("Enter room type (Single/Double/Suite): ");
                                        String roomType = scanner.nextLine();
                                        service.registerBooking(reservationId, roomType);

                                        validator.validate(guestName, roomType, inventory);
                                        service.cancelBooking(reservationId, inventory);

                                        Reservation reservation = new Reservation(guestName, roomType);
                                        bookingQueue.addRequest(reservation);
                                        service.showRollbackHistory();

                                        System.out.println("Booking request added successfully");

                                    } catch (InvalidBookingException e) {
                                        System.out.println("Booking failed: " + e.getMessage());
                                    } finally {
                                        scanner.close();
                                    }
                                    System.out.println("\nUpdated Single Room Availability: " + inventory.getAvailableRooms("Single"));
                                }
                            }