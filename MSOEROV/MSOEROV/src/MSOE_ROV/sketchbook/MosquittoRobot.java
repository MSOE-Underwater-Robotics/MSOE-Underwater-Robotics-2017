package MSOE_ROV.sketchbook;

import MSOE_ROV.Drawable.VBar;
import MSOE_ROV.IRobot;
import MSOE_ROV.sketchbook.PS4Controller;
import processing.core.PFont;
import sync.DataHandler;

import java.awt.*;

/**
 * Created by getkar on 5/13/2016.
 */
public class MosquittoRobot extends IRobot {


    PFont font;
    private static int MOTORMAX = 90;
    public MosquittoRobot() {
        controller = new PS4Controller(this);
        inVars = new String[]{"cycletime"};

        outVars = new String[]{"m0","m1","m2","m3","m4"};
        //m3 gripper
        //m1 left xy
        //m2 right xy
        //m0 all z


        handler = new DataHandler(
                inVars, //received from device
                outVars, //sent to device
                20, //update rate, in milliseconds
                false, //debug mode
                "192.168.2.217", //device IP
                4545, //device UDP port
                4550); //host UDP port


    }


    @Override
    public void setup() {
        font = createFont("Calibri", 30, true);
        fullScreen();
        fill(255);
        //background(0);
        textFont(font);


    }

    public volatile float precision = 1.0f;
    public void keyPressed(){
        if(key == CODED){
            if(keyCode == UP){
                handler.setValue("m3", 110);
            } else if(keyCode == DOWN){
                handler.setValue("m3", -110);
            } else if(keyCode == RIGHT){
                handler.setValue("m3", 210);
            } else if(keyCode == LEFT){
                handler.setValue("m3", -210);
            }
        }

        if(key == '1'){
            precision = 0.25f;
        } else if(key == '2'){
            precision = 0.50f;
        } else if(key == '3'){
            precision = 0.75f;
        } else if(key == '4'){
            precision = 1.00f;
        }
    }

    public void keyReleased(){
        handler.setValue("m3", 0);
    }


    @Override
    public void dispose() {
        //String outVars[] = {"frontLeftXY", "frontLeftZ", "frontRightXY", "frontRightZ",
        //"backRightXY", "backRightZ", "backLeftXY", "backLeftZ",
        //"PID_Kp", "PID_Ki", "PID_Kd", "PID_dir", "PID_setPoint", "PID_Kp_low", "PID_Ki_low", "PID_Kd_low", "PIDgainSwitchPoint_uBar", "depthFilter", "dFilterN", "PIDenable",
        //"gripperServo0", "gripperServo1"};
        handler.setValue("m0", 90);
        handler.setValue("m1", 90);
        handler.setValue("m2", 90);
        handler.setValue("m3", 0);
        //handler.setValue("m3", 90);

        //handler.setValue("gripperServo0", 90);
    }


    @Override
    public void draw() {
        super.draw();
        background(255);
        drawXY();
        drawZ();
    }

    @Override
    public void drive() {
        driveXY();
        driveZ();
    }

    @Override
    public void settings() {
        fullScreen();
        //size(500,500);
    }


    private void driveXY(){
        handler.setValue("m1",(int)(precision * MOTORMAX*(float)(controller.getValue("LeftStickY"))+95));
        handler.setValue("m1",(int)(precision * MOTORMAX*(float)(controller.getValue("LeftStickY"))+95));
        handler.setValue("m2",(int)(precision * MOTORMAX*(float)(controller.getValue("RightStickY"))+90));
        handler.setValue("m2",(int)(precision * MOTORMAX*(float)(controller.getValue("RightStickY"))+90));
    }


    VBar fLXY = new VBar(this,100,100, Color.red,"FLXY",true,MOTORMAX,-MOTORMAX);
    VBar rLXY = new VBar(this,250,100, Color.red,"RLXY",true,MOTORMAX,-MOTORMAX);
    VBar fRXY = new VBar(this,400,100, Color.red,"FRXY",true,MOTORMAX,-MOTORMAX);
    VBar rRXY = new VBar(this,550,100, Color.red,"RRXY",true,MOTORMAX,-MOTORMAX);

    private void drawXY(){
        fLXY.setValue((precision * -1*MOTORMAX*(float)(controller.getValue("LeftStickY"))));
        rLXY.setValue((precision * -1*MOTORMAX*(float)(controller.getValue("LeftStickY"))));
        fRXY.setValue((precision * -1*MOTORMAX*(float)(controller.getValue("RightStickY"))));
        rRXY.setValue((precision * -1*MOTORMAX*(float)(controller.getValue("RightStickY"))));
        fLXY.draw();
        rLXY.draw();
        fRXY.draw();
        rRXY.draw();
    }

    private void driveZ(){

        double value = (((float)(controller.getValue("RightTrigger")))+(-1*(float)(controller.getValue("LeftTrigger"))))/2.0;
        handler.setValue("m0",(int)(-1*precision * MOTORMAX*value)+95);
//        handler.setValue("m0",(int)(-1*precision * MOTORMAX*value)+90);
//        handler.setValue("m0",(int)(-1*precision * MOTORMAX*value)+90);
//        handler.setValue("m0",(int)(-1*precision * MOTORMAX*value)+90);
    }

    VBar fLZ = new VBar(this,100,600, Color.red,"FLZ",true,MOTORMAX,-MOTORMAX);
    VBar rLZ = new VBar(this,250,600, Color.red,"RLZ",true,MOTORMAX,-MOTORMAX);
    VBar fRZ = new VBar(this,400,600, Color.red,"FRZ",true,MOTORMAX,-MOTORMAX);
    VBar rRZ = new VBar(this,550,600, Color.red,"RRZ",true,MOTORMAX,-MOTORMAX);
    private void drawZ(){
        double value = (((float)(controller.getValue("RightTrigger")))+(-1*(float)(controller.getValue("LeftTrigger"))))/2.0;
        fLZ.setValue((-1*precision * MOTORMAX*(float)value));
        rLZ.setValue((-1*precision * MOTORMAX*(float)value));
        fRZ.setValue((-1*precision * MOTORMAX*(float)value));
        rRZ.setValue((-1*precision * MOTORMAX*(float)value));
        fLZ.draw();
        rLZ.draw();
        fRZ.draw();
        rRZ.draw();
    }


}
