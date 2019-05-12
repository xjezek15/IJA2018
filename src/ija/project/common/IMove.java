/**
 * IJA 2018/2019
 * Projekt
 * @author Jan Ježek (xjezek15)
 * @author Šimon Šesták (xsesta06)
 */

package ija.project.common;

/**
 * Interface for move.
 * @author xjezek15
 */
public interface IMove 
{

    /**
     *
     * @return field
     */
    IField getFromField();

    /**
     *
     * @return field
     */
    IField getToField();

    /**
     *
     * @return figure
     */
    IFigure getFromFigure();

    /**
     *
     * @return figure
     */
    IFigure getCapturedFigure();
}
