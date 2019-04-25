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
            board.addFigure(col, 1, new FigureClass(false, Figure.Pawn, new int[]{col,1}));
            board.addFigure(col, 6, new FigureClass(true, Figure.Pawn, new int[]{col,6}));
        }

        board.addFigure(0, 0, new FigureClass(false, Figure.Rook, new int[]{0,0}));
        board.addFigure(7, 0, new FigureClass(false, Figure.Rook, new int[]{7,0}));

        board.addFigure(0, 7, new FigureClass(true, Figure.Rook, new int[]{0,7}));
        board.addFigure(7, 7, new FigureClass(true, Figure.Rook, new int[]{7,7}));

        return new GameClass();
    }

    public static Game createCheckersGame(Board board)
    {
        for (int col = 0; col < board.getSize(); col++)
        {
            if (col % 2 == 0)
            {
                board.addFigure(col, 0, new FigureClass(false, Figure.Disk, new int[]{col,0}));
            }
            else
            {
                board.addFigure(col, 1, new FigureClass(false, Figure.Disk, new int[]{col,1}));
            }
        }
        
        return new GameClass();
    }
}
