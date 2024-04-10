package models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.*;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

public class TestInvestmentSquare {

    InvestmentSquare testSquare;
    private static Random rand = new Random();
    private static List<Arguments> nameNumberList;

    @BeforeEach
    void setup() {
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
