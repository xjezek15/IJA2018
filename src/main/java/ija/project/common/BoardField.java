/**
 * IJA 2018/2019
 * Projekt
 * @author Jan Ježek (xjezek15)
 */

package ija.project.common;

import ija.project.utilities.Location;

public class BoardField extends java.lang.Object implements Field
{
    private Figure figure;
    private Field surroundingFields[];
    private Location location;
    private boolean isBlack;
    
    public BoardField(int col, int row, boolean isBlack)
    {
        this.figure = null;
        this.surroundingFields = new Field[8];
         
        this.location = new Location(col, row);
        this.isBlack = isBlack;
    }

    public boolean isEmpty()
    {
        return this.figure == null;
    }

    public Location getLocation()
    {
        return this.location;
    }

    public Figure getFigure()
    {
        if (isEmpty())
        {
            return null;
        }
        else
        {
            return this.figure;
        }
    }
    
    public boolean putFigure(Figure figure)
    {
        if (isEmpty())
        {
            this.figure = figure;
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean removeFigure(Figure figure)
    {
        if (isEmpty() || !this.figure.equals(figure))
        {
            return false;
        }

        this.figure = null;
        return true;
    }

    public void addNextField(Field.Direction dirs, Field field)
    {
        if (nextField(dirs) == null)
        {
            surroundingFields[determineDirection(dirs)] = field;
        }
    }

    public Field nextField(Field.Direction dirs)
    {
        int directionIndex = determineDirection(dirs);
        if (directionIndex == -1)
        {
            return null;
        }

        return surroundingFields[directionIndex];
    }

    private int determineDirection(Field.Direction dirs)
    {
        // 0 = D, 1 = L, 2 = LD, 3 = LU, 4 = R, 5 = RD, 6 = RU, 7 = U
        switch (dirs) 
        {
            case D:
                return 0;
        
            case L:
                return 1;

            case LD:
                return 2;

            case LU:
                return 3;

            case R:
                return 4;

            case RD:
                return 5;

            case RU:
                return 6;

            case U:
                return 7;
        }

        return -1;
    }
    
    @Override
    public String getState()
    {
        String colour;
        String type;
        
        if (this.figure == null)
        {
            colour = "E";       
        }
        else if (this.figure.isBlack())
        {
            colour = "B";
        }
        else
        {
            colour = "W";
        }
        
        if (this.figure != null)
        {
            if (this.figure.getType() == Figure.Rook)
            {
                type = "R";
            }
            else if (this.figure.getType() == Figure.Pawn)
            {
                type = "P";
            }
            else if (this.figure.getType() == Figure.Knight)
            {
                type = "K";
            }
            else if (this.figure.getType() == Figure.Bishop)
            {
                type = "B";
            }
            else if (this.figure.getType() == Figure.Queen)
            {
                type = "Q";
            }
            else if (this.figure.getType() == Figure.King)
            {
                type = "KK";
            }
            else
            {
                type = "O";
            }
        }
        else
        {
            type = "E";
        } 
        
        int col = this.location.getCol() + 1;
        int row = this.location.getRow() + 1;
        
        return type + "[" + colour + "]" + col + ":" + row;
    }
}