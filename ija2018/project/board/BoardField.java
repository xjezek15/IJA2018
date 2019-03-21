/**
 * IJA 2018/2019
 * Projekt
 * @author Jan Ježek (xjezek15)
 */

package ija2018.project.board;

public class BoardField extends java.lang.Object implements Field
{
    private Figure figure;
    private Field surroundingFields[];
    private int col;
    private int row;
    private boolean isBlack;
    
    public BoardField(int col, int row, boolean isBlack)
    {
        this.col = col;
        this.row = row;
        this.isBlack = isBlack;

        this.figure = null;
        
        this.surroundingFields = new Field[8];
    }

    public boolean isEmpty()
    {
        return this.figure == null;
    }

    public int[] getLocation()
    {
        int[] location = new int[2];
        location[0] = this.col;
        location[1] = this.row;
        return location;
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
        if (!isEmpty())
        {
            return false;
        }
        else
        {
            this.figure = figure;
            return true;
        }
    }

    public boolean remove(Figure figure)
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
}