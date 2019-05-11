/**
 * IJA 2018/2019
 * Projekt
 * @author Jan Ježek (xjezek15)
 */

package ija.project.parser;

import ija.project.utilities.MoveDisplay;
import ija.project.utilities.ParsedMove;
import java.io.File;
import java.io.IOException;
import java.util.List;


public interface IInput 
{
    //void saveMoves(File file) throws IOException;
    List<MoveDisplay> getMoves();
    List<MoveDisplay> loadMoves(File file) throws IOException;
    ParsedMove parseMove(String move);
}
