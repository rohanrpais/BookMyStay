// Version 6.1 - Reservation Confirmation & Room Allocation

import java.util.*;

class Reservation {
    private String guestName;
    private String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getRoomType() {
        return roomType;
    }

    @Override
    public String toString() {
        return "Reservation [Guest: " + guestName + ", Room Type: " + roomType + "]";
    }
}

// Booking Request Queue (FIFO)
class BookingRequestQueue {
    private Queue<Reservation> requestQueue = new LinkedList<>();

    public void addRequest(Reservation reservation) {
        requestQueue.add(reservation);
        System.out.println("Request added: " + reservation);
    }

    public Reservation getNextRequest() {
        return requestQueue.poll(); // dequeue
    }

    public boolean isEmpty() {
        return requestQueue.isEmpty();
    }
}

// Inventory Service
class RoomInventory {
    private Map<String, Integer> inventory = new HashMap<>();

    public void addRoomType(String roomType, int count) {
        inventory.put(roomType, count);
    }

    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    public boolean allocateRoom(String roomType) {
        int available = getAvailability(roomType);
        if (available > 0) {
            inventory.put(roomType, available - 1);
            return true;
        }
        return false;
    }

    public void displayInventory() {
        System.out.println("Current Inventory:");
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println("Room Type: " + entry.getKey() + " | Available: " + entry.getValue());
        }
    }
}

// Booking Service (allocates rooms safely)
class BookingService {
    private RoomInventory inventory;
    private Map<String, Set<String>> allocatedRooms = new HashMap<>();

    public BookingService(RoomInventory inventory) {
        this.inventory = inventory;
    }

    public void processRequest(Reservation reservation) {
        String roomType = reservation.getRoomType();

        if (inventory.allocateRoom(roomType)) {
            // Generate unique room ID
            String roomId = UUID.randomUUID().toString();

            // Ensure uniqueness with Set
            allocatedRooms.computeIfAbsent(roomType, k -> new HashSet<>()).add(roomId);

            System.out.println("CONFIRMED: " + reservation.getGuestName() +
                    " booked a " + roomType + " | Room ID: " + roomId);
        } else {
            System.out.println("FAILED: No " + roomType + " available for " + reservation.getGuestName());
        }
    }

    public void displayAllocatedRooms() {
        System.out.println("\nAllocated Rooms:");
        for (Map.Entry<String, Set<String>> entry : allocatedRooms.entrySet()) {
            System.out.println("Room Type: " + entry.getKey() + " | Room IDs: " + entry.getValue());
        }
    }
}

public class BookMyStay {
    public static void main(String[] args) {
        // Initialize inventory
        RoomInventory inventory = new RoomInventory();
        inventory.addRoomType("Single Room", 2);
        inventory.addRoomType("Double Room", 1);
        inventory.addRoomType("Suite Room", 1);

        // Initialize booking queue
        BookingRequestQueue queue = new BookingRequestQueue();
        queue.addRequest(new Reservation("Alice", "Single Room"));
        queue.addRequest(new Reservation("Bob", "Double Room"));
        queue.addRequest(new Reservation("Charlie", "Suite Room"));
        queue.addRequest(new Reservation("Diana", "Single Room")); // may fail if no availability

        // Initialize booking service
        BookingService bookingService = new BookingService(inventory);

        // Process requests in FIFO order
        while (!queue.isEmpty()) {
            Reservation next = queue.getNextRequest();
            bookingService.processRequest(next);
        }

        // Display final inventory and allocations
        System.out.println();
        inventory.displayInventory();
        bookingService.displayAllocatedRooms();

        System.out.println("\nApplication terminated.");
    }
}
