package MSOE_ROV;

import org.gamecontrolplus.ControlDevice;
import org.gamecontrolplus.ControlIO;
import processing.core.PApplet;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by getkar on 5/13/2016.
 *
 * This is an abstract class to handle all Input for a IRobot.
 * It is represented by a HashMap<String,Object> because it needs to store Doubles and booleans.
 * As a convention, if it is a boolean value use a String containing "button"
 */
public abstract class IController {

    private Map<String, Object> controllerMap = new HashMap<String,Object>();

    protected ControlIO control;
    protected ControlDevice stick;

    public IController(PApplet pApplet){
        control = ControlIO.getInstance(pApplet);
    }
    public abstract void updateController();

    public Object getValue(String key){
        return controllerMap.get(key);
    }

    protected void setValue(String key,Object value){
        controllerMap.put(key, value);
    }

    public Set<String> getKeys(){
        return controllerMap.keySet();
    }

}
