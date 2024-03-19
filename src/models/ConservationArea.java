package models;

public class ConservationArea extends Square {
    private int contributionCost;
    private int benefit; 

    public ConservationArea(String name, int contributionCost, int benefit) {
        super(name);
        this.contributionCost = contributionCost;
        this.benefit = benefit;
    }

    @Override
    public void landOn(Player player) {
        System.out.println(player.getName() + " has landed on a Conservation Area. Contributing resources for future benefits.");
        if (player.getResources() >= contributionCost) {
            player.deductResources(contributionCost);
        } else {
            System.out.println("Not enough resources to contribute.");
        }
    }
}
