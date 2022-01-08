package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.robot.Ninjabot;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "TeleOp")
public class TeleOp extends OpMode {
    Ninjabot Ninjabot;

    @Override
    public void init() {
        Ninjabot = new Ninjabot(hardwareMap);
    }

    @Override
    public void loop() {
        double deadZone = 0.3;
        if (gamepad1.left_stick_y >= -deadZone && gamepad1.right_stick_y >= -deadZone && gamepad1.left_stick_y <= deadZone && gamepad1.right_stick_y <= deadZone && gamepad1.right_trigger < 0.1 && gamepad1.left_trigger < 0.1){
            Ninjabot.driveTrain.StopMotors();
        }
        else if (gamepad1.left_stick_y !=0 || gamepad1.right_stick_y != 0){
            Ninjabot.driveTrain.TeleMoveTank(-gamepad1.left_stick_y, -gamepad1.right_stick_y);
        }

        if(gamepad1.left_trigger > deadZone){
            Ninjabot.driveTrain.teleStrafeLeft(gamepad1.left_trigger);
        }
        else if (gamepad1.right_trigger > deadZone){
            Ninjabot.driveTrain.teleStrafeRight(gamepad1.right_trigger);
        }

        if(gamepad1.dpad_left){
            Ninjabot.turnTable.setPower();
        }
        else if (gamepad1.dpad_right){
            Ninjabot.turnTable.stopTurnTable();
        }
        if(gamepad2.dpad_left){
            Ninjabot.intake.runIntake();
        }
        else if (gamepad2.dpad_right){
            Ninjabot.intake.stopIntake();
        }

        if(gamepad2.dpad_up){
            Ninjabot.lifter.liftToTop();
        }
        else if (gamepad2.dpad_down){
            Ninjabot.lifter.dropDown();
        }

        if(gamepad2.right_trigger > deadZone){
            Ninjabot.cradle.closeGate();
            Ninjabot.intake.stopIntake();
        }
        else if (gamepad2.left_trigger > deadZone){
            Ninjabot.cradle.openGate();
        }

        if(gamepad2.a){
            Ninjabot.grasper.liftTo1();
        }
        else if (gamepad2.b){
            Ninjabot.grasper.liftTo2();
        }
        else if (gamepad2.x){
            Ninjabot.grasper.dropDown();
        }
        if(gamepad2.right_trigger > 0.3){
            Ninjabot.grasper.openGate();
        }
        else if (gamepad2.left_trigger > 0.3){
            Ninjabot.grasper.closeGate();
        }

        Ninjabot.update();

    }
}
