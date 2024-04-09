package models;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.util.InputMismatchException;
import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class PlayerTest {
	// test data

	private String userValid;
	private String userNull;
	private String userInvalid;

	private int startingMoneyValid, moneyValid;
	private int moneyInvalid, moneyInvalidLow;
	private int startingCarbonValid, carbonValid;
	private int carbonInvalid, carbonInvalidLow;
	private int invalidAddResources;
	private int postionStarterValid;

	private String propertyValid, propertyValid2;
	private String propertyNonExistent;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		userValid = "Ryan";
		userNull = null;
		userInvalid = "";

		startingMoneyValid = 500;
		moneyValid = 300;
		moneyInvalid = 0;
		moneyInvalidLow = -1;

		startingCarbonValid = 500;
		carbonValid = 300;
		carbonInvalid = 0;
		carbonInvalidLow = -1;

		invalidAddResources = -100;

		postionStarterValid = 0;

		propertyValid = "propertyValid";
		propertyValid2 = "propertyValid2";
		propertyNonExistent = "NonexistentProperty";

	}

	@Test
	public void testInitializationValid() {
		// Arrange

		Player player = new Player(userValid);

		// Assert
		assertEquals(userValid, player.getName());
		assertEquals(startingMoneyValid, player.getMoney());
		assertEquals(startingCarbonValid, player.getCarbonDebt());
		assertTrue(player.getProperties().isEmpty());
		assertEquals(postionStarterValid, player.getPosition());
	}

	@Test
	public void testInitializationInvalid() {

	}

	@Test
	public void testSetNameValid() {
		// Arrange
		Player player = new Player(userValid);

		// Act
		player.setName("NewName");

		// Assert
		assertEquals("NewName", player.getName());
	}

	@Test
	public void testSetNameNull() {
		// Arrange
		Player player = new Player(userValid);

		// Act & Assert
		assertThrows(NullPointerException.class, () -> player.setName(userNull));
	}

	@Test
	public void testSetNameInvalid() {
		// Arrange
		Player player = new Player(userValid);

		// Act & Assert
		assertThrows(IllegalArgumentException.class, () -> player.setName(userInvalid));
	}

	@Test
	public void testStartingMoneyAndCarbonDebt() {

		// Arrange
		Player player = new Player(userValid);

		// Assert
		assertEquals(startingMoneyValid, player.getMoney());
		assertEquals(startingCarbonValid, player.getCarbonDebt());
	}

	/*
	 * @Test // not sure if we will need these?? just the invalid tests for starting
	 * money public void testGetSetMoney() { //Arrange Player player = new
	 * Player(userValid);
	 * 
	 * // Act player.setMoney(moneyValidLow); // Assert assertEquals(moneyValidLow,
	 * player.getMoney()); // Act player.setMoney(moneyValidHigh); // Assert
	 * assertEquals(moneyValidHigh, player.getMoney());
	 * 
	 * 
	 * }
	 */

//    added logic to player class need to update in branch not sure if correct logic??/
	@Test
	public void testGetSetMoneyInvalid() {
		// Arrange
		Player player = new Player(userValid);

		// Act & Assert

		assertThrows(IllegalArgumentException.class, () -> {
			player.setMoney(moneyInvalid);

		});
		assertThrows(IllegalArgumentException.class, () -> {
			player.setMoney(moneyInvalidLow);
		});

	}

	@Test
	public void testGetSetCarbonInvalid() {
		// Arrange
		Player player = new Player(userValid);

		// Act & Assert

		assertThrows(IllegalArgumentException.class, () -> {
			player.setCarbonDebt(carbonInvalid);

		});
		assertThrows(IllegalArgumentException.class, () -> {
			player.setCarbonDebt(carbonInvalidLow);
		});
	}

	@Test
	public void testAddResourcesMoney() {
		// Arrange
		Player player = new Player(userValid);
		int startingMoneyValid = player.getMoney();

		// Act
		player.addResources("money", 100);

		// Assert
		assertEquals(startingMoneyValid + 100, player.getMoney());
	}

	@Test
	public void testAddResourcesMoneyOtherAmount() {
		// Arrange
		Player player = new Player(userValid);
		int moneyValid = player.getMoney();

		// Act
		player.addResources("money", 200);

		// Assert
		assertEquals(moneyValid + 200, player.getMoney());
	}

	@Test
	public void testAddResourcesCarbonDebt() {
		// Arrange
		Player player = new Player(userValid);
		int startingCarbonValid = player.getCarbonDebt();

		// Act
		player.addResources("carbonDebt", 100);

		// Assert
		assertEquals(startingCarbonValid + 100, player.getCarbonDebt());
	}

	@Test
	public void testAddResourcesCarbonDebtOtherAmount() {
		// Arrange
		Player player = new Player(userValid);
		int carbonValid = player.getCarbonDebt();

		// Act
		player.addResources("carbonDebt", 200);

		// Assert
		assertEquals(carbonValid + 200, player.getCarbonDebt());
	}

	@Test
	public void testAddNegativeResources() {
		// Arrange
		Player player = new Player(userValid);

		// Act & Assert
		assertThrows(IllegalArgumentException.class, () -> player.addResources("money", invalidAddResources));
		assertThrows(IllegalArgumentException.class, () -> player.addResources("carbonDebt", invalidAddResources));
		// Additional checks if adding negative resources doesn't change the state
	};

	@Test
	public void testDeductResourcesMoney() {
		// Arrange
		Player player = new Player(userValid);
		player.setMoney(500);

		// Act
		player.deductResources("money", 100);

		// Assert
		assertEquals(400, player.getMoney());
	}

	@Test
	public void testDeductResourcesCarbonDebt() {
		// Arrange
		Player player = new Player(userValid);
		player.setCarbonDebt(500);

		// Act
		player.deductResources("carbonDebt", 100);

		// Assert
		assertEquals(400, player.getCarbonDebt());

	}

	@Test
	public void testAddProperty() {
		// Arrange
		Player player = new Player(userValid);
		String property = propertyValid;

		// Act
		player.addProperty(property);

		// Assert
		assertTrue(player.getProperties().contains(property));
	}

	@Test
	public void testAddMultipleProperties() {
		// Arrange
		Player player = new Player(userValid);
		String property1 = propertyValid;
		String property2 = propertyValid2;

		// Act
		player.addProperty(property1);
		player.addProperty(property2);

		// Assert
		assertTrue(player.getProperties().contains(property1));
		assertTrue(player.getProperties().contains(property2));
	}

	@Test
	public void testRemoveProperty() {
		// Arrange
		Player player = new Player(userValid);
		String property = propertyValid;
		player.addProperty(property);

		// Act
		player.removeProperty(property);

		// Assert
		assertFalse(player.getProperties().contains(property));
	}

	@Test
	public void testRemoveNonexistentProperty() {
		// Arrange
		Player player = new Player(userValid);
		String nonexistentProperty = propertyNonExistent;

		// Act
		player.removeProperty(nonexistentProperty);

		// Assert
		assertFalse(player.getProperties().contains(nonexistentProperty));
	}

	@Test
	public void testRemoveNonexistentPropertyTwice() {
		// Arrange
		Player player = new Player(userValid);
		String property = propertyValid;

		// Act
		player.removeProperty(property);
		player.removeProperty(property);

		// Assert
		assertFalse(player.getProperties().contains(property)); // Ensure property is not present (should have no
																// effect)
	}

	@Test
	public void testRemovePropertyWithNoProperties() {
		// Arrange
		Player player = new Player(userValid);
		String property = propertyValid;

		// Act
		player.removeProperty(property);

		// Assert
		assertFalse(player.getProperties().contains(property)); // Ensure property is not present (should have no
																// effect)
	}

	@Test
	public void testRemovePropertyWithMultipleProperties() {
		// Arrange
		Player player = new Player(userValid);
		String property1 = propertyValid;
		String property2 = propertyValid2;
		player.addProperty(property1);
		player.addProperty(property2);

		// Act
		player.removeProperty(property1);

		// Assert
		assertFalse(player.getProperties().contains(property1)); // Ensure property1 is removed
		assertTrue(player.getProperties().contains(property2)); // Ensure property2 is still present
	}

	@Test
	public void testChoosePaymentMethodValidInputMoneyFee() {
		// Arrange
		ByteArrayInputStream in = new ByteArrayInputStream("1\n".getBytes());
		System.setIn(in);
		Scanner scanner = new Scanner(System.in);
		Player player = new Player("TestPlayer");

		// Act
		boolean result = player.choosePaymentMethod(scanner);

		// Assert
		assertEquals(true, result); // Pay by money

	}
	
	@Test
	public void testChoosePaymentMethodValidInputCarbonFee() {
		// Arrange
		ByteArrayInputStream in = new ByteArrayInputStream("2\n".getBytes());
		System.setIn(in);
		Scanner scanner = new Scanner(System.in);
		Player player = new Player("TestPlayer");

		// Act
		boolean result = player.choosePaymentMethod(scanner);

		// Assert
		assertEquals(false, result); // Pay by carbon

	}

	@Test
	public void testChoosePaymentMethodInvalidInputCorrectedChoiceMoneyFee() {
		// Arrange
		ByteArrayInputStream in = new ByteArrayInputStream("3\n1\n".getBytes());
		System.setIn(in);
		Scanner scanner = new Scanner(System.in);
		Player player = new Player("TestPlayer");

		// Act
		boolean result = player.choosePaymentMethod(scanner);

		// Assert
		assertEquals(true, result); // Pay by moneyFee (after retrying)
	}
	
	@Test
	public void testChoosePaymentMethodInvalidInputCorrectedChoiceCarbonDebt() {
	    // Arrange
	    ByteArrayInputStream in = new ByteArrayInputStream("3\n2\n".getBytes());
	    System.setIn(in);
	    Scanner scanner = new Scanner(System.in);
	    Player player = new Player("TestPlayer");

	    // Act
	    boolean result = player.choosePaymentMethod(scanner);

	    // Assert
	    assertEquals(false, result); // Pay by accepting to take carbon debt (after retrying)
	}

	
}
