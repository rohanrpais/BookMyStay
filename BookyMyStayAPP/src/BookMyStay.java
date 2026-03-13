// Version 5.1 - Booking Request Queue (First-Come-First-Served)

import java.util.LinkedList;
import java.util.Queue;

// Reservation represents a guest's intent to book
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

// BookingRequestQueue manages incoming requests in FIFO order
class BookingRequestQueue {
    private Queue<Reservation> requestQueue;

    public BookingRequestQueue() {
        requestQueue = new LinkedList<>();
    }

    // Accept a new booking request
    public void addRequest(Reservation reservation) {
        requestQueue.add(reservation);
        System.out.println("Request added: " + reservation);
    }

    // View all queued requests (without processing)
    public void displayQueue() {
        System.out.println("\nCurrent Booking Request Queue:");
        if (requestQueue.isEmpty()) {
            System.out.println("No requests in queue.");
        } else {
            for (Reservation r : requestQueue) {
                System.out.println(r);
            }
        }
    }
}

public class BookMyStay {
    public static void main(String[] args) {
        // Initialize booking request queue
        BookingRequestQueue queue = new BookingRequestQueue();

        // Guests submit booking requests
        queue.addRequest(new Reservation("Alice", "Single Room"));
        queue.addRequest(new Reservation("Bob", "Double Room"));
        queue.addRequest(new Reservation("Charlie", "Suite Room"));
        queue.addRequest(new Reservation("Diana", "Single Room"));

        // Display queued requests (FIFO order preserved)
        queue.displayQueue();

        System.out.println("\nApplication terminated.");
    }
}
