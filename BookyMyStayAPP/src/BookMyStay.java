import java.util.*;

class RoomInventory {
    private Map<String, Integer> availability = new HashMap<>();
    // Represents a reservation
    class Reservation {
        private String reservationId;
        private String guestName;
        private String roomType;
        private String roomId;
        private boolean cancelled;

        public Reservation(String reservationId, String guestName, String roomType, String roomId) {
            this.reservationId = reservationId;
            this.guestName = guestName;
            this.roomType = roomType;
            this.roomId = roomId;
            this.cancelled = false;
        }

        public RoomInventory() {
            availability.put("Single", 5);
            availability.put("Double", 3);
            availability.put("Suite", 2);
            public String getReservationId() { return reservationId; }
            public String getGuestName() { return guestName; }
            public String getRoomType() { return roomType; }
            public String getRoomId() { return roomId; }
            public boolean isCancelled() { return cancelled; }

            public void cancel() { this.cancelled = true; }

            @Override
            public String toString() {
                return "Reservation{" +
                        "ID='" + reservationId + '\'' +
                        ", Guest='" + guestName + '\'' +
                        ", RoomType='" + roomType + '\'' +
                        ", RoomID='" + roomId + '\'' +
                        ", Cancelled=" + cancelled +
                        '}';
            }
        }

        // Manages inventory counts
        class Inventory {
            private Map<String, Integer> roomInventory = new HashMap<>();

            public void increaseRoom(String type) {
                availability.put(type, availability.get(type) + 1);
    public Inventory() {
                    roomInventory.put("Deluxe", 2);
                    roomInventory.put("Suite", 2);
                    roomInventory.put("Standard", 2);
                }

                public int getAvailableRooms(String type) {
                    return availability.getOrDefault(type, 0);
                    public boolean allocateRoom(String roomType) {
                        int count = roomInventory.getOrDefault(roomType, 0);
                        if (count > 0) {
                            roomInventory.put(roomType, count - 1);
                            return true;
                        }
                        return false;
                    }

                    public void restoreRoom(String roomType) {
                        roomInventory.put(roomType, roomInventory.getOrDefault(roomType, 0) + 1);
                    }

                    public void displayInventory() {
                        System.out.println("Current Inventory: " + roomInventory);
                    }
                }

// Handles cancellations and rollback
                class CancellationService {
                    private Stack<String> releasedRoomIds;
                    private Map<String, String> reservationRoomTypeMap;
                    private Stack<String> rollbackStack = new Stack<>();
                    private Inventory inventory;
                    private Map<String, Reservation> reservations;

                    public CancellationService() {
                        releasedRoomIds = new Stack<>();
                        reservationRoomTypeMap = new HashMap<>();
    public CancellationService(Inventory inventory, Map<String, Reservation> reservations) {
                            this.inventory = inventory;
                            this.reservations = reservations;
                        }

                        public void registerBooking(String reservationId, String roomType) {
                            reservationRoomTypeMap.put(reservationId, roomType);
                        }
                        public void cancelReservation(String reservationId) {
                            Reservation reservation = reservations.get(reservationId);

                            public void cancelBooking(String reservationId, RoomInventory inventory) {
                                if (!reservationRoomTypeMap.containsKey(reservationId)) {
                                    System.out.println("Invalid reservation ID");
                                    if (reservation == null) {
                                        System.out.println("Cancellation failed: Reservation does not exist.");
                                        return;
                                    }
                                    if (reservation.isCancelled()) {
                                        System.out.println("Cancellation failed: Reservation already cancelled.");
                                        return;
                                    }

                                    String roomType = reservationRoomTypeMap.get(reservationId);

                                    inventory.increaseRoom(roomType);
                                    releasedRoomIds.push(reservationId);
                                    // Perform rollback
                                    reservation.cancel();
                                    rollbackStack.push(reservation.getRoomId());
                                    inventory.restoreRoom(reservation.getRoomType());

                                    System.out.println("Booking cancelled successfully. Inventory restored for room type: " + roomType);
                                    System.out.println("Cancellation successful. Room " + reservation.getRoomId() +
                                            " released back to inventory.");
                                }

                                public void showRollbackHistory() {
                                    System.out.println("\nRollback History (Most Recent First):");
                                    while (!releasedRoomIds.isEmpty()) {
                                        System.out.println("Released Reservation ID: " + releasedRoomIds.pop());
                                    }
                                    public void displayRollbackStack() {
                                        System.out.println("Rollback Stack (released rooms): " + rollbackStack);
                                    }
                                }

                                public class UseCase10BookingCancellation {
                                    public static void main(String[] args) {
                                        Scanner scanner = new Scanner(System.in);
                                        Inventory inventory = new Inventory();
                                        Map<String, Reservation> reservations = new HashMap<>();
                                        CancellationService cancellationService = new CancellationService(inventory, reservations);

                                        System.out.print("Enter number of bookings to confirm: ");
                                        int n = scanner.nextInt();
                                        scanner.nextLine();

                                        // Confirm bookings
                                        for (int i = 1; i <= n; i++) {
                                            System.out.println("\nEnter details for booking " + i + ":");
                                            System.out.print("Reservation ID: ");
                                            String id = scanner.nextLine();

                                            System.out.print("Guest Name: ");
                                            String guest = scanner.nextLine();

                                            System.out.print("Room Type (Deluxe / Suite / Standard): ");
                                            String roomType = scanner.nextLine();

                                            if (inventory.allocateRoom(roomType)) {
                                                String roomId = roomType.substring(0, 3).toUpperCase() + i; // simple room ID
                                                Reservation reservation = new Reservation(id, guest, roomType, roomId);
                                                reservations.put(id, reservation);
                                                System.out.println("Booking confirmed: " + reservation);
                                            } else {
                                                System.out.println("Booking failed: No rooms available for " + roomType);
                                            }
                                        }

                                        System.out.println("Booking Cancellation");

                                        RoomInventory inventory = new RoomInventory();
                                        CancellationService service = new CancellationService();

                                        String reservationId = "Single-1";
                                        String roomType = "Single";
                                        inventory.displayInventory();

                                        service.registerBooking(reservationId, roomType);
                                        // Cancellation flow
                                        System.out.print("\nEnter Reservation ID to cancel: ");
                                        String cancelId = scanner.nextLine();
                                        cancellationService.cancelReservation(cancelId);

                                        service.cancelBooking(reservationId, inventory);
                                        // Show rollback stack and inventory after cancellation
                                        cancellationService.displayRollbackStack();
                                        inventory.displayInventory();

                                        service.showRollbackHistory();
                                        // Show all reservations
                                        System.out.println("\n=== Reservations State ===");
                                        for (Reservation r : reservations.values()) {
                                            System.out.println(r);
                                        }

                                        System.out.println("\nUpdated Single Room Availability: " + inventory.getAvailableRooms("Single"));
                                        scanner.close();
                                    }
                                }