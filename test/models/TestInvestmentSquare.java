package models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
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

        investSquare = new InvestmentSquare(investmentSquareName, investmentCostValidHigh);

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
    /*
     * Verify that when a player lands on an owned square, they correctly pay the fee to the owner.
     * *Ensure that the player's resources decrease by the fee amount and the owner's resources increase accordingly.
     */
   
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
        square.landOn(player1, players, scanner);

        // Assert that player1 pays the fee to player2
        assertEquals(590, player1.getMoney()); // Assuming fee is 20
        assertEquals(510, player2.getMoney()); // Player2 receives the fee
    }
    
    
    /*
     * Verify that when a player lands on an unowned square, they can choose to invest in it.
     *Ensure that if the player has enough resources, they become the owner of the square.
   
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
        square.landOn(player1, scanner);

        // Assert: Player1 becomes the owner of the square
        assertEquals(player1, square.getOwner());
        assertEquals(initialMoneyPlayer1 - square.getInvestmentCost(), player1.getMoney());
    }
    
    /*Verify that if a player lands on a square they already own, no action should be taken, 
    *and their resources should remain unchanged.
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
        square.landOn(player1, new Scanner(System.in));

        // Assert: Player1's resources should remain unchanged
        assertEquals(initialMoneyPlayer1, player1.getMoney());
    }
    
//    @Test not doing right calculations need to think and re work and help!!
//    public void testPlayerPaysFeeWithCarbonDebt() {
//        // Create a new board, player, and owner
//        Board board = new Board();
//        Player player = new Player("Player 1");
//        Player owner = new Player("Owner");
//
//        // Get the investment square from the board
//        InvestmentSquare square = (InvestmentSquare) board.getSquare(1);
//
//        // Set square owner
//        square.setOwner(owner);
//
//        // Set up owner's initial resources (500 money/ 500 carbon)
//        owner.getMoney();
//        owner.getCarbonDebt();
//
//        // Set up player's initial resources (500 money/ 500 carbon )
//        player.getMoney(); // Player has less money than the fee
//        player.getCarbonDebt();
//
//        // Mock user input to simulate player choosing to pay by money
//        String input = "1" + System.getProperty("line.separator"); // Assuming player chooses to pay by money
//        InputStream in = new ByteArrayInputStream(input.getBytes());
//        System.setIn(in);
//        Scanner scanner = new Scanner(System.in);
//
//        // Simulate player landing on the square
//        square.landOn(player, scanner);
//
//        // Assert that player's money remains unchanged (expected: 10)
//        assertEquals(490, player.getMoney());
//
//        
//        // Mock user input to simulate player choosing to pay by money
//       input = "1" + System.getProperty("line.separator"); // Assuming player chooses to pay by money
//       in = new ByteArrayInputStream(input.getBytes());
//       System.setIn(in);
//       
//   
//       
//
//         //Assert that player's carbon debt is increased by the fee (expected:505?? )
//        assertEquals(500, player.getCarbonDebt());
//
//        // Assert that owner's money is increased by the fee (expected: 105)
//        assertEquals(510, owner.getMoney());
//
//        // Assert that owner's carbon debt is decreased by the fee (expected: 495)
//        assertEquals(500, owner.getCarbonDebt());
//    }
    
//    
    
    /*
     * This test case verifies the behaviour when a player lands on a square they already own. 
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
        Scanner scanner = new Scanner(System.in);

        // Simulate player landing on the square
        square.landOn(player, scanner);

        // Assert that player's resources remain unchanged
        assertEquals(500, player.getMoney()); // Player's money remains unchanged
    }
    
    
    /*
     * This test case verifies the behaviour when a player lands on an unowned square and chooses not to invest. 
     */
    @Test
    public void testPlayerChoosesNotToInvest() {
        Board board = new Board();
        Player player = new Player("Player 1");
        InvestmentSquare square = (InvestmentSquare) board.getSquare(1); // Assuming Sunny Acres is the second square

        // Set up player's resources
        player.addResources("money", 100); // Player has enough money

        // Mock user input to simulate player choosing not to invest
        String input = "no" + System.getProperty("line.separator"); // Player chooses not to invest
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);

        // Simulate player landing on the square
        square.landOn(player, scanner);

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
        square.landOn(player, null); // No scanner needed for neutral squares

        // Assert that player's resources remain unchanged
        assertEquals(500, player.getMoney()); // Player's money remains unchanged
    }
    
    private static Stream<Arguments> randomTestNamesPlayersNumbers() {
        nameNumberList = new LinkedList<>();
        List<Player> testPlayers = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            String randomString = randomName();
            testPlayers.add(new Player(randomName()));
            nameNumberList.add(Arguments.of(rand.nextInt(999) + 1, randomString, testPlayers.get(i)));
        }
        return nameNumberList.stream();
    }
//not doing right calculations!! need help
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
        square.landOn(player, scanner);

        // Assert that player's money is deducted by the fee
        assertEquals(0, player.getMoney()); // Player's money becomes 0

        // Assert that player's carbon debt is increased by the fee
        assertEquals(5, player.getCarbonDebt()); // Player's carbon debt is increased by the fee

        // Assert that owner's money is increased by the fee
        assertEquals(510, owner.getMoney()); // Owner receives the fee in money

        // Assert that owner's carbon debt remains unchanged
        assertEquals(500, owner.getCarbonDebt()); // Owner's carbon debt remains unchanged
    }
    
    
    private static String randomName() {
        byte[] byteArray = new byte[rand.nextInt(49) + 1];
        for (int j = 0; j < byteArray.length; j++) {
            byteArray[j] = (byte) (rand.nextInt(122 - 97) + 97);
        }
        String randomString = new String(byteArray, Charset.forName("UTF-8"));
        return randomString;
    }
    
    
    @ParameterizedTest
    @MethodSource("randomTestNamesPlayersNumbers")
    void testGetFee(int num, String name) {
        testSquare = new InvestmentSquare(name, num);
        assertEquals(Integer.max(1, num / 2), testSquare.getFee());
    }

    @ParameterizedTest
    @MethodSource("randomTestNamesPlayersNumbers")
    void testGetInvestmentCost(int num, String name) {
        testSquare = new InvestmentSquare(name, num);
        assertEquals(num, testSquare.getInvestmentCost());
    }

    @ParameterizedTest
    @MethodSource("randomTestNamesPlayersNumbers")
    void testGetMajorUpgradeCost(int num, String name) {
        testSquare = new InvestmentSquare(name, num);
        int expectedCost = ((num / 2) * 3);
        assertEquals(expectedCost, testSquare.getMajorUpgradeCost());
    }

    @ParameterizedTest
    @MethodSource("randomTestNamesPlayersNumbers")
    void testSetGetMinorUpgrade(int num, String name) {
        testSquare = new InvestmentSquare(name, num);
        int testLimit = rand.nextInt(4);
        for (int i = 0; i < testLimit; i++) {
            testSquare.setMinorUpgrade();
        }
        assertEquals(testLimit, testSquare.getMinorUpgrade());
    }

    @ParameterizedTest
    @MethodSource("randomTestNamesPlayersNumbers")
    void testSetGetMinorUpgradeCost(int num, String name) {
        testSquare = new InvestmentSquare(name, num);
        int expectedCost = num / 2;
        assertEquals(expectedCost, testSquare.getMinorUpgradeCost());
    }

    @ParameterizedTest
    @MethodSource("randomTestNamesPlayersNumbers")
    void testSetGetOwner(int num, String name, Player player) {
        testSquare = new InvestmentSquare(name, num);
        testSquare.setOwner(player);
        assertEquals(player, testSquare.getOwner());
    }

    @ParameterizedTest
    @MethodSource("randomTestNamesPlayersNumbers")
    void testSetIsMajorUpgrade(int num, String name) {
        testSquare = new InvestmentSquare(name, num);
        boolean check = false;
        if (rand.nextInt(Integer.MAX_VALUE) % 2 == 0) {
            testSquare.setMajorUpgrade();
            check = true;
        }
        assertEquals(check, testSquare.isMajorUpgrade());
    }

    @ParameterizedTest
    @MethodSource("randomTestNamesPlayersNumbers")
    void testIsOwned(int num, String name, Player player) {
        testSquare = new InvestmentSquare(name, num);
        boolean check = false;
        if (rand.nextInt(Integer.MAX_VALUE) % 2 == 0) {
            testSquare.setOwner(player);
            check = true;
        }
        assertEquals(check, testSquare.isOwned());
    }

}
