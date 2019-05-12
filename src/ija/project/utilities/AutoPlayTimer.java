/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ija.project.utilities;

import ija.project.gui.MainJPanel;
import java.util.TimerTask;
/**
 *
 * @author simon
 */
public class AutoPlayTimer extends TimerTask{

    private final MainJPanel panel;
    private final int rows;
    private int row = 1;
    public AutoPlayTimer( MainJPanel panel, int rows) {
        this.panel = panel;
        this.rows = rows;
    }
    
    
    @Override
    public void run() {
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
