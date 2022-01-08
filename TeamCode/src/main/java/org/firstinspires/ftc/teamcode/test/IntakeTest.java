package org.firstinspires.ftc.teamcode.test;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.robot.Intake;

@TeleOp(name = "Intake Test")
public class IntakeTest extends LinearOpMode {
    private Intake robot;
    @Override
    public void runOpMode() throws InterruptedException {
        this.robot = new Intake(hardwareMap);
        waitForStart();
        while (opModeIsActive()){
            robot.runIntake();
        }
    }
}
