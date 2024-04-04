package models;

import java.util.Scanner;

public class EcoZoneSquare extends Square {

    public EcoZoneSquare(String name) {
        super(name);
    }

    @Override
    public void landOn(Player player, Scanner scanner) {
        System.out.println(player.getName() + " landed on the ECO Zone. Chill there for a sec!");
    }
}
