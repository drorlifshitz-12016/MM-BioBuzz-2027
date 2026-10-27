package org.firstinspires.ftc.teamcode.Opmode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoImplEx;

@TeleOp
public class Itay extends LinearOpMode {
    Servo servo;
    @Override
    public void runOpMode() throws InterruptedException {
        servo = hardwareMap.get(Servo.class,"stopperServo");

        waitForStart();
        if (isStopRequested())
        {resetRuntime();}

        while (opModeIsActive()){
            servo.setPosition(gamepad1.left_trigger);
        }

    }
}
