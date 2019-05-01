/**
 * IJA 2018/2019
 * Projekt
 * @author Jan Ježek (xjezek15)
 */

package ija.project;

import ija.project.game.Board;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import ija.project.common.IGame;
import ija.project.game.IBoard;

public class project 
{
    public project() 
    {
        
    }
    
    private IBoard board;
    private IGame game;
    
    @BeforeEach
    public void initialize()
    {
        board = new Board(8);
        game = GameFactory.createChessGame(board);
    }
    
    @AfterEach
    public void tearDown()
    {
        board = null;
        game = null;
    }
    
    @Test
    public void allFiguresAreInPlace()
    {                
        for (int col = 1; col <= 8; col++)
        {
            // checks if all white pawns are in place
            String expected;
            expected = "P[W]";
            expected = expected.concat(Integer.toString(col));
            expected = expected.concat(":2");          
            assertEquals(expected, board.getField(col, 2).getState());  
            
            // checks if middle of board is empty
            assertTrue(board.getField(col, 3).isEmpty());
            assertTrue(board.getField(col, 4).isEmpty());
            assertTrue(board.getField(col, 5).isEmpty());
            assertTrue(board.getField(col, 6).isEmpty());
            
            // checks if all black pawns are in place
            expected = "P[B]";
            expected = expected.concat(Integer.toString(col));
            expected = expected.concat(":7");          
            assertEquals(expected, board.getField(col, 7).getState());  
        }
        
        // checks if rooks are in place
        assertEquals("R[W]1:1", board.getField(1, 1).getState());    
        assertEquals("R[W]8:1", board.getField(8, 1).getState());   
        assertEquals("R[B]1:8", board.getField(1, 8).getState());   
        assertEquals("R[B]8:8", board.getField(8, 8).getState());
        
        // checks if bishops are in place
        assertEquals("B[W]2:1", board.getField(2, 1).getState());    
        assertEquals("B[W]7:1", board.getField(7, 1).getState());   
        assertEquals("B[B]2:8", board.getField(2, 8).getState());   
        assertEquals("B[B]7:8", board.getField(7, 8).getState());
        
        // checks if knights are in place
        assertEquals("K[W]3:1", board.getField(3, 1).getState());    
        assertEquals("K[W]6:1", board.getField(6, 1).getState());   
        assertEquals("K[B]3:8", board.getField(3, 8).getState());   
        assertEquals("K[B]6:8", board.getField(6, 8).getState());
        
        // checks if queens are in place
        assertEquals("Q[W]4:1", board.getField(4, 1).getState());    
        assertEquals("Q[B]4:8", board.getField(4, 8).getState());   
        
        // checks if kings are in place
        assertEquals("KK[W]5:1", board.getField(5, 1).getState());   
        assertEquals("KK[B]5:8", board.getField(5, 8).getState());
    }
    
    @Test
    public void movePawnForward()
    {
        assertFalse(game.move(board.getField(1, 2), board.getField(4, 3)));
        assertFalse(game.move(board.getField(4, 2), board.getField(4, 1)));
        assertFalse(game.move(board.getField(4, 2), board.getField(4, 2)));
        assertFalse(game.move(board.getField(4, 2), board.getField(3, 3)));
        assertFalse(game.move(board.getField(4, 2), board.getField(5, 3)));
        
        assertTrue(game.move(board.getField(4, 2), board.getField(4, 3)));
        assertEquals("E[E]4:2", board.getField(4, 2).getState());   
        assertEquals("P[W]4:3", board.getField(4, 3).getState());   
        
        assertTrue(game.move(board.getField(3, 2), board.getField(3, 4)));
        assertEquals("E[E]3:2", board.getField(3, 2).getState());   
        assertEquals("P[W]3:4", board.getField(3, 4).getState());   
        
        assertTrue(game.move(board.getField(4, 7), board.getField(4, 6)));
        assertEquals("E[E]4:7", board.getField(4, 7).getState());   
        assertEquals("P[B]4:6", board.getField(4, 6).getState());   
        
        assertTrue(game.move(board.getField(5, 7), board.getField(5, 5)));
        assertEquals("E[E]5:7", board.getField(5, 7).getState());   
        assertEquals("P[B]5:5", board.getField(5, 5).getState());   
    }
    
    @Test
    public void capturingFigures()
    {
        // move white pawn
        assertTrue(game.move(board.getField(4, 2), board.getField(4, 4)));        
        
        // move black pawn
        assertTrue(game.move(board.getField(3, 7), board.getField(3, 5)));
        
        // capture black pawn
        assertTrue(game.move(board.getField(4, 4), board.getField(3, 5)));
         
        assertEquals("E[E]4:4", board.getField(4, 4).getState());  
        assertEquals("P[W]3:5", board.getField(3, 5).getState());  
        
        game.undo();
        
        assertEquals("P[W]4:4", board.getField(4, 4).getState());  
        assertEquals("P[B]3:5", board.getField(3, 5).getState());  
    }
}
