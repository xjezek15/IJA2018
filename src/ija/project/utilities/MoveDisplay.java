/**
 * IJA 2018/2019
 * Projekt
 * @author Jan Ježek (xjezek15)
 * @author Šimon Šesták (xsesta06)
 */

package ija.project.utilities;

/**
 * Functionality for MoveDisplay
 * @author xjeze15
 */
public class MoveDisplay 
{
    private final String moveText;
    private final ParsedMove whiteMove;
    private final ParsedMove blackMove;

    /**
     *
     * @param moveText
     * @param whiteMove
     * @param blackMove
     */
    public MoveDisplay(String moveText, ParsedMove whiteMove, ParsedMove blackMove) 
    {
        this.moveText = moveText;
        this.whiteMove = whiteMove;
        this.blackMove = blackMove;
    }     

    /**
     *
     * @return move text
     */
    public String getMoveText() 
    {
        return moveText;
    }

    /**
     *
     * @return parsedMove
     */
    public ParsedMove getWhiteMove() 
    {
        return whiteMove;
    }

    /**
     *
     * @return parsedMove
     */
    public ParsedMove getBlackMove() 
    {
        return blackMove;
    }
}
