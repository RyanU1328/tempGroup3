package models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import org.hamcrest.core.IsInstanceOf;

public class InvestmentSquare extends Square {
    private Player owner;
    private int investmentCost;
    private int fee; // set in the constructor, based on the investment cost
    private int minorUpgrade;
    private boolean majorUpgrade;
    private int minorUpgradeCost; // set in the constructor, based on the investment cost
    private int majorUpgradeCost; // set in the constructor, based on the investment cost
    private int field;

    /**
     * The `InvestmentSquare` class in Java is a subclass of the `Square` class
     * and represents a square on
     * a game board that players can invest in. The constructor `public
     * InvestmentSquare(String name, int
     * investmentCost)` initializes an `InvestmentSquare` object with a given
     * name and investment cost. The
     * constructor sets the investment cost, calculates the fee based on half of
     * the investment cost, and
     * sets other initial values such as owner to null.
     * 
     * @param name
     * @param investmentCost
     */
    public InvestmentSquare(String name, int investmentCost, int fieldNumber) {
        super(name);
        setInvestmentCost(investmentCost);
        setFee();
        setMinorUpgradeCost();
        setMajorUpgradeCost();
        setOwner(null);
        setField(fieldNumber);
    }

    /**
     * Sets the Investment Cost of the square, has checks to prevent setting the
     * fee, and unit costs wrong
     * 
     * @param investmentCost the investmentCost to set
     * @throws IllegalArgumentException if investment cost is negative
     */
    public void setInvestmentCost(int investmentCost) {
        if (investmentCost > 0) {
            this.investmentCost = investmentCost;
        } else {
            throw new IllegalArgumentException("Investment cost cannot be negative.");
        }
    }

    /**
     * @return the field
     */
    public int getField() {
        return field;
    }

    /**
     * @param field the field to set
     */
    public void setField(int field) {
        this.field = field;
    }

    /**
     * Sets the fee, which is half of the investment cost
     */
    public void setFee() {
        this.fee = ((getInvestmentCost() / 2) > 0) ? (getInvestmentCost() / 2) : 1;
    }

    /**
     * Sets the minor upgrade cost, which is the same as the fee
     */
    public void setMinorUpgradeCost() {
        this.minorUpgradeCost = getFee();
    }

    /**
     * Sets major cost, based off of minor upgrade cost
     */
    public void setMajorUpgradeCost() {
        this.majorUpgradeCost = getMinorUpgradeCost() * 3;
    }

    /**
     * 
     * @return boolean true if the square is owned, false if it is not.
     */
    public boolean isOwned() {
        return owner != null;
    }

    /**
     * @return owner
     */
    public Player getOwner() {
        return owner;
    }

    /**
     * @param owner default state null, as set by constructor
     */
    public void setOwner(Player owner) {
        this.owner = owner;
        if (owner != null) {
            owner.addProperty(this.getName());
        }

    }

    /**
     * @return investmentCost this is set explicitly in the constructor
     */
    public int getInvestmentCost() {
        return investmentCost;
    }

    /**
     * Returns the fee based on the below formulas
     * 
     * @return fee
     */
    public int getFee() {
        if (!majorUpgrade) {
            return fee + ((int) (1.5 * (investmentCost * minorUpgrade)));
        } else {
            return (fee + ((int) (1.5 * (investmentCost * minorUpgrade))) * 5);
        }
    }

    /**
     * The max this will be is 3, once there the player should be offered the
     * opportunity to buy a major upgrade
     * 
     * @return the minorUpgrade
     */
    public int getMinorUpgrade() {
        return minorUpgrade;
    }

    /**
     * This iterates the minor unit upgrade count, to a max of 3.
     * 
     * @throws IllegalCallerException
     */
    public void setMinorUpgrade() {
        if (minorUpgrade < 3) {
            this.minorUpgrade++;
        } else {
            throw new IllegalCallerException("Minor upgrade count is already at its maximum.");
        }
    }

    /**
     * @return the majorUpgrade
     */
    public boolean isMajorUpgrade() {
        return majorUpgrade;
    }

    /**
     * @param majorUpgrade the majorUpgrade to set
     */
    public void setMajorUpgrade() {
        this.majorUpgrade = true;
    }

    /**
     * @return the minorUpgradeCost
     */
    public int getMinorUpgradeCost() {
        return minorUpgradeCost;
    }

    /**
     * @return the majorUpgradeCost
     */
    public int getMajorUpgradeCost() {
        return majorUpgradeCost;
    }

    /**
     * This method handles what happens to a user when they land on the specific
     * square
     * 
     * @param player  Player that lands on the specific square
     * @param scanner Handles user input for choices related to square actions
     */
    @Override
    public void landOn(Player player, List<Player> players, Scanner scanner, Board board) {
        if (isOwned() && !owner.equals(player)) {
            System.out.println("This area is owned by " + this.getOwner().getName() + ". Paying fees.");

            while (true) {
                boolean payByMoney = player.choosePaymentMethod(scanner);
                if (payByMoney) {
                    if (player.getMoney() >= this.getFee()) {
                        player.deductResources("money", this.getFee());
                        this.getOwner().addResources("money", this.getFee());
                        System.out.println("Paid " + this.getFee() + " money to " + this.getOwner().getName()
                                + ". Remaining balance: " + player.getMoney());
                        break;
                    } else {
                        System.out.println("Not enough money to pay the fee. Trying carbon debt.");
                    }
                } else {
                    if (owner.getCarbonDebt() >= this.getFee()) {
                        owner.deductResources("carbonDebt", this.getFee());
                        player.addResources("carbonDebt", this.getFee());
                        System.out.println(player.getName() + " paid a fee of " + this.getFee() + " carbon debt to " +
                                this.getOwner().getName() + ". Remaining carbon debt: " + player.getCarbonDebt());
                        break;
                    } else {
                        System.out.println(
                                "Not enough carbon debt to pay the fee. Please choose another payment method.");
                    }
                }
            }
        } else if (!this.isOwned()) {
            System.out.println("Do you want to invest in " + this.getName() + "? It costs " + this.getInvestmentCost() +
                    " pounds. (yes/no)");
            while (true) {
                String input = scanner.nextLine().trim().toLowerCase();
                try {
                    if (!input.equals("yes") && !input.equals("no")) {
                        throw new InputMismatchException("Please enter 'yes' or 'no'.");
                    }
                    if ("yes".equals(input)) {
                        if (player.getMoney() >= this.getInvestmentCost()) {
                            player.deductResources("money", this.getInvestmentCost());
                            this.setOwner(player);
                            System.out.println("Investment successful. You now own " + this.getName() +
                                    ". Remaining balance: " + player.getMoney());
                        } else {
                            System.out.println("Not enough resources to invest.");
                        }
                    } else {
                        System.out.println("Offering to next player...");
                        int currentPlayerIndex = players.indexOf(player);
                        int nextPlayerIndex = (currentPlayerIndex + 1) % players.size();
                        for (int i = 0; i < players.size(); i++) {
                            int index = (nextPlayerIndex + i) % players.size();
                            Player nextPlayer = players.get(index);
                            if (!nextPlayer.equals(player)) {
                                System.out.println(
                                        nextPlayer.getName() + ", would you like to buy " + this.getName()
                                                + "? (yes/no)");
                                String response = scanner.nextLine().trim().toLowerCase();
                                if ("yes".equals(response)) {
                                    if (nextPlayer.getMoney() >= this.getInvestmentCost()) {
                                        nextPlayer.deductResources("money", this.getInvestmentCost());
                                        this.setOwner(nextPlayer);
                                        System.out.println("Investment successful. " + nextPlayer.getName()
                                                + " now owns " +
                                                this.getName() + ". Remaining balance: " + nextPlayer.getMoney());
                                        return;
                                    } else {
                                        System.out.println(
                                                nextPlayer.getName() + " does not have enough resources to buy " +
                                                        this.getName());
                                    }
                                } else {
                                    System.out.println(nextPlayer.getName() + " declined to buy " + this.getName());
                                }
                            }
                        }
                        System.out.println(
                                "No player chose to buy " + this.getName() + ". Moving to the next player's turn.");
                    }
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input: " + e.getMessage());
                }
            }
        } else if (this.isOwned() && this.owner == player) {
            List<String> fieldList = new ArrayList<>();
            List<String> playerProperties = player.getProperties();
            if (playerProperties.size() > 1) {
                fieldList.add(getName());
                for (Square square : board.getSquares()) {
                    if (square instanceof InvestmentSquare && !square.getName().equals(this.getName())) {
                        InvestmentSquare compareSquare = (InvestmentSquare) square;
                        if (compareSquare.getField() == this.field && fieldList.size() < 4) {
                            fieldList.add(compareSquare.getName());
                        }
                    }
                }
                Collections.sort(playerProperties);
                Collections.sort(fieldList);
                if (playerProperties.equals(fieldList)) {
                    if (!this.majorUpgrade && this.getMinorUpgrade() < 3) {
                        System.out.println("It is possible to upgrade this property.");
                        if (this.getMinorUpgrade() < 3) {
                            System.out.println("Would you like to buy a minor upgrade? It would cost: "
                                    + this.getMinorUpgradeCost());
                            String input = scanner.nextLine().trim().toLowerCase();
                            if (input.contains("y") && player.getMoney() > this.getMinorUpgradeCost()) {
                                player.deductResources("money", this.getMinorUpgradeCost());
                                this.setMinorUpgrade();
                            } else {
                                System.out
                                        .println((player.getMoney() < this.getMinorUpgradeCost() && input.contains("n"))
                                                ? "No upgrade purchased"
                                                : "No upgrade purchased, player doesn't have enough money");
                            }
                        }
                    } else if (!this.isMajorUpgrade()) {
                        System.out.println(
                                "Would you like to buy a major upgrade? It will cost: " + this.getMajorUpgradeCost());
                        String input = scanner.nextLine().trim().toLowerCase();
                        if (input.contains("y") && player.getMoney() > this.getMajorUpgradeCost()) {
                            player.deductResources("money", this.getMajorUpgradeCost());
                            this.setMajorUpgrade();
                        } else {
                            System.out.println((player.getMoney() < this.getMajorUpgradeCost() && input.contains("n"))
                                    ? "No upgrade purchased"
                                    : "No upgrade purchased, player doesn't have enough money");

                        }
                    }
                } else {
                    System.out.println(
                            "For the user to purchase upgrades on this property you need to own all the properties listed below:\n");
                    for (String i : fieldList) {
                        System.out.println(i);
                    }
                }
            }
        } else if (this.isOwned() && this.owner == player) {
            List<String> fieldList = new ArrayList<>();
            List<String> playerProperties = player.getProperties();
            if (playerProperties.size() > 1) {
                fieldList.add(getName());
                for (Square square : board.getSquares()) {
                    if (square instanceof InvestmentSquare && !square.getName().equals(this.getName())) {
                        InvestmentSquare compareSquare = (InvestmentSquare) square;
                        if (compareSquare.getField() == this.field && fieldList.size() < 4) {
                            fieldList.add(compareSquare.getName());
                        }
                    }
                }
                Collections.sort(playerProperties);
                Collections.sort(fieldList);
                if (playerProperties.equals(fieldList)) {
                    if (!this.majorUpgrade && this.getMinorUpgrade() < 3) {
                        System.out.println("It is possible to upgrade this property.");
                        if (this.getMinorUpgrade() < 3) {
                            System.out.println("Would you like to buy a minor upgrade? It would cost: "
                                    + this.getMinorUpgradeCost());
                            String input = scanner.nextLine().trim().toLowerCase();
                            if (input.contains("y") && player.getMoney() > this.getMinorUpgradeCost()) {
                                player.deductResources("money", this.getMinorUpgradeCost());
                                this.setMinorUpgrade();
                            } else {
                                System.out
                                        .println((player.getMoney() < this.getMinorUpgradeCost() && input.contains("n"))
                                                ? "No upgrade purchased"
                                                : "No upgrade purchased, player doesn't have enough money");
                            }
                        }
                    } else if (!this.isMajorUpgrade()) {
                        System.out.println(
                                "Would you like to buy a major upgrade? It will cost: " + this.getMajorUpgradeCost());
                        String input = scanner.nextLine().trim().toLowerCase();
                        if (input.contains("y") && player.getMoney() > this.getMajorUpgradeCost()) {
                            player.deductResources("money", this.getMajorUpgradeCost());
                            this.setMajorUpgrade();
                        } else {
                            System.out.println((player.getMoney() < this.getMajorUpgradeCost() && input.contains("n"))
                                    ? "No upgrade purchased"
                                    : "No upgrade purchased, player doesn't have enough money");

                        }
                    }
                } else {
                    System.out.println(
                            "For the user to purchase upgrades on this property you need to own all the properties listed below:\n");
                    for (String i : fieldList) {
                        System.out.println(i);
                    }
                }
            }
        }
    }
}
