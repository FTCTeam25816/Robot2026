package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

public class MecanumDrive {

    private DcMotor frontLeftMotor, backLeftMotor, frontRightMotor, backRightMotor;
    private IMU imu;
    public void init(HardwareMap hwMap){

        // MAKE SURE TO CHANGE NAMES
        frontLeftMotor = hwMap.get(DcMotor.class, "driveMotor1");
        backLeftMotor = hwMap.get(DcMotor.class, "driveMotor2");
        frontRightMotor = hwMap.get(DcMotor.class, "driveMotor3");
        backRightMotor = hwMap.get(DcMotor.class, "driveMotor4");

        frontLeftMotor.setDirection(DcMotor.Direction.REVERSE);
        backLeftMotor.setDirection(DcMotor.Direction.REVERSE);

        frontLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        imu = hwMap.get(IMU.class, "imu");

        RevHubOrientationOnRobot RevOrientation = new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.UP,
                RevHubOrientationOnRobot.UsbFacingDirection.valueOf(fortyFiveDegrees)
        ); // MUST CHANGE USB ORIENTATION

        imu.initialize(new IMU.Parameters(RevOrientation));
    }


    // Robot Oriented Drive
    public void drive(double forward, double strafe, double rotate){
        double frontLeftPower = forward + strafe + rotate;
        double backLeftPower = forward - strafe + rotate;
        double frontRightPower = forward - strafe - rotate;
        double backRightPower = forward + strafe - rotate;

        double maxPower = 1.0;

        /* Constrains all values back down. If pushing all joysticks
        you could be sending a value of one to the forward, strafe, and rotate, meaning you could
        actually give it a power of 3. If you do not clamp it back down to 1, calculations would be
        incorrect
        */

        double maxSpeed = 1.0;
        /* Good for outreach events; you can change max speed to something lower
        like 0.3 so kids don't drive so fast
         */

        maxPower = Math.max(maxPower, Math.abs(frontLeftPower));
        maxPower = Math.max(maxPower, Math.abs(backLeftPower));
        maxPower = Math.max(maxPower, Math.abs(frontRightPower));
        maxPower = Math.max(maxPower, Math.abs(backRightPower));
        /*
        Checks which motor has the absolute max power to see what the ratio we need to scale
        everything else down on so we know how fast to send individual ones
         */

        frontLeftMotor.setPower(maxSpeed * (frontLeftPower/maxPower));
        backLeftMotor.setPower(maxSpeed * (backLeftPower/maxPower));
        frontRightMotor.setPower(maxSpeed * (frontRightPower/maxPower));
        backRightMotor.setPower(maxSpeed * (backRightPower / maxPower));
    }

    // Field Oriented Drive
    public void driveFieldRelative(double forward, double strafe, double rotate){

        // Gets in terms of polar coordinates
        double theta = Math.atan2(forward, strafe);
        double r = Math.hypot(strafe, forward); // distance

        theta = AngleUnit.normalizeRadians(theta -
                imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS));

        // Converts back to Cartesian coordinates (regular)
        double newForward = r * Math.sin(theta);
        double newStrafe = r * Math.cos(theta);

        this.drive(newForward, newStrafe, rotate);
    }

}
