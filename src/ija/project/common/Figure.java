/**
 * IJA 2018/2019
 * Projekt
 * @author Jan Ježek (xjezek15)
 */

package ija.project.common;

public class Figure extends java.lang.Object implements IFigure
{
    private final boolean isBlack;
    private final IFigure.Type type;

    public Figure(boolean isBlack, IFigure.Type type) 
    {
        this.isBlack = isBlack;
        this.type = type;
    }

    public boolean isBlack()
    {
        return this.isBlack;
    }

    public IFigure.Type getType() 
    {
        return this.type;
    }
}
