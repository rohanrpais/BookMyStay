// Version 3.1 - Centralized Room Inventory Management using HashMap

import java.util.HashMap;
import java.util.Map;

class RoomInventory {
    private Map<String, Integer> inventory;

    // Initialize inventory in constructor
    public RoomInventory() {
        inventory = new HashMap<>();
    }

    // Register a room type with availability
    public void addRoomType(String roomType, int count) {
        inventory.put(roomType, count);
    }

    // Retrieve availability for a room type
    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    // Update availability (e.g., after booking or cancellation)
    public void updateAvailability(String roomType, int newCount) {
        if (inventory.containsKey(roomType)) {
            inventory.put(roomType, newCount);
        } else {
            System.out.println("Room type not found in inventory.");
        }
    }

    // Display current inventory state
    public void displayInventory() {
        System.out.println("Current Room Inventory:");
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println("Room Type: " + entry.getKey() +
                    " | Available: " + entry.getValue());
        }
    }
}

public class BookMyStay {
    public static void main(String[] args) {
        // Initialize centralized inventory
        RoomInventory inventory = new RoomInventory();

        // Register room types with availability
        inventory.addRoomType("Single Room", 5);
        inventory.addRoomType("Double Room", 3);
        inventory.addRoomType("Suite Room", 2);

        // Display initial inventory
        inventory.displayInventory();

        // Simulate booking a Double Room
        System.out.println("\nBooking a Double Room...");
        int currentDouble = inventory.getAvailability("Double Room");
        inventory.updateAvailability("Double Room", currentDouble - 1);

        // Display updated inventory
        inventory.displayInventory();

        // Simulate cancellation of a Suite Room
        System.out.println("\nCancelling a Suite Room booking...");
        int currentSuite = inventory.getAvailability("Suite Room");
        inventory.updateAvailability("Suite Room", currentSuite + 1);

        // Display final inventory
        inventory.displayInventory();

        System.out.println("\nApplication terminated.");
    }
}
