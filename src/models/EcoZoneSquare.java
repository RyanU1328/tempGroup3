package models;

public class EcoZoneSquare extends Square {

    public EcoZoneSquare(String name) {
        super(name);
    }

    @Override
    public void landOn(Player player) {
 
        System.out.println(player.getName() + " landed on the ECO Zone. Chill there for a sec!");
    }
}
