package org.firstinspires.ftc.teamcode.Opmode.TeleOp;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Subsystem.StopperSubsystem;

@Config
@TeleOp(name = "ServoCalibrate_TeleOp", group = "Linear Opmode")
public class ServoCalibrate_TeleOp extends LinearOpMode {

    public static double STOPPER_CLOSED = 0.68;
    public static double STOPPER_OPEN = 0.0;


    @Override
    public void runOpMode() {
        StopperSubsystem stopper = new StopperSubsystem(hardwareMap);
        FtcDashboard dashboard = FtcDashboard.getInstance();

        telemetry.addData("Status", "Initialized. Ready to start.");
        telemetry.update();
        waitForStart();


        while (opModeIsActive()) {

            if (gamepad1.dpad_up) {

                stopper.setPosition(STOPPER_CLOSED);

            } else if (gamepad1.dpad_down) {

                stopper.setPosition(STOPPER_OPEN);

            }

            telemetry.addData("Current Servo Feedback", stopper.stopperServo.getPosition());
            telemetry.update();

            TelemetryPacket packet = new TelemetryPacket();
            packet.put("Servo Position", stopper.stopperServo.getPosition());
            dashboard.sendTelemetryPacket(packet);

            sleep(20);
        }
    }
}