/**
 * IJA 2018/2019
 * Projekt
 * @author Jan Ježek (xjezek15)
 */

package ija2018.project.board;

public class Board extends java.lang.Object
{
    private int size;
    private Field board[][];

    public Board() 
    {
        this.size = 8;
        this.board = new Field[this.size][this.size];
        boolean isBlack = false;

        // creating fields
        for (int row = 0; row < this.size; row++) 
        {
            if (row % 2 == 0)
            {
                isBlack = true;
            }
            else
            {
                isBlack = false;
            }

            for (int col = 0; col < this.size; col++) 
            {
                if (col % 2 == 0 && isBlack)
                {
                    isBlack = false;
                }
                else
                {
                    isBlack = true;
                }

                this.board[col][row] = new BoardField(col, row, isBlack);
            }    
        }

        // adding surrounding fields
        for (int row = 0; row < this.size; row++)
        {
            for (int col = 0; col < this.size; col++)
            {
                if (col != this.size - 1) 
                {
                    this.board[col][row].addNextField(Field.R, this.board[col + 1][row]);
                }

                if (col > 0)
                {
                    this.board[col][row].addNextField(Field.L, this.board[col - 1][row]);
                }

                if (row != this.size - 1)
                {
                    this.board[col][row].addNextField(Field.U, this.board[col][row + 1]);
                }

                if (row > 0)
                {
                    this.board[col][row].addNextField(Field.D, this.board[col][row - 1]);
                }

                if (col != 0 && row != 0)
                {
                    this.board[col][row].addNextField(Field.LD, this.board[col - 1][row - 1]);
                }

                if (col != this.size - 1 && row != 0)
                {
                    this.board[col][row].addNextField(Field.RD, this.board[col + 1][row - 1]);
                }

                if (col != 0 && row != this.size - 1)
                {
                    this.board[col][row].addNextField(Field.LU, this.board[col - 1][row + 1]);
                }

                if (col != this.size - 1 && row != this.size - 1)
                {
                    this.board[col][row].addNextField(Field.RU, this.board[col + 1][row + 1]);
                }
            }
        }

        // adding figures
        for (int col = 0; col < this.size; col++) 
        {
            this.board[col][1].putFigure(new Figure(false, Figure.Pawn));
            this.board[col][6].putFigure(new Figure(true, Figure.Pawn));
        }

        this.board[0][0].putFigure(new Figure(false, Figure.Rook));
        this.board[1][0].putFigure(new Figure(false, Figure.Knight));
        this.board[2][0].putFigure(new Figure(false, Figure.Bishop));
        this.board[3][0].putFigure(new Figure(false, Figure.Queen));
        this.board[4][0].putFigure(new Figure(false, Figure.King));
        this.board[5][0].putFigure(new Figure(false, Figure.Bishop));
        this.board[6][0].putFigure(new Figure(false, Figure.Knight));
        this.board[7][0].putFigure(new Figure(false, Figure.Rook));

        this.board[0][7].putFigure(new Figure(true, Figure.Rook));
        this.board[1][7].putFigure(new Figure(true, Figure.Knight));
        this.board[2][7].putFigure(new Figure(true, Figure.Bishop));
        this.board[3][7].putFigure(new Figure(true, Figure.Queen));
        this.board[4][7].putFigure(new Figure(true, Figure.King));
        this.board[5][7].putFigure(new Figure(true, Figure.Bishop));
        this.board[6][7].putFigure(new Figure(true, Figure.Knight));
        this.board[7][7].putFigure(new Figure(true, Figure.Rook));
    }

    public Field getField(int col, int row)
    {
        if (col == 0 || row == 0)
        {
            return null;
        }

        if (col > this.size + 1 || row > this.size + 1)
        {
            return null;
        }
        else
        {
            return this.board[col - 1][row - 1];
        }
    }

    public int getSize()
    {
        return this.size;
    }
}