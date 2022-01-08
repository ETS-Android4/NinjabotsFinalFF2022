package org.firstinspires.ftc.teamcode.test;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.robot.vision.Camera;

@Autonomous(name = "Vision Test")
public class VisionTest extends LinearOpMode {
    private Camera webcam;

    @Override
    public void runOpMode() throws InterruptedException {
        webcam = new Camera(hardwareMap);
        //FtcDashboard dashboard = FtcDashboard.getInstance();
        //dashboard.setTelemetryTransmissionInterval(25);
        //packet = new TelemetryPacket();

        waitForStart();
        while (opModeIsActive()){
            webcam.detect();
            telemetry.addData("Analysis 1:", webcam.getAverage1());
            telemetry.addData("Analysis 2:", webcam.getAverage2());
            //telemetry.addData("Analysis 3:", webcam.getAverage3());

            telemetry.update();
        }
    }
}
