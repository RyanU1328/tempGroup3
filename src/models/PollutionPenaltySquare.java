package models;

public class PollutionPenaltySquare extends Square {
    private int penalty;

    public PollutionPenaltySquare(String name, int penalty) {
        super(name);
        this.penalty = penalty;
    }

    @Override
    public void landOn(Player player) {
        System.out.println(player.getName() + " has landed on " + getName() + ". Facing pollution penalties.");
        player.addResources("carbonDebt", penalty);
        System.out.println(player.getName() + " gained " + penalty + " carbon debt due to pollution.");
    }
}
