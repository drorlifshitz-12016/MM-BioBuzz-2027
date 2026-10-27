package org.firstinspires.ftc.teamcode.Subsystem;

import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DcMotor;

public class ShooterSubsystem {

    // הגדרת שני מנועי ה-DC לשוטר
    public DcMotor shooterMotor1;
    public DcMotor shooterMotor2;

    public ShooterSubsystem(HardwareMap hardwareMap) {
        // חיבור המנועים לשמות שהוגדרו בטלפון (Configuration)
        shooterMotor1 = hardwareMap.get(DcMotor.class, "shooterMotorR");
        shooterMotor2 = hardwareMap.get(DcMotor.class, "shooterMotorL");

// אם אחד המנועים מסתובב לכיוון ההפוך מהשני, פשוט הוסיפו את השורה הזו לאחד מהם:
        shooterMotor1.setDirection(DcMotor.Direction.REVERSE); // או REVERSE
        shooterMotor2.setDirection(DcMotor.Direction.FORWARD); // הופך את הכיוון של המנוע השני!

        // הגדרת בלימה
        shooterMotor1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        shooterMotor2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    // פונקציה להפעלת השוטר במהירות רצויה
    public void setPower(double power) {
        shooterMotor1.setPower(power);
        shooterMotor2.setPower(power);
    }

    // עצירת השוטר
    public void stop() {
        shooterMotor1.setPower(0.0);
        shooterMotor2.setPower(0.0);
    }
}