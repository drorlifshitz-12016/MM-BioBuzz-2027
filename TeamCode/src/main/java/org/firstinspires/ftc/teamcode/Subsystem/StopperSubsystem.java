package org.firstinspires.ftc.teamcode.Subsystem;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class StopperSubsystem {

    // הגדרת סרוו
    public Servo stopperServo;

    public StopperSubsystem(HardwareMap hardwareMap) {
        // חיבור הסרוו לשם שהוגדר בטלפון
        stopperServo = hardwareMap.get(Servo.class, "stopperServo");
    }

    // פונקציה לקביעת זווית/מצב הסרוו (ערך בין 0.0 ל-1.0)
    public void setPosition(double position) {
        stopperServo.setPosition(1);
    }
}