/**
 * IJA 2018/2019
 * Projekt
 * @author Jan Ježek (xjezek15)
 * @author Šimon Šesták (xsesta06)
 */

package ija.project.parser;

import ija.project.utilities.MoveDisplay;
import ija.project.utilities.ParsedMove;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Interface for input.
 * @author xjezek15
 */
public interface IInput 
{

    /**
     *
     * @param file
     * @throws IOException
     */
    void saveMoves(File file) throws IOException;

    /**
     *
     * @throws IOException
     */
    void loadMoves() throws IOException;

    /**
     *
     * @return list of moves
     */
    List<MoveDisplay> getMoves();

    /**
     *
     * @param move
     * @return ParsedMove
     */
    ParsedMove parseMove(String move);

    /**
     *
     * @param move
     * @return true if success
     */
    boolean addMove(MoveDisplay move);
}
