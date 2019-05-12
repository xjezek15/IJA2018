/**
 * IJA 2018/2019
 * Projekt
 * @author Jan Ježek (xjezek15)
 * @author Šimon Šesták (xsesta06)
 */

package ija.project;

import ija.project.common.Figure;
import ija.project.common.Game;
import ija.project.common.IFigure;
import ija.project.common.IGame;
import ija.project.game.IBoard;

/**
 * Abstract class for creating new game of chess
 * @author xjezek15
 */
public abstract class GameFactory extends java.lang.Object
{
    /**
     * Adds all starting figures to board.
     * @param board
     * @return game
     */
    public static IGame createChessGame(IBoard board)
    {
        // adding pawns
        for (int col = 0; col < board.getSize(); col++) 
        {
            board.addFigure(col, 1, new Figure(false, IFigure.PAWN));
            board.addFigure(col, 6, new Figure(true, IFigure.PAWN));
        }

        // adding rooks
        board.addFigure(0, 0, new Figure(false, IFigure.ROOK));
        board.addFigure(7, 0, new Figure(false, IFigure.ROOK));

        board.addFigure(0, 7, new Figure(true, IFigure.ROOK));
        board.addFigure(7, 7, new Figure(true, IFigure.ROOK));
        
        // adding bishops
        board.addFigure(2, 0, new Figure(false, IFigure.BISHOP));
        board.addFigure(5, 0, new Figure(false, IFigure.BISHOP));

        board.addFigure(2, 7, new Figure(true, IFigure.BISHOP));
        board.addFigure(5, 7, new Figure(true, IFigure.BISHOP));
        
        // adding knights
        board.addFigure(1, 0, new Figure(false, IFigure.KNIGHT));
        board.addFigure(6, 0, new Figure(false, IFigure.KNIGHT));

        board.addFigure(1, 7, new Figure(true, IFigure.KNIGHT));
        board.addFigure(6, 7, new Figure(true, IFigure.KNIGHT));
        
        // adding king and queen
        board.addFigure(4, 0, new Figure(false, IFigure.KING));
        board.addFigure(3, 0, new Figure(false, IFigure.QUEEN));

        board.addFigure(4, 7, new Figure(true, IFigure.KING));
        board.addFigure(3, 7, new Figure(true, IFigure.QUEEN));

        return new Game();
    }
}
