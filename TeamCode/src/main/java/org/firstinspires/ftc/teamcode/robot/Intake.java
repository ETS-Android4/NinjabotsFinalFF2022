package org.firstinspires.ftc.teamcode.robot;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Intake {
    private final DcMotor intakeMotor;
    public Intake(HardwareMap hw){
        this.intakeMotor = hw.get(DcMotor.class, "intake");
        this.intakeMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        this.intakeMotor.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    public void runIntake(){
        this.intakeMotor.setPower(1.0);
    }
    public void stopIntake(){
        this.intakeMotor.setPower(0.0);
    }
    public void reverseIntake(){
        this.intakeMotor.setPower(-1.0);
    }
}
