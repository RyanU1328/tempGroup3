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
    public void landOn(Player player) {
        // Default implementation, may be overridden by subclasses
    }

    public void performAction(Player player, Scanner scanner) {
        // Default calls landOn for backward compatibility
        this.landOn(player); // This ensures existing squares without input needs still work
    }
}

