import java.util.Scanner;

class Guest {
    String name;
    int age;
    String gender;

    public Guest(String name, int age, String gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }
}

class Room {
    int roomNumber;
    boolean isOccupied;
    Guest guest;

    public Room(int roomNumber) {
        this.roomNumber = roomNumber;
        isOccupied = false;
        guest = null;
    }

    public void checkIn(Guest guest) {
        this.guest = guest;
        isOccupied = true;
    }

    public void checkOut() {
        this.guest = null;
        isOccupied = false;
    }
}

public class HotelManagementSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Room[] rooms = new Room[10]; // Assuming 10 rooms in the hotel

        for (int i = 0; i < rooms.length; i++) {
            rooms[i] = new Room(i + 1);
        }

        while (true) {
            System.out.println("1. Check-In");
            System.out.println("2. Check-Out");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter guest name: ");
                    scanner.nextLine(); // Consume newline
                    String name = scanner.nextLine();
                    System.out.print("Enter guest age: ");
                    int age = scanner.nextInt();
                    System.out.print("Enter guest gender: ");
                    scanner.nextLine(); // Consume newline
                    String gender = scanner.nextLine();
                    Guest guest = new Guest(name, age, gender);

                    // Check for available rooms
                    boolean roomFound = false;
                    for (Room room : rooms) {
                        if (!room.isOccupied) {
                            room.checkIn(guest);
                            System.out.println("Guest checked in to room " + room.roomNumber);
                            roomFound = true;
                            break;
                        }
                    }
                    if (!roomFound) {
                        System.out.println("No available rooms.");
                    }
                    break;

                case 2:
                    System.out.print("Enter room number to check-out: ");
                    int roomNumber = scanner.nextInt();
                    if (roomNumber >= 1 && roomNumber <= rooms.length) {
                        Room selectedRoom = rooms[roomNumber - 1];
                        if (selectedRoom.isOccupied) {
                            selectedRoom.checkOut();
                            System.out.println("Guest checked out from room " + selectedRoom.roomNumber);
                        } else {
                            System.out.println("Room is not occupied.");
                        }
                    } else {
                        System.out.println("Invalid room number.");
                    }
                    break;

                case 3:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}