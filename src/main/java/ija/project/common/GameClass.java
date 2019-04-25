/**
 * IJA 2018/2019
 * Úkol 2
 * @author Jan Ježek (xjezek15)
 */

package ija.project.common;

import java.util.Stack;

public class GameClass extends java.lang.Object implements Game
{
    Stack<Move> moveStack;

    public GameClass()
    {
        moveStack = new Stack<Move>();
    }

    public void undo()
    {
        Move move = moveStack.pop();

        Field from = move.getFromField();
        from.putFigure(move.getFigure(), from.getLocation());
        Field moveTo = move.getMoveToField();
        moveTo.remove(move.getFigure());
        if (move.getCapturedFigure() != null)
        {
            moveTo.putFigure(move.getCapturedFigure(), moveTo.getLocation());   
        }           
    }

    public boolean move(Figure figure, Field field)
    {
        boolean canMove = false;

        if (field == null || figure == null)
        {
            return false;
        }

        boolean moveToEmpty = field.isEmpty();
        Field from = null;


        // check if figure can move
        if (figure.getType() == Figure.Pawn)
        {
            from = findField(figure, field);
            if (from != null && moveToEmpty)
            {
                canMove = true;
            }
            else
            {
                return false;
            }
        }
        else if (figure.getType() == Figure.Rook)
        {
            from = findField(figure, field, Field.D);
            if (from != null)
            {
                canMove = true;
            }
            else
            {
                return false;
            }
        }
        else if (figure.getType() == Figure.Disk)
        {
            if (figure.isBlack())
            {
                from = findFieldDisk(figure, field, Field.LU);
            }
            else
            {
                from = findFieldDisk(figure, field, Field.LD);
            }
            
            if (from != null)
            {
                canMove = true;
            }
            else
            {
                return false;
            }
        }

        if (canMove)
        {
            Figure capturedFigure = null;

            if (figure.getType() == Figure.Rook && !moveToEmpty)
            {
                if (field.get().isBlack())
                {
                    if (!figure.isBlack())
                    {
                        capturedFigure = field.get();
                        field.remove(capturedFigure);
                    }
                    else
                    {
                        return false;
                    }
                }
                else
                {
                    if (figure.isBlack())
                    {
                        capturedFigure = field.get();
                        field.remove(capturedFigure);
                    }
                    else
                    {
                        return false;
                    }
                }
            }

            if (from.remove(figure))
            {
                if (field.putFigure(figure, field.getLocation()))
                {
                    Move move = new Move(figure, from, field, capturedFigure);
                    move.push(this.moveStack);
                    return true;
                }
            }
        }

        return false;
    }

    // find pawn
    private Field findField(Figure figure, Field moveTo)
    {
        Field from = null;
        if (figure.isBlack())
        {
            from = moveTo.nextField(Field.U);
        }
        else
        {
            from = moveTo.nextField(Field.D);
        }
        

        if (from != null)
        {
            if (from.get().equals(figure))
            {
                return from;
            }
        }
        
        return null;        
    }

    // find rook
    private Field findField(Figure figure, Field field, Field.Direction dirs)
    {
        Field nextField = field.nextField(dirs);

        if (nextField == null)
        {
            dirs = determineNextDirection(figure, dirs);

            if (dirs == null)
            {
                return null;
            }

            return findField(figure, field, dirs);
        }

        Figure nextFieldFigure = nextField.get();

        if (nextFieldFigure == null)
        {
            return findField(figure, nextField, dirs);
        }

        if (nextFieldFigure.equals(figure))
        {
            return nextField;
        }
        else
        {
            dirs = determineNextDirection(figure, dirs);

            if (dirs == null)
            {
                return null;
            }

            return findField(figure, field, dirs);
        }
    }
    
    // find disk
    private Field findFieldDisk(Figure figure, Field field, Field.Direction dirs)
    {
        Field nextField = field.nextField(dirs);

        if (nextField == null)
        {
            dirs = determineNextDirection(figure, dirs);

            if (dirs == null)
            {
                return null;
            }

            return findFieldDisk(figure, field, dirs);
        }

        Figure nextFieldFigure = nextField.get();

        if (nextFieldFigure == null)
        {
            dirs = determineNextDirection(figure, dirs);

            if (dirs == null)
            {
                return null;
            }

            return findFieldDisk(figure, field, dirs);
        }

        if (nextFieldFigure.equals(figure))
        {
            return nextField;
        }
        else
        {
            dirs = determineNextDirection(figure, dirs);

            if (dirs == null)
            {
                return null;
            }

            return findFieldDisk(figure, field, dirs);
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
        else if (figure.getType().equals(Figure.Disk))
        {
            if (figure.isBlack())
            {
                if (dirs == Field.LU)
                {
                    return Field.RU;
                } 
                else
                {
                    return null;
                } 
            }
            else
            {
                if (dirs == Field.LD)
                {
                    return Field.RD;
                } 
                else
                {
                    return null;
                } 
            }            
        }

        return null;
    }
}
