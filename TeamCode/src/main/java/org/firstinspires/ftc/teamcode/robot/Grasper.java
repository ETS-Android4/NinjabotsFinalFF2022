package org.firstinspires.ftc.teamcode.robot;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Grasper {
    private final DcMotor grasperLifter;
    private final Servo grasper;
    public enum GrasperState{
        DISABLED,
        IDLE,
        LIFTING_1,
        LIFTING_2,
        DROPPING,
        REACHED_1,
        REACHED_2
    }
    private final int target1Lift = 136;
    private final int target2Lift = 354; // Figure this out
    private final int basePos = 0;
    private int currentTargetPos = 0;
    private GrasperState state;
    public double GRASPER_OPEN = 0.25;
    public double GRASPER_CLOSE = 1;
    private double currentGrasperTarget = GRASPER_CLOSE;

    public Grasper(HardwareMap hw){
        this.grasperLifter = hw.get(DcMotor.class, "Garm");
        this.grasper = hw.get(Servo.class, "Claw");
        this.grasperLifter.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        this.state = GrasperState.DISABLED;
        this.grasper.setPosition(GRASPER_CLOSE);
        this.grasperLifter.setDirection(DcMotorSimple.Direction.REVERSE);
    }
    public void init(){this.state = GrasperState.IDLE;}

    public void liftTo1(){
        this.state = GrasperState.LIFTING_1;
        currentTargetPos = target1Lift;
        this.grasperLifter.setTargetPosition(currentTargetPos);
        this.grasperLifter.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        this.grasperLifter.setPower(0.5);

    }
    public void liftTo2(){
        this.state = GrasperState.LIFTING_2;
        currentTargetPos = target2Lift;
        this.grasperLifter.setTargetPosition(currentTargetPos);
        this.grasperLifter.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        this.grasperLifter.setPower(0.6);
    }
    public void dropDown(){
        this.state = GrasperState.DROPPING;
        currentTargetPos = basePos;
        this.grasperLifter.setTargetPosition(currentTargetPos);
        this.grasperLifter.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        this.grasperLifter.setPower(0.3);
    }

    public void openGate(){
        currentGrasperTarget = GRASPER_OPEN;
        this.grasper.setPosition(GRASPER_OPEN);
    }
    public void closeGate(){
        currentGrasperTarget = GRASPER_CLOSE;
        this.grasper.setPosition(GRASPER_CLOSE);
    }

    public void update(){
        double liftPow = 0.5;
        if(state == GrasperState.LIFTING_1){
            if(getEncoderCount() >= currentTargetPos){
                state = GrasperState.REACHED_1;
            }

        }
        else if(state == GrasperState.LIFTING_2){
            if(getEncoderCount() >= currentTargetPos){
                state = GrasperState.REACHED_2;
            }

        }

        else if(state == GrasperState.DROPPING){
            if(getEncoderCount() == basePos){
                state = GrasperState.IDLE;
            }
            else{
                this.grasperLifter.setTargetPosition(currentTargetPos);
                double dropPow = -0.3;
                grasperLifter.setPower(dropPow);
            }
        }
        else if (state == GrasperState.IDLE){
            grasperLifter.setTargetPosition(basePos);
            grasperLifter.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            grasperLifter.setPower(0.1);
        }

        else if (state == GrasperState.REACHED_1 || state == GrasperState.REACHED_2){
            if(getEncoderCount() != currentTargetPos){
                this.grasperLifter.setTargetPosition(currentTargetPos);
                this.grasperLifter.setPower(liftPow);
            }

        }

    }
    public int getEncoderCount(){
        return this.grasperLifter.getCurrentPosition();
    }
    public int getTarget1Lift(){return target1Lift;}
    public int getTarget2Lift(){return target2Lift;}
    public GrasperState getState(){return state;}
}
