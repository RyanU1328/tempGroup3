package controllers;

import models.*;
import utils.ConsoleUI;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class GameController {
    private Player[] players;
    private boolean gameRunning = true;
    private Board board = new Board();
    private Scanner scanner = new Scanner(System.in);
    private ArrayList<String> nameList = new ArrayList<>();

    public void startGame() {
        initializePlayers();
        int currentPlayerIndex = determineStartingPlayer();
        System.out.println(players[currentPlayerIndex].getName() + " starts the game.");

        while (gameRunning) {
            boolean turnCompleted = false;
            Player currentPlayer = players[currentPlayerIndex];

            while (!turnCompleted) {
                System.out.println(currentPlayer.getName() + "'s turn. Press 'r' to roll the dice and 's' to show resources.");
                String action = scanner.nextLine().trim().toLowerCase();

                if ("s".equals(action)) {
                    System.out.println(currentPlayer.getName() + "'s resources: " + currentPlayer.getResources());
                    // Continue in the loop, allowing the player to also press 'r' to roll the dice.
                } else if ("r".equals(action)) {
                    turnCompleted = playerTurn(currentPlayer); // This method now returns true if the turn is completed.
                } else {
                    System.out.println("Invalid input. Please press 'r' to roll the dice or 's' to show resources.");
                }
            }

            // Advance to the next player only after the turn is completed.
            if (turnCompleted) {
                currentPlayerIndex = (currentPlayerIndex + 1) % players.length;
            }

            // Check for a game-ending condition
            if (currentPlayer.getResources() <= 0) {
                System.out.println(currentPlayer.getName() + " has won the game by running out of resources!");
                gameRunning = false;
            }
        }

        endGame();
        scanner.close();
    }


    private void initializePlayers() {
        int playerCount = ConsoleUI.promptForPlayerCount();
        players = new Player[playerCount];
        for (int i = 0; i < playerCount; i++) {
            String name = ConsoleUI.promptForPlayerName(nameList, i);
            nameList.add(name);
            players[i] = new Player(name);
        }
    }

    private void endGame() {
        System.out.println("Game over. Final resources:");
        for (Player player : players) {
            System.out.println(player.getName() + ": " + player.getResources());
        }
    }
    
    private boolean playerTurn(Player player) {
        int[] diceRoll = rollDice();
        System.out.println(player.getName() + " rolled a " + diceRoll[0] + " and a " + diceRoll[1] + ", moves " + (diceRoll[0] + diceRoll[1]) + " spaces.");
        Square currentSquare = movePlayerAndGetSquare(player, diceRoll[0] + diceRoll[1]);
        System.out.println(player.getName() + " has landed on " + currentSquare.getName());
        handleSquareActions(player, currentSquare);

        if (diceRoll[0] == diceRoll[1]) {
            System.out.println("Doubles! " + player.getName() + " gets another turn.");
            return false; // Turn not completed due to doubles.
        }
        return true; // Turn completed.
    }


    private boolean handleRollAndActions(Player player) {
        int[] diceRoll = rollDice();
        System.out.println(player.getName() + " rolled a " + diceRoll[0] + " and a " + diceRoll[1] + ", moves " + (diceRoll[0] + diceRoll[1]) + " spaces.");
        Square currentSquare = movePlayerAndGetSquare(player, diceRoll[0] + diceRoll[1]);
        System.out.println(player.getName() + " has landed on " + currentSquare.getName());
        handleSquareActions(player, currentSquare);

        // If no doubles are rolled, return true to end the turn.
        return diceRoll[0] != diceRoll[1];
    }

    private int[] rollDice() {
        Random rand = new Random();
        int die1 = rand.nextInt(6) + 1;
        int die2 = rand.nextInt(6) + 1;
        return new int[]{die1, die2};
    }

    
    private int rollSingleDice() {
        Random rand = new Random();
        return rand.nextInt(6) + 1;
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
                int roll = rollSingleDice();
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
                    int roll = rollSingleDice();
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
        int newPosition = (player.getPosition() + roll) % board.getSize();
        player.setPosition(newPosition);
        return board.getSquare(newPosition);
    }

    private void handleSquareActions(Player player, Square square) {
        if (square instanceof ResourceSquare) {
            ResourceSquare resourceSquare = (ResourceSquare) square;
            System.out.println("This is a resource square. Collecting resources.");
            player.addResources(resourceSquare.collectResources());
            System.out.println("Your new balance: " + player.getResources());
        } else if (square instanceof InvestmentSquare) {
            handleInvestmentSquare(player, (InvestmentSquare) square);
        } else {
            square.performAction(player, scanner);
        }
    }

    private void handleResourceSquare(Player player, ResourceSquare square) {
        System.out.println("This is a resource square. Collecting resources.");
        player.addResources(square.collectResources());
        System.out.println("Your new balance: " + player.getResources());
    }

    private void handleInvestmentSquare(Player player, InvestmentSquare square) {
        if (!square.isOwned()) {
        } else if (square.getOwner() != player) {
            System.out.println("This area is owned by " + square.getOwner().getName() + ". Paying fees.");
            if (player.getResources() >= square.getFee()) {
                player.deductResources(square.getFee());
                square.getOwner().addResources(square.getFee());
                System.out.println("Paid " + square.getFee() + " resources to " + square.getOwner().getName());
            } else {
                System.out.println("Not enough resources to pay the fee.");
            }
        }
    }

    
    
}
