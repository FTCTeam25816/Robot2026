package org.firstinspires.ftc.teamcode.Examples.Examples.teleops;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Examples.Examples.mechanisms.TouchSensorEx;

@TeleOp
public class TouchSensorPractice extends OpMode {

    TouchSensorEx bench = new TouchSensorEx();
    @Override
    public void init(){
        bench.init(hardwareMap);
    }

    @Override
    public void loop(){
        if (bench.isTouchSensorReleased()){
            telemetry.addData("Touch Sensor State", "Pressed!");
        }
        else {
            telemetry.addData("Touch Sensor State", "Not Pressed!");
        }
    }

}
