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
    private IField surroundingFields[];
    private Location location;
    private boolean isBlack;
    
    public Field(int col, int row, boolean isBlack)
    {
        this.figure = null;
        this.surroundingFields = new IField[8];
         
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

    public boolean removeFigure(IFigure figure)
    {
        if (isEmpty() || !this.figure.equals(figure))
        {
            return false;
        }

        this.figure = null;
        return true;
    }

    public void addNextField(IField.Direction dirs, IField field)
    {
        if (nextField(dirs) == null)
        {
            surroundingFields[determineDirection(dirs)] = field;
        }
    }

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
            if (this.figure.getType() == IFigure.Rook)
            {
                type = "R";
            }
            else if (this.figure.getType() == IFigure.Pawn)
            {
                type = "P";
            }
            else if (this.figure.getType() == IFigure.Knight)
            {
                type = "K";
            }
            else if (this.figure.getType() == IFigure.Bishop)
            {
                type = "B";
            }
            else if (this.figure.getType() == IFigure.Queen)
            {
                type = "Q";
            }
            else if (this.figure.getType() == IFigure.King)
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