/**
 * IJA 2018/2019
 * Úkol 2
 * @author Jan Ježek (xjezek15)
 */

package ija.project.common;

import java.util.Stack;

public class Move extends java.lang.Object
{
    private Figure fromFigure;
    private Field from;
    private Field to;
    private Figure capturedFigure;

    public Move(Figure fromFigure, Field from, Field to, Figure capturedFigure)
    {
        this.fromFigure = fromFigure;
        this.from = from;
        this.to = to;
        this.capturedFigure = capturedFigure;
    }

    public Field getFromField()
    {
        return this.from;
    }

    public Field getToField()
    {
        return this.to;
    }

    public Figure getFromFigure()
    {
        return this.fromFigure;
    }

    public Figure getCapturedFigure()
    {
        return this.capturedFigure;
    }

    public Move push(Stack<Move> stack)
    {
        return stack.push(this);
    }
}
