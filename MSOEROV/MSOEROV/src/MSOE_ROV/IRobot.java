package MSOE_ROV;

import MSOE_ROV.IController;
import processing.core.PApplet;
import sync.DataHandler;

/**
 * Created by getkar on 4/16/2016.
 *
 * An abstract class to represent the actual ROV, containing all Inputs and Outputs, and drawing to the screen.
 */
public abstract class IRobot extends PApplet{

    //IController Input
    protected IController controller;

    //DataHandler Output
    protected String inVars[];
    protected String outVars[];
    protected DataHandler handler;



    //MUST CALL DRIVE IN DRAW METHOD
    public void draw(){
        clear();
        controller.updateController();
        drive();
    }
    public abstract void drive();
    public abstract void settings();
    public abstract void setup();
    public abstract void dispose();


}
