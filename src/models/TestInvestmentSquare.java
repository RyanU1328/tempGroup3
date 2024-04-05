package models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class TestInvestmentSquare {

    InvestmentSquare testSquare;

    /**
     * @throws java.lang.Exception
     */
    @BeforeEach
    void setUp() throws Exception {
    }

    @ParameterizedTest @ValueSource(ints={Math.,2,5,10,20,40,45,100,Integer.MAX_VALUE

    })

    void testGetFee(int num) {
        testSquare = new InvestmentSquare("testSquare", num);

    }

    @Test
    void testGetInvestmentCost() {

    }

    @Test
    void testGetMajorUpgradeCost() {

    }

    @Test
    void testGetMinorUpgrade() {

    }

    @Test
    void testGetMinorUpgradeCost() {

    }

    @Test
    void testGetOwner() {

    }

    @Test
    void testIsMajorUpgrade() {

    }

    @Test
    void testIsOwned() {

    }

    @Test
    void testLandOn() {

    }

    @Test
    void testSetMajorUpgrade() {

    }

    @Test
    void testSetMinorUpgrade() {

    }

    @Test
    void testSetOwner() {

    }
}
