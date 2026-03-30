import java.util.*;

class Reservation {
    private String guestName;
    private String roomType;
    class Service {
        private String serviceName;
        private double cost;

        public Reservation(String guestName, String roomType) {
            this.guestName = guestName;
            this.roomType = roomType;
    public Service(String serviceName, double cost) {
                this.serviceName = serviceName;
                this.cost = cost;
            }

            public String getGuestName() {
                return guestName;
                public String getServiceName() {
                    return serviceName;
                }

                public String getRoomType() {
                    return roomType;
                    public double getCost() {
                        return cost;
                    }
                }

                class RoomInventory {
                    private Map<String, Integer> availability = new HashMap<>();
                    class AddOnServiceManager {
                        private Map<String, List<Service>> servicesByReservation;

                        public RoomInventory() {
                            availability.put("Single", 5);
                            availability.put("Double", 3);
                            availability.put("Suite", 2);
    public AddOnServiceManager() {
                                servicesByReservation = new HashMap<>();
                            }

                            public int getAvailableRooms(String type) {
                                return availability.getOrDefault(type, 0);
                                public void addService(String reservationId, Service service) {
                                    servicesByReservation
                                            .computeIfAbsent(reservationId, k -> new ArrayList<>())
                                            .add(service);
                                }

                                public void reduceRoom(String type) {
                                    availability.put(type, availability.get(type) - 1);
                                }
                            }

                            class RoomAllocationService {
                                private Set<String> allocatedRoomIds = new HashSet<>();
                                private Map<String, Set<String>> assignedRoomsByType = new HashMap<>();
                                private Map<String, Integer> counters = new HashMap<>();

                                public RoomAllocationService() {
                                    assignedRoomsByType.put("Single", new HashSet<>());
                                    assignedRoomsByType.put("Double", new HashSet<>());
                                    assignedRoomsByType.put("Suite", new HashSet<>());
                                    public double calculateTotalServiceCost(String reservationId) {
                                        List<Service> services = servicesByReservation.get(reservationId);
                                        if (services == null) return 0.0;

                                        counters.put("Single", 0);
                                        counters.put("Double", 0);
                                        counters.put("Suite", 0);
                                    }

                                    public void allocateRoom(Reservation reservation, RoomInventory inventory) {
                                        String type = reservation.getRoomType();

                                        if (inventory.getAvailableRooms(type) <= 0) {
                                            System.out.println("No rooms available for " + type);
                                            return;
                                            double total = 0.0;
                                            for (Service s : services) {
                                                total += s.getCost();
                                            }

                                            String roomId = generateRoomId(type);

                                            allocatedRoomIds.add(roomId);
                                            assignedRoomsByType.get(type).add(roomId);
                                            inventory.reduceRoom(type);

                                            System.out.println("Booking confirmed for Guest: " + reservation.getGuestName() + ", Room ID: " + roomId);
                                        }

                                        private String generateRoomId(String roomType) {
                                            int count = counters.get(roomType) + 1;
                                            counters.put(roomType, count);
                                            return roomType + "-" + count;
                                            return total;
                                        }
                                    }

                                    public class UseCase6RoomAllocation {
                                        public class UseCase7AddOnServiceSelection {
                                            public static void main(String[] args) {

                                                System.out.println("Room Allocation Processing");
                                                System.out.println("Add-On Service Selection");

                                                String reservationId = "Single-1";

                                                AddOnServiceManager manager = new AddOnServiceManager();

                                                Service s1 = new Service("Breakfast", 500.0);
                                                Service s2 = new Service("Spa", 1000.0);

                                                RoomInventory inventory = new RoomInventory();
                                                RoomAllocationService service = new RoomAllocationService();
                                                manager.addService(reservationId, s1);
                                                manager.addService(reservationId, s2);

                                                Reservation r1 = new Reservation("Abhi", "Single");
                                                Reservation r2 = new Reservation("Subha", "Double");
                                                Reservation r3 = new Reservation("Vannathi", "Suite");
                                                double totalCost = manager.calculateTotalServiceCost(reservationId);

                                                service.allocateRoom(r1, inventory);
                                                service.allocateRoom(r2, inventory);
                                                service.allocateRoom(r3, inventory);
                                                System.out.println("Reservation ID: " + reservationId);
                                                System.out.println("Total Add-On Cost: " + totalCost);
                                            }
                                        }