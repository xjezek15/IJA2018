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
        Rook, Pawn, Disk;
    }

    public static final Figure.Type Rook    = Type.Rook;
    public static final Figure.Type Pawn    = Type.Pawn;
    public static final Figure.Type Disk    = Type.Disk;

    boolean isBlack();
    Figure.Type getType();    
    String getState();
    void updateLocation(int[] location);
}