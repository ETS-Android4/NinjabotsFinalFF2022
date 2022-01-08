package org.firstinspires.ftc.teamcode.test;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.robot.Ninjabot;

@TeleOp(name = "Cradle Test")
public class CradleTest extends LinearOpMode {
    Ninjabot ninjabot;
    @Override
    public void runOpMode() throws InterruptedException {
        ninjabot = new Ninjabot(hardwareMap);
        waitForStart();
        while(opModeIsActive()){
            if(gamepad1.left_bumper){
                ninjabot.cradle.closeGate();
            }
            else if (gamepad1.right_bumper){
                ninjabot.cradle.openGate();
            }

            telemetry.addData("Grasper Open: ",  ninjabot.cradle.GATE_OPEN);
            telemetry.addData("Grasper Close: ",  ninjabot.cradle.GATE_CLOSE);
            telemetry.addData("Current: ",  ninjabot.cradle.getGatePos());
            telemetry.update();

        }
    }
}
