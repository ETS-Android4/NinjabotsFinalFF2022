package org.firstinspires.ftc.teamcode.Autonomous.autoLevels;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.robot.DriveTrain;
import org.firstinspires.ftc.teamcode.robot.Grasper;
import org.firstinspires.ftc.teamcode.robot.Lifter;
import org.firstinspires.ftc.teamcode.robot.Ninjabot;

@Autonomous(name = "Auto Bottom")
public class autoBottom extends LinearOpMode {
    private Ninjabot robot;
    int level = -1;
    @Override
    public void runOpMode() throws InterruptedException {
        robot = new Ninjabot(hardwareMap);
        robot.driveTrain.setBrake();
        level = 3;
        waitForStart();

        if (level == 3){
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
            robot.intake.runIntake();
            robot.cradle.openGate();
            robot.driveTrain.MoveTank(32, 0.65);
            waitUntilMove();
            robot.intake.stopIntake();
            robot.cradle.closeGate();
            robot.driveTrain.turnRight(120, 0.65);
            waitUntilMove();
            robot.grasper.liftToHigh();
            robot.driveTrain.MoveTank(-23, 0.3);
            waitUntilMove();
            robot.driveTrain.MoveTank(-3, 0.21);
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
