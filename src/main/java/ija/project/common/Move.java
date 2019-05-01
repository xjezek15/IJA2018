/**
 * IJA 2018/2019
 * Projekt
 * @author Jan Ježek (xjezek15)
 */

package ija.project.common;

import java.util.Stack;

public class Move extends java.lang.Object implements IMove
{
    private IFigure fromFigure;
    private IFigure capturedFigure;
    private IField from;
    private IField to;
   

    public Move(IFigure fromFigure, IField from, IField to, IFigure capturedFigure)
    {
        this.fromFigure = fromFigure;
        this.from = from;
        this.to = to;
        this.capturedFigure = capturedFigure;
    }

    public IField getFromField()
    {
        return this.from;
    }

    public IField getToField()
    {
        return this.to;
    }

    public IFigure getFromFigure()
    {
        return this.fromFigure;
    }

    public IFigure getCapturedFigure()
    {
        return this.capturedFigure;
    }

    public IMove push(Stack<IMove> stack)
    {
        return stack.push(this);
    }
}
