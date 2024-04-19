package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import models.Board;
import models.Player;
import models.Square;
import utils.ConsoleUI;

public class GameController {
  private Player[] players;
  private boolean gameRunning = false; // Initially, the game has not started
  private Board board = new Board();
  private Scanner scanner = new Scanner(System.in);
  private ArrayList<String> nameList = new ArrayList<>();
  private String resouceRelativePath = "/src/resources/";

  public void startGame() throws IOException {
    FileHandler.printFileContents(resouceRelativePath + "asciititle.txt");
    System.out.println("\n\n");

    boolean validChoice = false;
    while (!validChoice) {
      // Display options for the player
      System.out.println("Press 'i' to view instructions or 'p' to start the game.");
      String choice = scanner.nextLine().trim().toLowerCase();

      if ("i".equals(choice)) {
        displayInstructions();
        validChoice = true; // Set validChoice to true to exit the loop
      } else if ("p".equals(choice)) {
        gameRunning = true; // Mark the game as started
        System.out.println("Starting the game...");
        initializePlayers();
        int currentPlayerIndex = determineStartingPlayer();
        System.out.println(players[currentPlayerIndex].getName() + " starts the game.");
        // Continue with the game loop
        gameLoop(currentPlayerIndex);
        validChoice = true; // Set validChoice to true to exit the loop
      } else {
        System.out.println("Invalid choice. Please enter 'i' to view instructions or 'p' to start the game.");
        // validChoice remains false, so the loop will continue
      }
      // Consume any extra characters left in the buffer
      scanner.nextLine();

    }
  }

  private void gameLoop(int currentPlayerIndex) throws IOException {
    while (gameRunning) {
      boolean turnCompleted = false;
      Player currentPlayer = players[currentPlayerIndex];

      while (!turnCompleted) {
        System.out.println("\n" +
            currentPlayer.getName()
            + "'s turn. Press 'r' to roll the dice , 's' to show resources, or 'i' to view instructions.\n");
        String action = scanner.nextLine().trim().toLowerCase();

        if ("s".equals(action)) {
          System.out.println(
              "\n" + currentPlayer.getName() + "'s resources: \nMoney: " + currentPlayer.getMoney()
                  + "\nCarbon Debt: " + currentPlayer.getCarbonDebt() + "\n");
          currentPlayer.getProperties().forEach(property -> {
            System.out.println(property.getName());
          });
          // Continue in the loop
        } else if ("r".equals(action)) {
          turnCompleted = playerTurn(currentPlayer);
        } else if ("i".equals(action)) {
          displayInstructions();
          // After returning from instructions, continue in the loop

        } else {
          System.out.println(
              "Invalid input. Please press 'r' to roll the dice or 's' to show resources, or 'i' to view instructions.");
        }
      }

      // Advance to the next player only after the turn is completed.
      if (turnCompleted) {
        currentPlayerIndex = (currentPlayerIndex + 1) % players.length;
      }

      // Check for any player achieving Net zero - will check all players rather than
      // player on the current turn
      for (Player player : players) {
        if (player.getCarbonDebt() <= 0) {
          System.out.println(player.getName() + " has won the game by negating all of their carbon debt!!");
          gameRunning = false;
          break;
        }
      }
    }
    endGame();
    scanner.close();

  }

  private void displayInstructions() throws IOException {

    while (true) {
      System.out.println("\nChoose an option:");
      System.out.println("1. Game Objective");
      System.out.println("2. Game Setup");
      System.out.println("3. Gameplay");
      System.out.println("4. Purchasing and Development");
      System.out.println("5. Units");
      System.out.println("6. Upgrading");
      System.out.println("7. Fines");

      if (gameRunning) {
        System.out.println("8. Return to Game");
      } else {
        System.out.println("8. Return to Main Menu");
      }

      int choice = scanner.nextInt();
      scanner.nextLine(); // Consume newline

      switch (choice) {
        case 1:
          FileHandler.printFileContents(resouceRelativePath + "GameObjective.txt");
          break;
        case 2:
          FileHandler
              .printFileContents(resouceRelativePath + "GameSetup.txt");
          break;
        case 3:
          FileHandler
              .printFileContents(resouceRelativePath + "Gameplay.txt");
          break;
        case 4:
          FileHandler
              .printFileContents(resouceRelativePath + "PurchasingAndDevelopment.txt");
          break;
        case 5:
          FileHandler
              .printFileContents(resouceRelativePath + "Units.txt");
          break;
        case 6:
          FileHandler
              .printFileContents(resouceRelativePath + "Upgrading.txt");
          break;
        case 7:
          FileHandler
              .printFileContents(resouceRelativePath + "Fines.txt");
          break;
        case 8:
          if (gameRunning) {
            return;
            // Return to the game
          } else {
            startGame(); // Exit to main menu
          }
          break;
        default:
          System.out.println("Invalid choice.");
      }
    }
  }

  /**
   * Method to initialise players for the game. Includes defining number of
   * players,
   * each player defining a name, and selecting an avatar.
   * 
   * @throws IOException
   */

  private void initializePlayers() throws IOException {
    {
      int playerCount = ConsoleUI.promptForPlayerCount();
      players = new Player[playerCount];
      List<String[]> avatarList = new ArrayList<>();
      for (String j : FileHandler
          .listFilesInDir(System.getProperty("user.dir") + resouceRelativePath, "avatar")) {
        avatarList
            .add(FileHandler
                .getFileContents(resouceRelativePath + j));
      }
      for (int i = 0; i < playerCount; i++) {
        String name = ConsoleUI.promptForPlayerName(nameList, i);
        nameList.add(name);
        players[i] = new Player(name);
        players[i].setAvatar(ConsoleUI.promptForPlayerAvatar(avatarList, i));
        avatarList.remove(players[i].getAvatar());
      }
    }
  }

  private void endGame() {
    System.out.println("Game over. Final resources:");

    // Array to store ratings temporarily rather than add a new field to the player
    // class
    int[] ratings = new int[players.length];

    for (int i = 0; i < players.length; i++) {
      Player player = players[i];
      System.out.println(
          player.getName() + ": \nMoney: " + player.getMoney() + "\nCarbon Debt: " + player.getCarbonDebt());

      // Prompt the player to rate their game experience
      System.out.print("Please rate your game experience (1-5): ");

      // Prompt the player to rate their game experience, ensures its between 1 and 5
      int rating;
      while (true) {
        System.out.print("Please rate your game experience (1-5): ");
        try {
          rating = scanner.nextInt();
          if (rating >= 1 && rating <= 5) {
            break;
          } else {
            System.out.println("Please enter a number between 1 and 5.");
          }
        } catch (Exception e) {
          System.out.println("Please enter a valid number.");
          scanner.next(); // Clear the invalid input
        }
      }

      ratings[i] = rating;
    }

    // Print out ratings
    System.out.println("\nPlayer Ratings:");
    for (int i = 0; i < players.length; i++) {
      Player player = players[i];
      int rating = ratings[i];
      System.out.println(player.getName() + ": " + rating);
    }
  }

  private boolean playerTurn(Player player) {
    int[] diceRoll = rollDice(2);

    System.out.println(player.getName() + " rolled a " + diceRoll[0] + " and a " + diceRoll[1] + ", moves "
        + (diceRoll[0] + diceRoll[1]) + " spaces.");
    Square currentSquare = movePlayerAndGetSquare(player, diceRoll[0] + diceRoll[1]);
    System.out.println(player.getName() + " has landed on " + currentSquare.getName());
    handleSquareActions(player, currentSquare, Arrays.asList(players), scanner, board); // Add 'scanner' as the third
                                                                                        // argument

    if (diceRoll[0] == diceRoll[1]) {
      System.out.println("Doubles! " + player.getName() + " gets another turn.");
      return false; // Turn not completed due to doubles.
    }
    return true; // Turn completed.
  }



  /**
   * Rolls a specified number of dice and returns the result.
   *
   * @param numDice the number of dice to roll.
   * @return an array of integers representing the outcome of each die roll.
   */
  private int[] rollDice(int numDice) {
    Random rand = new Random();
    int[] rolls = new int[numDice];
    for (int i = 0; i < numDice; i++) {
      rolls[i] = rand.nextInt(4) + 1;
    }
    return rolls;
  }

  private int determineStartingPlayer() {
    ArrayList<Integer> playersWithHighestRolls = new ArrayList<>();
    int highestRoll = 0;

    // Initial Roll
    for (int i = 0; i < players.length; i++) {
      System.out.println(players[i].getName() + ", press 'r' to roll the dice.");
      String input = scanner.nextLine();
      if ("r".equalsIgnoreCase(input.trim())) {
        System.out.println("Rolling dice...");
        int roll = rollDice(1)[0];

        System.out.println(players[i].getName() + " rolled a " + roll);

        if (roll > highestRoll) {
          highestRoll = roll;
          playersWithHighestRolls.clear();
          playersWithHighestRolls.add(i);
        } else if (roll == highestRoll) {
          playersWithHighestRolls.add(i);
        }
      }
    }

    // Handle Ties
    while (playersWithHighestRolls.size() > 1) {
      System.out.println("Tie detected. Tied players will roll again.");
      highestRoll = 0;
      ArrayList<Integer> newTieBreakers = new ArrayList<>();

      for (Integer playerIndex : playersWithHighestRolls) {
        System.out.println(players[playerIndex].getName() + ", press 'r' to roll again.");
        String input = scanner.nextLine();
        if ("r".equalsIgnoreCase(input.trim())) {
          System.out.println("Rolling dice...");
          int roll = rollDice(1)[0];

          System.out.println(players[playerIndex].getName() + " re-rolled a " + roll);

          if (roll > highestRoll) {
            highestRoll = roll;
            newTieBreakers.clear();
            newTieBreakers.add(playerIndex);
          } else if (roll == highestRoll) {
            newTieBreakers.add(playerIndex);
          }
        }
      }

      playersWithHighestRolls = new ArrayList<>(newTieBreakers);
    }

    return playersWithHighestRolls.isEmpty() ? -1 : playersWithHighestRolls.get(0);
  }

  private Square movePlayerAndGetSquare(Player player, int roll) {
    int oldPosition = player.getPosition();
    int newPosition = (oldPosition + roll) % board.getSize();
    player.setPosition(newPosition);

    if (newPosition < oldPosition && newPosition != 0) {
      System.out.println(
          player.getName() + " passed Go! Gaining 50 resources and reducing their carbon debt by 10!");
      player.addResources("money", 50);
      player.addResources("carbonDebt", -10);
    }

    return board.getSquare(newPosition);
  }

  private void handleSquareActions(Player player, Square square, List<Player> players, Scanner scanner, Board board) {
    square.landOn(player, players, scanner, board);
  }


}
