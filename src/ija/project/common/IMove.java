/**
 * IJA 2018/2019
 * Projekt
 * @author Jan Ježek (xjezek15)
 */

package ija.project.common;

import java.util.Stack;

public interface IMove 
{
    IMove deepCopy();
    IField getFromField();
    IField getToField();
    IFigure getFromFigure();
    IFigure getCapturedFigure();
    IMove push(Stack<IMove> stack);
}
