/**
 * IJA 2018/2019
 * Projekt
 * @author Jan Ježek (xjezek15)
 */

package ija.project.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
    public void initialize() throws IOException
    {
        input = new Input(new File("C:/tmp/input.txt"));
    }
    
    @AfterEach
    public void tearDown()
    {
        input = null;
    }
    
    @Test
    public void parseFileTest()
    {       
        var list = input.getListOfMoves();
    }
}
