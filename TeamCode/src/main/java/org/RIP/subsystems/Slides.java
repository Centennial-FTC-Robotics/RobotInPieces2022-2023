package org.RIP.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.RIP.Subsystem;

import java.io.IOException;



public class Slides extends Subsystem {
    DcMotor slideLeft;
    DcMotor slideRight;
    Servo claw;
    ElapsedTime globalTimer;
    LinearOpMode opMode;
//    private final int LOWEST_POSITION = 0;
//    private final int LOW_POSITION = 1000;
//    private final int MID_POSITION = 5000;
//    private final int HIGH_POSITION = 7500;
    private final int HIGHEST_POSITION = 10000;

    @Override
    public void initialize(LinearOpMode opMode, ElapsedTime globalTimer) throws IOException {
        this.opMode = opMode;
        this.globalTimer = globalTimer;
        slideLeft = opMode.hardwareMap.get(DcMotor.class, "slide_left");
        slideRight = opMode.hardwareMap.get(DcMotor.class, "slide_right");
        claw = opMode.hardwareMap.get(Servo.class, "claw");

        slideRight.setDirection(DcMotor.Direction.REVERSE);
        slideLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        slideRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        ElapsedTime timeout = new ElapsedTime();
        timeout.reset();
        //move slides down
        int startPos = slideLeft.getCurrentPosition();
    }
    @Override
    public void disable() {

    }
    private final double clawLockedPos = 0.2;
    private final double clawUnlockedPos = 0.8;
    private boolean clawLocked = true;
    public boolean isClawLocked() {
        return clawLocked;
    }
    /** Only call this once, don't call it repeatedly*/
    public void lockClaw() {
        claw.setPosition(clawLockedPos);
        clawLocked = true;
    }
    public void unlockClaw() {
        claw.setPosition(clawUnlockedPos);
        clawLocked = false;

    }
    public int percentToTop() {
        return (avgEncoderPos() / HIGHEST_POSITION) * 100;
    }
    public int avgEncoderPos() {
        return (slideLeft.getCurrentPosition() + slideRight.getCurrentPosition()) / 2;
    }

    @Override
    public void update() {
        //opMode.telemetry.addData("pressed: ", slideSensor.isPressed());
    }
}
