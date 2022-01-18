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
                robot.update();
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
            robot.driveTrain.turnRight(138, 0.5);
            waitUntilMove();
            robot.driveTrain.MoveTank(-14, 0.4);
            waitUntilMove();
            robot.driveTrain.MoveTank(-2 ,0.15);
            waitUntilMove();
            robot.turnTable.setPower();
            ElapsedTime time = new ElapsedTime(ElapsedTime.Resolution.MILLISECONDS);
            time.reset();
            while(time.milliseconds() < 2800){
                robot.update();
            }
            robot.turnTable.stopTurnTable();
            robot.driveTrain.MoveTank(19, 0.7); //16
            waitUntilMove();
            robot.imu.resetAngle();
            robot.driveTrain.turnRight(62, 0.3);
            waitUntilMove();
            robot.driveTrain.strafeRight(56, 0.9); //42
            waitUntilMove();
            robot.driveTrain.MoveTank(75, 1);
            waitUntilMove();
        }


        else if (level == 3){
            robot.grasper.init();
            robot.driveTrain.MoveTank(30,0.5);
            waitUntilMove();
            robot.driveTrain.turnRight(90,0.5);
            waitUntilMove();
            robot.driveTrain.MoveTank(7, 0.3);
            waitUntilMove();
            robot.grasper.liftTo1();
            robot.driveTrain.turnRight(60, 0.2);
            waitUntilMove();
            waitForGrasper();
            robot.grasper.openGate();
            robot.driveTrain.MoveTank(12,0.5);
            waitUntilMove();
            robot.driveTrain.turnLeft(80, 0.4);
            robot.grasper.dropDown();
            waitUntilMove();
            robot.driveTrain.MoveTank(-20, 0.5);
            waitUntilMove();
            robot.driveTrain.turnLeft(33, 0.3);
            waitUntilMove();
            robot.driveTrain.MoveTank(-16, 0.35);
            waitUntilMove();
            robot.turnTable.setPower();
            ElapsedTime time = new ElapsedTime(ElapsedTime.Resolution.MILLISECONDS);
            time.reset();
            while(time.milliseconds() < 2800){
                robot.update();
            }
            robot.turnTable.stopTurnTable();
            robot.driveTrain.MoveTank(19, 0.7); //16
            waitUntilMove();
            robot.imu.resetAngle();
            robot.driveTrain.turnRight(55, 0.3);
            waitUntilMove();
            robot.driveTrain.strafeRight(56, 0.9); //42
            waitUntilMove();
            robot.driveTrain.MoveTank(75, 1);
            waitUntilMove();

        }
        else if (level == 2){
            robot.grasper.init();
            robot.driveTrain.MoveTank(30,0.5);
            waitUntilMove();
            robot.driveTrain.turnRight(90,0.5);
            waitUntilMove();
            robot.driveTrain.MoveTank(7, 0.3);
            waitUntilMove();
            robot.grasper.liftTo2();
            robot.driveTrain.turnRight(60, 0.2);
            waitUntilMove();
            waitForGrasper();
            robot.grasper.openGate();
            Thread.sleep(200);
            robot.driveTrain.MoveTank(12,0.5);
            waitUntilMove();
            robot.driveTrain.turnLeft(80, 0.4);
            robot.grasper.dropDown();
            waitUntilMove();
            robot.driveTrain.MoveTank(-20, 0.5);
            waitUntilMove();
            robot.driveTrain.turnLeft(33, 0.3);
            waitUntilMove();
            robot.driveTrain.MoveTank(-18, 0.35);
            waitUntilMove();
            robot.turnTable.setPower();
            ElapsedTime time = new ElapsedTime(ElapsedTime.Resolution.MILLISECONDS);
            time.reset();
            while(time.milliseconds() < 2800){
                robot.update();
            }
            robot.turnTable.stopTurnTable();
            robot.driveTrain.MoveTank(19, 0.5); //16
            waitUntilMove();
            robot.imu.resetAngle();
            robot.driveTrain.turnRight(30, 0.35);
            waitUntilMove();
            robot.driveTrain.strafeRight(56, 0.9); //42
            waitUntilMove();
            robot.driveTrain.MoveTank(75, 1);
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
