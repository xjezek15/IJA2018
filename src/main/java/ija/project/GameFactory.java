/*
 * IJA 2018/2019
 * Ukol 2
 */

package ija.project;

import ija.project.common.Figure;
import ija.project.common.FigureClass;
import ija.project.common.Game;
import ija.project.common.GameClass;
import ija.project.game.Board;

public abstract class GameFactory extends java.lang.Object
{
    public static Game createChessGame(Board board)
    {
        // adding pawns
        for (int col = 0; col < board.getSize(); col++) 
        {
            board.addFigure(col, 1, new FigureClass(false, Figure.Pawn));
            board.addFigure(col, 6, new FigureClass(true, Figure.Pawn));
        }

        // adding rooks
        board.addFigure(0, 0, new FigureClass(false, Figure.Rook));
        board.addFigure(7, 0, new FigureClass(false, Figure.Rook));

        board.addFigure(0, 7, new FigureClass(true, Figure.Rook));
        board.addFigure(7, 7, new FigureClass(true, Figure.Rook));
        
        // adding bishops
        board.addFigure(1, 0, new FigureClass(false, Figure.Bishop));
        board.addFigure(6, 0, new FigureClass(false, Figure.Bishop));

        board.addFigure(1, 7, new FigureClass(true, Figure.Bishop));
        board.addFigure(6, 7, new FigureClass(true, Figure.Bishop));
        
        // adding knights
        board.addFigure(2, 0, new FigureClass(false, Figure.Knight));
        board.addFigure(5, 0, new FigureClass(false, Figure.Knight));

        board.addFigure(2, 7, new FigureClass(true, Figure.Knight));
        board.addFigure(5, 7, new FigureClass(true, Figure.Knight));
        
        // adding king and queen
        board.addFigure(4, 0, new FigureClass(false, Figure.King));
        board.addFigure(3, 0, new FigureClass(false, Figure.Queen));

        board.addFigure(4, 7, new FigureClass(true, Figure.King));
        board.addFigure(3, 7, new FigureClass(true, Figure.Queen));

        return new GameClass();
    }

    public static Game createCheckersGame(Board board)
    {
        throw new UnsupportedOperationException("Checkers game not implemented");
//        for (int col = 0; col < board.getSize(); col++)
//        {
//            if (col % 2 == 0)
//            {
//                board.addFigure(col, 0, new FigureClass(false, Figure.Disk, new int[]{col,0}));
//            }
//            else
//            {
//                board.addFigure(col, 1, new FigureClass(false, Figure.Disk, new int[]{col,1}));
//            }
//        }
//        
//        return new GameClass();
    }
}
