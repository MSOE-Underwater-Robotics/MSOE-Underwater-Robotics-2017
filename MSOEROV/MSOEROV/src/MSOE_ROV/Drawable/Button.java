package MSOE_ROV.Drawable;

import processing.core.PApplet;

import java.awt.*;

/**
 * Created by getkar on 4/9/2016.
 */
public class Button extends IDrawable{

    private static int RADUIS = 7;
    private int width;
    private int height;
    private String label;
    private Color color;

    public Button(PApplet pApplet, int startX, int startY,int width, int height, Color color, String label) {
        super(pApplet, startX, startY);
        this.color = color;
        this.width = width;
        this.height = height;
        this.label = label;
    }

    @Override
    public void draw() {
        pApplet.fill(color.getRGB());
        pApplet.rect(xCoordStart,yCoordStart,width,height,RADUIS);
        pApplet.fill(0);
        pApplet.text(label,xCoordStart + width/2-label.length()*4,yCoordStart+height/2);
    }

    public boolean mouseIsOver() {
        if (pApplet.mouseX > xCoordStart && pApplet.mouseX < (xCoordStart + width) && pApplet.mouseY > yCoordStart && pApplet.mouseY < (yCoordStart + height)) {
            return true;
        }
        return false;
    }
}
