package ija.project.utilities;

/**
 *
 * @author janje
 */
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
