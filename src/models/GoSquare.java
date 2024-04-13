package models;

import java.util.List;
import java.util.Scanner;

public class GoSquare extends Square {

    public GoSquare(String name) {
        super(name);
    }

    @Override
    public void landOn(Player player, List<Player> players, Scanner scanner) {
        System.out.println(player.getName() + " has gained 50 money and reduced their carbon debt by 10!");
        player.addResources("money", 50);
        player.addResources("carbonDebt", -10);
    }
    
}
