/**
 * IJA 2018/2019
 * Projekt
 * @author Jan Ježek (xjezek15)
 */

package ija.project.utilities;

public class MoveDisplay 
{
    private final String moveText;
    private final ParsedMove whiteMove;
    private final ParsedMove blackMove;

    public MoveDisplay(String moveText, ParsedMove whiteMove, ParsedMove blackMove) 
    {
        this.moveText = moveText;
        this.whiteMove = whiteMove;
        this.blackMove = blackMove;
    }     

    public String getMoveText() 
    {
        return moveText;
    }

    public ParsedMove getWhiteMove() 
    {
        return whiteMove;
    }

    public ParsedMove getBlackMove() 
    {
        return blackMove;
    }
}
