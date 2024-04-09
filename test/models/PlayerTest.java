/**
 * 
 */
package models;

///*import static org.junit.jupiter.api.Assertions.*;
//
//import org.junit.jupiter.api.AfterAll;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//
///**
// * 
// */
///*class PlayerTest {
//
//	private String user1, user2, user3, user4;
//	private Player testPlay;
//	private int moneyValid, moneyValidLow, moneyValidHigh;
//	private int moneyInvalid, moneyInvalidLow; */
//
//	//test data
//
//
//
//	/**
//	 * @throws java.lang.Exception
//	 */
//	@BeforeAll
//	static void setUpBeforeClass() throws Exception {
//	}
//
//	/**
//	 * @throws java.lang.Exception
//	 */
//	@AfterAll
//	static void tearDownAfterClass() throws Exception {
//	}
//
//	/**
//	 * @throws java.lang.Exception
//	 */
//	@BeforeEach
//	void setUp() throws Exception {
//		user1 = "Ryan";
//		moneyValid = 500;
//		moneyValidLow = 501;
//		moneyValidHigh = 600;
//		moneyInvalid = 499;
//		moneyInvalidLow = 490;
//		
//
//
//	}
//
//	/**
//	 * @throws java.lang.Exception
//	 */
//	@AfterEach
//	void tearDown() throws Exception {
//	}
//
//	/**
//	 * Test method for {@link models.Player#getName()}.
//	 */
//	@Test
//	final void testGetName() {
//		testPlay = new Player(user1);
//		assertEquals(user1, testPlay.getName());
//	}
//
//	/**
//	 * test method for player money resources set at 500
//	 */
//	@Test
//	final void testGetStartingMoney(){
//		testPlay = new Player(user1);
//		assertEquals(500, testPlay.getCarbonDebt());
//	} */
//}
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.List;

public class PlayerTest {

    @Test
    public void testInitialization() {
        // Arrange
        String name = "TestPlayer";
        Player player = new Player(name);

        // Assert
        assertEquals(name, player.getName());
        assertEquals(500, player.getMoney());
        assertEquals(500, player.getCarbonDebt());
        assertTrue(player.getProperties().isEmpty());
        assertEquals(0, player.getPosition());
    }

    @Test
    public void testSetNameValid() {
        // Arrange
        Player player = new Player("TestPlayer");

        // Act
        player.setName("NewName");

        // Assert
        assertEquals("NewName", player.getName());
    }

    @Test
    public void testSetNameNull() {
        // Arrange
        Player player = new Player("TestPlayer");

        // Act & Assert
        assertThrows(NullPointerException.class, () -> player.setName(null));
    }

    @Test
    public void testSetNameEmpty() {
        // Arrange
        Player player = new Player("TestPlayer");

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> player.setName(""));
    }

    @Test
    public void testAddResourcesMoney() {
        // Arrange
        Player player = new Player("TestPlayer");
        int initialMoney = player.getMoney();

        // Act
        player.addResources("money", 100);

        // Assert
        assertEquals(initialMoney + 100, player.getMoney());
    }

    @Test
    public void testAddResourcesCarbonDebt() {
        // Arrange
        Player player = new Player("TestPlayer");
        int initialCarbonDebt = player.getCarbonDebt();

        // Act
        player.addResources("carbonDebt", 100);

        // Assert
        assertEquals(initialCarbonDebt + 100, player.getCarbonDebt());
    }

    @Test
    public void testDeductResourcesMoney() {
        // Arrange
        Player player = new Player("TestPlayer");
        player.setMoney(200);

        // Act
        player.deductResources("money", 100);

        // Assert
        assertEquals(100, player.getMoney());
    }

    @Test
    public void testDeductResourcesCarbonDebt() {
        // Arrange
        Player player = new Player("TestPlayer");
        player.setCarbonDebt(200);

        // Act
        player.deductResources("carbonDebt", 100);

        // Assert
        assertEquals(100, player.getCarbonDebt());
    }
}


