package MSOE_ROV.Drawable;

import processing.core.PApplet;

import java.awt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

/**
 * Created by getkar on 4/9/2016.
 */
public class Clock extends IDrawable {

    List<ClockEvent> events = new ArrayList<>();
    private static int WIDTH = 300;
    private static int HEIGHT = 200;
    private static int RADIUS = 7;
    private static DateFormat formater = new SimpleDateFormat("mm:ss:SSS");
    private long msDuration;
    private boolean isStart = false;

    private ClockEvent currentEvent;

    private long startTime;
    private long endTime;
    private Button startButton;
    private Button stopButton;

    public Clock(PApplet pApplet, int startX, int startY,long msDuration,ClockEvent currentEvent) {
        super(pApplet, startX, startY);
        if(currentEvent.numMSec!=0){
            throw new IllegalArgumentException("currentEvent.numMSec must equal 0!");
        }
        this.msDuration = msDuration;
        this.currentEvent = currentEvent;
        events.add(currentEvent);
        startButton = new Button(pApplet,xCoordStart+10,yCoordStart+100,100,50,Color.red,"Start");
        stopButton = new Button(pApplet,xCoordStart + 190,yCoordStart+100,100,50,Color.red,"Stop");


    }

    public boolean start(){
        if(isStart){
            return false;
        }
        startTime = System.currentTimeMillis();
        endTime = startTime + msDuration;
        isStart = true;
        return true;
    }
    public void stop(){
        isStart = false;
    }
    public void restart(){
        startTime = System.currentTimeMillis();
        endTime = startTime + msDuration;
        isStart = true;
    }
    private void addClockEvent(ClockEvent event){
        events.add(event);
    }
    private void getCurrentEvent(){
        Collections.sort(events);
        for(int i = 0 ; i<events.size();i++) {
            if (events.get(i).numMSec < System.currentTimeMillis() - startTime) {
                if (currentEvent.compareTo(events.get(i)) < 0) {
                    currentEvent = events.get(i);
                }
            }
        }
    }

    @Override
    public void draw() {
        pApplet.stroke(currentEvent.color.getRGB());
        pApplet.fill(currentEvent.color.getRGB());
        pApplet.rect(xCoordStart,yCoordStart,WIDTH,HEIGHT,RADIUS);
        if(isStart) {
            pApplet.fill(0);
            pApplet.text(currentEvent.label, xCoordStart + WIDTH / 2 - currentEvent.label.length() * 4, yCoordStart + 30);
            String time = formater.format(new Date(System.currentTimeMillis()-startTime ));
            pApplet.text(time, xCoordStart + WIDTH / 2 - time.length() * 4, yCoordStart + 60);
        }
        if(pApplet.mousePressed&&startButton.mouseIsOver()){
            restart();
        }
        if(pApplet.mousePressed && stopButton.mouseIsOver()){
            stop();
        }
        startButton.draw();
        stopButton.draw();


    }
}

