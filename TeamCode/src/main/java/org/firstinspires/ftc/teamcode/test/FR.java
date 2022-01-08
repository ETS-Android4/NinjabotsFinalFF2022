package org.firstinspires.ftc.teamcode.test;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "fr test")
public class FR extends LinearOpMode {
    DcMotor fr;
    @Override
    public void runOpMode() throws InterruptedException {
        fr = hardwareMap.get(DcMotor.class, "fr");
        waitForStart();
        while(opModeIsActive()){
            fr.setPower(-0.15);
            telemetry.addData("fr", fr.getPower());
            telemetry.update();
        }
    }
}
