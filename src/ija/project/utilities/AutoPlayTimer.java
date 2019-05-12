/**
 * IJA 2018/2019
 * Projekt
 * @author Jan Ježek (xjezek15)
 * @author Šimon Šesták (xsesta06)
 */
package ija.project.utilities;

import ija.project.gui.MainJPanel;
import java.util.TimerTask;

/**
 * Functionality for autoplay
 * @author xsesta06
 */
public class AutoPlayTimer extends TimerTask
{
    private final MainJPanel panel;
    private final int rows;
    private int row = 1;
    
    /**
     * 
     * @param panel
     * @param rows
     */
    public AutoPlayTimer( MainJPanel panel, int rows) 
    {
        this.panel = panel;
        this.rows = rows;
    }
    
    /**
     * Runs timer
     */
    @Override
    public void run() 
    {
        panel.Next();
        panel.highlight(row++);
        if(row >= rows)
        {
            panel.getTe().cancel();
            panel.getTimer().cancel();
            panel.getTimer().purge();
            return;
        }
    }
}
