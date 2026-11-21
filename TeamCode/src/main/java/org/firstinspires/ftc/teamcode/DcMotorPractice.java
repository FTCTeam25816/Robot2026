package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.mechanisms.Combined;

public class DcMotorPractice extends OpMode {
    Combined bench = new Combined();

    @Override
    public void init(){
        bench.init(hardwareMap);
    }

    @Override
    public void loop(){
        if (bench.getTouchSensorState()){
            bench.setMotorSpeed(0.5);
        }
        else {
            bench.setMotorSpeed(0);
        }


        bench.setMotorSpeed(0.5);
        telemetry.addData("Motor Revs", bench.getMotorRevs());

        // double motorSpeed = gamepad1.left_stick_y;
        // bench.setMotorSpeed(motorSpeed);

        if (gamepad1.a){
            bench.setBrakeMode();
        }
        if (gamepad1.b){
            bench.setFloatMode();
        }
    }
}
