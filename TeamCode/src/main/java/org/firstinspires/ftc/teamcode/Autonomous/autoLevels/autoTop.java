package org.firstinspires.ftc.teamcode.Autonomous.autoLevels;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.robot.DriveTrain;
import org.firstinspires.ftc.teamcode.robot.Grasper;
import org.firstinspires.ftc.teamcode.robot.Lifter;
import org.firstinspires.ftc.teamcode.robot.Ninjabot;

@Autonomous(name = "Auto Top")
public class autoTop extends LinearOpMode {
    private Ninjabot robot;
    int level = -1;
    @Override
    public void runOpMode() throws InterruptedException {
        robot = new Ninjabot(hardwareMap);
        robot.driveTrain.setBrake();
        level = 1;
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
            //robot.driveTrain.MoveTank(-1 ,0.15);
            //waitUntilMove();
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
            //robot.driveTrain.strafeRight(38, 0.9); //42
            //waitUntilMove();
            //robot.driveTrain.MoveTank(41, 0.5);
            //robot.driveTrain.addLeftPow(0.2);
            //waitUntilMove();
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
