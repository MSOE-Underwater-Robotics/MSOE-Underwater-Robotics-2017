package Unused;

import MSOE_ROV.Drawable.Clock;
import MSOE_ROV.Drawable.ClockEvent;
import MSOE_ROV.Drawable.HBar;
import MSOE_ROV.Drawable.VBar;
import processing.core.PApplet;
import processing.core.PFont;

import java.awt.*;

public class Sandbox extends PApplet {

    PFont pFont;

    HBar tBar = new HBar(this,1,1, Color.blue,"Two Directional",true,100,0);
    HBar oBar = new HBar(this,1,100, Color.blue,"One Directional",false,100,0);
    VBar vBar = new VBar(this,600,1,Color.red,"VBar",true,100,0);
    VBar vtBar = new VBar(this,800,1,Color.red,"VBar",false,100,0);

    Clock clock = new Clock(this,500,500,5000,new ClockEvent(0,Color.green,"Clock Started"));



    public void settings(){
        size(1000,1000);

    }
    public void setup(){
        pFont = createFont("Calibre",16,true);
        clock.start();
    }
    private int cnt = 0;
    public void draw(){
        clear();
        background(255);
        textFont(pFont);
        cnt++;
        if(cnt>100){
            cnt = 0;
        }
        oBar.setValue(cnt);
        oBar.draw();
        tBar.setValue((float)cnt);
        tBar.draw();
        vBar.setValue(cnt);
        vBar.draw();
        vtBar.setValue(cnt);
        vtBar.draw();


        clock.draw();
    }
}