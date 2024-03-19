package utils;

import java.util.Scanner;

public class ConsoleUI {
    private static final Scanner scanner = new Scanner(System.in);

    public static int promptForPlayerCount() {
        int count;
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

    public static String promptForPlayerName(int playerIndex) {
        System.out.printf("Enter name for Player %d:%n", playerIndex + 1);
        String name = scanner.next();
      
        return name;
    }
}
