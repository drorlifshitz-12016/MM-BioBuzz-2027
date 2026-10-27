package org.firstinspires.ftc.teamcode.Opmode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

// ייבוא תת-המערכות
import org.firstinspires.ftc.teamcode.Subsystem.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.Subsystem.TransferSubsystem;
import org.firstinspires.ftc.teamcode.Subsystem.StopperSubsystem;
import org.firstinspires.ftc.teamcode.Subsystem.ShooterSubsystem;

@TeleOp(name = "roboTesting_DM", group = "Linear Opmode")
public class roboTesting_DM extends LinearOpMode {

    // --- אזור כיול (כאן משנים את הערכים) ---
//    public static double STOPPER_CLOSED_POSITION = 0.2; // מיקום המעצור האידאלי (סגור/איפוס)
    public static double STOPPER_OPEN_POSITION = 0.8;   // מיקום המעצור (פתוח/משוחרר)
    public static double SHOOTER_TARGET_SPEED = 0.85;   // מהירות הירי האידאלית (בין 0.0 ל-1.0)
    // ------------------------------------------------

    @Override
    public void runOpMode() {
        // אתחול תת-המערכות
        IntakeSubsystem intake = new IntakeSubsystem(hardwareMap);
        TransferSubsystem transfer = new TransferSubsystem(hardwareMap);
//        StopperSubsystem stopper = new StopperSubsystem(hardwareMap);
        ShooterSubsystem shooter = new ShooterSubsystem(hardwareMap);

        boolean intakeRunning = false;
        boolean lastYState = false;

        telemetry.addData("Status", "Initialized. Ready to start.");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {

            // --- שליטה בכפתור Y: איסוף וטרנספר עם מצב הדלקה/כיבוי (Toggle) ---
            boolean currentYState = gamepad1.y;
            if (currentYState && !lastYState) { // זיהוי לחיצה חדשה (Edge Detection)
                intakeRunning = !intakeRunning; // היפוך מצב
                if (intakeRunning) {
//                    stopper.setPosition(STOPPER_CLOSED_POSITION); // איפוס המעצור
                }
            }
            lastYState = currentYState;

            if (intakeRunning) {
                // *** שינוי כיוון: החליפו למינוס (-) אם הכיוון הפוך ***
                intake.setPower(1.0);
                transfer.setPower(1.0);
            } else {
                // כשהאיסוף כבוי (אלא אם הופעל עכשיו ירי)
                if (!gamepad1.a) {
                    intake.setPower(0.0);
                    transfer.setPower(0.0);
                }
            }

            // --- שליטה בכפתור A: האצת ירי, שחרור מעצור והפעלת טרנספר ---
            if (gamepad1.a) {
                // 1. האצת גלגלי הירי (*** שינוי כיוון אפשרי כאן ע"י פלוס/מינוס ***)
                shooter.setPower(SHOOTER_TARGET_SPEED);

                // בדיקה האם השוטר הגיע למהירות היעד (אפשר להוסיף תנאי מהירות אמיתי בהמשך אם יש אנקודר)
                // ברגע שהם רצים, נשחרר את המעצור ונדחוף עם הטרנספר
//                stopper.setPosition(STOPPER_OPEN_POSITION);

                // *** שינוי כיוון: החליפו למינוס (-) אם הטרנספר צריך להסתובב הפוך בזמן ירי ***
                transfer.setPower(1.0);
            } else {
                // עצירת השוטר כשלא לוחצים A
                shooter.setPower(0.0);
                if (!intakeRunning) {
                    transfer.setPower(0.0);
                }
            }

            // הצגת נתונים על מסך הטלפון לכיול
            telemetry.addData("Intake Running", intakeRunning);
            telemetry.addData("Shooter Speed Target", SHOOTER_TARGET_SPEED);
//            telemetry.addData("Stopper Position", stopper.stopperServo.getPosition());
            telemetry.update();
        }
    }
}