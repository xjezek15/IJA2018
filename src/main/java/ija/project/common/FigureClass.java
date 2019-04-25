/**
 * IJA 2018/2019
 * Úkol 2
 * @author Jan Ježek (xjezek15)
 */

package ija.project.common;

public class FigureClass extends java.lang.Object implements Figure
{
    private boolean isBlack;
    private Figure.Type type;
    private int[] location;

    public FigureClass(boolean isBlack, Figure.Type type, int[] location) 
    {
        this.isBlack = isBlack;
        this.type = type;
        this.location = location;
    }
    
    public void updateLocation(int[] location)
    {
        // possibly some checks for correct location
        this.location = location;
    }

    public boolean isBlack()
    {
        return this.isBlack;
    }

    public Figure.Type getType() 
    {
        return this.type;
    }

    @Override
    public String getState()
    {
        String colour;
        String type;
        if (this.isBlack)
        {
            colour = "B";
        }
        else
        {
            colour = "W";
        }

        if (this.type == Figure.Rook)
        {
            type = "V";
        }
        else
        {
            type = "P";
        }

        int[] outLocation = new int[2];
        outLocation[0] = this.location[0] + 1;
        outLocation[1] = this.location[1] + 1;

        return type + "[" + colour + "]" + outLocation[0] + ":" + outLocation[1];
    }
}
