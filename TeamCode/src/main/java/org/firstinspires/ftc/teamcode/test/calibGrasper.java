package org.firstinspires.ftc.teamcode.test;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.robot.Ninjabot;

@TeleOp(name = "Grasper Test")
public class calibGrasper extends LinearOpMode {
    Ninjabot robot;
    @Override
    public void runOpMode() throws InterruptedException {
        robot = new Ninjabot(hardwareMap);
        robot.grasper.init();
        waitForStart();
        while (opModeIsActive()){
            if(gamepad1.dpad_up){
                robot.grasper.liftTo1();
            }
            if(gamepad1.dpad_left){
                robot.grasper.liftTo2();
            }
            if(gamepad1.dpad_down){
                robot.grasper.dropDown();
            }
            if(gamepad1.left_trigger > 0.3){
                robot.grasper.closeGate();
            }
            else if (gamepad1.right_trigger > 0.3){
                robot.grasper.openGate();
            }
            robot.update();
            telemetry.addData("1:", robot.grasper.getTarget1Lift());
            telemetry.addData("2:", robot.grasper.getTarget2Lift());
            telemetry.update();

        }
    }
}
