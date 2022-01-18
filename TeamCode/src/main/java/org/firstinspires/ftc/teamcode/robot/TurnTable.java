package org.firstinspires.ftc.teamcode.robot;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class TurnTable {
    private final DcMotor motor;
    public TurnTable(HardwareMap hardwareMap){
        this.motor = hardwareMap.get(DcMotor.class, "TurnTable");//shoule be turntable, just for testing
        this.motor.setDirection(DcMotorSimple.Direction.REVERSE);
    }
    public void setPower(){
        double POWER = 0.6;//0.48
        motor.setPower(POWER);
    }
    public void stopTurnTable(){
        motor.setPower(0.0);
    }
}

