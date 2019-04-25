/*
 * IJA 2018/2019
 * Ukol 1
 */
package ija.project.common;

public interface Field 
{
    public static enum Direction
    {
        D, L, LD, LU, R, RD, RU, U;
    }

    public static final Field.Direction D = Direction.D;
    public static final Field.Direction L = Direction.L;
    public static final Field.Direction LD = Direction.LD;
    public static final Field.Direction LU = Direction.LU;
    public static final Field.Direction R = Direction.R;
    public static final Field.Direction RD = Direction.RD;
    public static final Field.Direction RU = Direction.RU;
    public static final Field.Direction U = Direction.U;

    void addNextField(Field.Direction dirs, Field field);
    Field nextField(Field.Direction dirs);
    boolean isEmpty();
    // 0 = col, 1 = row
    int[] getLocation();
    Figure get();
    boolean putFigure(Figure figure);
    boolean putFigure(Figure figure, int[] location);
    boolean remove(Figure figure);
}
