/**
 * IJA 2018/2019
 * Projekt
 * @author Jan Ježek (xjezek15)
 * @author Šimon Šesták (xsesta06)
 */

package ija.project.common;

/**
 * Implements functionality for moving figures.
 * @author xjezek15
 */
public class Move extends java.lang.Object implements IMove
{
    private final IFigure fromFigure;
    private final IFigure capturedFigure;
    private final IField from;
    private final IField to;
   
    /**
     *
     * @param fromFigure
     * @param from
     * @param to
     * @param capturedFigure
     */
    public Move(IFigure fromFigure, IField from, IField to, IFigure capturedFigure)
    {
        this.fromFigure = fromFigure;
        this.from = from;
        this.to = to;
        this.capturedFigure = capturedFigure;
    } 

    /**
     * 
     * @return field
     */
    @Override
    public IField getFromField()
    {
        return this.from;
    }

    /**
     *
     * @return field
     */
    @Override
    public IField getToField()
    {
        return this.to;
    }

    /**
     *
     * @return figure
     */
    @Override
    public IFigure getFromFigure()
    {
        return this.fromFigure;
    }

    /**
     *
     * @return figure
     */
    @Override
    public IFigure getCapturedFigure()
    {
        return this.capturedFigure;
    }
}
