package org.firstinspires.ftc.teamcode.robot;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.checkerframework.checker.units.qual.C;
import org.firstinspires.ftc.teamcode.robot.control.IMU;
import org.firstinspires.ftc.teamcode.robot.vision.Camera;

public class Ninjabot {

    public IMU imu;
    public Camera webcam;
    public Cradle cradle;
    public DriveTrain driveTrain;
    public Grasper grasper;
    public Intake intake;
    public Lifter lifter;
    public TurnTable turnTable;

    public Ninjabot(HardwareMap hardwareMap){
        imu = new IMU(hardwareMap);
        webcam = new Camera(hardwareMap);
        cradle = new Cradle(hardwareMap);
        driveTrain = new DriveTrain(hardwareMap, imu);
        grasper = new Grasper(hardwareMap);
        intake = new Intake(hardwareMap);
        lifter = new Lifter(hardwareMap);
        turnTable = new TurnTable(hardwareMap);
    }

    // Update Cycle
    public void update(){
        lifter.update();
        driveTrain.update();
        grasper.update();
    }
}
