/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ija.project.tests;

import ija.project.parser.Input;
import ija.project.utilities.MoveDisplay;
import java.io.File;
import java.io.IOException;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author janje
 */
public class InputTests {

    private static Input input;
    
    public InputTests() {
    }
    
    @BeforeAll
    public static void setUpClass() throws IOException 
    {
        input = new Input(new File("C:/tmp/input.txt"));
    }
    
    @AfterAll
    public static void tearDownClass() 
    {
        input = null;
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }
    
    @Test
    public void parseFileTest()
    {
        List<MoveDisplay> list = input.getMoves();
        
        System.out.println(list.get(0).getMoveText());
    }
}
