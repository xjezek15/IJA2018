/**
 * IJA 2018/2019
 * Projekt
 * @author Jan Ježek (xjezek15)
 * @author Šimon Šesták (xsesta06)
 */

package ija.project.common;

/**
 * Implements figure behavior.
 * @author xjezek15
 */
public class Figure extends java.lang.Object implements IFigure
{
    private final boolean isBlack;
    private final IFigure.Type type;

    /**
     *
     * @param isBlack
     * @param type
     */
    public Figure(boolean isBlack, IFigure.Type type) 
    {
        this.isBlack = isBlack;
        this.type = type;
    }

    /**
     *
     * @return isBlack
     */
    @Override
    public boolean isBlack()
    {
        return this.isBlack;
    }

    /**
     *
     * @return figure type
     */
    @Override
    public IFigure.Type getType() 
    {
        return this.type;
    }
}
