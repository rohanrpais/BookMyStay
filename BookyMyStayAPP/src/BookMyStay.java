// Version 4.1 - Room Search & Availability Check

import java.util.HashMap;
import java.util.Map;

// Abstract Room class
abstract class Room {
    private String roomType;
    private int numberOfBeds;
    private double size;
    private double price;

    public Room(String roomType, int numberOfBeds, double size, double price) {
        this.roomType = roomType;
        this.numberOfBeds = numberOfBeds;
        this.size = size;
        this.price = price;
    }

    public String getRoomType() {
        return roomType;
    }

    public int getNumberOfBeds() {
        return numberOfBeds;
    }

    public double getSize() {
        return size;
    }

    public double getPrice() {
        return price;
    }

    public abstract void displayDetails();
}

// Concrete room classes
class SingleRoom extends Room {
    public SingleRoom() {
        super("Single Room", 1, 20.0, 2500.0);
    }
    @Override
    public void displayDetails() {
        System.out.println("Room Type: " + getRoomType() +
                ", Beds: " + getNumberOfBeds() +
                ", Size: " + getSize() + " sqm" +
                ", Price: ₹" + getPrice());
    }
}

class DoubleRoom extends Room {
    public DoubleRoom() {
        super("Double Room", 2, 35.0, 4000.0);
    }
    @Override
    public void displayDetails() {
        System.out.println("Room Type: " + getRoomType() +
                ", Beds: " + getNumberOfBeds() +
                ", Size: " + getSize() + " sqm" +
                ", Price: ₹" + getPrice());
    }
}

class SuiteRoom extends Room {
    public SuiteRoom() {
        super("Suite Room", 3, 60.0, 8000.0);
    }
    @Override
    public void displayDetails() {
        System.out.println("Room Type: " + getRoomType() +
                ", Beds: " + getNumberOfBeds() +
                ", Size: " + getSize() + " sqm" +
                ", Price: ₹" + getPrice());
    }
}

// Centralized Inventory
class RoomInventory {
    private Map<String, Integer> inventory = new HashMap<>();

    public void addRoomType(String roomType, int count) {
        inventory.put(roomType, count);
    }

    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    public Map<String, Integer> getAllAvailability() {
        return new HashMap<>(inventory); // defensive copy
    }
}

// Search Service (read-only)
class RoomSearchService {
    private RoomInventory inventory;
    private Map<String, Room> roomCatalog;

    public RoomSearchService(RoomInventory inventory) {
        this.inventory = inventory;
        roomCatalog = new HashMap<>();
        roomCatalog.put("Single Room", new SingleRoom());
        roomCatalog.put("Double Room", new DoubleRoom());
        roomCatalog.put("Suite Room", new SuiteRoom());
    }

    public void searchAvailableRooms() {
        System.out.println("Available Rooms:");
        for (Map.Entry<String, Integer> entry : inventory.getAllAvailability().entrySet()) {
            if (entry.getValue() > 0) {
                Room room = roomCatalog.get(entry.getKey());
                room.displayDetails();
                System.out.println("Availability: " + entry.getValue() + " rooms\n");
            }
        }
    }
}

public class BookMyStay {
    public static void main(String[] args) {
        // Initialize inventory
        RoomInventory inventory = new RoomInventory();
        inventory.addRoomType("Single Room", 5);
        inventory.addRoomType("Double Room", 0); // unavailable
        inventory.addRoomType("Suite Room", 2);

        // Initialize search service
        RoomSearchService searchService = new RoomSearchService(inventory);

        // Guest initiates search
        searchService.searchAvailableRooms();

        System.out.println("Application terminated.");
    }
}
