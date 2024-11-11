import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

class Item {
    String name;

    public Item(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Room {
    String name;
    List<Item> items;

    public Room(String name) {
        this.name = name;
        this.items = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Item> getItems() {
        return items;
    }

    public void addItem(Item item) {
        this.items.add(item);
    }

    public void removeItem(Item item) {
        this.items.remove(item);
    }

    public void displayItems() {
        if (items.isEmpty()) {
            System.out.println("No items in this room.");
        } else {
            for (int i = 0; i < items.size(); i++) {
                System.out.println((i + 1) + ". " + items.get(i).getName());
            }
        }
    }

    public int getItemCount() {
        return items.size();
    }
}

class House {
    List<Room> rooms;
    Room storageRoom;  // Downstairs Master Bedroom will be storage
    Room checkRoom;    // Living Room will be check room

    public House() {
        rooms = new ArrayList<>();
        // Create rooms in the house
        rooms.add(new Room("Living Room"));  
        rooms.add(new Room("Room attached to Living Room"));
        rooms.add(new Room("Dining Room"));
        rooms.add(new Room("Kitchen"));
        rooms.add(new Room("Room attached to Kitchen"));
        rooms.add(new Room("Hallway Closet"));
        rooms.add(new Room("Bathroom"));
        rooms.add(new Room("Downstairs Master Bedroom")); 
        rooms.add(new Room("Downstairs Spare Bedroom"));
        rooms.add(new Room("Upstairs Room"));

        storageRoom = rooms.get(7);  // Set Downstairs Master Bedroom as storage
        checkRoom = rooms.get(0);    // Set Living Room as check room
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public Room getStorageRoom() {
        return storageRoom;
    }

    public Room getCheckRoom() {
        return checkRoom;
    }

    public void setStorageRoom(Room newStorageRoom) {
        if (this.storageRoom != newStorageRoom) {
            System.out.println("Storage room changed from '" + storageRoom.getName() + "' to '" + newStorageRoom.getName() + "'.");
            storageRoom = newStorageRoom;
        }
    }

    public void seedItems() {
        String[] itemList = {
            "Acoustic guitar", "Electric guitar", "Bass guitar", "Keyboard", 
            "Cables", "Audio mixers", "Recordings", "Table", "Chairs", 
            "Bed", "Dresser", "Sofa", "Cabinet", "Trash"
        };

        Random rand = new Random();

        // Randomly seed items into each room
        for (Room room : rooms) {
            int numItems = rand.nextInt(itemList.length); // Random number of items to assign to the room
            for (int i = 0; i < 3; i++) {
                String itemName = itemList[rand.nextInt(itemList.length)];
                room.addItem(new Item(itemName));
            }
        }
    }
}

public class Clean {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        House house = new House();
        house.seedItems(); // Seed items into the rooms

        // Display how to use the program
        System.out.println("Welcome to the House Organizing Program!");
        System.out.println("The living room is the designated check room.");
        System.out.println("The Downstairs Master Bedroom is the designated storage room.");
        System.out.println("You can only remove items from the house if they are placed in the living room.");
        System.out.println("Otherwise, you can go into any room and either store its items or move them to the living room.");
        System.out.println("Items in the living room can be either saved (removed from the house) or stored in the storage room.");
        System.out.println("Let's get started!\n");

        while (true) {
            // Display the storage room information
            Room currentStorageRoom = house.getStorageRoom();
            System.out.println("\nCurrent storage room: " + currentStorageRoom.getName());
            System.out.println("Number of items in storage room: " + currentStorageRoom.getItemCount());

            // Display the list of rooms
            System.out.println("\nSelect a room to view its contents:");
            for (int i = 0; i < house.getRooms().size(); i++) {
                System.out.println((i + 1) + ". " + house.getRooms().get(i).getName());
            }
            System.out.println((house.getRooms().size() + 1) + ". Exit");

            int roomChoice = scanner.nextInt();
            if (roomChoice == house.getRooms().size() + 1) {
                break; // Exit the program
            }

            Room selectedRoom = house.getRooms().get(roomChoice - 1);

            while (true) {  // Keep the user in the selected room
                System.out.println("\nContents of " + selectedRoom.getName() + ":");
                selectedRoom.displayItems();

                if (!selectedRoom.getItems().isEmpty()) {
                    // Display action options
                    System.out.println("\nWhat would you like to do?");
                    if (selectedRoom == house.getCheckRoom()) {
                        // In the check room, allow the user to Save or Store items
                        System.out.println("1. Remove an item (remove it from the house)");
                        System.out.println("2. Store an item (move it to storage room)");
                    } else {
                        // In any other room, allow the user to Store or Move an item
                        System.out.println("1. Store an item (move it to storage room)");
                        System.out.println("2. Move an item to the check room");
                    }
                    System.out.println("3. Return to room selection");

                    int actionChoice = scanner.nextInt();
                    if (actionChoice == 1) {
                        // Store or Save action
                        if (selectedRoom == house.getCheckRoom()) {
                            // Save item in check room
                            System.out.println("Select an item to save:");
                            int itemChoice = scanner.nextInt() - 1;
                            if (itemChoice >= 0 && itemChoice < selectedRoom.getItems().size()) {
                                Item selectedItem = selectedRoom.getItems().get(itemChoice);
                                selectedRoom.removeItem(selectedItem);
                                System.out.println("Item '" + selectedItem.getName() + "' removed from the house.");
                            } else {
                                System.out.println("Invalid item choice.");
                            }
                        } else {
                            // Store item in storage room
                            System.out.println("Select an item to store:");
                            int itemChoice = scanner.nextInt() - 1;
                            if (itemChoice >= 0 && itemChoice < selectedRoom.getItems().size()) {
                                Item selectedItem = selectedRoom.getItems().get(itemChoice);
                                selectedRoom.removeItem(selectedItem);
                                house.getStorageRoom().addItem(selectedItem);
                                System.out.println("Item '" + selectedItem.getName() + "' stored in " + house.getStorageRoom().getName());
                            } else {
                                System.out.println("Invalid item choice.");
                            }
                        }
                    } else if (actionChoice == 2) {
                        // Move item to check room
                        if (selectedRoom != house.getCheckRoom()) {
                            System.out.println("Select an item to move to the check room:");
                            int itemChoice = scanner.nextInt() - 1;
                            if (itemChoice >= 0 && itemChoice < selectedRoom.getItems().size()) {
                                Item selectedItem = selectedRoom.getItems().get(itemChoice);
                                selectedRoom.removeItem(selectedItem);
                                house.getCheckRoom().addItem(selectedItem);
                                System.out.println("Item '" + selectedItem.getName() + "' moved to " + house.getCheckRoom().getName());
                            } else {
                                System.out.println("Invalid item choice.");
                            }
                        } else {
                            // Store item in check room (Living Room)
                            System.out.println("Select an item to store (move it to storage room):");
                            int itemChoice = scanner.nextInt() - 1;
                            if (itemChoice >= 0 && itemChoice < selectedRoom.getItems().size()) {
                                Item selectedItem = selectedRoom.getItems().get(itemChoice);
                                selectedRoom.removeItem(selectedItem);
                                house.getStorageRoom().addItem(selectedItem);
                                System.out.println("Item '" + selectedItem.getName() + "' stored in " + house.getStorageRoom().getName());
                            } else {
                                System.out.println("Invalid item choice.");
                            }
                        }
                    } else if (actionChoice == 3) {
                        break; // Return to room selection
                    }
                } else {
                    System.out.println("No items in this room. Returning to room selection.");
                    break; // Go back to room selection if there are no items in the room
                }
            }
        }
        scanner.close();
    }
}
