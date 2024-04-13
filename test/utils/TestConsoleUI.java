package utils;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestConsoleUI {
	
	
	
    private InputStream originalSystemIn;

    @BeforeEach
    public void setUp() {
        originalSystemIn = System.in;
    }

    @AfterEach
    public void tearDown() throws IOException {
        System.setIn(originalSystemIn);
        originalSystemIn.close();
    }

//    @Test
//    public void testPromptForPlayerCount_ValidInput() {
//        // Prepare test input
//        String input = "3\n"; // Simulate user input "3"
//        InputStream in = new ByteArrayInputStream(input.getBytes());
//        System.setIn(in);
//
//        // Invoke method
//        int result = ConsoleUI.promptForPlayerCount();
//
//        // Verify output
//        assertEquals(3, result, "Expected player count to be 3");
//    }





//    @Test
//    public void testPromptForPlayerCount_LowerBoundary() {
//        // Prepare test input
//        String input = "1\n"; // Simulate user input "1"
//        InputStream in = new ByteArrayInputStream(input.getBytes());
//        System.setIn(in);
//
//        // Invoke method
//        int result = ConsoleUI.promptForPlayerCount();
//
//        // Verify output
//        assertEquals(1, result, "Expected player count to be 1");
//    }
    
//    @Test
//    public void testPromptForPlayerCount_UpperBoundary() {
//        // Prepare test input
//        String input = "4\n"; // Simulate user input "4"
//        InputStream in = new ByteArrayInputStream(input.getBytes());
//        System.setIn(in);
//
//        // Invoke method
//        int result = ConsoleUI.promptForPlayerCount();
//
//        // Verify output
//        assertEquals(4, result, "Expected player count to be 4");
//    }


//    @Test // 
//    public void testPromptForPlayerCount_InvalidInputThenValidInput() {
//        // Prepare test input
//        String input = "0\n5\n2\n"; // Simulate user input "0", "5", "2"
//        InputStream in = new ByteArrayInputStream(input.getBytes());
//        System.setIn(in);
//
//        // Invoke method
//        int result = ConsoleUI.promptForPlayerCount();
//
//        // Verify output
//        assertEquals(2, result, "Expected player count to be 2");
//    }
    @Test
    public void testPromptForPlayerCount_InvalidInputRepeated() {
        // Prepare test input
        String input = "0\n0\n0\n5\n2\n"; // Simulate user input "0", "0", "0", "5", "2"
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        // Invoke method
        int result = ConsoleUI.promptForPlayerCount();

        // Verify output
        assertEquals(2, result, "Expected player count to be 2");
    }


//@Test
//public void testPromptForPlayerName_UniqueName() {
//    // Prepare test input
//    String input = "David\n"; // Simulate user input "David"
//    InputStream in = new ByteArrayInputStream(input.getBytes());
//    System.setIn(in);
//
//    // Invoke method
//    String result = ConsoleUI.promptForPlayerName(new ArrayList<>(), 0); // Empty nameList
//
//    // Verify output
//    assertEquals("David", result, "Expected player name to be David");
//}

//    @Test
//    public void testPromptForPlayerName_DifferentNames() {
//        // Prepare test input
//        String input = "Alice\nCharlie\n"; // Simulate user input "Alice" and then "Charlie"
//        InputStream in = new ByteArrayInputStream(input.getBytes());
//        System.setIn(in); // Set the input stream
//
//        // Invoke method twice with different nameLists
//        ArrayList<String> nameList1 = new ArrayList<>(Arrays.asList("Bob"));
//        ArrayList<String> nameList2 = new ArrayList<>(Arrays.asList("David"));
//        String result1 = ConsoleUI.promptForPlayerName(nameList1, 0);
//        String result2 = ConsoleUI.promptForPlayerName(nameList2, 1);
//
//        // Verify output
//        assertEquals("Alice", result1, "Expected player name to be Alice");
//        assertEquals("Charlie", result2, "Expected player name to be Charlie");
//    }
    
//    @Test
//    public void testPromptForPlayerName_DuplicateNamePrompt() {
//        // Prepare test input
//        String input = "Charlie\n"; // Simulate user input "Charlie"
//        InputStream in = new ByteArrayInputStream(input.getBytes());
//        System.setIn(in); // Set the input stream
//
//        // Prepare the nameList with a duplicate name
//        ArrayList<String> nameList = new ArrayList<>(Arrays.asList("Alice", "Bob"));
//
//        // Redirect output stream to capture console output
//        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//        System.setOut(new PrintStream(outputStream));
//
//        // Invoke the method
//        String result = ConsoleUI.promptForPlayerName(nameList, 0);
//
//        // Verify output
//        assertEquals("Charlie", result.trim(), "Expected player name to be 'Charlie'");
//    }

//@Test
//
//public void testPromptForPlayerName_EmptyNameList() {
//    // Prepare test input
//    String input = "John\n"; // Simulate user input "John"
//    InputStream in = new ByteArrayInputStream(input.getBytes());
//    System.setIn(in);
//
//    // Invoke method
//    String result = ConsoleUI.promptForPlayerName(new ArrayList<>(), 0); // Empty nameList
//
//    // Verify output
//    assertEquals("John", result, "Expected player name to be John");
//}



//    @Test
//    public void testPromptForPlayerName_MultiplePlayers() {
//        // Prepare test input
//        String input = "Alice\nBob\nCharlie\nDavid\n"; // Simulate user input for four players
//        InputStream in = new ByteArrayInputStream(input.getBytes());
//        System.setIn(in);
//
//        // Invoke method for each player
//        String result1 = ConsoleUI.promptForPlayerName(new ArrayList<>(), 0); // Empty nameList
//        String result2 = ConsoleUI.promptForPlayerName(new ArrayList<>(), 1);
//        String result3 = ConsoleUI.promptForPlayerName(new ArrayList<>(), 2);
//        String result4 = ConsoleUI.promptForPlayerName(new ArrayList<>(), 3);
//
//        // Verify output
//        assertEquals("Alice", result1, "Expected player name to be Alice");
//        assertEquals("Bob", result2, "Expected player name to be Bob");
//        assertEquals("Charlie", result3, "Expected player name to be Charlie");
//        assertEquals("David", result4, "Expected player name to be David");
//    }
//    @Test
//    public void testPromptForPlayerAvatar_SingleAvatar() {
//        // Prepare test input
//        String input = "1\n"; // Simulate user input "1" for the first avatar
//        InputStream in = new ByteArrayInputStream(input.getBytes());
//        System.setIn(in);
//
//        // Prepare list of avatars
//        List<String> avatarList = new ArrayList<>();
//        avatarList.add("Avatar1");
//
//        // Invoke method
//        String result = ConsoleUI.promptForPlayerAvatar(avatarList, 0);
//
//        // Verify output
//        assertEquals("Avatar1", result, "Expected avatar to be Avatar1");
//    }
    
    //Provide an empty list of avatars and ensure the method handles this case gracefully, such as returning null 
    //or throwing an appropriate exception.
    
//    @Test
//    public void testPromptForPlayerAvatar_NoAvatarsAvailable() {
//        // Prepare test input
//        String input = ""; // Simulate no avatars available
//        InputStream in = new ByteArrayInputStream(input.getBytes());
//        System.setIn(in);
//
//        // Prepare empty list of avatars
//        List<String> avatarList = new ArrayList<>();
//
//        // Invoke method
//        String result = ConsoleUI.promptForPlayerAvatar(avatarList, 0);
//     // Print result for debugging
//        System.out.println("Result: " + result);
//
//
//        // Verify output
//        assertNull(result);
//    }
    
    //Simulate user input that is invalid (e.g., a non-integer or out-of-range selection) and verify that the method handles it appropriately,
    //such as reprompting the user or throwing an exception.
    
//    @Test
//    public void testPromptForPlayerAvatar_InvalidInput() {
//        // Prepare test input
//    	String input = "invalid\n"; // Simulate invalid input
//        InputStream in = new ByteArrayInputStream(input.getBytes());
//        System.setIn(in);
//
//        // Prepare list of avatars
//        List<String> avatarList = new ArrayList<>();
//        avatarList.add("Avatar1");
//
//        // Invoke method
//        String result = ConsoleUI.promptForPlayerAvatar(avatarList, 0);
//
//        // Verify output
//        assertNull(result);
//    }
    
    //Boundary Case Test selecting the first and last avatars in the list. 
    //test selecting the first and sixth avatars.
    //failing
//    @Test
//    public void testPromptForPlayerAvatar_FirstAndLastAvatars() {
//        // Prepare test input for selecting first and last avatars
//        String inputFirst = "1\n"; // Select first avatar
//        String inputLast = "6\n"; // Select last avatar
//        InputStream inFirst = new ByteArrayInputStream(inputFirst.getBytes());
//        InputStream inLast = new ByteArrayInputStream(inputLast.getBytes());
//
//        // Prepare list of avatars
//        List<String> avatarList = new ArrayList<>();
//        avatarList.add("Avatar1");
//        avatarList.add("Avatar2");
//        avatarList.add("Avatar3");
//        avatarList.add("Avatar4");
//        avatarList.add("Avatar5");
//        avatarList.add("Avatar6");
//
//        // Invoke method for selecting the first avatar
//        System.setIn(inFirst);
//        String resultFirst = ConsoleUI.promptForPlayerAvatar(avatarList, 0);
//
//        // Verify output for the first avatar
//        assertEquals("Avatar1", resultFirst, "Expected first avatar");
//
//        // Reset input stream
//        System.setIn(System.in);
//
//        // Invoke method for selecting the last avatar
//        System.setIn(inLast);
//        String resultLast = ConsoleUI.promptForPlayerAvatar(avatarList, 0);
//
//        // Verify output for the last avatar
//        assertEquals("Avatar6", resultLast, "Expected last avatar");
//    }
//}
//Test selecting an avatar from a large list (e.g., 100 avatars). This ensures that the method performs efficiently 
//and does not have any performance issues with a large number of avatars.
    
//    @Test
//    public void testPromptForPlayerAvatar_LargeAvatarList() {
//        // Prepare test input
//        StringBuilder inputBuilder = new StringBuilder();
//        for (int i = 1; i <= 100; i++) {
//            inputBuilder.append(i).append("\n"); // Simulate selecting avatar numbers from 1 to 100
//        }
//        String input = inputBuilder.toString();
//        InputStream in = new ByteArrayInputStream(input.getBytes());
//        System.setIn(in);
//
//        // Prepare list of avatars
//        List<String> avatarList = new ArrayList<>();
//        for (int i = 1; i <= 100; i++) {
//            avatarList.add("Avatar" + i);
//        }
//
//        // Invoke method for each avatar
//        for (int i = 0; i < 100; i++) {
//            String result = ConsoleUI.promptForPlayerAvatar(avatarList, i);
//            assertEquals("Avatar" + (i + 1), result, "Expected avatar " + (i + 1));
//        }
//    }
//}
//    

}
