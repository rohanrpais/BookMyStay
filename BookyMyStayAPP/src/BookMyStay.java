import java.io.*;
import java.util.*;

// Represents a reservation
class Reservation {
    // Reservation must be Serializable to persist
    class Reservation implements Serializable {
        private String reservationId;
        private String guestName;
        private String roomType;
        private String roomId;
        private boolean cancelled;

        public Reservation(String reservationId, String guestName, String roomType, String roomId) {
    public Reservation(String reservationId, String guestName, String roomType) {
                this.reservationId = reservationId;
                this.guestName = guestName;
                this.roomType = roomType;
                this.roomId = roomId;
                this.cancelled = false;
            }

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
            // Inventory must also be Serializable
            class Inventory implements Serializable {
                private Map<String, Integer> roomInventory = new HashMap<>();

                public Inventory() {
                    @@ -55,64 +42,52 @@ public boolean allocateRoom(String roomType) {
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
                    private Stack<String> rollbackStack = new Stack<>();
                    private Inventory inventory;
                    private Map<String, Reservation> reservations;

                    public CancellationService(Inventory inventory, Map<String, Reservation> reservations) {
                        this.inventory = inventory;
                        this.reservations = reservations;
                    }

                    public void cancelReservation(String reservationId) {
                        Reservation reservation = reservations.get(reservationId);

                        if (reservation == null) {
                            System.out.println("Cancellation failed: Reservation does not exist.");
                            return;
// Persistence service for saving and loading state
                            class PersistenceService {
                                private static final String FILE_NAME = "system_state.ser";

                                public static void saveState(List<Reservation> reservations, Inventory inventory) {
                                    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
                                        oos.writeObject(reservations);
                                        oos.writeObject(inventory);
                                        System.out.println("System state saved successfully.");
                                    } catch (IOException e) {
                                        System.out.println("Error saving state: " + e.getMessage());
                                    }
                                    if (reservation.isCancelled()) {
                                        System.out.println("Cancellation failed: Reservation already cancelled.");
                                        return;
                                    }

                                    // Perform rollback
                                    reservation.cancel();
                                    rollbackStack.push(reservation.getRoomId());
                                    inventory.restoreRoom(reservation.getRoomType());

                                    System.out.println("Cancellation successful. Room " + reservation.getRoomId() +
                                            " released back to inventory.");
                                }

                                public void displayRollbackStack() {
                                    System.out.println("Rollback Stack (released rooms): " + rollbackStack);
                                    @SuppressWarnings("unchecked")
                                    public static Object[] loadState() {
                                        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
                                            List<Reservation> reservations = (List<Reservation>) ois.readObject();
                                            Inventory inventory = (Inventory) ois.readObject();
                                            System.out.println("System state restored successfully.");
                                            return new Object[]{reservations, inventory};
                                        } catch (Exception e) {
                                            System.out.println("No previous state found. Starting fresh.");
                                            return new Object[]{new ArrayList<Reservation>(), new Inventory()};
                                        }
                                    }
                                }

                                public class UseCase10BookingCancellation {
                                    public class UseCase12DataPersistenceRecovery {
                                        public static void main(String[] args) {
                                            Scanner scanner = new Scanner(System.in);
                                            Inventory inventory = new Inventory();
                                            Map<String, Reservation> reservations = new HashMap<>();
                                            CancellationService cancellationService = new CancellationService(inventory, reservations);

                                            // Load previous state if available
                                            Object[] state = PersistenceService.loadState();
                                            List<Reservation> reservations = (List<Reservation>) state[0];
                                            Inventory inventory = (Inventory) state[1];

                                            System.out.print("Enter number of bookings to confirm: ");
                                            int n = scanner.nextInt();
                                            scanner.nextLine();

                                            // Confirm bookings
                                            for (int i = 1; i <= n; i++) {
                                                System.out.println("\nEnter details for booking " + i + ":");
                                                System.out.print("Reservation ID: ");
                                                @@ -125,31 +100,23 @@ public static void main(String[] args) {
                                                    String roomType = scanner.nextLine();

                                                    if (inventory.allocateRoom(roomType)) {
                                                        String roomId = roomType.substring(0, 3).toUpperCase() + i; // simple room ID
                                                        Reservation reservation = new Reservation(id, guest, roomType, roomId);
                                                        reservations.put(id, reservation);
                                                        Reservation reservation = new Reservation(id, guest, roomType);
                                                        reservations.add(reservation);
                                                        System.out.println("Booking confirmed: " + reservation);
                                                    } else {
                                                        System.out.println("Booking failed: No rooms available for " + roomType);
                                                    }
                                                }

                                                inventory.displayInventory();

                                                // Cancellation flow
                                                System.out.print("\nEnter Reservation ID to cancel: ");
                                                String cancelId = scanner.nextLine();
                                                cancellationService.cancelReservation(cancelId);

                                                // Show rollback stack and inventory after cancellation
                                                cancellationService.displayRollbackStack();
                                                inventory.displayInventory();

                                                // Show all reservations
                                                System.out.println("\n=== Reservations State ===");
                                                for (Reservation r : reservations.values()) {
                                                    // Display current state
                                                    System.out.println("\n=== Current Reservations ===");
                                                    for (Reservation r : reservations) {
                                                        System.out.println(r);
                                                    }
                                                    inventory.displayInventory();

                                                    // Save state before shutdown
                                                    PersistenceService.saveState(reservations, inventory);

                                                    scanner.close();
                                                }