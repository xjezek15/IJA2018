/**
 * IJA 2018/2019
 * Projekt
 * @author Jan Ježek (xjezek15)
 */

package ija.project.common;

import ija.project.utilities.Location;
import java.util.Stack;

public class Game extends java.lang.Object implements IGame
{
    Stack<IMove> moveStack;

    public Game()
    {
        moveStack = new Stack<>();
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
    public boolean move(Figure.Type type, IField to)
    {
        return false;
    }

    @Override
    public boolean move(IField from, IField to)
    {
        if (from == null) return false;
        
        IFigure fromFigure = from.getFigure();

        if (fromFigure == null) return false;
        
        return false;
    }
    
    private boolean doMove(Figure.Type type, boolean isBlack, IField to)
    {
        if (to == null) return false;
        
        boolean canMove = false;
        boolean toEmpty = to.isEmpty();

        // check if figure can move
        if (type == IFigure.PAWN)
        {
            canMove = canMovePawn(isBlack, to);
        }
        else if (type == IFigure.ROOK)
        {
            canMove = canMove(from, from, to, IField.D);
        }
        else if (type == IFigure.BISHOP)
        {
            canMove = canMove(from, from, to, IField.LD);
        }
        else if (type == IFigure.QUEEN)
        {
            canMove = canMove(from, from, to, IField.D);
        }
        else if (type == IFigure.KING)
        {
            canMove = canMoveKing(from, to, IField.D);
        }
        else if (type == IFigure.KNIGHT)
        {
            throw new UnsupportedOperationException("Move with Knight not implemented");
        }

        if (canMove)
        {
            IFigure capturedFigure = null;

            // not empty
            if (!toEmpty)
            {
                if (fromFigure.isBlack())
                {
                    if (!to.getFigure().isBlack())
                    {
                        capturedFigure = to.getFigure();
                        to.removeFigure(capturedFigure);
                    }
                    else
                    {
                        return false;
                    }
                }
                else
                {
                    if (to.getFigure().isBlack())
                    {
                        capturedFigure = to.getFigure();
                        to.removeFigure(capturedFigure);
                    }
                    else
                    {
                        return false;
                    }
                }
            }

            if (from.removeFigure(fromFigure))
            {
                if (to.putFigure(fromFigure))
                {
                    IMove move = new Move(fromFigure, from, to, capturedFigure);
                    move.push(this.moveStack);
                    return true;
                }
            }
        }

        return false;
    }
    
    private boolean canMovePawnForward(boolean isBlack, IField to)
    {
        // to is not empty
        if (!to.isEmpty()) return false;

        IField.Direction direction = isBlack ? IField.Direction.U : IField.Direction.D;
        
        IField nextField = to.nextField(direction);
        
        if (nextField == null) return false;        
        if (nextField.getFigure().getType() == IFigure.PAWN && nextField.getFigure().isBlack() == isBlack) return true;
        if (!nextField.isEmpty()) return false;
        
        nextField = nextField.nextField(direction);
        
        if (nextField == null) return false;       
        return nextField.getFigure().getType() == IFigure.PAWN && nextField.getFigure().isBlack() == isBlack;
    }
    
    private boolean canMovePawnSideways(boolean fromIsBlack, IField to)
    {
        IField.Direction directionL = fromIsBlack ? IField.Direction.LU : IField.Direction.LD;
        IField.Direction directionR = fromIsBlack ? IField.Direction.RU : IField.Direction.RD;
        
        if (to.isEmpty()) return false;
        
        boolean toIsBlack = to.getFigure().isBlack();
        
        if (fromIsBlack != !toIsBlack) return false;
        
        IField nextField = to.nextField(directionL);
        
        if (!nextField.isEmpty())
        {
            if (nextField.getFigure().getType() == IFigure.PAWN) return true;            
        }
        
        nextField = to.nextField(directionR);
        
        if (!nextField.isEmpty())
        {
            if (nextField.getFigure().getType() == IFigure.PAWN) return true;            
        }
        
        return false;
    }

    private boolean canMovePawn(boolean isBlack, IField to)
    {   
        if (canMovePawnForward(isBlack, to)) return true;
        return canMovePawnSideways(isBlack, to);                
    }
    
    private boolean canMoveKing(IField from, IField to, IField.Direction dirs)
    {
        IField nextField = from.nextField(dirs);
        
        if (nextField == null || !nextField.equals(to))
        {
            dirs = determineNextDirection(from.getFigure(), dirs);

            if (dirs == null)
            {
                return false;
            }
            
            return canMoveKing(from, to, dirs);
        }
        else
        {
            return true;
        }
    }

//    in case of failure, this is the problem
    private boolean canMove(IField source, IField from, IField to, IField.Direction dirs)
    {
        IField nextField = from.nextField(dirs);
        
        if (nextField == null)
        {
            dirs = determineNextDirection(source.getFigure(), dirs);

            if (dirs == null)
            {
                return false;
            }

            return canMove(source, source, to, dirs);
        }

        if (nextField.equals(to)) return true;

        if (nextField.isEmpty())
        {
            return canMove(source, nextField, to, dirs);
        }
        else
        {
            dirs = determineNextDirection(source.getFigure(), dirs);

            if (dirs == null)
            {
                return false;
            }

            return canMove(source, source, to, dirs);
        }
    }

    private IField.Direction determineNextDirection(IFigure figure, IField.Direction dirs)
    {
        if (figure.getType().equals(IFigure.ROOK))
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
        else if (figure.getType().equals(IFigure.BISHOP))
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
        else if (figure.getType().equals(IFigure.KING) || figure.getType().equals(IFigure.QUEEN))
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
