package org.firstinspires.ftc.teamcode.robot;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Cradle {
    //private final Servo swivel;
    private final Servo gate;

    public double GATE_OPEN = 0.25;//
    public double GATE_CLOSE = 0.0;
    private double currentTarget;

    public Cradle(HardwareMap hw){
        this.gate = hw.get(Servo.class, "grasper");
        this.gate.setPosition(0.18);//Base
        currentTarget = 0;
    }
    public void openGate(){
        currentTarget = GATE_OPEN;
        this.gate.setPosition(GATE_OPEN);
    }
    public void closeGate(){
        currentTarget = GATE_CLOSE;
        this.gate.setPosition(GATE_CLOSE);
    }

    /* public void Swivel(){
        double SWIVEL_TARGET = 0.7;
        this.swivel.setPosition(SWIVEL_TARGET);
    }
    public void closeSwivel(){
        double SWIVEL_BASE = 0.0;
        this.swivel.setPosition(SWIVEL_BASE);
    } */

    public double getGatePos(){return gate.getPosition();}
    public double getTargetPos(){return currentTarget;}
}
