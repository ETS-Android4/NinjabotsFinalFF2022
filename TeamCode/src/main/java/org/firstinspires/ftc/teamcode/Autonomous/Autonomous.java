package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.robot.DriveTrain;
import org.firstinspires.ftc.teamcode.robot.Lifter;
import org.firstinspires.ftc.teamcode.robot.Ninjabot;
import org.firstinspires.ftc.teamcode.robot.vision.CameraPipeline;

@com.qualcomm.robotcore.eventloop.opmode.Autonomous(name = "Autonomous")
public class Autonomous extends LinearOpMode {
    private Ninjabot robot;
    int level = -1;
    @Override
    public void runOpMode() throws InterruptedException {
        robot = new Ninjabot(hardwareMap);
        robot.webcam.detect();
        while(level == -1){
            if (robot.webcam.getPosition() == CameraPipeline.DuckPosition.ONE) {
                telemetry.addData("Rings: ", "ONE");
                level = 1;
            } else if (robot.webcam.getPosition() == CameraPipeline.DuckPosition.TWO) {
                telemetry.addData("Rings: ", "TWO");
                level = 2;
            } else if (robot.webcam.getPosition() == CameraPipeline.DuckPosition.THREE) {
                telemetry.addData("Rings: ", "THREE");
                level = 3;
            }
            else{
                robot.webcam.detect();
                sleep(1);
                telemetry.addData("Rings: ", "Unknown");
            }
            telemetry.addData("Cam:", robot.webcam.getPosition());
            telemetry.update();

        }
        waitForStart();

        if(level == 1){
            robot.driveTrain.MoveTank(24, 0.5);
            waitUntilMove();
            robot.imu.resetAngle();
            robot.driveTrain.turnLeft(120, 0.15);
            waitUntilMove();
            robot.lifter.liftToTop();
            waitForLifter();
            robot.driveTrain.MoveTank(-12, 0.3);
            waitUntilMove();
            telemetry.addData("Passed:", "True");
            telemetry.update();
            robot.cradle.openGate();
            waitForCradle();
            robot.driveTrain.MoveTank(12, 0.5);
            waitUntilMove();
            robot.cradle.closeGate();
            robot.lifter.dropDown();
            robot.driveTrain.MoveTank(16, 0.4);
            waitUntilMove();
            robot.imu.resetAngle();
            robot.driveTrain.turnRight(138, 0.15);
            waitUntilMove();
            robot.driveTrain.MoveTank(-13, 0.2);
            waitUntilMove();
            robot.turnTable.setPower();
            ElapsedTime time = new ElapsedTime(ElapsedTime.Resolution.MILLISECONDS);
            time.reset();
            while(time.milliseconds() < 4300){
                robot.update();
                robot.turnTable.setPower();
            }
            robot.turnTable.stopTurnTable();
            robot.driveTrain.MoveTank(16, 0.7);
            waitUntilMove();
            robot.imu.resetAngle();
            robot.driveTrain.turnRight(62, 0.3);
            waitUntilMove();
            robot.driveTrain.strafeRight(30, 0.6);
            waitUntilMove();
            robot.driveTrain.MoveTank(96, 1);
            waitUntilMove();
        }
    }

    private void waitUntilMove(){
        while(robot.driveTrain.getState() == DriveTrain.DriveState.DRIVING || robot.driveTrain.getState() == DriveTrain.DriveState.TURNING_L || robot.driveTrain.getState() == DriveTrain.DriveState.TURNING_R || robot.driveTrain.getState() == DriveTrain.DriveState.STRAFING){
            robot.update();
            telemetry.addData("Robot Targ:", robot.driveTrain.getTargetPos());
            telemetry.addData("Robot State:", robot.driveTrain.getState());
            telemetry.update();
        }

    }

    private void waitForLifter(){
        while(robot.lifter.getState() == Lifter.LiftState.LIFTING || robot.lifter.getState() == Lifter.LiftState.DROPPING){
            robot.update();
            telemetry.addData("Robot Targ:", robot.lifter.getEncoderCount());
            telemetry.addData("IMU deg:", robot.imu.getAngle());
            telemetry.addData("Robot State:", robot.lifter.getState());
            telemetry.update();

        }
    }
    private void waitForCradle(){
        while(robot.cradle.getGatePos() != robot.cradle.getTargetPos()){
            robot.update();
        }
    }
}
