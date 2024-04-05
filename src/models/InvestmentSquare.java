package models;

import java.util.Scanner;

public class InvestmentSquare extends Square {
    private Player owner;
    private int investmentCost;
    private int fee; // set in the constructor, based on the investment cost
    private int minorUpgrade;
    private boolean majorUpgrade;
    private int minorUpgradeCost; // set in the constructor, based on the investment cost
    private int majorUpgradeCost; // set in the constructor, based on the investment cost

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
    public InvestmentSquare(String name, int investmentCost) {
        super(name);
        this.investmentCost = investmentCost;
        this.fee = investmentCost / 2;
        this.minorUpgradeCost = investmentCost / 2;
        this.majorUpgradeCost = (investmentCost / 2) * 3;
        this.owner = null;
        if (this.fee < 1) {
            this.fee = 1;
        }
    }

    public boolean isOwned() {
        return owner != null;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public int getInvestmentCost() {
        return investmentCost;
    }

    /**
     * Returns the fee based on the below forumulas
     * 
     * @return fee
     */
    public int getFee() {
        if (!majorUpgrade) {
            return fee + ((int) (1.5 * (investmentCost * minorUpgrade)));
        } else {
            return fee + ((int) (((1.5 * (investmentCost * minorUpgrade)) * 5)));
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
     */
    public void setMinorUpgrade() {
        if (minorUpgrade < 3) {
            this.minorUpgrade++;
        } else {
            throw new IllegalCallerException();
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

    @Override
    public void landOn(Player player, Scanner scanner) {
        if (!this.isOwned()) {
            System.out.println("Do you want to invest in " + this.getName() + "? It costs " + this.getInvestmentCost()
                    + " resources. (yes/no)");
            String input = scanner.nextLine().trim().toLowerCase();
            if ("yes".equals(input)) {
                if (player.getMoney() >= this.getInvestmentCost()) {
                    player.deductResources("money", this.getInvestmentCost());
                    this.setOwner(player);
                    System.out.println("Investment successful. You now own " + this.getName());
                } else {
                    System.out.println("Not enough resources to invest.");
                }
            }
        } else if (this.getOwner() != player) {
            System.out.println("This area is owned by " + this.getOwner().getName() + ". Paying fees.");
            if (player.getMoney() >= this.getFee()) {
                player.deductResources("money", this.getFee());
                this.getOwner().addResources("money", this.getFee());
                System.out.println("Paid " + this.getFee() + " resources to " + this.getOwner().getName());
            } else {
                System.out.println("Not enough resources to pay the fee.");
            }
        }
    }
}
