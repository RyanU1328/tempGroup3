package models;

import java.util.Scanner;

public abstract class Square {
    private String name;

    public Square(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    // Make this method abstract to enforce implementation in all subclasses
    // Also, add a Scanner parameter to allow subclasses to interact with the player
    public abstract void landOn(Player player, Scanner scanner);
}
