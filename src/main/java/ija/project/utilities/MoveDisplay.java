/**
 * IJA 2018/2019
 * Projekt
 * @author Jan Ježek (xjezek15)
 */

package ija.project.utilities;

import ija.project.game.Board;
import ija.project.game.IBoard;

public class MoveDisplay 
{
    private String move;
    private boolean check;
    private boolean mate;
    private IBoard board;

    public MoveDisplay(String move, boolean check, boolean mate, IBoard board) 
    {
        this.move = move;

        
        
        this.check = check;
        this.mate = mate;
        this.board = board;
    }     
}
