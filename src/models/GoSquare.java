package models;

import java.util.List;
import java.util.Scanner;

public class GoSquare extends Square {

    public GoSquare(String name) {
        super(name);
    }

    @Override
    public void landOn(Player player, List<Player> players, Scanner scanner, Board board) {
        System.out.println(player.getName() + " has gained 10 money and reduced their carbon debt by 10!");
        player.addResources("money", 10);
        player.deductResources("carbonDebt", 10);
    }

}
