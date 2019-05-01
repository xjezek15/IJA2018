/**
 * IJA 2018/2019
 * Projekt
 * @author Jan Ježek (xjezek15)
 */

package ija.project;

import ija.project.common.Figure;
import ija.project.common.Game;
import ija.project.game.Board;
import ija.project.common.IFigure;
import ija.project.common.IGame;
import ija.project.game.IBoard;

public abstract class GameFactory extends java.lang.Object
{
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
        board.addFigure(1, 0, new Figure(false, IFigure.BISHOP));
        board.addFigure(6, 0, new Figure(false, IFigure.BISHOP));

        board.addFigure(1, 7, new Figure(true, IFigure.BISHOP));
        board.addFigure(6, 7, new Figure(true, IFigure.BISHOP));
        
        // adding knights
        board.addFigure(2, 0, new Figure(false, IFigure.KNIGHT));
        board.addFigure(5, 0, new Figure(false, IFigure.KNIGHT));

        board.addFigure(2, 7, new Figure(true, IFigure.KNIGHT));
        board.addFigure(5, 7, new Figure(true, IFigure.KNIGHT));
        
        // adding king and queen
        board.addFigure(4, 0, new Figure(false, IFigure.KING));
        board.addFigure(3, 0, new Figure(false, IFigure.QUEEN));

        board.addFigure(4, 7, new Figure(true, IFigure.KING));
        board.addFigure(3, 7, new Figure(true, IFigure.QUEEN));

        return new Game();
    }

    public static IGame createCheckersGame(Board board)
    {
        throw new UnsupportedOperationException("Checkers game not implemented");
//        for (int col = 0; col < board.getSize(); col++)
//        {
//            if (col % 2 == 0)
//            {
//                board.addFigure(col, 0, new Figure(false, IFigure.Disk, new int[]{col,0}));
//            }
//            else
//            {
//                board.addFigure(col, 1, new Figure(false, IFigure.Disk, new int[]{col,1}));
//            }
//        }
//        
//        return new Game();
    }
}
