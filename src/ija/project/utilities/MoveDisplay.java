/**
 * IJA 2018/2019
 * Projekt
 * @author Jan Ježek (xjezek15)
 */

package ija.project.utilities;

import ija.project.common.IMove;

public class MoveDisplay 
{
    private final String moveText;
    private final IMove whiteMove;
    private final IMove blackMove;
    private final boolean check;
    private final boolean mate;

    public MoveDisplay(String moveText, IMove whiteMove, IMove blackMove, boolean check, boolean mate) 
    {
        this.moveText = moveText;
        this.whiteMove = whiteMove;
        this.blackMove = blackMove;
        this.check = check;
        this.mate = mate;
    }     

    public String getMoveText() 
    {
        return moveText;
    }

    public IMove getWhiteMove() 
    {
        return whiteMove;
    }

    public IMove getBlackMove() 
    {
        return blackMove;
    }

    public boolean isCheck() 
    {
        return check;
    }

    public boolean isMate() 
    {
        return mate;
    }
}
