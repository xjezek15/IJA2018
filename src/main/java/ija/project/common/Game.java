/*
 * IJA 2018/2019
 * Ukol 2
 */
package ija.project.common;

public interface Game 
{
    boolean move(Figure figure, Field field);
    void undo();
}
