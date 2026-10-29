package org.firstinspires.ftc.teamcode.Subsystem;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class StopperSubsystem {

    public Servo stopperServo;

    public StopperSubsystem(HardwareMap hardwareMap) {
        stopperServo = hardwareMap.get(Servo.class, "stopperServo");
        stopperServo.setDirection(Servo.Direction.REVERSE);

        // הוספת טווח עבודה מותאם לסרבו Axon / דיגיטלי
        stopperServo.scaleRange(0.0, 1.0);
    }

    public void setPosition(double position) {
        stopperServo.setPosition(position);
    }
}