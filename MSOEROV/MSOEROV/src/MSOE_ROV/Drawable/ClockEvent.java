package MSOE_ROV.Drawable;

import java.awt.*;

/**
 * Created by getkar on 4/9/2016.
 */
public class ClockEvent implements Comparable<ClockEvent> {
    public long numMSec;
    public Color color;
    public String label;

    public ClockEvent(int numMSec,Color color,String label){
        this.numMSec = numMSec;
        this.color = color;
        this.label = label;
    }


    @Override
    public int compareTo(ClockEvent o) {
            return (int)(this.numMSec - o.numMSec);
    }
}
