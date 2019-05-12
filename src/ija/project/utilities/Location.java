/**
 * IJA 2018/2019
 * Projekt
 * @author Jan Ježek (xjezek15)
 * @author Šimon Šesták (xsesta06)
 */

package ija.project.utilities;

/**
 * Functionality for Location
 * @author xjezek15
 */
public class Location 
{
    private final int col;
    private final int row;
    
    /**
     * Converts a-h to 1-8
     * @param column
     * @return int for column
     */
    public static int determineColumn(char column)
    {
        switch(column)
        {
            case 'a':
                return 1;
                
            case 'b':
                return 2;
               
            case 'c':
                return 3;
                
            case 'd':
                return 4;
                
            case 'e':
                return 5;
                
            case 'f':
                return 6;
                
            case 'g':
                return 7;
                
            case 'h':
                return 8;
                
            default:
                return 0;
        }           
    }
    
    /**
     *
     * @param col
     * @param row
     */
    public Location(int col, int row)
    {
        this.col = col;
        this.row = row;
    }
    
    /**
     *
     * @return column
     */
    public int getCol()
    {
        return this.col;
    }
    
    /**
     *
     * @return row
     */
    public int getRow()
    {
        return this.row;
    }
}
