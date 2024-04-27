import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static ArrayList<User> userList = new ArrayList<>();
    private static ArrayList<Item> itemList = new ArrayList<>();
    private static ArrayList<Transaction> transactionList = new ArrayList<>();
    private static int userIdCounter = 1;
    private static int itemIdCounter = 1;
    private static int transactionIdCounter = 1;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Menu:");
            System.out.println("1. Add User");
            System.out.println("2. Add Item");
            System.out.println("3. Borrow Item");
            System.out.println("4. Return Item");
            System.out.println("5. View User Data");
            System.out.println("6. View Item Data");
            System.out.println("7. Save Data to File");
            System.out.println("8. Exit");
            System.out.print("Select option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Clear newline character

            switch (choice) {
                case 1:
                    addUser(scanner);
                    break;
                case 2:
                    addItem(scanner);
                    break;
                case 3:
                    borrowItem(scanner);
                    break;
                case 4:
                    returnItem(scanner);
                    break;
                case 5:
                    viewUserData();
                    break;
                case 6:
                    viewItemData();
                    break;
                case 7:
                    saveDataToFile();
                    break;
                case 8:
                    System.out.println("Exiting...");
                    System.exit(0);
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void addUser(Scanner scanner) {
        System.out.println("Choose user type:");
        System.out.println("1. Student");
        System.out.println("2. Lecturer");
        System.out.print("Select user type: ");
        int userType = scanner.nextInt();
        scanner.nextLine(); // Clear newline character

        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Address: ");
        String address = scanner.nextLine();
        System.out.print("Enter Phone Number: ");
        String phoneNumber = scanner.nextLine();
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();

        User user;
        if (userType == 1) {
            user = new User(userIdCounter++, "Student", name, address, phoneNumber, email);
        } else if (userType == 2) {
            user = new User(userIdCounter++, "Lecturer", name, address, phoneNumber, email);
        } else {
            System.out.println("Invalid user type.");
            return;
        }

        userList.add(user);
        System.out.println("User data added successfully.");
    }

    private static void addItem(Scanner scanner) {
        System.out.println("Choose item type:");
        System.out.println("1. Book");
        System.out.println("2. CD");
        System.out.println("3. Magazine");
        System.out.print("Select item type: ");
        int itemType = scanner.nextInt();
        scanner.nextLine(); // Clear newline character

        System.out.print("Enter Title: ");
        String title = scanner.nextLine();

        Item item;
        if (itemType == 1) {
            item = new Item(itemIdCounter++, "Book", title);
        } else if (itemType == 2) {
            item = new Item(itemIdCounter++, "CD", title);
        } else if (itemType == 3) {
            item = new Item(itemIdCounter++, "Magazine", title);
        } else {
            System.out.println("Invalid item type.");
            return;
        }

        itemList.add(item);
        System.out.println("Item added successfully.");
    }

    private static void borrowItem(Scanner scanner) {
        System.out.print("Enter User ID: ");
        int userId = scanner.nextInt();
        scanner.nextLine(); // Clear newline character

        System.out.print("Enter Item ID: ");
        int itemId = scanner.nextInt();
        scanner.nextLine(); // Clear newline character

        User user = findUserById(userId);
        Item item = findItemById(itemId);

        if (user == null) {
            System.out.println("User not found.");
            return;
        }

        if (item == null) {
            System.out.println("Item not found.");
            return;
        }

        if (item.isBorrowed()) {
            System.out.println("Item is already borrowed.");
            return;
        }

        user.borrowItem(item);
        item.setBorrowed(true);
        System.out.println("Item borrowed successfully.");

        // Record transaction
        Transaction transaction = new Transaction(transactionIdCounter++, userId, itemId);
        transactionList.add(transaction);
    }

    private static void returnItem(Scanner scanner) {
        System.out.print("Enter Item ID: ");
        int itemId = scanner.nextInt();
        scanner.nextLine(); // Clear newline character

        Item item = findItemById(itemId);

        if (item == null) {
            System.out.println("Item not found.");
            return;
        }

        item.setBorrowed(false);
        System.out.println("Item returned successfully.");
    }

    private static void viewUserData() {
        if (userList.isEmpty()) {
            System.out.println("No user data available.");
            return;
        }

        System.out.println("+----+-----------+-------------------+-----------------+-----------------+-------------------+");
        System.out.println("| ID |   Type    |       Name        |     Address     |   Phone Number |       Email       |");
        System.out.println("+----+-----------+-------------------+-----------------+-----------------+-------------------+");
        for (User user : userList) {
            System.out.printf("| %-2d | %-9s | %-17s | %-15s | %-15s | %-17s |\n",
                    user.getId(),
                    user.getType(),
                    user.getName(),
                    user.getAddress(),
                    user.getPhoneNumber(),
                    user.getEmail());
        }
        System.out.println("+----+-----------+-------------------+-----------------+-----------------+-------------------+");
    }

    private static void viewItemData() {
        if (itemList.isEmpty()) {
            System.out.println("No item data available.");
            return;
        }

        System.out.println("+----+-----------+-------------------+");
        System.out.println("| ID |   Type    |       Title       |");
        System.out.println("+----+-----------+-------------------+");
        for (Item item : itemList) {
            System.out.printf("| %-2d | %-9s | %-17s |\n",
                    item.getId(),
                    item.getType(),
                    item.getTitle());
        }
        System.out.println("+----+-----------+-------------------+");
    }

    private static void saveDataToFile() {
        if (userList.isEmpty()) {
            System.out.println("No user data available to save.");
            return;
        }

        if (itemList.isEmpty()) {
            System.out.println("No item data available to save.");
            return;
        }

        try {
            FileWriter userDataWriter = new FileWriter("user_data.txt");
            userDataWriter.write("+----+-----------+-------------------+-----------------+-----------------+-------------------+\n");
            userDataWriter.write("| ID |   Type    |       Name        |     Address     |   Phone Number |       Email       |\n");
            userDataWriter.write("+----+-----------+-------------------+-----------------+-----------------+-------------------+\n");

            for (User user : userList) {
                userDataWriter.write(String.format("| %-2d | %-9s | %-17s | %-15s | %-15s | %-17s |\n",
                        user.getId(),
                        user.getType(),
                        user.getName(),
                        user.getAddress(),
                        user.getPhoneNumber(),
                        user.getEmail()));
            }

            userDataWriter.write("+----+-----------+-------------------+-----------------+-----------------+-------------------+\n");
            userDataWriter.close();
            System.out.println("User data saved to file: user_data.txt");

            FileWriter transactionDataWriter = new FileWriter("transaction_data.txt");
            transactionDataWriter.write("+----+---------+---------+\n");
            transactionDataWriter.write("| ID | User ID | Item ID |\n");
            transactionDataWriter.write("+----+---------+---------+\n");

            for (Transaction transaction : transactionList) {
                transactionDataWriter.write(String.format("| %-2d | %-7d | %-7d |\n",
                        transaction.getId(),
                        transaction.getUserId(),
                        transaction.getItemId()));
            }

            transactionDataWriter.write("+----+---------+---------+\n");
            transactionDataWriter.close();
            System.out.println("Transaction data saved to file: transaction_data.txt");
        } catch (IOException e) {
            System.out.println("Error writing to file.");
            e.printStackTrace();
        }
    }

    private static User findUserById(int id) {
        for (User user : userList) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    private static Item findItemById(int id) {
        for (Item item : itemList) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }
}