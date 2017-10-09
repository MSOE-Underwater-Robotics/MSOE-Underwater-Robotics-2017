package MSOE_ROV.Drawable;

import java.awt.*;

import processing.core.*;

/**
 * Created by getkar on 4/8/2016.
 */
public class HBar extends IDrawable {

    private Color color;
    private static int WIDTH = 500;
    private static int HEIGHT = 100;
    private static int RADIUS = 7;
    private String label;
    private boolean twoDirectional;
    private float max;
    private float min;
    private float value = 0;

    public HBar(PApplet pApplet,int startX, int startY, Color color, String label, boolean twoDirectional,float max,float min) {
        super(pApplet,startX, startY);
        this.color = color;
        this.label = label;
        this.twoDirectional = twoDirectional;
        this.max = max;
        this.min = min;
    }

    public boolean setValue(float value){
        if(value<min||value>max){
            return false;
        }
        this.value = value;
        return true;
    }


    @Override
    public void draw() {
        pApplet.fill(0);
        pApplet.text(label,xCoordStart+WIDTH/2-label.length()*4,yCoordStart+HEIGHT/2-10);
        pApplet.stroke(color.getRGB());
        pApplet.noFill();
        pApplet.rect(xCoordStart,yCoordStart+HEIGHT/2,WIDTH,HEIGHT*2/3,RADIUS);
        pApplet.fill(color.getRGB());

        if(twoDirectional){
            twoDirectionalDraw();
        } else{
            oneDirectionalDraw();
        }

    }

    private void oneDirectionalDraw(){
        float dist = max-min;
        float newVal = value/dist;

        pApplet.rect(xCoordStart,yCoordStart+HEIGHT/2,newVal*WIDTH,HEIGHT*2/3,RADIUS);
    }
    private void twoDirectionalDraw(){
        float dist = max-min;
        float newVal = (value-dist/2)/(dist);

        pApplet.rect(xCoordStart+WIDTH/2,yCoordStart+HEIGHT/2,newVal*WIDTH,HEIGHT*2/3,RADIUS);
    }

    /*
    max-min = total distance
    value/totalDistance = percentatge?
     */


}
