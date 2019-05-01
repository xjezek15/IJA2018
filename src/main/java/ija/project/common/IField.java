/**
 * IJA 2018/2019
 * Projekt
 * @author Jan Ježek (xjezek15)
 */

package ija.project.common;

import ija.project.utilities.Location;

public interface IField 
{
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

    void addNextField(IField.Direction dirs, IField field);
    IField nextField(IField.Direction dirs);
    boolean isEmpty();
    Location getLocation();
    IFigure getFigure();
    boolean putFigure(IFigure figure);
    boolean removeFigure(IFigure figure);
    String getState();
}
