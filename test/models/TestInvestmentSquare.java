package models;

import org.junit.jupiter.api.BeforeEach;
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

    InvestmentSquare testSquare;

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
