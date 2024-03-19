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
        player.deductResources(penalty);
        System.out.println(player.getName() + " lost " + penalty + " resources due to pollution.");
    }
}
