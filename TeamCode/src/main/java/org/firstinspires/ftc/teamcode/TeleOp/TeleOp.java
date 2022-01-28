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
        double dampen = 0.88;
        if (gamepad1.left_stick_y >= -deadZone && gamepad1.right_stick_y >= -deadZone && gamepad1.left_stick_y <= deadZone && gamepad1.right_stick_y <= deadZone && gamepad1.right_trigger < 0.1 && gamepad1.left_trigger < 0.1 && !gamepad1.dpad_up && !gamepad1.dpad_down){
            Ninjabot.driveTrain.StopMotors();
        }
        else if (gamepad1.left_stick_y !=0 || gamepad1.right_stick_y != 0){
            Ninjabot.driveTrain.TeleMoveTank(-gamepad1.left_stick_y * dampen, -gamepad1.right_stick_y * dampen);
        }

        if(gamepad1.left_trigger > deadZone){
            Ninjabot.driveTrain.teleStrafeLeft(gamepad1.left_trigger * dampen);
        }
        else if (gamepad1.right_trigger > deadZone){
            Ninjabot.driveTrain.teleStrafeRight(gamepad1.right_trigger * dampen);
        }

        if(gamepad1.dpad_up){
            Ninjabot.driveTrain.TeleMoveTank(0.75 * dampen,0.6 * dampen);
        }
        else if(gamepad1.dpad_down){
            Ninjabot.driveTrain.TeleMoveTank(-0.75 * dampen,-0.6 * dampen);
        }

        if(gamepad1.dpad_left){
            Ninjabot.turnTable.setPower();
        }
        else if (gamepad1.dpad_right){
            Ninjabot.turnTable.stopTurnTable();
        }
        if(gamepad2.dpad_left){
            Ninjabot.intake.runIntake();
            Ninjabot.cradle.openGate();
        }
        else if (gamepad2.dpad_right){
            Ninjabot.intake.stopIntake();
        }
        else if (gamepad2.a){
            Ninjabot.intake.reverseIntake();
        }
        if(gamepad2.dpad_up){
            Ninjabot.lifter.liftToTop();
        }
        else if (gamepad2.dpad_down){
            Ninjabot.cradle.closeGate();
            Ninjabot.lifter.dropDown();
        }

        if(gamepad2.right_trigger > deadZone){
            Ninjabot.cradle.closeGate();
            Ninjabot.intake.stopIntake();
            Ninjabot.lifter.liftToTop();
        }
        else if (gamepad2.left_trigger > deadZone){
            Ninjabot.cradle.openGate();
        }

        Ninjabot.update();

    }
}
