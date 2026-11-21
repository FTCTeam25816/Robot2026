package org.firstinspires.ftc.teamcode.Examples.Examples.mechanisms;

import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class TouchSensorEx {
    private DigitalChannel touchSensor;
    public void init(HardwareMap hwMap){
        touchSensor = hwMap.get(DigitalChannel.class, "touch_sensor");
        touchSensor.setMode(DigitalChannel.Mode.INPUT);
    }

    public boolean isTouchSensorPressed(){
        return !touchSensor.getState();
    }

    // Returns false when pressed and true when not pressed

    public boolean isTouchSensorReleased(){
        return touchSensor.getState();
    }
}
