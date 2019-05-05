/**
 * IJA 2018/2019
 * Projekt
 * @author Jan Ježek (xjezek15)
 */

package ija.project.utilities;

import ija.project.common.IFigure;

public class ParsedMove 
{
    private IFigure.Type figureTypeFrom;
    private IFigure.Type figureTypeTo;
    private Location locationFrom;
    private Location locationTo;
    private boolean capture;
    private boolean check;
    private boolean mate;
    
    public ParsedMove(IFigure.Type figureTypeTo, Location locationTo, boolean capture, boolean  check, boolean mate) 
    {
        this.figureTypeFrom = null;
        this.figureTypeTo = figureTypeTo;
        this.locationFrom = null;
        this.locationTo = locationTo;
        this.capture = capture;
        this.check = check;
        this.mate = mate;
    }

    public ParsedMove(IFigure.Type figureTypeFrom, IFigure.Type figureTypeTo, Location locationFrom, Location locationTo, boolean capture, boolean  check, boolean mate) 
    {
        this.figureTypeFrom = figureTypeFrom;
        this.figureTypeTo = figureTypeTo;
        this.locationFrom = locationFrom;
        this.locationTo = locationTo;
        this.capture = capture;
        this.check = check;
        this.mate = mate;
    }

    public boolean isCapture() 
    {
        return capture;
    }

    public boolean isCheck() 
    {
        return check;
    }

    public boolean isMate() 
    {
        return mate;
    }

    public IFigure.Type getFigureTypeFrom() 
    {
        return figureTypeFrom;
    }

    public IFigure.Type getFigureTypeTo() 
    {
        return figureTypeTo;
    }

    public Location getLocationFrom() 
    {
        return locationFrom;
    }

    public Location getLocationTo() 
    {
        return locationTo;
    }
    
    
}
