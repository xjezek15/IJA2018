/**
 * IJA 2018/2019
 * Úkol 2
 * @author Jan Ježek (xjezek15)
 */

package ija.project.game;

import ija.project.common.BoardField;
import ija.project.common.Field;
import ija.project.common.Figure;

public class Board extends java.lang.Object
{
    private int size;
    private Field board[][];

    public Board(int size) 
    {
        this.size = size;
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
    }

    public boolean addFigure(int col, int row, Figure figure)
    {
        return this.board[col][row].putFigure(figure);
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
