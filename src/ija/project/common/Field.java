/**
 * IJA 2018/2019
 * Projekt
 * @author Jan Ježek (xjezek15)
 */

package ija.project.common;

import ija.project.utilities.Location;

public class Field extends java.lang.Object implements IField
{
    private IFigure figure;
    private final IField surroundingFields[];
    private final Location location;
    
    public Field(int col, int row)
    {
        this.figure = null;
        this.surroundingFields = new IField[8];
        this.location = new Location(col, row);
    }
    
    public Field(IFigure figure, IField[] surroundingFields, Location location)
    {
        this.figure = figure;
        this.surroundingFields = surroundingFields;
        this.location = location;
    }
    
    private IField[] deepCopySurroundingFields()
    {
        IField[] copy = new IField[8];
        
        for (int i = 0; i < copy.length; i++) 
        {
            if (surroundingFields[i] != null)
                copy[i] = surroundingFields[i].deepCopy();         
        }
        
        return copy;
    }
    
    @Override
    public IField deepCopy()
    {
        return new Field(this.figure, this.surroundingFields, this.location);        
    }
    
    @Override
    public IFigure swapFigure(IFigure figure)
    {
        IFigure old = this.figure;
        this.figure = figure;
        return old;
    }

    @Override
    public IField[] getSurroundingFields() 
    {
        return this.surroundingFields;
    }
   
    @Override
    public boolean isEmpty()
    {
        return this.figure == null;
    }

    @Override
    public Location getLocation()
    {
        return this.location;
    }

    @Override
    public IFigure getFigure()
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
    
    @Override
    public boolean putFigure(IFigure figure)
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

    @Override
    public boolean removeFigure(IFigure figure)
    {
        if (isEmpty() || !this.figure.equals(figure))
        {
            return false;
        }

        this.figure = null;
        return true;
    }

    @Override
    public void addNextField(IField.Direction dirs, IField field)
    {
        if (nextField(dirs) == null)
        {
            surroundingFields[determineDirection(dirs)] = field;
        }
    }

    @Override
    public IField nextField(IField.Direction dirs)
    {
        int directionIndex = determineDirection(dirs);
        if (directionIndex == -1)
        {
            return null;
        }

        return surroundingFields[directionIndex];
    }

    private int determineDirection(IField.Direction dirs)
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
            if (this.figure.getType() == IFigure.ROOK)
            {
                type = "R";
            }
            else if (this.figure.getType() == IFigure.PAWN)
            {
                type = "P";
            }
            else if (this.figure.getType() == IFigure.KNIGHT)
            {
                type = "K";
            }
            else if (this.figure.getType() == IFigure.BISHOP)
            {
                type = "B";
            }
            else if (this.figure.getType() == IFigure.QUEEN)
            {
                type = "Q";
            }
            else if (this.figure.getType() == IFigure.KING)
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