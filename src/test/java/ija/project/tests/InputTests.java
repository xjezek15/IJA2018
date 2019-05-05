/**
 * IJA 2018/2019
 * Projekt
 * @author Jan Ježek (xjezek15)
 */

package ija.project.tests;

import ija.project.GameFactory;
import ija.project.game.Board;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import ija.project.common.IGame;
import ija.project.game.IBoard;
import ija.project.parser.IInput;
import ija.project.parser.Input;
import java.io.File;
import java.io.IOException;

public class InputTests 
{
    public InputTests() 
    {
        
    }
    
    private IInput input;
    
    @BeforeEach
    public void initialize()
    {
        input = new Input();
    }
    
    @AfterEach
    public void tearDown()
    {
        input = null;
    }
    
    @Test
    public void parseFileTest() throws IOException
    {
        File file = new File("C:/tmp/input.txt");
        
        input.loadMoves(file);
    }
}
