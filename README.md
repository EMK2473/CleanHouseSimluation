# House Organizing Program


## Table of Contents

1. [How to Run the Program](#how-to-run-the-program)
2. [Initial Setup](#initial-setup)
3. [Overview](#overview)
4. [Code Explanation](#code-explanation)
5. [Contributing](#contributing)
6. [License](#license)


## How to Run the Program

1. **Clone or Download the Project**:
   - Clone this repository using Git:
     ```bash
     git clone https://github.com/EMK2473/CleanHouseSimluation.git
     ```
   - Or, download the ZIP file and extract it.

2. **Compile the Java Program**:
   - Open a terminal and navigate to the directory containing the Java files.
   - Compile the Java file:
     ```bash
     javac Clean.java
     ```

3. **Run the Program**:
   - Execute the program:
     ```bash
     java Clean
     ```

   The program will prompt you to interact with the rooms and organize the items.

## Overview

The **House Organizing Program** is a console-based application that helps the user organize and manage items spread across different rooms in a house. The house consists of various rooms with items that can be moved or stored in designated locations based on user inputs. The living room is designated as the **check room**, where items can be saved or moved, and the **Downstairs Master Bedroom** is the **storage room** where items are stored for future use.


### Initial Setup

- **Rooms**: The house consists of 10 rooms, including:
  - **Living Room (Check Room)**: Used for checking items, either saving or moving them to the storage room.
  - **Downstairs Master Bedroom (Storage Room)**: Used to store items.
  - Other rooms: Kitchen, Dining Room, Hallway Closet, etc.

- **Items**: Items such as musical instruments, furniture, and trash are randomly seeded into rooms at the start of the program.

### User Interaction

The program prompts the user to select a room, view the items in the room, and choose an action. Available actions are:

- **Store an item**: Moves an item to the storage room.
- **Move an item to the check room**: Moves an item from any room to the living room.
- **Remove an item** (only in the check room): Permanently removes an item from the house.
- **Return to room selection**: Exit the current room and go back to room selection.

The user can continue to interact with the program until they choose to exit.


## Code Explanation

1. **Item**:
   - Represents an item with a name.
   - `getName()`: Returns the name of the item.

2. **Room**:
   - Represents a room in the house.
   - `addItem(Item item)`: Adds an item to the room.
   - `removeItem(Item item)`: Removes an item from the room.
   - `displayItems()`: Displays the list of items in the room.
   - `getItemCount()`: Returns the number of items in the room.

3. **House**:
   - Represents the entire house, consisting of multiple rooms.
   - `seedItems()`: Randomly assigns items to each room.
   - `getRooms()`, `getStorageRoom()`, `getCheckRoom()`: Methods to access rooms.
   - `setStorageRoom(Room newStorageRoom)`: Changes the storage room (if applicable).

4. **Clean**:
   - The main class that runs the program.
   - Handles user input, room selection, and item management.
   - Continually loops until the user decides to exit.


## Contributing

For Questions, contact me at emk2473@gmail.com or visit My Github: [EMK2473](https://github.com/EMK2473)

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
