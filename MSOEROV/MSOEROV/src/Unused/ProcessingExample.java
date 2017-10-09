package Unused;

import processing.core.*;

public class ProcessingExample extends PApplet {

    public void settings() {
        size(400, 400);
    }
    public void setup() {
        background(0);
    }

    public void draw() {
        stroke(255);
        if (mousePressed) {
            line(mouseX,mouseY,pmouseX,pmouseY);
        }
    }
}