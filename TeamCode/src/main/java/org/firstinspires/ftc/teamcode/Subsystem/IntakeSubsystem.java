package org.firstinspires.ftc.teamcode.Subsystem;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DcMotor;

public class IntakeSubsystem {

    // הגדרת משתנה עבור המנוע
    public DcMotor intakeMotor;

    // פעולת האתחול שרצה כשמפעילים את הרובוט
    public IntakeSubsystem(HardwareMap hardwareMap) {
        // חיבור המנוע לשם שהוגדר בטלפון (Configuration)
        intakeMotor = hardwareMap.get(DcMotor.class, "intake");

        // הגדרת כיוון סיבוב ברירת מחדל (אופציונלי)
        intakeMotor.setDirection(DcMotor.Direction.FORWARD);

        // עצירה ובלימה בתחילת הדרך
        intakeMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    // פונקציה שמפעילה את האיסוף במהירות נתונה
    public void setPower(double power) {
        intakeMotor.setPower(power);
    }

    // פונקציה שעוצרת את האיסוף
    public void stop() {
        intakeMotor.setPower(0.0);
    }
}