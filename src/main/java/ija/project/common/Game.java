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
    public boolean move(IField from, IField to)
    {
        boolean canMove = false;

        if (from == null || to == null)
        {
            return false;
        }
        
        IFigure fromFigure = from.getFigure();

        if (fromFigure == null)
        {
            return false;
        }
        
        boolean toEmpty = to.isEmpty();

        // check if figure can move
        if (fromFigure.getType() == IFigure.Pawn)
        {
            canMove = canMovePawn(from, to);
        }
        else if (fromFigure.getType() == IFigure.Rook)
        {
            canMove = canMove(from, from, to, IField.D);
        }
        else if (fromFigure.getType() == IFigure.Bishop)
        {
            canMove = canMove(from, from, to, IField.LD);
        }
        else if (fromFigure.getType() == IFigure.Queen)
        {
            canMove = canMove(from, from, to, IField.D);
        }
        else if (fromFigure.getType() == IFigure.King)
        {
            canMove = canMoveKing(from, to, IField.D);
        }
        else if (fromFigure.getType() == IFigure.Knight)
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
    
    private boolean canMovePawnForward(boolean isBlack, IField from, IField to)
    {
        Location fromLocation = from.getLocation();
        Location toLocation = to.getLocation();  
        
        int fromRow = isBlack ? 6 : 1;
        int forward1 = isBlack ? 5 : 2;
        int forward2 = isBlack ? 4 : 3;
        IField.Direction direction = isBlack ? IField.Direction.D : IField.Direction.U;
        
        // same column
        if (fromLocation.getCol() == toLocation.getCol())
        {
            // to is empty
            if (!to.isEmpty()) return false;
            
            // one field
            if (toLocation.getRow() == forward1) return true;
            
            // from row is initial
            if (fromLocation.getRow() == fromRow)
            {         
                // two fields
                if (toLocation.getRow() == forward2)
                {
                    // middle field is empty
                    if (from.nextField(direction).isEmpty()) return true;
                }
            }
        }
        
        return false;
    }
    
    private boolean canMovePawnSideways(boolean isBlack, IField from, IField to)
    {
        IField.Direction directionL = isBlack ? IField.Direction.LD : IField.Direction.LU;
        IField.Direction directionR = isBlack ? IField.Direction.RD : IField.Direction.RU;
        IFigure toFigure = to.getFigure();
        
        if (toFigure == null) return false;
        
        // figures are different color
        if (from.getFigure().isBlack() != to.getFigure().isBlack())
        {
            return from.nextField(directionL).equals(to) || from.nextField(directionR).equals(to);
        }         
        
        return false;
    }

    private boolean canMovePawn(IField from, IField to)
    {   
        if (from.getFigure().isBlack())
        {
            if (canMovePawnForward(true, from, to)) return true;
            return canMovePawnSideways(true, from, to);            
        }
        else
        {
            if (canMovePawnForward(false, from, to)) return true;
            return canMovePawnSideways(false, from, to);
        }       
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
        if (figure.getType().equals(IFigure.Rook))
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
        else if (figure.getType().equals(IFigure.Bishop))
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
        else if (figure.getType().equals(IFigure.King) || figure.getType().equals(IFigure.Queen))
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
