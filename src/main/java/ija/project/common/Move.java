/**
 * IJA 2018/2019
 * Úkol 2
 * @author Jan Ježek (xjezek15)
 */

package ija.project.common;

import java.util.Stack;

public class Move extends java.lang.Object
{
    private Figure movedFigure;
    private Field from;
    private Field moveTo;
    private Figure capturedFigure;

    public Move(Figure movedFigure, Field from, Field moveTo, Figure capturedFigure)
    {
        this.movedFigure = movedFigure;
        this.from = from;
        this.moveTo = moveTo;
        this.capturedFigure = capturedFigure;
    }

    public Field getFromField()
    {
        return this.from;
    }

    public Field getMoveToField()
    {
        return this.moveTo;
    }

    public Figure getFigure()
    {
        return this.movedFigure;
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
