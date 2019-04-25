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

    public FigureClass(boolean isBlack, Figure.Type type) 
    {
        this.isBlack = isBlack;
        this.type = type;
    }

    public boolean isBlack()
    {
        return this.isBlack;
    }

    public Figure.Type getType() 
    {
        return this.type;
    }
}
