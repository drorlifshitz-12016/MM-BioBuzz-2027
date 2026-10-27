package org.firstinspires.ftc.teamcode.Subsystem;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DcMotor;

public class TransferSubsystem {

    // הגדרת מנוע הטרנספר
    public DcMotor transferMotor;

    public TransferSubsystem(HardwareMap hardwareMap) {
        // חיבור המנוע לשם שהוגדר בטלפון
        transferMotor = hardwareMap.get(DcMotor.class, "transfer");

        transferMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    // פונקציה להפעלת הטרנספר
    public void setPower(double power) {
        transferMotor.setPower(power);
    }

    // עצירת הטרנספר
    public void stop() {
        transferMotor.setPower(0.0);
    }
}