package org.firstinspires.ftc.teamcode.Examples.Examples.teleops;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Examples.Examples.mechanisms.ServoEx;

@TeleOp
public class ServoExample extends OpMode {

    ServoEx bench = new ServoEx();

    @Override
    public void init(){
        bench.init(hardwareMap);
    }

    // Full left is 0, full right is 1.0
    @Override
    public void loop(){
        if(gamepad1.a){
            bench.setServoPos(0);
        }
        else {
            bench.setServoPos(1.0);
        }

        if(gamepad1.b){
            bench.setServoRot(1.0);
        }
        else{
            bench.setServoRot(0);
        }

    }
}
