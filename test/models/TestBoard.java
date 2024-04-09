
package models;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;


public class TestBoard {
	
	public class BoardTest {
		@Test
	    public void testGetSquareValidPosition() {
	        Board board = new Board();
	        Square square1 = board.getSquare(0);
	        Square square2 = board.getSquare(5);
	        assertEquals("Go", square1.getName());
	        assertTrue(square2 instanceof InvestmentSquare);
	    }
		
		 @Test
		    public void testGetSquare_PositionBeyondSize() {
		        Board board = new Board();
		        Square square = board.getSquare(12); // 12 % 12 = 0, equivalent to position 0
		        assertEquals("Go", square.getName());
		    }
	
	@Test
    public void testBoardSize() {
        Board board = new Board();
        
        // Test that the board size matches the number of squares added during initialization
        assertEquals(12, board.getSize());
    }
	@Test
    public void testSquareTypes() {
        Board board = new Board();
        assertTrue(board.getSquare(0) instanceof GoSquare);
        assertTrue(board.getSquare(6) instanceof EcoZoneSquare);
        assertTrue(board.getSquare(3) instanceof InvestmentSquare);
    }
}
}
