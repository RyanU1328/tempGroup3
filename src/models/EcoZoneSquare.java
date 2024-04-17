package models;

import java.util.List;
import java.util.Scanner;

public class EcoZoneSquare extends Square {

    public EcoZoneSquare(String name) {
        super(name);
    }

    @Override
    public void landOn(Player player, List<Player> players, Scanner scanner, Board board) {
        System.out.println("Chill there for a sec :)");
    }
}
