/**
 * IJA 2018/2019
 * Úkol 2
 * @author Jan Ježek (xjezek15)
 */

package ija.project.common;

import ija.project.utilities.Location;
import java.util.Stack;

public class GameClass extends java.lang.Object implements Game
{
    Stack<Move> moveStack;

    public GameClass()
    {
        moveStack = new Stack<>();
    }

    @Override
    public void undo()
    {
        if (moveStack.empty()) return;
        
        Move move = moveStack.pop();

        Field from = move.getFromField();
        from.putFigure(move.getFromFigure());
        Field to = move.getToField();
        to.removeFigure(move.getFromFigure());
        if (move.getCapturedFigure() != null)
        {
            to.putFigure(move.getCapturedFigure());   
        }           
    }

    @Override
    public boolean move(Field from, Field to)
    {
        boolean canMove = false;

        if (from == null || to == null)
        {
            return false;
        }
        
        Figure fromFigure = from.getFigure();

        if (fromFigure == null)
        {
            return false;
        }
        
        boolean toEmpty = to.isEmpty();

        // check if figure can move
        if (fromFigure.getType() == Figure.Pawn)
        {
            canMove = canMovePawn(from, to);
        }
        else if (fromFigure.getType() == Figure.Rook)
        {
            canMove = canMove(from, from, to, Field.D);
        }
        else if (fromFigure.getType() == Figure.Bishop)
        {
            canMove = canMove(from, from, to, Field.LD);
        }
        else if (fromFigure.getType() == Figure.Queen)
        {
            canMove = canMove(from, from, to, Field.D);
        }
        else if (fromFigure.getType() == Figure.King)
        {
            canMove = canMoveKing(from, to, Field.D);
        }
        else if (fromFigure.getType() == Figure.Knight)
        {
            throw new UnsupportedOperationException("Move with Knight not implemented");
        }

        if (canMove)
        {
            Figure capturedFigure = null;

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
                    Move move = new Move(fromFigure, from, to, capturedFigure);
                    move.push(this.moveStack);
                    return true;
                }
            }
        }

        return false;
    }
    
    private boolean canMovePawnForward(boolean isBlack, Field from, Field to)
    {
        Location fromLocation = from.getLocation();
        Location toLocation = to.getLocation();  
        
        int fromRow = isBlack ? 6 : 1;
        int forward1 = isBlack ? 5 : 2;
        int forward2 = isBlack ? 4 : 3;
        Field.Direction direction = isBlack ? Field.Direction.D : Field.Direction.U;
        
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
    
    private boolean canMovePawnSideways(boolean isBlack, Field from, Field to)
    {
        Field.Direction directionL = isBlack ? Field.Direction.LD : Field.Direction.LU;
        Field.Direction directionR = isBlack ? Field.Direction.RD : Field.Direction.RU;
        Figure toFigure = to.getFigure();
        
        if (toFigure == null) return false;
        
        // figures are different color
        if (from.getFigure().isBlack() != to.getFigure().isBlack())
        {
            return from.nextField(directionL).equals(to) || from.nextField(directionR).equals(to);
        }         
        
        return false;
    }

    private boolean canMovePawn(Field from, Field to)
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
    
    private boolean canMoveKing(Field from, Field to, Field.Direction dirs)
    {
        Field nextField = from.nextField(dirs);
        
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
    private boolean canMove(Field source, Field from, Field to, Field.Direction dirs)
    {
        Field nextField = from.nextField(dirs);
        
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

    private Field.Direction determineNextDirection(Figure figure, Field.Direction dirs)
    {
        if (figure.getType().equals(Figure.Rook))
        {
            if (dirs == Field.D)
            {
                return Field.L;
            }
            else if (dirs == Field.L)
            {
                return Field.U;
            }
            else if (dirs == Field.U)
            {
                return Field.R;
            }
            else
            {
                return null;
            } 
        }
        else if (figure.getType().equals(Figure.Bishop))
        {
            if (dirs == Field.LD)
            {
                return Field.LU;
            }
            else if (dirs == Field.LU)
            {
                return Field.RU;
            }
            else if (dirs == Field.RU)
            {
                return Field.RD;
            }
            else
            {
                return null;
            } 
        }
        else if (figure.getType().equals(Figure.King) || figure.getType().equals(Figure.Queen))
        {
            if (dirs == Field.D)
            {
                return Field.LD;
            }
            else if (dirs == Field.LD)
            {
                return Field.L;
            }
            else if (dirs == Field.L)
            {
                return Field.LU;
            }
            else if (dirs == Field.LU)
            {
                return Field.U;
            } 
            else if (dirs == Field.U)
            {
                return Field.RU;
            }
            else if (dirs == Field.RU)
            {
                return Field.R;
            }
            else if (dirs == Field.R)
            {
                return Field.RD;
            }
            else
            {
                return null;
            } 
        }

        return null;

    }
}
