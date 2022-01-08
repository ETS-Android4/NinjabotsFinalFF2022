package org.firstinspires.ftc.teamcode.robot;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.robot.control.IMU;

public class DriveTrain {

    public enum DriveState{
        IDLE,
        TELE,
        DRIVING,
        STRAFING,
        TURNING_L,
        TURNING_R
    }

    private DcMotor bl, br, fl, fr;
    private IMU imu;
    private DriveState state;
    private double currentTargDeg;
    private final double rotPerInch = 0.0795774715459477;
    private final double ticksPerRev = 537.6;
    private final double degPerRot = 1;//75


    public DriveTrain(HardwareMap hardwareMap, IMU robotIMU){
        //Initialize Motors
        bl = hardwareMap.get(DcMotor.class, "bl");
        br = hardwareMap.get(DcMotor.class, "br");
        fl = hardwareMap.get(DcMotor.class, "fl");
        fr = hardwareMap.get(DcMotor.class, "fr");

        bl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        br.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        fl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        fr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        bl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        br.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        fl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        fr.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        br.setDirection(DcMotorSimple.Direction.REVERSE);
        fr.setDirection(DcMotorSimple.Direction.REVERSE);
        fl.setDirection(DcMotorSimple.Direction.REVERSE);

        // Add IMU and set initial state to IDLE
        this.imu = robotIMU;
        this.imu.initIMU();
        this.state = DriveState.IDLE;
    }

    // Moves all drive train motors forward or backward
    public void MoveTank(int inches, double speed){
        resetMotors();
        imu.resetAngle();

        double rotations =(double) inches * rotPerInch;


        SetTargPos((int) (rotations * ticksPerRev));
        bl.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        br.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        fl.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        fr.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        setState(DriveState.DRIVING);

        bl.setPower(speed);
        br.setPower(speed);
        fl.setPower(speed);
        fr.setPower(speed);
    }

    // Strafing
    public void strafeLeft(int inches, double speed){
        resetMotors();
        double rotations = (double) inches * rotPerInch;
        SetTargPos((int) (rotations * ticksPerRev));
        br.setTargetPosition(-br.getTargetPosition());
        fl.setTargetPosition(-fl.getTargetPosition());
        setState(DriveState.STRAFING);
        bl.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        br.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        fl.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        fr.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        bl.setPower(speed);
        br.setPower(-speed);
        fl.setPower(-speed);
        fr.setPower(speed);


    }

    public void strafeRight(int inches, double speed){
        resetMotors();
        double rotations = (double) inches * rotPerInch;
        SetTargPos((int) (rotations * ticksPerRev));
        bl.setTargetPosition(-bl.getTargetPosition());
        fr.setTargetPosition(-fr.getTargetPosition());
        setState(DriveState.STRAFING);
        bl.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        br.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        fl.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        fr.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        bl.setPower(-speed);
        br.setPower(speed);
        fl.setPower(speed);
        fr.setPower(-speed);
    }

    // Turning

    public void turnLeft(int degrees, double speed){
        imu.resetAngle();
        currentTargDeg = degrees;
        resetMotors();
        SetTargPos((int) (((double) degrees/degPerRot) * ticksPerRev));
        bl.setTargetPosition(-bl.getTargetPosition());
        fl.setTargetPosition(-fl.getTargetPosition());
        setState(DriveState.TURNING_L);
        bl.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        br.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        fl.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        fr.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        bl.setPower(-speed);
        br.setPower(speed);
        fl.setPower(-speed);
        fr.setPower(speed);
    }

    public void turnRight(int degrees, double speed){
        imu.resetAngle();
        currentTargDeg = -degrees;
        resetMotors();
        SetTargPos((int) (((double) degrees/degPerRot) * ticksPerRev));
        br.setTargetPosition(-br.getTargetPosition());
        fr.setTargetPosition(-fr.getTargetPosition());
        setState(DriveState.TURNING_R);
        bl.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        br.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        fl.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        fr.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        bl.setPower(speed);
        br.setPower(-speed);
        fl.setPower(speed);
        fr.setPower(-speed);
    }

    // IMU BASED CALCULATION
    private void adjustPow(double correction){
        fr.setPower(fr.getPower() + correction);
        br.setPower(br.getPower() + correction);
        fl.setPower(fl.getPower() - correction);
        bl.setPower(bl.getPower() - correction);
    }
    private void adjustAllPow(double inc){
        if(br.getPower() < 0){
            br.setPower(br.getPower() + inc);
        }
        else{
            br.setPower(br.getPower() - inc);
        }

        if(fr.getPower() < 0){
            fr.setPower(fr.getPower() + inc);
        }
        else{
            fr.setPower(fr.getPower() - inc);
        }

        if(fl.getPower() < 0){
            fl.setPower(fl.getPower() + inc);
        }
        else{
            fl.setPower(fl.getPower() - inc);
        }

        if(bl.getPower() < 0){
            bl.setPower(bl.getPower() + inc);
        }
        else{
            bl.setPower(bl.getPower() - inc);
        }
    }
    private void rampPow(){
        double currentDelta;
        double minPower = 0.1;
        if(Math.abs(getFrPow()) > minPower){
            if(state == DriveState.DRIVING){
                currentDelta = Math.abs(getTargetPos() - getCurrentPos());
                if(currentDelta < 0.5 * Math.abs(getTargetPos())){
                    adjustAllPow(0.02);

            }
            else{
                currentDelta = Math.abs(imu.getAngle() - currentTargDeg);
                if(currentDelta < 0.6 * Math.abs(currentTargDeg)){
                    adjustAllPow(0.05);
                }
            }

            }
        }
    }
    public void StopMotors(){
        fr.setPower(0);
        fl.setPower(0);
        br.setPower(0);
        bl.setPower(0);
        setState(DriveState.IDLE);
        resetMotors();


    }
    public void SetTargPos(int targ){
        if(bl.getMode() == DcMotor.RunMode.RUN_TO_POSITION ||
                br.getMode() == DcMotor.RunMode.RUN_TO_POSITION ||
                fl.getMode() == DcMotor.RunMode.RUN_TO_POSITION ||
                fr.getMode() == DcMotor.RunMode.RUN_TO_POSITION){
            return;
        }
        else{
            bl.setTargetPosition(targ);
            br.setTargetPosition(targ);
            fl.setTargetPosition(targ);
            fr.setTargetPosition(targ);
        }
    }

    public void update(){
        switch (this.state){
            case DRIVING:
                if(getCurrentPos() == getTargetPos()){
                    setState(DriveState.IDLE);
                    StopMotors();
                    break;
                }
                double correction = imu.checkDirection();
                adjustPow(correction);
                rampPow();
                break;
            case STRAFING:
                if(getCurrentPos() == getTargetPos()){
                    setState(DriveState.IDLE);
                    StopMotors();
                    break;
                }
                break;
            case TURNING_L:
                //rampPow();
                if(imu.getAngle() >= currentTargDeg && imu.getAngle() != 0){
                    setState(DriveState.IDLE);
                    StopMotors();
                    break;

                }
                if(getCurrentPos() > getTargetPos() + 50){
                    setState(DriveState.IDLE);
                    StopMotors();
                    break;
                }
                break;
            case TURNING_R:
                rampPow();
                if(imu.getAngle() <= currentTargDeg && imu.getAngle() != 0){
                    setState(DriveState.IDLE);
                    StopMotors();
                    break;
                }
                break;
            case IDLE:

            case TELE:
        }
    }

    // TeleOP --------------------------------------------------

    //Tele Move Tank
    public void TeleMoveTank(double leftPow, double rightPow){
        setState(DriveState.TELE);
        setTeleOp();
        fl.setPower(leftPow);
        fr.setPower(rightPow);
        bl.setPower(leftPow);
        br.setPower(rightPow);
    }

    // Tele strafing
    public void teleStrafeLeft(double pow){
        setState(DriveState.TELE);
        setTeleOp();
        fl.setPower(-pow);
        fr.setPower(pow);
        bl.setPower(pow);
        br.setPower(-pow);
    }

    public void teleStrafeRight(double pow){
        setState(DriveState.TELE);
        setTeleOp();
        fl.setPower(pow);
        fr.setPower(-pow);
        bl.setPower(-pow);
        br.setPower(pow);
    }

    // Reset the motors
    public void resetMotors(){
        bl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        br.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        fl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        fr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    // Setting TeleOp Mode
    public void setTeleOp(){
        bl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        br.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        fl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        fr.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    // Setting State
    public void setState(DriveState state){this.state = state;}

    // Getters
    public double getFrPow(){return fr.getPower();}
    public int getTargetPos(){return br.getTargetPosition();}
    public int getCurrentPos(){return br.getCurrentPosition();}
    public DriveState getState(){return state;}
}
