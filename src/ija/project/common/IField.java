/**
 * IJA 2018/2019
 * Projekt
 * @author Jan Ježek (xjezek15)
 * @author Šimon Šesták (xsesta06)
 */

package ija.project.common;

import ija.project.utilities.Location;

/**
 * Interface for field.
 * @author xjezek15
 */
public interface IField 
{

    /**
     * All 8 directions.
     */
    public static enum Direction
    {
        D, L, LD, LU, R, RD, RU, U;
    }

    public static final IField.Direction D = Direction.D;
    public static final IField.Direction L = Direction.L;
    public static final IField.Direction LD = Direction.LD;
    public static final IField.Direction LU = Direction.LU;
    public static final IField.Direction R = Direction.R;
    public static final IField.Direction RD = Direction.RD;
    public static final IField.Direction RU = Direction.RU;
    public static final IField.Direction U = Direction.U;

    /**
     *
     * @param dirs
     * @param field
     */
    void addNextField(IField.Direction dirs, IField field);

    /**
     *
     * @param dirs
     * @return field in dirs
     */
    IField nextField(IField.Direction dirs);

    /**
     *
     * @return isEmpty
     */
    boolean isEmpty();

    /**
     *
     * @return location of field
     */
    Location getLocation();

    /**
     *
     * @return figure on field
     */
    IFigure getFigure();

    /**
     *
     * @param figure
     * @return true if success
     */
    boolean putFigure(IFigure figure);

    /**
     *
     * @param figure
     * @return true if success
     */
    boolean removeFigure(IFigure figure);

    /**
     * For testing.
     * @return state
     */
    String getState();
}
