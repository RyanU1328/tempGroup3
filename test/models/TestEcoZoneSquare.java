package models;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

class TestEcoZoneSquare {

    @Test
    public void testGetName() {
        // Arrange
        EcoZoneSquare ecoZoneSquare = new EcoZoneSquare("Eco Zone");

        // Assert
        assertEquals("Eco Zone", ecoZoneSquare.getName());
    }

    @Test
    public void testLandOn() {
        // Arrange
        Player player = new Player("TestPlayer");
        EcoZoneSquare ecoZoneSquare = new EcoZoneSquare("ECO Zone");

        // Redirect System.out to capture printed output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));
        List<Player> players = new ArrayList<>();

        // Act
        ecoZoneSquare.landOn(player, players, new Scanner(System.in), new Board());
        // Reset System.out
        System.setOut(originalOut);

        // Assert
        String expectedOutput = "TestPlayer landed on the ECO Zone. Chill there for a sec!"
                + System.getProperty("line.separator");

        String actualOutput = outContent.toString();

        assertEquals(expectedOutput, actualOutput);

        // Reset System.out
        System.setOut(System.out);
    }
}
