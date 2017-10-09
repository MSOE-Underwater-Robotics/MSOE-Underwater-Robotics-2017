package MSOE_ROV.Drawable;

import processing.core.PApplet;

/**
 * Created by getkar on 3/12/2016.
 */
public abstract class IDrawable {
    int xCoordStart = 0;
    int yCoordStart = 0;
    PApplet pApplet;

    public IDrawable(PApplet pApplet,int startX, int startY){
        xCoordStart = startX;
        yCoordStart = startY;
        this.pApplet = pApplet;
    }
    public abstract void draw();
}
