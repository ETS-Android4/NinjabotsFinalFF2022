package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.robot.DriveTrain;
import org.firstinspires.ftc.teamcode.robot.Grasper;
import org.firstinspires.ftc.teamcode.robot.Lifter;
import org.firstinspires.ftc.teamcode.robot.Ninjabot;
import org.firstinspires.ftc.teamcode.robot.vision.CameraPipeline;
import org.firstinspires.ftc.teamcode.test.MoveTank;

@com.qualcomm.robotcore.eventloop.opmode.Autonomous(name = "Autonomous")
public class Autonomous extends LinearOpMode {
    private Ninjabot robot;
    int level = -1;
    @Override
    public void runOpMode() throws InterruptedException {
        robot = new Ninjabot(hardwareMap);
        robot.driveTrain.setBrake();
        robot.webcam.detect();
        while(level == -1){
            if (robot.webcam.getPosition() == CameraPipeline.DuckPosition.ONE) {
                telemetry.addData("Rings: ", "ONE");
                telemetry.addData("Pos 1:", robot.webcam.getAverage1());
                telemetry.addData("Pos 2:", robot.webcam.getAverage2());
                level = 1;
            } else if (robot.webcam.getPosition() == CameraPipeline.DuckPosition.TWO) {
                telemetry.addData("Rings: ", "TWO");
                telemetry.addData("Pos 1:", robot.webcam.getAverage1());
                telemetry.addData("Pos 2:", robot.webcam.getAverage2());
                level = 2;
            } else if (robot.webcam.getPosition() == CameraPipeline.DuckPosition.THREE) {
                telemetry.addData("Rings: ", "THREE");
                telemetry.addData("Pos 1:", robot.webcam.getAverage1());
                telemetry.addData("Pos 2:", robot.webcam.getAverage2());
                level = 3;
            }
            else{
                robot.webcam.detect();
                robot.update();
                sleep(1);
                telemetry.addData("Rings: ", "Unknown");
                telemetry.addData("Pos 1:", robot.webcam.getAverage1());
                telemetry.addData("Pos 2:", robot.webcam.getAverage2());
            }
            telemetry.addData("Cam:", robot.webcam.getPosition());
            telemetry.update();

        }
        waitForStart();

        if(level == 1){
            robot.driveTrain.MoveTank(22, 0.5);
            waitUntilMove();
            robot.imu.resetAngle();
            robot.driveTrain.turnLeft(120, 0.5);
            waitUntilMove();
            robot.lifter.liftToTop();
            waitForLifter();
            robot.driveTrain.MoveTank(-12, 0.3);
            waitUntilMove();
            telemetry.addData("Passed:", "True");
            telemetry.update();
            robot.cradle.openGate(); // Open Gate
            waitForCradle();
            robot.driveTrain.MoveTank(12, 0.5);
            waitUntilMove();
            robot.cradle.closeGate();
            robot.lifter.dropDown();
            robot.driveTrain.MoveTank(16, 0.4);
            waitUntilMove();
            robot.imu.resetAngle();
            robot.driveTrain.turnRight(159, 0.5);
            waitUntilMove();
            robot.driveTrain.MoveTank(-14, 0.3);
            waitUntilMove();
            robot.turnTable.setPower();
            ElapsedTime time = new ElapsedTime(ElapsedTime.Resolution.MILLISECONDS);
            time.reset();
            while(time.milliseconds() < 2800){
                robot.update();
            }
            robot.turnTable.stopTurnTable();
            robot.driveTrain.MoveTank(21, 0.7); //16
            waitUntilMove();
            robot.imu.resetAngle();
            robot.driveTrain.turnRight(63, 0.3);
            waitUntilMove();
            robot.driveTrain.MoveTank(93, 0.5);
            robot.driveTrain.addLeftPow(0.2);
            waitUntilMove();

        }


        else if (level == 3){
            robot.grasper.init();
            robot.driveTrain.MoveTank(31,0.5);
            waitUntilMove();
            robot.driveTrain.turnRight(108, 0.65);
            waitUntilMove();
            robot.driveTrain.MoveTank(26, 0.4);
            waitUntilMove();
            robot.grasper.openGate();
            Thread.sleep(200);
            robot.driveTrain.turnRight(160, 0.5);
            waitUntilMove();
            robot.driveTrain.MoveTank(32, 0.65);
            waitUntilMove();
            robot.driveTrain.turnRight(120, 0.65);
            waitUntilMove();
            robot.grasper.liftToHigh();
            robot.driveTrain.MoveTank(-21, 0.3);
            waitUntilMove();
            robot.driveTrain.MoveTank(-5, 0.21);
            waitUntilMove();
            robot.turnTable.setPower();
            ElapsedTime time = new ElapsedTime(ElapsedTime.Resolution.MILLISECONDS);
            time.reset();
            while(time.milliseconds() < 2800){
                robot.update();
            }
            robot.turnTable.stopTurnTable();
            robot.driveTrain.MoveTank(20, 0.5);
            waitUntilMove();
            robot.driveTrain.turnRight(67, 0.4);
            waitUntilMove();
            robot.grasper.dropDown();
            robot.driveTrain.MoveTank(96, 0.67);
            robot.driveTrain.addLeftPow(0.17);
            waitUntilMove();

        }
        else if (level == 2){
            robot.grasper.init();
            robot.driveTrain.MoveTank(36,0.5);
            waitUntilMove();
            robot.driveTrain.MoveTank(-5,0.5);
            waitUntilMove();
            robot.driveTrain.turnRight(115, 0.65);
            waitUntilMove();
            robot.grasper.liftTo2();
            waitForGrasper();
            robot.driveTrain.MoveTank(26, 0.4);
            waitUntilMove();
            robot.grasper.openGate();
            Thread.sleep(200);
            robot.driveTrain.turnRight(160, 0.5);
            waitUntilMove();
            robot.driveTrain.MoveTank(32, 0.65);
            waitUntilMove();
            robot.driveTrain.turnRight(115, 0.65);
            waitUntilMove();
            robot.grasper.liftToHigh();
            robot.driveTrain.MoveTank(-24, 0.3);
            waitUntilMove();
            robot.driveTrain.MoveTank(-2, 0.19);
            waitUntilMove();
            robot.turnTable.setPower();
            ElapsedTime time = new ElapsedTime(ElapsedTime.Resolution.MILLISECONDS);
            time.reset();
            while(time.milliseconds() < 2800){
                robot.update();
            }
            robot.turnTable.stopTurnTable();
            robot.driveTrain.MoveTank(20, 0.5);
            waitUntilMove();
            robot.driveTrain.turnRight(66, 0.4);
            waitUntilMove();
            robot.grasper.dropDown();
            robot.driveTrain.MoveTank(96, 0.67);
            robot.driveTrain.addLeftPow(0.17);
            waitUntilMove();


        }
    }

    private void waitUntilMove(){
        while(robot.driveTrain.getState() == DriveTrain.DriveState.DRIVING ||
                robot.driveTrain.getState() == DriveTrain.DriveState.TURNING_L ||
                robot.driveTrain.getState() == DriveTrain.DriveState.TURNING_R ||
                robot.driveTrain.getState() == DriveTrain.DriveState.STRAFING){
            robot.update();
            telemetry.addData("Robot Targ:", robot.driveTrain.getTargetPos());
            telemetry.addData("Robot State:", robot.driveTrain.getState());
            telemetry.update();
        }

    }

    private void waitForLifter(){
        while(robot.lifter.getState() == Lifter.LiftState.LIFTING ||
                robot.lifter.getState() == Lifter.LiftState.DROPPING){
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
    private void waitForGrasper(){
        while(robot.grasper.getState() == Grasper.GrasperState.LIFTING_1 || robot.grasper.getState() == Grasper.GrasperState.LIFTING_2 || robot.grasper.getState() == Grasper.GrasperState.DROPPING){
            robot.update();
        }
    }
}
