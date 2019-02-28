// will 2-16-19

package frc.robot;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Joystick;

public class Intake {

    static Spark intakeMotor = new Spark(4);
    // input pivot motor
    // input motor to return to fram perimeter

    public static void intakeControl(double up) {
        intakeMotor.set(up);
    }

    public static void intakeDirection(Joystick opStick) {
        if (opStick.getRawButton(3)) { // intake
            intakeControl(1);
        } else if (opStick.getRawButton(5)) { // outtake
            intakeControl(-1);
        }
    }

    public static void pivotIntake(Joystick opsStick) {

    }

    public static void toggleStartingConfig(Joystick opStick) {
        
    }
}