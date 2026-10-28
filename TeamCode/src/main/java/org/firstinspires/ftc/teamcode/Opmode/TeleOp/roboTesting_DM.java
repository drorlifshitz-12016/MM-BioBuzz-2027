package org.firstinspires.ftc.teamcode.Opmode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

// ייבוא FTC Dashboard
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;

// ייבוא תת-המערכות שלכם
import org.firstinspires.ftc.teamcode.Subsystem.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.Subsystem.TransferSubsystem;
import org.firstinspires.ftc.teamcode.Subsystem.StopperSubsystem;
import org.firstinspires.ftc.teamcode.Subsystem.ShooterSubsystem;

@Config
@TeleOp(name = "roboTesting_DM", group = "Linear Opmode")
public class roboTesting_DM extends LinearOpMode {

    // --- משתנים הניתנים לעריכה חיה דרך ה-FTC Dashboard ---
    public static double STOPPER_CLOSED_POSITION = 0.0; // מיקום מעצור סגור
    public static double STOPPER_OPEN_POSITION = 0.8;   // מיקום מעצור פתוח
    public static double SHOOTER_TARGET_SPEED = 0.85;   // מהירות שוטר

    @Override
    public void runOpMode() {
        // 1. אתחול תתי-המערכות בלבד (בלי הנעה)
        IntakeSubsystem intake = new IntakeSubsystem(hardwareMap);
        TransferSubsystem transfer = new TransferSubsystem(hardwareMap);
        StopperSubsystem stopper = new StopperSubsystem(hardwareMap);
        ShooterSubsystem shooter = new ShooterSubsystem(hardwareMap);

        FtcDashboard dashboard = FtcDashboard.getInstance();

        boolean intakeRunning = false;
        boolean lastYState = false;

        telemetry.addData("Status", "Initialized. Ready to start.");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {

            // --- א. שליטה בכפתור Y: איסוף וטרנספר (Toggle) ---
            boolean currentYState = gamepad1.y;
            if (currentYState && !lastYState) { // Edge Detection (לחיצה בודדת)
                intakeRunning = !intakeRunning;
                if (intakeRunning) {
                    stopper.setPosition(STOPPER_CLOSED_POSITION);
                }
            }
            lastYState = currentYState;

            if (intakeRunning) {
                intake.setPower(1.0);
                transfer.setPower(1.0);
            } else {
                if (!gamepad1.a) {
                    intake.setPower(0.0);
                    transfer.setPower(0.0);
                }
            }


            // --- ב. שליטה בכפתור A: ירי, שחרור מעצור וטרנספר ---
            if (gamepad1.a) {
                shooter.setPower(SHOOTER_TARGET_SPEED);
                stopper.setPosition(STOPPER_OPEN_POSITION);
                transfer.setPower(1.0);
            } else {
                shooter.setPower(0.0);
                if (!intakeRunning) {
                    transfer.setPower(0.0);
                }
            }


            // --- ג. Telemetry רגיל ושלח נתונים ל-Dashboard ---
            telemetry.addData("Intake Running", intakeRunning);
            telemetry.addData("Shooter Speed", SHOOTER_TARGET_SPEED);
            telemetry.update();

            TelemetryPacket packet = new TelemetryPacket();
            packet.put("Shooter Target Speed", SHOOTER_TARGET_SPEED);
            packet.put("Stooper Open Postion" , STOPPER_OPEN_POSITION);
            packet.put("Stooper Closed Postion" , STOPPER_CLOSED_POSITION);
            dashboard.sendTelemetryPacket(packet);
        }
    }
}