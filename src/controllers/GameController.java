package controllers;

import models.*;
import utils.ConsoleUI;
import java.util.Random;
import java.util.Scanner;

public class GameController {
    private Player[] players;
    private boolean gameRunning = true;
    private Board board = new Board();
    private Scanner scanner = new Scanner(System.in); // Keep only this scanner instance

    public void startGame() {
        initializePlayers();
        int currentPlayerIndex = 0;

        while (gameRunning) {
            Player currentPlayer = players[currentPlayerIndex];
            System.out.println(currentPlayer.getName() + "'s turn. Your resources: " + currentPlayer.getResources());

            System.out.println("Do you want to roll the dice? (y/n)");
            String input = scanner.nextLine();
            if ("y".equalsIgnoreCase(input.trim())) {
                playerTurn(currentPlayer);
            } else { 
                System.out.println("Skipping turn...");
            }

            System.out.println("Continue playing? (y/n)");
            input = scanner.nextLine();
            if ("n".equalsIgnoreCase(input.trim())) {
                gameRunning = false;
            }

            currentPlayerIndex = (currentPlayerIndex + 1) % players.length;
        }
        
        endGame();
        scanner.close(); // Properly close scanner here
    }


    private void initializePlayers() {
        int playerCount = ConsoleUI.promptForPlayerCount();
        players = new Player[playerCount];
        for (int i = 0; i < playerCount; i++) {
            String name = ConsoleUI.promptForPlayerName(i);
            players[i] = new Player(name);
        }
    }

    private void endGame() {
        System.out.println("Game over. Final resources:");
        for (Player player : players) {
            System.out.println(player.getName() + ": " + player.getResources());
        }
    }
    
    public void playerTurn(Player player) {
        System.out.println(player.getName() + "'s turn. Your resources: " + player.getResources());
        
        int roll = rollDice();
        System.out.println(player.getName() + " rolled a " + roll);
        
        Square currentSquare = movePlayerAndGetSquare(player, roll);
        System.out.println(player.getName() + " has landed on " + currentSquare.getName());
        
        handleSquareActions(player, currentSquare);
    }

    private int rollDice() {
        Random rand = new Random();
        return rand.nextInt(6) + 1 + rand.nextInt(6) + 1;
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
            // Handle other square types appropriately
            square.performAction(player, scanner); // Now correctly handles squares needing input
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
