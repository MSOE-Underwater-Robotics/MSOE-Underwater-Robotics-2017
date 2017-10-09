import sync.DataHandler;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import org.gamecontrolplus.gui.*;
import org.gamecontrolplus.*;

DataHandler handler;
String inVars[] = {"millis", "num-millis", "null"};
String outVars[] = {"frontLeftXY", "frontLeftZ", "frontRightXY", "frontRightZ", "backRightXY", "backRightZ", "backLeftXY", "backLeftZ"};

double yAxis, xAxis, zAxis, yRotation, xRotation;
boolean xButton, triangleButton,circleButton,squareButton,leftBumper,rightBumper,selectButton,startButton,
        leftStickButton, rightStickButton, upArrow,leftArrow,downArrow,rightArrow;
static int MOTORMAX = 3200;
ControlIO control;
ControlDevice stick;

PFont font;

void setup() {
  font = createFont("Calibri",30,true);
  fullScreen();
  fill(255);
  background(0);
  textFont(font);
  handler = new DataHandler(
    inVars, //received from device
    outVars, //sent to device
    20, //update rate, in milliseconds
    false, //debug mode
    "192.168.1.217", //device IP
    4545, //device UDP port
    4550); //host UDP port
    
    
  // Initialise the ControlIO
  control = ControlIO.getInstance(this);
  // Find a device that matches the configuration file
  stick = control.getMatchedDevice("PS3Controller");
  if (stick == null) {
    println("No suitable device configured");
   System.exit(-1); // End the program NOW!
  }
   // GUI Stuff
  updateControllerValues();

    frameRate(60);
}



void draw(){
  background(0);
  textFont(font,40);
  text("MSOE ROV 2016",15,30);
  textFont(font);
  updateControllerValues();
  
    tankDriveXY();
    triggerZDrive();
} //<>//


void triggerZDrive(){
   drawAndDriveZ(zAxis,zAxis,zAxis,zAxis); 
}

//tankDrive
void tankDriveXY(){
  drawAndDriveXY(yAxis,yRotation,yAxis,yRotation);
}

void vectorDriveXY(){
  double x = xRotation;
  double y = yAxis*-1;
  double r = xAxis;
  
  double v_FrontLeft  = r-y-x,
         v_FrontRight = r+y+x,
         v_BackLeft   = r-y+x,
         v_BackRight  = r+y-x;
         
 //v_FrontLeft =v_FrontLeft *-1;
 //v_BackLeft = v_BackLeft*-1;
 v_FrontRight = v_FrontRight*-1;
 v_BackRight = v_BackRight *-1;
         
  if(v_FrontLeft>1.0){
     v_FrontLeft = 1.0;
  }
  if(v_FrontLeft<-1.0){
     v_FrontLeft = -1.0;
  }
  
  if(v_FrontRight>1.0){
     v_FrontRight = 1.0;
  }
  if(v_FrontRight<-1.0){
     v_FrontRight = -1.0;
  }
  
  if(v_BackLeft>1.0){
     v_BackLeft = 1.0;
  }
  if(v_BackLeft<-1.0){
     v_BackLeft = -1.0;
  }
  
  if(v_BackRight>1.0){
     v_BackRight = 1.0;
  }
  if(v_BackRight<-1.0){
     v_BackRight = -1.0;
  }
  
    drawAndDriveXY(v_FrontLeft,v_FrontRight,v_BackLeft,v_BackRight);

}

void updateControllerValues(){
 //double yAxis, xAxis, zAxis, yRotation, xRotation;
 //boolean xButton, triangleButton,circleButton,squareButton,leftBumper,rightBumper,selectButton,startButton,
        //leftStickButton, rightStickButton, upArrow,leftArrow,downArrow,rightArrow;
  yAxis = stick.getSlider("YAxis").getValue()*-1;
  xAxis = stick.getSlider("XAxis").getValue();
  zAxis = stick.getSlider("ZAxis").getValue();
  yRotation = stick.getSlider("YRotation").getValue()*-1;
  xRotation = stick.getSlider("XRotation").getValue();

  xButton = stick.getButton("XButton").pressed();
  triangleButton = stick.getButton("TriangleButton").pressed();
  circleButton = stick.getButton("CircleButton").pressed();
  squareButton = stick.getButton("SquareButton").pressed();
  leftBumper = stick.getButton("LeftBumper").pressed();
  rightBumper = stick.getButton("RightBumper").pressed();
  selectButton = stick.getButton("SelectButton").pressed();
  startButton = stick.getButton("StartButton").pressed();
  leftStickButton = stick.getButton("LeftStickButton").pressed();
  rightStickButton = stick.getButton("RightStickButton").pressed();
  
  
  upArrow = stick.getHat("ArrowDirections").up();
  leftArrow = stick.getHat("ArrowDirections").left();
  rightArrow = stick.getHat("ArrowDirections").right();
  downArrow = stick.getHat("ArrowDirections").down();
  
 
  
}


void drawAndDriveZ(double flZ, double frZ, double blZ, double brZ){
  
  text("FrontLeftZ",15,500);
  text("FrontRightZ",215,500);
  text("BackLeftZ",415,500);
  text("BackRightZ",615,500);
  
  
  rect(40,700,75,(float)(-flZ*150));
  rect(240,700,75,(float)(-frZ*150));
  rect(440,700,75,(float)(-blZ*150));
  rect(640,700,75,(float)(-brZ*150));
  
  handler.setValue("frontLeftZ",(int)(flZ*MOTORMAX));
  handler.setValue("frontRightZ",(int)(frZ*MOTORMAX));
  handler.setValue("backLeftZ",(int)(blZ*MOTORMAX));
  handler.setValue("backRightZ",(int)(brZ*MOTORMAX));
  
  
}




//takes values for motors from -1 to 1 as doubles
void drawAndDriveXY(double flXY,double frXY, double blXY, double brXY){
  text("FrontLeftXY",15,100);
  text("FrontRightXY",215,100);
  text("BackLeftXY",415,100);
  text("BackRightXY",615,100);
  
  rect(40,300,75,(float)(-flXY*150));
  rect(240,300,75,(float)(-frXY*150));
  rect(440,300,75,(float)(-blXY*150));
  rect(640,300,75,(float)(-brXY*150));
  
  handler.setValue("frontLeftXY",(int)(flXY*MOTORMAX));
  handler.setValue("frontRightXY",(int)(frXY*MOTORMAX));
  handler.setValue("backLeftXY",(int)(blXY*MOTORMAX));
  handler.setValue("backRightXY",(int)(brXY*MOTORMAX));

}