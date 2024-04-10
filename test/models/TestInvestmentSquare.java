package models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

import static utils.TestUtils.*;

public class TestInvestmentSquare {
	
	InvestmentSquare investSquare;

    InvestmentSquare testSquare;
<<<<<<< Upstream, based on branch 'SET3-69-i-want-the-app-tested' of https://gitlab.eeecs.qub.ac.uk/CSC7083-2324/CSC7083-2324-G3.git
=======
    private static Random rand = new Random();
    private static List<Arguments> nameNumberList;
    
    String investmentSquareName;
    int investmentCostValid;
	int investmentCostValidHigh;
	int investmentCostInvalid, investmentCostInvalidHigh;
	
	int SunnyAcresFeeValid, RadiantRidgeFeesValid, WindyMillsFeesValid, GustoGroveFeesValid, AiryHeightsFeesValid, SplashvilleFeesValid, 
	TorrentialTerraceFeesValid, HydroHavenFeesValid, NucleoNestFeesValid, AtomicOasisFeesValid;
	

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
    	SplashvilleFeesValid =  45;
    	TorrentialTerraceFeesValid =  50;
    	HydroHavenFeesValid = 55;
    	NucleoNestFeesValid = 65;
    	AtomicOasisFeesValid = 70;
    	
    	
    	
    	
    	
    	investSquare = new InvestmentSquare(investmentSquareName , investmentCostValidHigh);
    	
    }
    
    @Test 
    public void testInitialsation(){
    	// Arrange
    	//InvestmentSquare investSquare = new InvestmentSquare(investmentSquareName , investmentCostValidHigh);
    	
    	//Assert
    	assertEquals(investmentSquareName, investSquare.getName());
    	assertEquals(investmentCostValidHigh, investSquare.getInvestmentCost());
    	
    }
    
    @Test
    public void testSetInvestmentCostValid() {
    	// Arrange
    	
    	//InvestmentSquare investSquare = new InvestmentSquare(investmentSquareName , investmentCostValidHigh);
    	
    	//Act
    	investSquare.setInvestmentCost(investmentCostValid);
    	//Assert
    	assertEquals(investmentCostValid, investSquare.getInvestmentCost());
    	

    	//Act
    	investSquare.setInvestmentCost(investmentCostValidHigh);
    	//Assert
    	assertEquals(investmentCostValidHigh, investSquare.getInvestmentCost());
    	
    	
    }
    
    @Test
    public void testSetInvestmentCostInValid() {
    	// Arrange
    	//InvestmentSquare investSquare = new InvestmentSquare(investmentSquareName , investmentCostValidHigh);
    	
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
    	//InvestmentSquare investSquare = new InvestmentSquare(investmentSquareName , investmentCostValidHigh);
    	
    	//Assert
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
    public void testSetMinorUpgrade() {
    	
    	 InvestmentSquare square = new InvestmentSquare("Sunny Acres", SunnyAcresFeeValid);
            
            // Ensure that minorUpgradeCost is initially set to fee
            assertEquals(square.getFee(), square.getMinorUpgradeCost());
            
            // Simulate a minor upgrade
            square.setMinorUpgrade();
            
            // Ensure that minorUpgradeCost is still equal to fee after a minor upgrade
            assertEquals(square.getFee(), square.getMinorUpgradeCost());
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

    private static String randomName() {
        byte[] byteArray = new byte[rand.nextInt(49) + 1];
        for (int j = 0; j < byteArray.length; j++) {
            byteArray[j] = (byte) (rand.nextInt(122 - 97) + 97);
        }
        String randomString = new String(byteArray, Charset.forName("UTF-8"));
        return randomString;
    }
>>>>>>> d5e9172 updated edge case testing on investment square. still not fully completed

    @ParameterizedTest
    @MethodSource("utils.TestUtils#randomTestNamesPlayersNumbers")
    void testGetFee(int num, String name) {
        testSquare = new InvestmentSquare(name, num);
        assertEquals(Integer.max(1, num / 2), testSquare.getFee());
    }

    @ParameterizedTest
    @MethodSource("utils.TestUtils#randomTestNamesPlayersNumbers")
    void testGetInvestmentCost(int num, String name) {
        testSquare = new InvestmentSquare(name, num);
        assertEquals(num, testSquare.getInvestmentCost());
    }

    @ParameterizedTest
    @MethodSource("utils.TestUtils#randomTestNamesPlayersNumbers")
    void testGetMajorUpgradeCost(int num, String name) {
        testSquare = new InvestmentSquare(name, num);
        int expectedCost = ((num / 2) * 3);
        assertEquals(expectedCost, testSquare.getMajorUpgradeCost());
    }

    @ParameterizedTest
    @MethodSource("utils.TestUtils#randomTestNamesPlayersNumbers")
    void testSetGetMinorUpgrade(int num, String name) {
        testSquare = new InvestmentSquare(name, num);
        int testLimit = rand.nextInt(4);
        for (int i = 0; i < testLimit; i++) {
            testSquare.setMinorUpgrade();
        }
        assertEquals(testLimit, testSquare.getMinorUpgrade());
    }

    @ParameterizedTest
    @MethodSource("utils.TestUtils#randomTestNamesPlayersNumbers")
    void testSetGetMinorUpgradeCost(int num, String name) {
        testSquare = new InvestmentSquare(name, num);
        int expectedCost = num / 2;
        assertEquals(expectedCost, testSquare.getMinorUpgradeCost());
    }

    @ParameterizedTest
    @MethodSource("utils.TestUtils#randomTestNamesPlayersNumbers")
    void testSetGetOwner(int num, String name, Player player) {
        testSquare = new InvestmentSquare(name, num);
        testSquare.setOwner(player);
        assertEquals(player, testSquare.getOwner());
    }

    @ParameterizedTest
    @MethodSource("utils.TestUtils#randomTestNamesPlayersNumbers")
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
    @MethodSource("utils.TestUtils#randomTestNamesPlayersNumbers")
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
