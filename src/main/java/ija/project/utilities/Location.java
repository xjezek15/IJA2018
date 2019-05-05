/**
 * IJA 2018/2019
 * Projekt
 * @author Jan Ježek (xjezek15)
 */

package ija.project.utilities;

public class Location 
{
    private int col;
    private int row;
    
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
    
    public Location(int col, int row)
    {
        this.col = col;
        this.row = row;
    }
    
    public int getCol()
    {
        return this.col;
    }
    
    public int getRow()
    {
        return this.row;
    }
}
