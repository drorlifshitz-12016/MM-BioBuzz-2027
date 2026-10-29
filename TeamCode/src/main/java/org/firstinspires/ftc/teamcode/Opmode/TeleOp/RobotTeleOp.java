package org.firstinspires.ftc.teamcode.Opmode.TeleOp;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

// ייבוא תת-המערכות שלכם
import org.firstinspires.ftc.teamcode.Subsystem.DrivetrainSubsystem;
import org.firstinspires.ftc.teamcode.Subsystem.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.Subsystem.StopperSubsystem;
import org.firstinspires.ftc.teamcode.Subsystem.TransferSubsystem;

import org.firstinspires.ftc.teamcode.Subsystem.ShooterSubsystem;

@Config
@TeleOp(name = "RobotTeleOp", group = "Linear Opmode")
public class RobotTeleOp extends LinearOpMode {

    // --- הגדרות וערכים ---
//    public static double STOPPER_CLOSED_POSITION = 0.2;
//    public static double STOPPER_OPEN_POSITION = 0.0;
    public static double SHOOTER_TARGET_SPEED = 0.85;

    @Override
    public void runOpMode() {
        // 1. אתחול כל תת-המערכות (כולל הנעה)
        DrivetrainSubsystem drive = new DrivetrainSubsystem(hardwareMap);
        IntakeSubsystem intake = new IntakeSubsystem(hardwareMap);
        TransferSubsystem transfer = new TransferSubsystem(hardwareMap);
//        StopperSubsystem stopper = new StopperSubsystem(hardwareMap);
        ShooterSubsystem shooter = new ShooterSubsystem(hardwareMap);

        boolean intakeRunning = false;
        boolean lastYState = false;

        telemetry.addData("Status", "Robot TeleOp Initialized & Ready");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {

            // --- א. מערכת הנעה רגילה דרך ה-Subsystem ---
            // ג'ויסטיק שמאל: נסיעה קדימה/אחורה ושמאלה/ימינה
            // ג'ויסטיק ימין: סיבוב
            double forward = gamepad1.left_stick_y;
            double strafe  = -gamepad1.left_stick_x;
            double turn    = -gamepad1.right_stick_x;

            drive.drive(forward, strafe, turn);


            // --- ב. שליטה בכפתור Y: איסוף וטרנספר (Toggle) ---
            boolean currentYState = gamepad1.y;
            if (currentYState && !lastYState) {
                intakeRunning = !intakeRunning;
                if (intakeRunning) {
//                    stopper.setPosition(STOPPER_CLOSED_POSITION);
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


            // --- ג. שליטה בכפתור A: ירי, שחרור מעצור וטרנספר ---
            if (gamepad1.a) {
                shooter.setPower(SHOOTER_TARGET_SPEED);
//                stopper.setPosition(STOPPER_OPEN_POSITION);
                transfer.setPower(1.0);
            } else {
                shooter.setPower(0.0);
                if (!intakeRunning) {
                    transfer.setPower(0.0);
                }
            }

            telemetry.addData("Status", "Running Subsystem TeleOp");
            telemetry.update();
        }
    }
}