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
    private final boolean check;
   

    public Move(IFigure fromFigure, IField from, IField to, IFigure capturedFigure, boolean check)
    {
        this.fromFigure = fromFigure;
        this.from = from;
        this.to = to;
        this.capturedFigure = capturedFigure;
        this.check = check;
    } 

    public boolean isCheck() {
        return check;
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
