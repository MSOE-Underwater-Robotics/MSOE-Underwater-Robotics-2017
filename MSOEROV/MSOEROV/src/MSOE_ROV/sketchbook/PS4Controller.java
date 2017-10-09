package MSOE_ROV.sketchbook;

import MSOE_ROV.IController;
import processing.core.PApplet;

/**
 * Created by getkar on 5/13/2016.
 */
public class PS4Controller extends IController {


    public PS4Controller(PApplet pApplet) {
        super(pApplet);
        stick = control.getMatchedDevice("PS4Controller");
    }

    @Override
    public void updateController() {

        if((stick.getSlider("RightStickY").getValue()<0.05&&stick.getSlider("RightStickY").getValue()>=0)||
                ((stick.getSlider("RightStickY").getValue()>-0.05&&stick.getSlider("RightStickY").getValue()<=0))){
            setValue("RightStickY", (float)(0.0));
        } else{
            setValue("RightStickY", stick.getSlider("RightStickY").getValue());
        }
        if((stick.getSlider("LeftStickY").getValue()<0.05&&stick.getSlider("LeftStickY").getValue()>=0) ||
                ((stick.getSlider("LeftStickY").getValue()>-0.05&&stick.getSlider("LeftStickY").getValue()<=0))){
            setValue("LeftStickY", (float)0.0);
        } else{
            setValue("LeftStickY", stick.getSlider("LeftStickY").getValue());
        }
        setValue("RightStickX",  stick.getSlider("RightStickX").getValue());
        //setValue("LeftStickY",  stick.getSlider("LeftStickY").getValue());
        setValue("LeftStickX", stick.getSlider("LeftStickX").getValue());
        setValue("RightTrigger" , stick.getSlider("RightTrigger").getValue());
        setValue("LeftTrigger" , stick.getSlider("LeftTrigger").getValue());


        setValue("SquareButton" , stick.getButton("SquareButton").pressed());
        setValue("XButton" , stick.getButton("XButton").pressed());
        setValue("CircleButton",  stick.getButton("CircleButton").pressed());
        setValue("TriangleButton" , stick.getButton("TriangleButton").pressed());
        setValue("LeftBumperButton" , stick.getButton("LeftBumperButton").pressed());
        setValue("RightBumperButton" , stick.getButton("RightBumperButton").pressed());
        setValue("LeftTriggerButton" , stick.getButton("LeftTriggerButton").pressed());
        setValue("RightTriggerButton" , stick.getButton("RightTriggerButton").pressed());
        setValue("ShareButton" , stick.getButton("ShareButton").pressed());

        setValue("OptionsButton" , stick.getButton("OptionsButton").pressed());
        setValue("LeftStickButton" , stick.getButton("LeftStickButton").pressed());
        setValue("RightStickButton" , stick.getButton("RightStickButton").pressed());
        setValue("PSButton" , stick.getButton("PSButton").pressed());
        setValue("TrackPadButton" , stick.getButton("TrackPadButton").pressed());



        setValue("UpArrow" ,stick.getHat("ArrowHat").up());
        setValue("LeftArrow" , stick.getHat("ArrowHat").left());
        setValue("RightArrow",  stick.getHat("ArrowHat").right());
        setValue("DownArrow" , stick.getHat("ArrowHat").down());
    }
}
