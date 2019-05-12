/**
 * IJA 2018/2019
 * Projekt
 * @author Jan Ježek (xjezek15)
 * @author Šimon Šesták (xsesta06)
 */

package ija.project.common;

/**
 * Interface for figure
 * @author xjezek15
 */
public interface IFigure
{

    /**
     * All figure types in chess.
     */
    public static enum Type
    {
        Rook, Pawn, Bishop, Knight, Queen, King;
    }

    public static final IFigure.Type ROOK    = Type.Rook;
    public static final IFigure.Type PAWN    = Type.Pawn;
    public static final IFigure.Type BISHOP  = Type.Bishop;
    public static final IFigure.Type KNIGHT  = Type.Knight;
    public static final IFigure.Type QUEEN   = Type.Queen;
    public static final IFigure.Type KING    = Type.King;
    
    /**
     *
     * @return isBlack
     */
    boolean isBlack();

    /**
     *
     * @return figure type
     */
    IFigure.Type getType();    
}