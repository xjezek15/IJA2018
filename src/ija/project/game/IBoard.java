/**
 * IJA 2018/2019
 * Projekt
 * @author Jan Ježek (xjezek15)
 */

package ija.project.game;

import ija.project.common.IField;
import ija.project.common.IFigure;

public interface IBoard 
{
    boolean addFigure(int col, int row, IFigure figure);
    IField getField(int col, int row);
    int getSize();
}
