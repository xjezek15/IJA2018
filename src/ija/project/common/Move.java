/**
 * IJA 2018/2019
 * Projekt
 * @author Jan Ježek (xjezek15)
 */

package ija.project.common;

import java.util.Stack;

public class Move extends java.lang.Object implements IMove
{
    private final IFigure fromFigure;
    private final IFigure capturedFigure;
    private final IField from;
    private final IField to;
   

    public Move(IFigure fromFigure, IField from, IField to, IFigure capturedFigure)
    {
        this.fromFigure = fromFigure;
        this.from = from;
        this.to = to;
        this.capturedFigure = capturedFigure;
    }
    
    @Override
    public IMove deepCopy()
    {
        return new Move(fromFigure, to.deepCopy(), from.deepCopy(), capturedFigure);
    }

    @Override
    public IField getFromField()
    {
        return this.from;
    }

    @Override
    public IField getToField()
    {
        return this.to;
    }

    @Override
    public IFigure getFromFigure()
    {
        return this.fromFigure;
    }

    @Override
    public IFigure getCapturedFigure()
    {
        return this.capturedFigure;
    }

    @Override
    public IMove push(Stack<IMove> stack)
    {
        return stack.push(this);
    }
}
