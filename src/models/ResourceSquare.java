package models;

public class ResourceSquare extends Square {
    private int resourceBonus;

    public ResourceSquare(String name, int resourceBonus) {
        super(name);
        this.resourceBonus = resourceBonus;
    }

    @Override
    public void landOn(Player player) {
        player.addResources(resourceBonus);
        System.out.println(player.getName() + " landed on " + getName() + " and collected " + resourceBonus + " resources.");
    }
    
    public int collectResources() {
        return resourceBonus;
    }
}

