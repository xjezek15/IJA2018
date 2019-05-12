/**
 * IJA 2018/2019
 * Projekt
 * @author Jan Ježek (xjezek15)
 * @author Šimon Šesták (xsesta06)
 */

package ija.project.game;

import ija.project.common.IField;
import ija.project.common.IFigure;

/**
 * Interface for board.
 * @author janje
 */
public interface IBoard 
{

    /**
     * Adds figure to field specified by location.
     * @param col
     * @param row
     * @param figure
     * @return true if success
     */
    boolean addFigure(int col, int row, IFigure figure);

    /**
     * Returns field on said location.
     * @param col
     * @param row
     * @return field
     */
    IField getField(int col, int row);

    /**
     *
     * @return size of board
     */
    int getSize();
}
