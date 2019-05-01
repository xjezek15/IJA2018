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
