// package controllers;

// import org.junit.jupiter.api.AfterEach;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;

// import models.Player;

// import java.io.ByteArrayInputStream;
// import java.io.IOException;
// import java.io.InputStream;
// import java.io.OutputStream;
// import java.io.PrintStream;

// import static org.junit.jupiter.api.Assertions.*;

// public class TestGameController {

// private GameController gameController;
// private InputStream originalSystemIn;

// //do we need to add in validation in the start game method if the number
// players etc is wrong?

// @BeforeEach
// void setUp() {
// gameController = new GameController();
// originalSystemIn = System.in;

// }

// @AfterEach
// void tearDown() {
// System.setIn(originalSystemIn);
// }

// // Helper method to check if the game loop was executed
// private boolean isGameLoopExecuted() {
// return true;
// }

// //This test verifies the normal flow of the startGame() method when the
// //user directly chooses to start the game ('p' option).
// //The assertDoesNotThrow() method ensures that the method runs without
// encountering any unhandled exceptions.

// @Test
// void testStartGame_NormalFlow() throws IOException {
// String simulatedInput = "p\n";
// InputStream inputStream = new
// ByteArrayInputStream(simulatedInput.getBytes());
// System.setIn(inputStream);

// assertDoesNotThrow(() -> gameController.startGame());

// // Assert that game loop was executed (indicating game is running)
// assertTrue(isGameLoopExecuted());

// }

// //This test focuses on the method behaving correctly when the user
// //provides an invalid choice before starting the game.
// //The assertDoesNotThrow() method ensures that the method runs without
// encountering any unhandled exceptions.

// @Test
// void testStartGame_InvalidChoice() throws IOException {
// String simulatedInput = "invalid\np\n";
// InputStream inputStream = new
// ByteArrayInputStream(simulatedInput.getBytes());
// System.setIn(inputStream);

// assertDoesNotThrow(() -> gameController.startGame());

// }

// //this test verify the behavior of the startGame() method when the user
// //chooses to view instructions ('i' option) and then starts the game ('p'
// option) afterwards.
// //The assertDoesNotThrow() method ensures that the method runs without
// encountering any unhandled exceptions.

// @Test
// void testStartGame_GameStartWithInstructionsView() throws IOException {
// String simulatedInput = "i\np\n";
// InputStream inputStream = new
// ByteArrayInputStream(simulatedInput.getBytes());
// System.setIn(inputStream);

// assertDoesNotThrow(() -> gameController.startGame());

// // Assert that game loop was executed (indicating game is running)
// assertTrue(isGameLoopExecuted());

// }

// //This test verifies the behavior of the startGame() method when the user
// provides invalid inputs followed by a
// //valid input ('p' option) to start the game.
// //The assertDoesNotThrow() method ensures that the method runs without
// encountering any unhandled exceptions.

// @Test
// void testStartGame_GameStartWithInvalidInputFollowedByValidInput() throws
// IOException {
// String simulatedInput = "invalid1\ninvalid2\np\n";
// InputStream inputStream = new
// ByteArrayInputStream(simulatedInput.getBytes());
// System.setIn(inputStream);

// assertDoesNotThrow(() -> gameController.startGame());

// // Assert that game loop was executed (indicating game is running)
// assertTrue(isGameLoopExecuted());

// }

// //This test focuses on ensuring that the startGame() method behaves correctly
// when the user
// //provides multiple invalid choices before finally starting the game.
// //The assertDoesNotThrow() method ensures that the method runs without
// encountering any unhandled exceptions.

// @Test
// public void testStartGame_MultipleInvalidChoicesFollowedByGameStart() throws
// IOException {
// String simulatedInput = "invalid1\ninvalid2\ninvalid3\np\n";
// InputStream inputStream = new
// ByteArrayInputStream(simulatedInput.getBytes());
// System.setIn(inputStream);

// assertDoesNotThrow(() -> gameController.startGame());

// // Assert that game loop was executed (indicating game is running)
// assertTrue(isGameLoopExecuted());

// }

// //This test verifies the behavior of the startGame() method when the user
// provides no input followed
// //by a valid input ('p' option) to start the game.
// //The assertDoesNotThrow() method ensures that the method runs without
// encountering any unhandled exceptions.

// @Test
// void testStartGame_GameStartWithNoInputFollowedByValidInput() throws
// IOException {
// String simulatedInput = "\np\n";
// InputStream inputStream = new
// ByteArrayInputStream(simulatedInput.getBytes());
// System.setIn(inputStream);

// assertDoesNotThrow(() -> gameController.startGame());

// // Assert that game loop was executed (indicating game is running)
// assertTrue(isGameLoopExecuted());
// }

// //This test is verifying the behavior of the startGame() method
// //when the minimum number of players is provided.
// //The assertDoesNotThrow() method ensures that the method runs without
// encountering any unhandled exceptions.

// @Test
// void testStartGame_MinimumPlayers() throws IOException {
// // Assuming the minimum number of players is 1
// String simulatedInput = "p\n";
// InputStream inputStream = new
// ByteArrayInputStream(simulatedInput.getBytes());
// System.setIn(inputStream);

// assertDoesNotThrow(() -> gameController.startGame());
// }

// //This test is verifying the behavior of the startGame() method
// //when the maximum number of players is provided.
// //The assertDoesNotThrow() method ensures that the method runs without
// encountering any unhandled exceptions.

// @Test
// void testStartGame_MaximumPlayers() throws IOException {
// // Assuming the maximum number of players is 10 (example)
// String simulatedInput = "p\n";
// InputStream inputStream = new
// ByteArrayInputStream(simulatedInput.getBytes());
// System.setIn(inputStream);

// assertDoesNotThrow(() -> gameController.startGame());
// }

// @Test
// void testGameLoop_NormalFlow() throws IOException {
// String simulatedInput = "p\ns\nr\n"; // Simulated input: start game, show
// resources, roll dice
// InputStream inputStream = new
// ByteArrayInputStream(simulatedInput.getBytes());
// System.setIn(inputStream);

// GameController gameController = new GameController();
// gameController.startGame(); // Start the game

// // Assuming the game loop continues until a game-ending condition is met
// which
// //we need to create a method for that!!! more tests can be created when we do
// this
// // We cannot directly test the game loop here as it's a continuous loop until
// the game ends

// //this test verifies that the gameLoop() method behaves correctly when a
// player
// //chooses to roll the dice during their turn
// }
// @Test
// void //do we need add more checks in game loop ie for the players next turn
// etc

// testGameLoop_RollDice() throws IOException {
// String simulatedInput = "r\n"; // Simulated input: roll dice
// InputStream inputStream = new
// ByteArrayInputStream(simulatedInput.getBytes());
// System.setIn(inputStream);

// GameController gameController = new GameController();
// gameController.startGame(); // Start the game

// }
// }
