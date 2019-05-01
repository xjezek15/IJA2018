/**
 * IJA 2018/2019
 * Projekt
 * @author Jan Ježek (xjezek15)
 */

package ija.project.common;

public interface IFigure
{
    public static enum Type
    {
        Rook, Pawn, Bishop, Knight, Queen, King;
    }

    public static final IFigure.Type Rook    = Type.Rook;
    public static final IFigure.Type Pawn    = Type.Pawn;
    public static final IFigure.Type Bishop  = Type.Bishop;
    public static final IFigure.Type Knight  = Type.Knight;
    public static final IFigure.Type Queen   = Type.Queen;
    public static final IFigure.Type King    = Type.King;
    

    boolean isBlack();
    IFigure.Type getType();    
}