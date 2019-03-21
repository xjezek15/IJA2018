/**
 * IJA 2018/2019
 * Projekt
 * @author Jan Ježek (xjezek15)
 */

package ija2018.project.board;

public class Figure extends java.lang.Object 
{
    public static enum Type
    {
        Queen, King, Bishop, Knight, Rook, Pawn;
    }

    public static final Figure.Type Queen   = Type.Queen;
    public static final Figure.Type King    = Type.King;
    public static final Figure.Type Bishop  = Type.Bishop;
    public static final Figure.Type Knight  = Type.Knight;
    public static final Figure.Type Rook    = Type.Rook;
    public static final Figure.Type Pawn    = Type.Pawn;

    private boolean isBlack;
    private Figure.Type type;

    public Figure(boolean isBlack, Figure.Type type) 
    {
        this.isBlack = isBlack;
        this.type = type;
    }

    public boolean isBlack()
    {
        return this.isBlack;
    }

    public Figure.Type getType() 
    {
        return this.type;
    }

    public int move(Field from, Field moveTo)
    {
        if (from == null || moveTo == null)
        {
            return 10;
        }
        if (from.isEmpty())
        {
            return 20;
        }

        // check if figure can move
        if (this.type == Figure.Pawn)
        {
            if (!canMovePawn(from, moveTo))
            {
                return 41;
            }
        }
        else if (this.type == Figure.Knight)
        {
            if (!canMoveKnight(from, moveTo))
            {
                return 42;
            }
        }

        if (!moveTo.isEmpty())
        {
            if (moveTo.getFigure().isBlack())
            {
                if (!this.isBlack)
                {
                    moveTo.remove(moveTo.getFigure());
                }
                else
                {
                    return 30;
                }
            }
            else
            {
                if (this.isBlack)
                {
                    moveTo.remove(moveTo.getFigure());
                }
                else
                {
                    return 30;
                }
            }
        }

        if (from.remove(this))
        {
            if (moveTo.putFigure(this))
            {
                return 0;
            }
        }
        
        return 99;
    }

    private boolean canMoveKnight(Field from, Field moveTo)
    {
        int[] fromLocation = from.getLocation();

        // up
        fromLocation[1] += 2;
        // right
        fromLocation[0] += 1;

        if (fromLocation.equals(moveTo.getLocation()))
        {
            return true;
        }
        
        // left
        fromLocation[0] -= 2;

        if (fromLocation.equals(moveTo.getLocation()))
        {
            return true;
        }
        
        fromLocation = from.getLocation();

        // down
        fromLocation[1] -= 2;
        // right
        fromLocation[0] += 1;

        if (fromLocation.equals(moveTo.getLocation()))
        {
            return true;
        }
        
        // left
        fromLocation[0] -= 2;

        if (fromLocation.equals(moveTo.getLocation()))
        {
            return true;
        }
        
        fromLocation = from.getLocation();

        // right
        fromLocation[0] += 2;
        // up
        fromLocation[1] += 1;

        if (fromLocation.equals(moveTo.getLocation()))
        {
            return true;
        }
        
        // down
        fromLocation[0] -= 2;

        if (fromLocation.equals(moveTo.getLocation()))
        {
            return true;
        }
        
        fromLocation = from.getLocation();

        // left
        fromLocation[0] -= 2;
        // up
        fromLocation[1] += 1;

        if (fromLocation.equals(moveTo.getLocation()))
        {
            return true;
        }
        
        // down
        fromLocation[0] -= 2;

        if (fromLocation.equals(moveTo.getLocation()))
        {
            return true;
        }

        return false;
    }

    private boolean canMovePawn(Field from, Field moveTo)
    {
        if (!from.nextField(Field.U).equals(moveTo))
        {
            if (!from.nextField(Field.RU).equals(moveTo))
            {
                return false;
            }
        }

        return true;
    }

    private int canMove(Field source, Field from, Field moveTo, Field.Direction dirs)
    {
        if (from.equals(moveTo))
        {
            return 0;
        }

        Field nextField = from.nextField(dirs);

        if (nextField == null)
        {
            dirs = determineNextDirection(dirs);

            if (dirs == null)
            {
                return 10;
            }

            return canMove(source, source, moveTo, dirs);
        }

        if (nextField.isEmpty())
        {
            return canMove(source, from, moveTo, dirs);
        }
        else
        {
            dirs = determineNextDirection(dirs);

            if (dirs == null)
            {
                return 10;
            }

            return canMove(source, source, moveTo, dirs);
        }
    }

    private Field.Direction determineNextDirection(Field.Direction dirs)
    {
        if (this.type.equals(Figure.Rook))
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
        else if (this.type.equals(Figure.Bishop))
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
                return Field.RU;
            }
            else
            {
                return null;
            } 
        }
        else if (this.type.equals(Figure.King) || this.type.equals(Figure.Queen) || this.type.equals(Figure.King))
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
        else if (this.type.equals(Figure.Pawn))
        {
            if (dirs == Field.U)
            {
                return Field.RU;
            }
            else
            {
                return null;
            }
        }
        else if (this.type.equals(Figure.Knight))
        {
            if (dirs == Field.U)
            {
                return Field.L;
            }
        }

        return null;
    }
}