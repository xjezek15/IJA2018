/**
 * IJA 2018/2019
 * Projekt
 * @author Jan Ježek (xjezek15)
 */

package ija.project.utilities;

import ija.project.game.IBoard;

public class MoveDisplay 
{
    private final String move;
    private final boolean check;
    private final boolean mate;
    private final IBoard board;

    public MoveDisplay(String move, boolean check, boolean mate, IBoard board) 
    {
        this.move = move;
        this.check = check;
        this.mate = mate;
        this.board = board;
    }     
}
