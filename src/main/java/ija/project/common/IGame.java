/**
 * IJA 2018/2019
 * Projekt
 * @author Jan Ježek (xjezek15)
 */

package ija.project.common;

public interface IGame 
{
    boolean move(IField from, IField to);
    void undo();
}
