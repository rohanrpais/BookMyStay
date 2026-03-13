// Version 2.1 - Refactored domain model with inheritance and abstraction

abstract class Room {
    private String roomType;
    private int numberOfBeds;
    private double size; // in square meters
    private double price; // per night

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

    // Abstract method to enforce consistent structure
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

public class BookMyStay {
    public static void main(String[] args) {
        // Static availability variables
        int singleRoomAvailability = 5;
        int doubleRoomAvailability = 3;
        int suiteRoomAvailability = 2;

        // Initialize room objects
        Room single = new SingleRoom();
        Room doubleR = new DoubleRoom();
        Room suite = new SuiteRoom();

        // Display details and availability
        single.displayDetails();
        System.out.println("Availability: " + singleRoomAvailability + " rooms\n");

        doubleR.displayDetails();
        System.out.println("Availability: " + doubleRoomAvailability + " rooms\n");

        suite.displayDetails();
        System.out.println("Availability: " + suiteRoomAvailability + " rooms\n");

        System.out.println("Application terminated.");
    }
}
