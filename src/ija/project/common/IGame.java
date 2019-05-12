/**
 * IJA 2018/2019
 * Projekt
 * @author Jan Ježek (xjezek15)
 * @author Šimon Šesták (xsesta06)
 */

package ija.project.common;

/**
 * Interface for game.
 * @author xjezek15
 */
public interface IGame 
{

    /**
     *
     * @return move
     */
    IMove getLastMove();

    /**
     *
     * @param type
     * @param dirs
     * @return next direction
     */
    IField.Direction determineNextDirection(IFigure.Type type, IField.Direction dirs);

    /**
     *
     * @param isBlack
     * @param type
     * @param to
     * @return true if success
     */
    boolean move(boolean isBlack, Figure.Type type, IField to);

    /**
     *
     * @param from
     * @param to
     * @return true if success
     */
    boolean move(IField from, IField to);

    /**
     * Gets last move from move stack.
     * Return state of game to that before this move.
     */
    void undo();
}
