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

    public static String[] promptForPlayerAvatar(List<String[]> avatarList, int playerIndex) {
        scanner = new Scanner(System.in);
        int selection;
        System.out.println("\nFrom the below list please select an avatar to represent you:");
        for (int i = 0; i < avatarList.size(); i++) {
            System.out.println("\n\t" + (i + 1) + "\n");
            for (String line : avatarList.get(i)) {
                System.out.println(line);
            }
        }
        System.out.println("\nPlease enter the number of the avatar you wish to select:\n");
        while (true) {
            try {
                selection = scanner.nextInt();
                if (selection < 1 || selection > avatarList.size()) {
                    System.out.println("Invalid input, please select a valid avatar number:");
                } else {
                    return avatarList.get(selection - 1);
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input, try again:");
                scanner.next(); 
            }
        }
    }
    
    
    }


