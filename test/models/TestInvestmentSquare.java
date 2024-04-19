package models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Stream;

public class TestInvestmentSquare {

    InvestmentSquare investSquare;

    InvestmentSquare testSquare;
    private static Random rand = new Random();
    private static List<Arguments> nameNumberList;

    String investmentSquareName;
    int investmentCostValid;
    int investmentCostValidHigh;
    int investmentCostInvalid, investmentCostInvalidHigh;

    int SunnyAcresFeeValid, RadiantRidgeFeesValid, WindyMillsFeesValid, GustoGroveFeesValid, AiryHeightsFeesValid,
            SplashvilleFeesValid,
            TorrentialTerraceFeesValid, HydroHavenFeesValid, NucleoNestFeesValid, AtomicOasisFeesValid;
    int SunnyAcresInvalidLow, SunnyAcresInvalidHigh;

    @BeforeEach
    void setup() {
        investmentSquareName = "Sunny Acres";
        investmentCostValid = 1;
        investmentCostValidHigh = 10;
        investmentCostInvalid = 0;
        investmentCostInvalidHigh = -1;

        SunnyAcresFeeValid = 10;
        RadiantRidgeFeesValid = 15;
        WindyMillsFeesValid = 25;
        GustoGroveFeesValid = 30;
        AiryHeightsFeesValid = 35;
        SplashvilleFeesValid = 45;
        TorrentialTerraceFeesValid = 50;
        HydroHavenFeesValid = 55;
        NucleoNestFeesValid = 65;
        AtomicOasisFeesValid = 70;

        SunnyAcresInvalidLow = 9;
        SunnyAcresInvalidHigh = 11;

        investSquare = new InvestmentSquare(investmentSquareName, investmentCostValidHigh, 0);

    }

    @Test
    public void testInitialsation() {
        // Arrange
        // InvestmentSquare investSquare = new InvestmentSquare(investmentSquareName ,
        // investmentCostValidHigh);

        // Assert
        assertEquals(investmentSquareName, investSquare.getName());
        assertEquals(investmentCostValidHigh, investSquare.getInvestmentCost());

    }

    @Test
    public void testSetInvestmentCostValid() {
        // Arrange

        // InvestmentSquare investSquare = new InvestmentSquare(investmentSquareName ,
        // investmentCostValidHigh);

        // Act
        investSquare.setInvestmentCost(investmentCostValid);
        // Assert
        assertEquals(investmentCostValid, investSquare.getInvestmentCost());

        // Act
        investSquare.setInvestmentCost(investmentCostValidHigh);
        // Assert
        assertEquals(investmentCostValidHigh, investSquare.getInvestmentCost());

    }

    @Test
    public void testSetInvestmentCostInValid() {
        // Arrange
        // InvestmentSquare investSquare = new InvestmentSquare(investmentSquareName ,
        // investmentCostValidHigh);

        // Act & Assert

        assertThrows(IllegalArgumentException.class, () -> {
            investSquare.setInvestmentCost(investmentCostInvalid);

        });
        assertThrows(IllegalArgumentException.class, () -> {
            investSquare.setInvestmentCost(investmentCostInvalidHigh);
        });

    }

    @Test
    public void testSetFeesValidEachSquare() {
        // InvestmentSquare investSquare = new InvestmentSquare(investmentSquareName ,
        // investmentCostValidHigh);

        // Assert
        investSquare.setInvestmentCost(20);
        investSquare.setFee();
        assertEquals(SunnyAcresFeeValid, investSquare.getFee());

        investSquare.setInvestmentCost(30);
        investSquare.setFee();
        assertEquals(RadiantRidgeFeesValid, investSquare.getFee());

        investSquare.setInvestmentCost(50);
        investSquare.setFee();
        assertEquals(WindyMillsFeesValid, investSquare.getFee());

        investSquare.setInvestmentCost(60);
        investSquare.setFee();
        assertEquals(GustoGroveFeesValid, investSquare.getFee());

        investSquare.setInvestmentCost(70);
        investSquare.setFee();
        assertEquals(AiryHeightsFeesValid, investSquare.getFee());

        investSquare.setInvestmentCost(90);
        investSquare.setFee();
        assertEquals(SplashvilleFeesValid, investSquare.getFee());

        investSquare.setInvestmentCost(100);
        investSquare.setFee();
        assertEquals(TorrentialTerraceFeesValid, investSquare.getFee());

        investSquare.setInvestmentCost(110);
        investSquare.setFee();
        assertEquals(HydroHavenFeesValid, investSquare.getFee());

        investSquare.setInvestmentCost(130);
        investSquare.setFee();
        assertEquals(NucleoNestFeesValid, investSquare.getFee());

        investSquare.setInvestmentCost(140);
        investSquare.setFee();
        assertEquals(AtomicOasisFeesValid, investSquare.getFee());

    }

    @Test
    public void testSetMinorUpgradeIncrement() {

        investSquare.setMinorUpgrade();
        assertEquals(1, investSquare.getMinorUpgrade());
    }

    @Test
    public void testSetMinorUpgradeMaxValue() {

        investSquare.setMinorUpgrade(); // Increment once
        investSquare.setMinorUpgrade(); // Increment twice
        investSquare.setMinorUpgrade(); // Increment thrice, should throw exception
        assertThrows(IllegalCallerException.class, investSquare::setMinorUpgrade);
        assertEquals(3, investSquare.getMinorUpgrade());
    }

    @Test
    public void testSetMajorUpgrade() {
        investSquare.setMajorUpgrade();
        assertTrue(investSquare.isMajorUpgrade());
    }

    @Test
    public void testPlayerPaysFeeWhenLandingOnOwnedSquare() {
        Board board = new Board();
        Player player1 = new Player("Player 1");
        Player player2 = new Player("Player 2");
        InvestmentSquare square = (InvestmentSquare) board.getSquare(1); // Assuming Sunny Acres is the second square
        List<Player> players = new ArrayList<>();

        // Set square owner
        square.setOwner(player2);

        // Set player1's money
        player1.addResources("money", 100);

        // Mock user input to simulate player1 choosing to pay by money
        String input = "1" + System.getProperty("line.separator"); // Assuming player1 chooses to pay by money
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);

        // Simulate player1 landing on the square
        square.landOn(player1, players, scanner, board);

        // Assert that player1 pays the fee to player2
        assertEquals(340, player1.getMoney()); // Assuming fee is 20
        assertEquals(260, player2.getMoney()); // Player2 receives the fee
    }

    /*
     * Verify that when a player lands on an unowned square, they can choose to
     * invest in it.
     * Ensure that if the player has enough resources, they become the owner of the
     * square.
     * 
     */

    @Test
    public void testPlayerInvestsInUnownedSquare() {
        // Arrange
        Board board = new Board();
        Player player1 = new Player("Player 1");
        InvestmentSquare square = (InvestmentSquare) board.getSquare(1); // Assuming Sunny Acres is the second square
        // Set player1's money

        player1.addResources("money", square.getInvestmentCost() + 100); // Ensure player has enough money to invest
        int initialMoneyPlayer1 = player1.getMoney();

        // Set square owner to null (unowned)
        square.setOwner(null);

        // Mock user input to simulate player1 choosing to invest
        String input = "yes" + System.getProperty("line.separator"); // Assuming player1 chooses to invest
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);

        // Act: Simulate player1 landing on the square
        square.landOn(player1, null, scanner, null);

        // Assert: Player1 becomes the owner of the square
        assertEquals(player1, square.getOwner());
        assertEquals(initialMoneyPlayer1 - square.getInvestmentCost(), player1.getMoney());
    }

    /*
     * Verify that if a player lands on a square they already own, no action should
     * be taken,
     * and their resources should remain unchanged.
     */

    @Test
    public void testPlayerLandsOnOwnedSquareBySamePlayer() {
        // Arrange
        Board board = new Board();
        Player player1 = new Player("Player 1");
        InvestmentSquare square = (InvestmentSquare) board.getSquare(1); // Assuming Sunny Acres is the second square
        square.setOwner(player1);
        int initialMoneyPlayer1 = player1.getMoney();

        // Act: Simulate player1 landing on the square they already own
        square.landOn(player1, null, new Scanner(System.in), null);

        // Assert: Player1's resources should remain unchanged
        assertEquals(initialMoneyPlayer1, player1.getMoney());
    }

    @Test
    public void testPlayerPaysFeeWithCarbonDebt() {
        // Create a new board, player, and owner
        Board board = new Board();
        Player player = new Player("Player 1");
        Player owner = new Player("Owner");

        // Get the investment square from the board
        InvestmentSquare square = (InvestmentSquare) board.getSquare(1);

        // Set square owner
        square.setOwner(owner);

        // Set up owner's initial resources (500 money/ 500 carbon)
        owner.getMoney();
        owner.getCarbonDebt();

        // Set up player's initial resources (500 money/ 500 carbon )
        player.getMoney(); // Player has less money than the fee
        player.getCarbonDebt();

        // Mock user input to simulate player choosing to pay by money
        String input = "1" + System.getProperty("line.separator"); // Assuming player chooses to pay by money
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);

        // Simulate player landing on the square
        square.landOn(player, null, scanner, null);

        // Assert that player's money remains unchanged (expected: 10)
        assertEquals(240, player.getMoney());

        // Mock user input to simulate player choosing to pay by money
        input = "1" + System.getProperty("line.separator"); // Assuming player chooses to pay by money
        in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        // Assert that player's carbon debt is increased by the fee (expected:505?? )
        assertEquals(500, player.getCarbonDebt());

        // Assert that owner's money is increased by the fee (expected: 105)
        assertEquals(260, owner.getMoney());

        // Assert that owner's carbon debt is decreased by the fee (expected: 495)
        assertEquals(500, owner.getCarbonDebt());
    }

    /*
     * This test case verifies the behaviour when a player lands on a square they
     * already own.
     * 
     */
    @Test
    public void testPlayerOwnsSquare() {
        Board board = new Board();
        Player player = new Player("Player 1");
        InvestmentSquare square = (InvestmentSquare) board.getSquare(1); // Assuming Sunny Acres is the second square

        // Set square owner
        square.setOwner(player);

        player.getMoney();

        // Mock user input to simulate player choosing to do nothing
        String input = "no" + System.getProperty("line.separator"); // Player chooses not to invest
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        // Act: Simulate player1 landing on the square they already own
        square.landOn(player, null, new Scanner(System.in), null);

        // Assert that player's resources remain unchanged
        assertEquals(250, player.getMoney()); // Player's money remains unchanged
    }

    /*
     * This test case verifies the behaviour when a player lands on an unowned
     * square and chooses not to invest.
     */
    @Test
    public void testPlayerChoosesNotToInvest() {
        Board board = new Board();
        Player player1 = new Player("Player 1");
        Player player2 = new Player("Player 2"); // Add another player for testing

        InvestmentSquare square = (InvestmentSquare) board.getSquare(1); // Assuming Sunny Acres is the second square

        // Set up player1's resources
        player1.addResources("money", 100); // Player1 has enough money

        // Add players to the board
        ArrayList<Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);

        // Mock user input to simulate player2 declining to buy
        String input = "no\nno\n"; // Player2 declines to buy
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(in);
        // Scanner scanner = new Scanner(System.in);

        // Simulate player1 landing on the square
        square.landOn(player1, players, scanner, null);

        // Assert that ownership remains unchanged
        assertNull(square.getOwner()); // Square remains unowned
    }

    /*
     * This test case verifies the behaviour when a player lands on a neutral square
     * the ecoZone nothing changes in a players resources.
     */
    @Test
    public void testPlayerLandsOnEcoSquare() {
        Board board = new Board();
        Player player = new Player("Player 1");
        Square square = board.getSquare(6); // Assuming ecosquare nothing happens.

        // Set up player's resources
        player.getMoney(); // Player has enough money

        // Simulate player landing on the square
        square.landOn(player, null, null, null); // No scanner needed for neutral squares

        // Assert that player's resources remain unchanged
        assertEquals(250, player.getMoney()); // Player's money remains unchanged
    }

    // not doing right calculations!! need help
    @Test
    public void testPlayerPaysFeeWithInsufficientResources() {
        Board board = new Board();
        Player player = new Player("Player 1");
        Player owner = new Player("Owner");
        InvestmentSquare square = (InvestmentSquare) board.getSquare(1); // Assuming Sunny Acres is the second square

        // Set square owner
        square.setOwner(owner);

        // Set up owner's resources
        owner.getMoney(); // Owner has enough money
        owner.getCarbonDebt();

        // Set up player's resources
        player.setMoney(10); // Player doesn't have enough money to pay the fee
        player.setCarbonDebt(5); // Player initially has no carbon debt

        // Mock user input to simulate player choosing to pay with insufficient money
        String input = "1" + System.getProperty("line.separator"); // Player chooses to pay with money
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);

        // Simulate player landing on the square
        square.landOn(player, null, scanner, null);

        // Assert that player's money is deducted by the fee
        assertEquals(0, player.getMoney()); // Player's money becomes 0
        // Assert that player's carbon debt is increased by the fee
        assertEquals(5, player.getCarbonDebt()); // Player's carbon debt is unchanged by the fee
        // Assert that owner's money is increased by the fee
        assertEquals(260, owner.getMoney()); // Owner receives the fee in money
        // Assert that owner's carbon debt remains unchanged
        assertEquals(500, owner.getCarbonDebt()); // Owner's carbon debt remains unchanged
    }

    @Test
    public void testOfferSquareToNextPlayer_PlayerChoosesNotToBuy() {
        Board board = new Board();
        Player player1 = new Player("Player 1");
        Player player2 = new Player("Player 2");
        InvestmentSquare square = (InvestmentSquare) board.getSquare(1); // Assuming Sunny Acres is the second square
        List<Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);

        // Set up player 1's resources
        player1.addResources("money", 100); // Player 1 has enough money

        // Set up player 2's resources
        player2.addResources("money", 50); // Player 2 has insufficient money to buy the square

        // Create a ByteArrayOutputStream to capture the printed output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Mock user input to simulate player 1 choosing not to buy the square
        String input = "no" + System.getProperty("line.separator") + "no" + System.getProperty("line.separator");
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);

        // Simulate player 1 landing on the square(failing this line here)
        square.landOn(player1, players, scanner, null);

        // Get the printed output
        String printedOutput = outputStream.toString();

        // Assert that player 2 is offered to buy the square
        assertTrue(printedOutput.contains("Offering to next player..." + System.getProperty("line.separator")));
        assertTrue(printedOutput
                .contains(player2.getName() + ", would you like to buy"));
        assertTrue(printedOutput.contains("Player 2 declined to buy"));

        // Assert that the square remains unowned
        assertNull(square.getOwner());
    }

    @Test
    public void testPlayerUpgradesToMajor() {
        Board board = new Board();
        Player player = new Player("Player 1");
        InvestmentSquare square = (InvestmentSquare) board.getSquare(1);

        // Set up player's resources
        player.addResources("money", square.getInvestmentCost() * 3); // Ensure player has enough money for major
                                                                      // upgrade

        // Perform three minor upgrades
        for (int i = 0; i < 3; i++) {
            square.setMinorUpgrade();
        }

        // Ensure minor upgrade count is 3
        assertEquals(3, square.getMinorUpgrade());

        // Perform major upgrade
        square.setMajorUpgrade();

        // Assert that major upgrade is successful
        assertTrue(square.isMajorUpgrade());

    }

    // The below tests are parameterized random tests, this is used as a backup to
    // the edge-case testing done above
    @ParameterizedTest
    @MethodSource("utils.TestUtils#randomTestNumbersAndNames")
    public void testSetGetField(int num, String name) {
        testSquare = new InvestmentSquare(name, num, num);
        assertEquals(num, testSquare.getField());
    }

    @ParameterizedTest
    @MethodSource("utils.TestUtils#randomTestNumbersAndNames")
    public void testGetFee(int num, String name) {
        testSquare = new InvestmentSquare(name, num, 0);
        assertEquals(Integer.max(1, num / 2), testSquare.getFee());
    }

    @ParameterizedTest
    @MethodSource("utils.TestUtils#randomTestNumbersAndNames")
    public void testGetFeeWithUpgrades(int num, String name) {
        testSquare = new InvestmentSquare(name, num, num);
        int fee = (num / 2 > 0) ? num / 2 : 1;
        testSquare.setMinorUpgrade();
        assertEquals((int) (fee + ((int) (1.5 * (num * 1)))), testSquare.getFee());
        testSquare.setMinorUpgrade();
        assertEquals((int) (fee + ((int) (1.5 * (num * 2)))), testSquare.getFee());
        testSquare.setMinorUpgrade();
        assertEquals((int) (fee + ((int) (1.5 * (num * 3)))), testSquare.getFee());
        testSquare.setMajorUpgrade();
        assertEquals((int) (fee + ((int) (1.5 * (num * 3))) * 5), testSquare.getFee());
    }

    @ParameterizedTest
    @MethodSource("utils.TestUtils#randomTestNumbersAndNames")
    void testGetInvestmentCost(int num, String name) {
        testSquare = new InvestmentSquare(name, num, 0);
        assertEquals(num, testSquare.getInvestmentCost());
    }

    @ParameterizedTest
    @MethodSource("utils.TestUtils#randomTestNumbersAndNames")
    void testGetMajorUpgradeCost(int num, String name) {
        testSquare = new InvestmentSquare(name, num, 0);
        int fee = ((num / 2) > 0) ? num / 2 : 1;
        int expectedCost = (fee * 3);
        assertEquals(expectedCost, testSquare.getMajorUpgradeCost());
    }

    @ParameterizedTest
    @MethodSource("utils.TestUtils#randomTestNumbersAndNames")
    void testSetGetMinorUpgrade(int num, String name) {
        testSquare = new InvestmentSquare(name, num, 0);
        int testLimit = rand.nextInt(4);
        for (int i = 0; i < testLimit; i++) {
            testSquare.setMinorUpgrade();
        }
        assertEquals(testLimit, testSquare.getMinorUpgrade());
    }

    @ParameterizedTest
    @MethodSource("utils.TestUtils#randomTestNumbersAndNames")
    void testSetGetMinorUpgradeCost(int num, String name) {
        testSquare = new InvestmentSquare(name, num, 0);
        int expectedCost = (num / 2 > 0) ? num / 2 : 1;
        assertEquals(expectedCost, testSquare.getMinorUpgradeCost());
    }

    @ParameterizedTest
    @MethodSource("utils.TestUtils#randomTestNamesPlayersNumbers")
    void testSetGetOwner(int num, String name, Player player) {
        testSquare = new InvestmentSquare(name, num, 0);
        testSquare.setOwner(player);
        assertEquals(player, testSquare.getOwner());
    }

    @ParameterizedTest
    @MethodSource("utils.TestUtils#randomTestNumbersAndNames")
    void testSetIsMajorUpgrade(int num, String name) {
        testSquare = new InvestmentSquare(name, num, 0);
        boolean check = false;
        if (rand.nextInt(Integer.MAX_VALUE) % 2 == 0) {
            testSquare.setMajorUpgrade();
            check = true;
        }
        assertEquals(check, testSquare.isMajorUpgrade());
    }

    @ParameterizedTest
    @MethodSource("utils.TestUtils#randomTestNamesPlayersNumbers")
    void testIsOwned(int num, String name, Player player) {
        testSquare = new InvestmentSquare(name, num, 0);
        boolean check = false;
        if (rand.nextInt(Integer.MAX_VALUE) % 2 == 0) {
            testSquare.setOwner(player);
            check = true;
        }
        assertEquals(check, testSquare.isOwned());
    }

}
