/*
 * IJA 2018/2019
 * Ukol 2
 */
package ija.project.common;

public interface Game 
{
    boolean move(Field from, Field to);
    void undo();
}
