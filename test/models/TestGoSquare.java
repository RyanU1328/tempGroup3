package models;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

class TestGoSquare {

    @Test

    public void testGetName() {
        // Arrange
        GoSquare goSquare = new GoSquare("Go");
    @Test

    public void testGetName() {
        // Arrange
        GoSquare goSquare = new GoSquare("Go");

        // Assert
        assertEquals("Go", goSquare.getName());
    }

    @Test
        // Assert
        assertEquals("Go", goSquare.getName());
    }

    @Test
    public void testLandOn() {
        // Arrange
        Player player = new Player("TestPlayer");
        GoSquare goSquare = new GoSquare("Go");

        // Redirect System.out to capture printed output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Act
        goSquare.landOn(player, new Scanner(System.in));

        // Assert
        // Assert
        assertEquals(550, player.getMoney()); // Initial money is 500 + 50 gained
        assertEquals(490, player.getCarbonDebt()); // Initial carbon debt is 500 - 10 reduced
        assertEquals("TestPlayer has gained 50 money and reduced their carbon debt by 10!\n", outContent.toString());
        assertEquals(
                "TestPlayer has gained 50 money and reduced their carbon debt by 10!"
                        + System.getProperty("line.separator"),
                outContent.toString());

        // Reset System.out
        System.setOut(System.out);
    }

    @Test

    @Test
    public void testAddResourcesInvalid() {
        // Arrange
        Player player = new Player("TestPlayer");
        // GoSquare goSquare = new GoSquare("Go");
        // GoSquare goSquare = new GoSquare("Go");

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            player.addResources("money", 0); // Attempt to add 0 money
        });

        assertThrows(IllegalArgumentException.class, () -> {
            player.addResources("carbonDebt", 0); // Attempt to add 0 carbon debt
        });
    }
}
