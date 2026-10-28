package org.firstinspires.ftc.teamcode.Opmode.TeleOp;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Subsystem.StopperSubsystem;


@TeleOp(name = "ServoCalibrate_TeleOp", group = "Linear Opmode")
public class ServoCalibrate_TeleOp extends LinearOpMode {

    public static double STOPPER_CLOSED = 0.8;
    public static double STOPPER_OPEN = 0.0;

    @Override
    public void runOpMode() {
        StopperSubsystem stopper = new StopperSubsystem(hardwareMap);

        // יצירת מופע של הדאשבורד
        FtcDashboard dashboard = FtcDashboard.getInstance();

        waitForStart();

        while (opModeIsActive()) {

            // שימוש בערכים שניתן לשנות תוך כדי ריצה מהדאשבורד
            if (gamepad1.dpad_up) {
                stopper.setPosition(STOPPER_OPEN);
            } else if (gamepad1.dpad_down) {
                stopper.setPosition(STOPPER_CLOSED);
            }

            // --- דרך 1: שליחת Telemetry רגילה (מופיעה גם בדאשבורד אוטומטית) ---
            telemetry.addData("Stopper Target Position", stopper.stopperServo.getPosition());
            telemetry.update();

            // --- דרך 2: שליחת נתונים מתקדמת לדאשבורד (לגרפים ונתונים בזמן אמת) ---
            TelemetryPacket packet = new TelemetryPacket();
            packet.put("Servo Position", stopper.stopperServo.getPosition());
            dashboard.sendTelemetryPacket(packet);
        }
    }
}