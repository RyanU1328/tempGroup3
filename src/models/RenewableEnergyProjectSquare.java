package models;

public class RenewableEnergyProjectSquare extends InvestmentSquare {
    private int energyProductionBonus;

    public RenewableEnergyProjectSquare(String name, int investmentCost, int fee, int energyProductionBonus) {
        super(name, investmentCost, fee);
        this.energyProductionBonus = energyProductionBonus;
    }

    @Override
    public void landOn(Player player) {
        super.landOn(player);
        if (isOwned() && getOwner().equals(player)) {
            System.out.println(player.getName() + " benefits from their investment in " + getName() + ", gaining an energy production bonus.");
        }
    }
}
