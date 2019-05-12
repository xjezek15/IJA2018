/**
 * IJA 2018/2019
 * Projekt
 * @author Jan Ježek (xjezek15)
 */

package ija.project.parser;

import ija.project.GameFactory;
import ija.project.common.IFigure;
import ija.project.common.IFigure.Type;
import ija.project.common.IGame;
import ija.project.game.Board;
import ija.project.game.IBoard;
import ija.project.utilities.Location;
import ija.project.utilities.MoveDisplay;
import ija.project.utilities.ParsedMove;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Input implements IInput
{
    private List<MoveDisplay> list;
    private int movesCounter = 1;
    private final char[] figures = new char[] {'K', 'D', 'V', 'S', 'J', 'p'};
    private File file = null;
    
    public Input(File file)
    {
        this.file = file;
    }
    
    @Override
    public void saveMoves(File file) throws IOException
    {  
        try (FileWriter fileWriter = new FileWriter(file)) 
        {
            for(MoveDisplay move : list)
                fileWriter.write(move.getMoveText() + "\n");
        }        
    }
    
    @Override
    public List<MoveDisplay> getMoves()
    {
        return this.list;
    }
    
    @Override
    public void loadMoves() throws IOException 
    {
        IBoard board = new Board(8);
        List<MoveDisplay> moveDisplayList = new ArrayList<>();
        
        FileInputStream fileInputStream = new FileInputStream(this.file);
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream))) 
        {
            String line;
            while ((line = bufferedReader.readLine()) != null) 
            {
                String[] components = line.split(" ");
                if (components.length != 3) return;

                // check if number is correct
                if (components[0].charAt(components[0].length() - 1) != '.') return;
                if (this.movesCounter != Integer.parseInt(components[0].substring(0, components[0].length() - 1))) return;

                ParsedMove whiteMove = parseMove(components[1]);
                ParsedMove blackMove = parseMove(components[2]);                                                            
                if (whiteMove == null || blackMove == null) return;
                               
                moveDisplayList.add(new MoveDisplay(line, whiteMove, blackMove));

                this.movesCounter++;
            }
        }
            
        this.list = moveDisplayList;
    }
    
    @Override
    public boolean addMove(MoveDisplay move)
    {
        return list.add(move);
       
    }   
    
    private static enum State
    {
        START, 
        FROM_FIGURE_TYPE, FROM_FIGURE_COLUMN, FROM_FIGURE_ROW, 
        SHORT_END, 
        TO_FIGURE, TO_FIGURE_COLUMN, TO_FIGURE_ROW,
    }
    
    private Type figureExists(char element)
    {
        for (int index = 0; index < figures.length; index++) 
        {
            if (element == figures[index])
            {
                switch(element)
                {
                    case 'K':
                        return IFigure.KING;
                        
                    case 'D':
                        return IFigure.QUEEN;
                        
                    case 'V':
                        return IFigure.ROOK;
                        
                    case 'S':
                        return IFigure.BISHOP;
                        
                    case 'J':
                        return IFigure.KNIGHT;
                        
                    case 'p':
                        return IFigure.PAWN;
                }
            }         
        }
        
        return null;
    }
    
    @Override
    public ParsedMove parseMove(String move)
    {
        State state = State.START;
        Type figureTypeFrom = null;
        Type figureTypeTo = null;
        
        char columnFrom = 0;
        int rowFrom = 0;
        char columnTo = 0;
        int rowTo = 0;
        
        boolean capture = false;
        boolean check = false;
        boolean mate = false;
        
        for (int index = 0; index < move.length(); index++)
        {
            char element = move.charAt(index);        
            
            switch (state)
            {
                case START:
                    figureTypeFrom = figureExists(element);
                    if (figureTypeFrom == null)
                    {
                        figureTypeFrom = IFigure.PAWN;
                        index--;
                    }   
                    state = State.FROM_FIGURE_TYPE;
                    break;
                case FROM_FIGURE_TYPE:
                    if (element >= 'a' && element <= 'h')
                    {
                        columnFrom = element;
                        state = State.FROM_FIGURE_COLUMN;
                    }
                    else if (element == 'x')
                    {
                        if (capture) 
                            return null;
                        else 
                            capture = true;
                    }   
                    else return null;
                    break;
                case FROM_FIGURE_COLUMN:
                    if (element >= '1' && element <= '8')
                    {
                        rowFrom = Integer.parseInt(String.valueOf(element));
                        state = State.FROM_FIGURE_ROW;
                    }
                    else return null;
                    break;
                case FROM_FIGURE_ROW:
                    switch (element) 
                    {
                        case '+':
                            check = true;
                            state = State.SHORT_END;
                            break;
                        case '#':
                            mate = true;
                            state = State.SHORT_END;
                            break;
                        default:                            
                            state = State.TO_FIGURE;
                            index--;
                            break;
                    }
                    break;
                case TO_FIGURE:
                    if (element >= 'a' && element <= 'h')
                    {
                        columnTo = element;
                        state = State.TO_FIGURE_COLUMN;
                    }
                    else if (element == 'x')
                    {                   
                        if (capture) 
                            return null;
                        else 
                            capture = true;
                    }   
                    else 
                        return null;
                    break;
                case TO_FIGURE_COLUMN:
                    if (element >= '1' && element <= '8')
                    {
                        rowTo = Integer.parseInt(String.valueOf(element));
                        state = State.TO_FIGURE_ROW;
                    }
                    else return null;
                    break;
                case TO_FIGURE_ROW:
                    switch (element) 
                    {
                        case '+':
                            check = true;
                            break;
                        case '#':
                            mate = true;
                            break;
                        default:
                            return null;
                    }
                default:
                    return null;                   
            }
        }
        
        if (state == State.FROM_FIGURE_COLUMN || state == State.SHORT_END)
        {
            if (columnFrom == 0 || rowFrom == 0) return null;               
            
            Location locationTo = new Location(Location.determineColumn(columnFrom), rowFrom);
            
            return new ParsedMove(figureTypeFrom, locationTo, capture, check, mate);
        }
        else if (state == State.TO_FIGURE_ROW)
        {
            if (columnFrom == 0 || rowFrom == 0) return null;   
            if (columnTo == 0   || rowTo == 0) return null;               
            
            Location locationFrom = new Location(Location.determineColumn(columnFrom), rowFrom);
            Location locationTo = new Location(Location.determineColumn(columnTo), rowTo);
            
            return new ParsedMove(figureTypeFrom, figureTypeTo, locationFrom, locationTo, capture, check, mate);
        }
        
        return null;
    }
}
