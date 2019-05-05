/**
 * IJA 2018/2019
 * Projekt
 * @author Jan Ježek (xjezek15)
 */

package ija.project.parser;

import ija.project.GameFactory;
import ija.project.common.IField;
import ija.project.common.IFigure;
import ija.project.common.IFigure.Type;
import ija.project.common.IGame;
import ija.project.common.IMove;
import ija.project.game.Board;
import ija.project.game.IBoard;
import ija.project.utilities.Location;
import ija.project.utilities.MoveDisplay;
import ija.project.utilities.ParsedMove;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Input implements IInput
{
    private List<MoveDisplay> list;
    private int movesCounter = 1;
    private final char[] figures = new char[] {'K', 'D', 'V', 'S', 'J', 'p'};
    
    public Input(File file) throws IOException 
    {
        this.list = loadMoves(file);
    }
    
    private List<MoveDisplay> loadMoves(File file) throws IOException 
    {
        IBoard board = new Board(8);
        IGame game = GameFactory.createChessGame(board);  
        List<MoveDisplay> moveDisplayList = new ArrayList<>();
        
        FileInputStream fileInputStream = new FileInputStream(file);
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream))) 
        {
            String line;
            while ((line = bufferedReader.readLine()) != null) 
            {
                String[] components = line.split(" ");
                if (components.length != 3) return null;

                // check if number is correct
                if (components[0].charAt(components[0].length() - 1) != '.') return null;
                if (this.movesCounter != Integer.parseInt(components[0].substring(0, components[0].length() - 1))) return null;

                ParsedMove whiteMove = parseMove(components[1]);
                ParsedMove blackMove = parseMove(components[2]);                                                            
                if (whiteMove == null || blackMove == null) return null;

                boolean check = false;
                boolean mate  = false;

                if (whiteMove.isCheck() || blackMove.isCheck()) check = true;
                if (whiteMove.isMate() || blackMove.isMate()) mate = true;
                
                IField fieldFrom;
                IField fieldTo;
                
                // white plays
                if (whiteMove.getLocationFrom() == null)
                {
                    fieldTo = board.getField(whiteMove.getLocationTo().getCol(), whiteMove.getLocationTo().getRow());
                  //  game.move(fieldTo);
                    
                    throw new UnsupportedOperationException("Short moves not implemented.");
                }
                else
                {
                    fieldFrom = board.getField(whiteMove.getLocationFrom().getCol(), whiteMove.getLocationFrom().getRow());
                    fieldTo = board.getField(whiteMove.getLocationTo().getCol(), whiteMove.getLocationTo().getRow());
                    
                    if (fieldFrom.getFigure().getType() != whiteMove.getFigureTypeFrom()) return null;
                    
                    game.move(fieldFrom, fieldTo);
                }
                
                // black plays
                if (blackMove.getLocationFrom() == null)
                {                                       
                    fieldTo = board.getField(blackMove.getLocationTo().getCol(), blackMove.getLocationTo().getRow());
                   // game.move(fieldTo);
                    
                    throw new UnsupportedOperationException("Short moves not implemented.");
                }
                else
                {
                    fieldFrom = board.getField(blackMove.getLocationFrom().getCol(), blackMove.getLocationFrom().getRow());
                    fieldTo = board.getField(blackMove.getLocationTo().getCol(), blackMove.getLocationTo().getRow());
                    
                    if (fieldFrom.getFigure().getType() != blackMove.getFigureTypeFrom()) return null;
                    
                    game.move(fieldFrom, fieldTo);
                }
               
                moveDisplayList.add(new MoveDisplay(line, check, mate, board));


                this.movesCounter++;
            }
        }
            
        return moveDisplayList;
    }
    
    private static enum State
    {
        START, 
        FROM_FIGURE_TYPE, FROM_FIGURE_COLUMN, FROM_FIGURE_ROW, 
        SHORT_END, 
        TO_FIGURE_TYPE, TO_FIGURE_COLUMN, TO_FIGURE_ROW,
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
                        
                    case 'P':
                        return IFigure.PAWN;
                }
            }         
        }
        
        return null;
    }
    
    private ParsedMove parseMove(String move)
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
                        if (capture) return null;
                            else capture = true;
                    }   
                    else return null;
                    break;
                case FROM_FIGURE_COLUMN:
                    if (element >= 1 && element <= 8)
                    {
                        rowFrom = element;
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
                            if (capture) return null;
                            
                            figureTypeTo = figureExists(element);
                            if (figureTypeTo == null)
                            {
                                figureTypeTo = IFigure.PAWN;
                                index--;
                            }
                            state = State.TO_FIGURE_TYPE;
                            break;
                    }
                    break;
                case TO_FIGURE_TYPE:
                    if (element >= 'a' && element <= 'h')
                    {
                        columnTo = element;
                        state = State.TO_FIGURE_COLUMN;
                    }
                    else if (element == 'x')
                    {                   
                        if (capture) return null;
                            else capture = true;
                    }   
                    else return null;
                    break;
                case TO_FIGURE_COLUMN:
                    if (element >= 1 && element <= 8)
                    {
                        rowTo = element;
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
