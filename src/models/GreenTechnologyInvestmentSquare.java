package models;

public class GreenTechnologyInvestmentSquare extends InvestmentSquare {
    private int bonusReturn;

    public GreenTechnologyInvestmentSquare(String name, int investmentCost, int fee, int bonusReturn) {
        super(name, investmentCost, fee);
        this.bonusReturn = bonusReturn;
    }

    @Override
    public void landOn(Player player) {
        super.landOn(player);
        if (isOwned() && getOwner().equals(player)) {
            System.out.println(player.getName() + " receives a bonus return from " + getName()
                    + " for investing in green technology.");
            player.addResources("money", bonusReturn);
        }
    }
}
