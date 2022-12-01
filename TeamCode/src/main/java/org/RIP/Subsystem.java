package org.RIP;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.io.IOException;

public abstract class Subsystem {
    private boolean isActive = false;
    private LinearOpMode opMode;
    private ElapsedTime globalTimer;

    public boolean isActive(){
        return isActive;
    }
    public void disable(){
        isActive = false;
    }
    public void initialize(LinearOpMode opMode, ElapsedTime globalTimer) throws IOException {
        this.opMode = opMode;
        this.globalTimer = globalTimer;
    };
    public abstract void update();
    public abstract void stop();

}