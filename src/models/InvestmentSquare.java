package models;

import java.util.Scanner;

public class InvestmentSquare extends Square {
    private Player owner;
    private int investmentCost;
    private int fee;

    public InvestmentSquare(String name, int investmentCost, int fee) {
        super(name);
        this.investmentCost = investmentCost;
        this.fee = fee;
        this.owner = null;
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

    public int getFee() {
        return fee;
    }

    @Override
    public void landOn(Player player, Scanner scanner) {
        if (!this.isOwned()) {
            System.out.println("Do you want to invest in " + this.getName() + "? It costs " + this.getInvestmentCost() + " resources. (yes/no)");
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
