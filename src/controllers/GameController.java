package controllers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import models.Board;
import models.InvestmentSquare;
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

    // Display options for the player
    System.out.println("Press 'i' to view instructions or 'p' to start the game.");
    String choice = scanner.nextLine().trim().toLowerCase();

    if ("i".equals(choice)) {
      displayInstructions();
    } else if ("p".equals(choice)) {
      gameRunning = true; // Mark the game as started
      System.out.println("Starting the game...");
      initializePlayers();
      int currentPlayerIndex = determineStartingPlayer();
      System.out.println(players[currentPlayerIndex].getName() + " starts the game.");
      // Continue with the game loop
      gameLoop(currentPlayerIndex);
    } else {
      System.out.println("Invalid choice. Please enter 'i' to view instructions or 'p' to start the game.");
      startGame(); // Restart the game if the choice is invalid
    }
  }

  private void gameLoop(int currentPlayerIndex) throws IOException {
    while (gameRunning) {
      boolean turnCompleted = false;
      Player currentPlayer = players[currentPlayerIndex];

      while (!turnCompleted) {
        System.out.println(
            currentPlayer.getName()
                + "'s turn. Press 'r' to roll the dice , 's' to show resources, or 'i' to view instructions.\n");
        String action = scanner.nextLine().trim().toLowerCase();

        if ("s".equals(action)) {
          System.out.println(
              "\n" + currentPlayer.getName() + "'s resources: \nMoney: " + currentPlayer.getMoney()
                  + "\nCarbon Debt: " + currentPlayer.getCarbonDebt() + "\n");
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

      // Check for a game-ending condition
      if (currentPlayer.getCarbonDebt() <= 0) {
        System.out.println(currentPlayer.getName() + " has won the game by negating all of their!");
        gameRunning = false;
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
    for (Player player : players) {
      System.out.println(
          player.getName() + ": \nMoney: " + player.getMoney() + "\nCarbon Debt: " + player.getCarbonDebt());
    }
  }

  private boolean playerTurn(Player player) {
    int[] diceRoll = rollDice(2);

    System.out.println(player.getName() + " rolled a " + diceRoll[0] + " and a " + diceRoll[1] + ", moves "
        + (diceRoll[0] + diceRoll[1]) + " spaces.");
    Square currentSquare = movePlayerAndGetSquare(player, diceRoll[0] + diceRoll[1]);
    System.out.println(player.getName() + " has landed on " + currentSquare.getName());
    handleSquareActions(player, currentSquare, Arrays.asList(players), scanner); // Add 'scanner' as the third argument

    if (diceRoll[0] == diceRoll[1]) {
      System.out.println("Doubles! " + player.getName() + " gets another turn.");
      return false; // Turn not completed due to doubles.
    }
    return true; // Turn completed.
  }

  private boolean handleRollAndActions(Player player) {
    int[] diceRoll = rollDice(2);
    System.out.println(player.getName() + " rolled a " + diceRoll[0] + " and a " + diceRoll[1] + ", moves "
        + (diceRoll[0] + diceRoll[1]) + " spaces.");
    Square currentSquare = movePlayerAndGetSquare(player, diceRoll[0] + diceRoll[1]);
    System.out.println(player.getName() + " has landed on " + currentSquare.getName());
    handleSquareActions(player, currentSquare, null, scanner); // Add 'scanner' as the third argument

    // If no doubles are rolled, return true to end the turn.
    return diceRoll[0] != diceRoll[1];
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

  private void handleSquareActions(Player player, Square square, List<Player> players, Scanner scanner) {
    square.landOn(player, players, scanner);
  }

  private void handleInvestmentSquare(Player player, InvestmentSquare square) {
    if (!square.isOwned()) {
    } else if (square.getOwner() != player) {
      System.out.println("This area is owned by " + square.getOwner().getName() + ". Paying fees.");
      if (player.getMoney() >= square.getFee()) {
        player.deductResources("money", square.getFee());
        square.getOwner().addResources("money", square.getFee());
        System.out.println("Paid " + square.getFee() + " resources to " + square.getOwner().getName());
      } else {
        System.out.println("Not enough resources to pay the fee.");
      }
    }
  }

}
