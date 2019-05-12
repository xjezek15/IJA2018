/**
 * IJA 2018/2019
 * Projekt
 * @author Jan Ježek (xjezek15)
 * @author Šimon Šesták (xsesta06)
 */

package ija.project.game;

import ija.project.common.Field;
import ija.project.common.IField;
import ija.project.common.IFigure;

/**
 * Implements functionality for game board.
 * @author xjezek15
 */
public class Board extends java.lang.Object implements IBoard
{
    private final int size;
    private final IField board[][];

    /**
     * Initializes all surrounding fields.
     * @param size
     */
    public Board(int size) 
    {
        this.size = size;
        this.board = new IField[this.size][this.size];

        // creating fields
        for (int row = 0; row < this.size; row++) 
        {
            for (int col = 0; col < this.size; col++) 
            {
                this.board[col][row] = new Field(col, row);
            }    
        }

        // adding surrounding fields
        for (int row = 0; row < this.size; row++)
        {
            for (int col = 0; col < this.size; col++)
            {
                if (col != this.size - 1) 
                {
                    this.board[col][row].addNextField(IField.R, this.board[col + 1][row]);
                }

                if (col > 0)
                {
                    this.board[col][row].addNextField(IField.L, this.board[col - 1][row]);
                }

                if (row != this.size - 1)
                {
                    this.board[col][row].addNextField(IField.U, this.board[col][row + 1]);
                }

                if (row > 0)
                {
                    this.board[col][row].addNextField(IField.D, this.board[col][row - 1]);
                }

                if (col != 0 && row != 0)
                {
                    this.board[col][row].addNextField(IField.LD, this.board[col - 1][row - 1]);
                }

                if (col != this.size - 1 && row != 0)
                {
                    this.board[col][row].addNextField(IField.RD, this.board[col + 1][row - 1]);
                }

                if (col != 0 && row != this.size - 1)
                {
                    this.board[col][row].addNextField(IField.LU, this.board[col - 1][row + 1]);
                }

                if (col != this.size - 1 && row != this.size - 1)
                {
                    this.board[col][row].addNextField(IField.RU, this.board[col + 1][row + 1]);
                }
            }
        }
    }

    /**
     * Adds figure to field.
     * @param col
     * @param row
     * @param figure
     * @return true if success
     */
    @Override
    public boolean addFigure(int col, int row, IFigure figure)
    {
        return this.board[col][row].putFigure(figure);
    }

    /**
     * Input is from 1 to 8.
     * @param col
     * @param row
     * @return field
     */
    @Override
    public IField getField(int col, int row)
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

    /**
     *
     * @return size of board
     */
    @Override
    public int getSize()
    {
        return this.size;
    }
}
