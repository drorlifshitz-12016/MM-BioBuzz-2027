package org.firstinspires.ftc.teamcode.Opmode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

// ייבוא תת-המערכת של המעצור
import org.firstinspires.ftc.teamcode.Subsystem.StopperSubsystem;

@TeleOp(name = "ServoCalibrate_TeleOp", group = "Linear Opmode")
public class ServoCalibrate_TeleOp extends LinearOpMode {

    @Override
    public void runOpMode() {
        // אתחול המעצור בלבד
        StopperSubsystem stopper = new StopperSubsystem(hardwareMap);

        telemetry.addData("Status", "Calibration Mode Initialized");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {

            // לחיצה על X תשלח את הסרוו למצב 0 (הכי סגור)
            if (gamepad1.x) {
                stopper.setPosition(0.0);
            }
            // לחיצה על B תשלח את הסרוו למצב 1 (הכי פתוח)
            else if (gamepad1.b) {
                stopper.setPosition(1.0);
            }

            // הצגת המיקום הנוכחי על מסך הטלפון בזמן אמת
            telemetry.addData("Instructions", "Press X for 0.0 (Closed), Press B for 1.0 (Open)");
            telemetry.addData("Current Stopper Position", stopper.stopperServo.getPosition());
            telemetry.update();
        }
    }
}