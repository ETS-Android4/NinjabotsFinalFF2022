package org.firstinspires.ftc.teamcode.test;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.robot.DriveTrain;
import org.firstinspires.ftc.teamcode.robot.Ninjabot;

@TeleOp(name = "Imu Left Turn")
public class ImuLeftTurn extends LinearOpMode {
    Ninjabot robot;
    @Override
    public void runOpMode() throws InterruptedException {
        robot = new Ninjabot(hardwareMap);
        waitForStart();
        robot.driveTrain.turnLeft(90, 0.4);
        waitUntilMove();
        telemetry.addData("Robot state:", robot.driveTrain.getState());
        telemetry.update();



    }
    private void waitUntilMove(){
        while(robot.driveTrain.getState() == DriveTrain.DriveState.DRIVING || robot.driveTrain.getState() == DriveTrain.DriveState.TURNING_L || robot.driveTrain.getState() == DriveTrain.DriveState.TURNING_R){
            robot.update();
        }

    }

}
