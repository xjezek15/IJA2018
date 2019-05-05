/**
 * IJA 2018/2019
 * Projekt
 * @author Jan Ježek (xjezek15)
 */

package ija.project.common;

import java.util.Stack;

public interface IMove 
{
    IField getFromField();
    IField getToField();
    IFigure getFromFigure();
    IFigure getCapturedFigure();
}
