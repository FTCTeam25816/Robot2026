package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.HardwareMap;

public class DcMotorEx {
    private com.qualcomm.robotcore.hardware.DcMotor motor; //
    private double ticksPerRev;

    public void init(HardwareMap hwMap){

        motor = hwMap.get(com.qualcomm.robotcore.hardware.DcMotor.class, "motor");
        motor.setMode(com.qualcomm.robotcore.hardware.DcMotor.RunMode.RUN_USING_ENCODER);
        ticksPerRev = motor.getMotorType().getTicksPerRev();
    }

    public void setMotorSpeed(double speed){
        // accepts values from -1.0 to 1.0
        motor.setPower(speed);
    }

    public double getMotorRevs(){
        return motor.getCurrentPosition() / ticksPerRev; // normalizes ticks to revolutions
    }

    // motor.getCurrentPosition() gets the current tick count relative to the encoder zero.
    // It accumulates over time

    // If we had a 2:1 gear ratio, we'd multiply that line of code by 2.
    // It means that the motor turns twice for every one turn of the mechanism

}
