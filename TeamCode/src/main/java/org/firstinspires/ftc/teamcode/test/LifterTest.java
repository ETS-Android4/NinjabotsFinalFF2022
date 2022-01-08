package org.firstinspires.ftc.teamcode.test;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.robot.Ninjabot;

@TeleOp(name = "Lifter Test")
public class LifterTest extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        Ninjabot ninjabot = new Ninjabot(hardwareMap);
        waitForStart();
        while(opModeIsActive()){
            if(gamepad1.dpad_up){
                ninjabot.lifter.liftToTop();
            }
            else if (gamepad1.dpad_down){
                ninjabot.lifter.dropDown();
            }
            ninjabot.lifter.update();
            telemetry.addData("State: ", ninjabot.lifter.getState());
            telemetry.update();
        }
    }
}
