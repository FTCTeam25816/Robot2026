package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.mechanisms.ImuEx;

public class ImuPractice extends OpMode {

    ImuEx bench = new ImuEx();

    @Override
    public void init(){
        bench.init(hardwareMap);
    }

    @Override
    public void loop(){
        telemetry.addData("Heading", bench.getHeading(AngleUnit.DEGREES));
    }
}
