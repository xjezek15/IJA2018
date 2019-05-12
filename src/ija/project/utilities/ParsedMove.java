/**
 * IJA 2018/2019
 * Projekt
 * @author Jan Ježek (xjezek15)
 * @author Šimon Šesták (xsesta06)
 */

package ija.project.utilities;

import ija.project.common.IFigure;

/**
 * Implements parsedMove
 * @author xjezek15
 */
public class ParsedMove 
{
    private final IFigure.Type figureTypeFrom;
    private final IFigure.Type figureTypeTo;
    private final Location locationFrom;
    private final Location locationTo;
    private final boolean capture;
    private final boolean check;
    private final boolean mate;
    
    /**
     * ParsedMove without from location.
     * @param figureTypeTo
     * @param locationTo
     * @param capture
     * @param check
     * @param mate
     */
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

    /**
     * ParsedMove with from location.
     * @param figureTypeFrom
     * @param figureTypeTo
     * @param locationFrom
     * @param locationTo
     * @param capture
     * @param check
     * @param mate
     */
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

    /**
     *
     * @return location from
     */
    public Location getLocationFrom() 
    {
        return locationFrom;
    }

    /**
     *
     * @return location to
     */
    public Location getLocationTo() 
    {
        return locationTo;
    }
    
    
}
