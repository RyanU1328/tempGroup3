package utils;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ConsoleUI {
    private static Scanner scanner;

    public static int promptForPlayerCount() {
        int count;
        scanner = new Scanner(System.in);
        do {
            System.out.println("Enter the number of players (1-4):");
            while (!scanner.hasNextInt()) {
                System.out.println("That's not a valid number. Enter the number of players (1-4):");
                scanner.next();
            }
            count = scanner.nextInt();
            if (count < 1 || count > 4) {
                System.out.println("Please enter a number between 1 and 4.");
            }
        } while (count < 1 || count > 4);
        return count;
    }
    return name;
  }

    public static String promptForPlayerName(ArrayList<String> nameList, int playerIndex) {
        scanner = new Scanner(System.in);
        boolean check = false;
        String name = "";
        while (!check) {
            System.out.printf("Enter name for Player %d:%n", playerIndex + 1);
            name = scanner.next();
            if (!nameList.contains(name)) {
                check = true;
            } else {
                System.out.println("The name you have input has already been used, please input another name");

            }
        }
        return name;
    }
    System.out.println("\nPlease enter the number of the avatar you wish to select:\n");
    int selection = scanner.nextInt();

    public static String promptForPlayerAvatar(List<String> avatarList, int playerIndex) {
        scanner = new Scanner(System.in);
        if (avatarList.isEmpty()) {
            System.out.println("No avatars available.");
            return null;
        }
        System.out.println("\nFrom the below list please select an avatar to represent you:");
        for (int i = 0; i < avatarList.size(); i++) {
            System.out.println("\n\t" + (i + 1) + "\n");
            System.out.println(avatarList.get(i));
        }
        System.out.println("\nPlease enter the number of the avatar you wish to select:\n");
        try {
            int selection = scanner.nextInt();
            if (selection < 1 || selection > avatarList.size()) {
                throw new InputMismatchException(); // Invalid selection
            }
            return avatarList.get(selection - 1);
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid number.");
            scanner.next(); // Consume invalid input
            return null; // Return null for invalid input
        }
    }
}
