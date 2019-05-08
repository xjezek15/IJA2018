/**
 * IJA 2018/2019
 * Projekt
 * @author Jan Ježek (xjezek15)
 */

package ija.project.common;

import static java.lang.Math.abs;
import java.util.Stack;

public class Game extends java.lang.Object implements IGame
{
    Stack<IMove> moveStack;

    public Game()
    {
        moveStack = new Stack<>();
    }
    
    @Override
    public IMove getLastMove()
    {
        if (moveStack.empty())
            return null;
                    
        return moveStack.lastElement();
    }

    @Override
    public void undo()
    {
        if (moveStack.empty()) return;
        
        IMove move = moveStack.pop();

        IField from = move.getFromField();
        from.putFigure(move.getFromFigure());
        IField to = move.getToField();
        to.removeFigure(move.getFromFigure());
        if (move.getCapturedFigure() != null)
        {
            to.putFigure(move.getCapturedFigure());   
        }           
    }
    
    @Override
    public boolean move(boolean isBlack, Figure.Type type, IField to)
    {
        return doMove(isBlack, type, to);
    }

    @Override
    public boolean move(IField from, IField to)
    {
        if (from == null) return false;
        
        IFigure fromFigure = from.getFigure();

        if (fromFigure == null) return false;
        
        if (fromFigure.getType().equals(IFigure.PAWN))
        {
            int absValue = abs(from.getLocation().getCol() - to.getLocation().getCol());
            if (absValue != 1 && absValue != 0)
                return false;
        }
                   
        return doMove(fromFigure.isBlack(), fromFigure.getType(), to);
    }
    
    private boolean doMove(boolean isBlack, Figure.Type type, IField to)
    {
        if (to == null) return false;
        
        IField from = null;
        boolean toEmpty = to.isEmpty();

        // check if figure can move
        if (type == IFigure.PAWN)
        {
            from = canMovePawn(isBlack, to);
        }
        else if (type == IFigure.ROOK)
        {
            from = canMove(isBlack, type, to, to, IField.D);
        }
        else if (type == IFigure.BISHOP)
        {
            from = canMove(isBlack, type, to, to, IField.LD);
        }
        else if (type == IFigure.QUEEN)
        {
            from = canMove(isBlack, type, to, to, IField.D);
        }
        else if (type == IFigure.KING)
        {
            from = canMoveKing(isBlack, to, IField.D);
        }
        else if (type == IFigure.KNIGHT)
        {
            throw new UnsupportedOperationException("Move with Knight not implemented");
        }

        if (from != null)
        {
            IFigure capturedFigure = null;

            // to is not empty
            if (!toEmpty)
            {
                if (isBlack)
                {
                    if (!to.getFigure().isBlack())
                    {
                        capturedFigure = to.getFigure();
                        to.removeFigure(capturedFigure);
                    }
                    else
                        return false;
                }
                else
                {
                    if (to.getFigure().isBlack())
                    {
                        capturedFigure = to.getFigure();
                        to.removeFigure(capturedFigure);
                    }
                    else
                        return false;
                }
            }
            
            IFigure fromFigure = from.getFigure();

            if (from.removeFigure(fromFigure))
            {
                if (to.putFigure(fromFigure))
                {
                    IMove move = new Move(fromFigure, from, to, capturedFigure);
                    this.moveStack.push(move);
                    return true;
                }
            }
        }

        return false;
    }
    
    private IField canMovePawnForward(boolean isBlack, IField to)
    {
        // to is not empty
        if (!to.isEmpty()) 
            return null;

        IField.Direction direction = isBlack ? IField.Direction.U : IField.Direction.D;
        
        IField nextField = to.nextField(direction);
        
        if (nextField == null) 
            return null;        
        if (nextField.isEmpty())
        {
            nextField = nextField.nextField(direction);
            
            if (nextField == null || nextField.isEmpty()) 
                return null;   
            
            if (nextField.getFigure().getType() == IFigure.PAWN && nextField.getFigure().isBlack() == isBlack)
                return nextField;
            else
                return null;
        }
        else
        {
            if (nextField.getFigure().getType() == IFigure.PAWN && nextField.getFigure().isBlack() == isBlack) 
                return nextField;
            else
                return null;
        }
    }
    
    private IField canMovePawnSideways(boolean fromIsBlack, IField to)
    {
        IField.Direction directionL = fromIsBlack ? IField.Direction.LU : IField.Direction.LD;
        IField.Direction directionR = fromIsBlack ? IField.Direction.RU : IField.Direction.RD;
        
        if (to.isEmpty()) 
            return null;
        
        boolean toIsBlack = to.getFigure().isBlack();
        
        if (fromIsBlack != !toIsBlack) 
            return null;
        
        IField nextField = to.nextField(directionL);
        
        if (!nextField.isEmpty())
        {
            if (nextField.getFigure().getType() == IFigure.PAWN) 
                return nextField;            
        }
        
        nextField = to.nextField(directionR);
        
        if (!nextField.isEmpty())
        {
            if (nextField.getFigure().getType() == IFigure.PAWN) 
                return nextField;            
        }
        
        return null;
    }

    private IField canMovePawn(boolean isBlack, IField to)
    {   
        IField from = canMovePawnForward(isBlack, to);
        if (from == null)
            return canMovePawnSideways(isBlack, to);     
        else
            return from;
    }
    
    private IField canMoveKing(boolean isBlack, IField to, IField.Direction dirs)
    {
        IField nextField = to.nextField(dirs);
        
        if (nextField != null)
            if (nextField.getFigure() != null)
                if (nextField.getFigure().getType() == IFigure.KING && nextField.getFigure().isBlack() == isBlack)
                    return null;
        
        
        dirs = determineNextDirection(IFigure.KING, dirs);
        if (dirs == null) 
            return null;
        return canMoveKing(isBlack, to, dirs);       
    }

//    in case of failure, this is the problem
    private IField canMove(boolean isBlack, IFigure.Type type, IField next, IField to, IField.Direction dirs)
    {
        IField nextField = next.nextField(dirs);
        
        if (nextField == null)
        {
            dirs = determineNextDirection(type, dirs);
            if (dirs == null) 
                return null;
            return canMove(isBlack, type, to, to, dirs);
        }

        if (nextField.isEmpty()) 
            return canMove(isBlack, type, nextField, to, dirs);
        else
        {
            if (nextField.getFigure().getType() == type && nextField.getFigure().isBlack() == isBlack)
                return nextField;
            
            dirs = determineNextDirection(type, dirs);
            if (dirs == null) 
                return null;
            return canMove(isBlack, type, to, to, dirs);
        }
    }

    private IField.Direction determineNextDirection(IFigure.Type type, IField.Direction dirs)
    {
        if (type.equals(IFigure.ROOK))
        {
            if (dirs == IField.D)
            {
                return IField.L;
            }
            else if (dirs == IField.L)
            {
                return IField.U;
            }
            else if (dirs == IField.U)
            {
                return IField.R;
            }
            else
            {
                return null;
            } 
        }
        else if (type.equals(IFigure.BISHOP))
        {
            if (dirs == IField.LD)
            {
                return IField.LU;
            }
            else if (dirs == IField.LU)
            {
                return IField.RU;
            }
            else if (dirs == IField.RU)
            {
                return IField.RD;
            }
            else
            {
                return null;
            } 
        }
        else if (type.equals(IFigure.KING) || type.equals(IFigure.QUEEN))
        {
            if (dirs == IField.D)
            {
                return IField.LD;
            }
            else if (dirs == IField.LD)
            {
                return IField.L;
            }
            else if (dirs == IField.L)
            {
                return IField.LU;
            }
            else if (dirs == IField.LU)
            {
                return IField.U;
            } 
            else if (dirs == IField.U)
            {
                return IField.RU;
            }
            else if (dirs == IField.RU)
            {
                return IField.R;
            }
            else if (dirs == IField.R)
            {
                return IField.RD;
            }
            else
            {
                return null;
            } 
        }

        return null;
    }
}