package models;

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
    public void landOn(Player player) {
        if (isOwned() && !owner.equals(player)) {
            player.deductResources("money", fee);
            owner.addResources("money", fee);
            System.out.println(player.getName() + " paid a fee of " + fee + " resources to " + owner.getName());
        }
    }
}
