/**
 * IJA 2018/2019
 * Úkol 2
 * @author Jan Ježek (xjezek15)
 */

package ija.project.common;

public interface Figure
{
    public static enum Type
    {
        Rook, Pawn, Bishop, Knight, Queen, King;
    }

    public static final Figure.Type Rook    = Type.Rook;
    public static final Figure.Type Pawn    = Type.Pawn;
    public static final Figure.Type Bishop  = Type.Bishop;
    public static final Figure.Type Knight  = Type.Knight;
    public static final Figure.Type Queen   = Type.Queen;
    public static final Figure.Type King    = Type.King;
    

    boolean isBlack();
    Figure.Type getType();    
}