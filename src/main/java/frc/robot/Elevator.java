//Gwen, Elijah, Liam 02/09/19
package frc.robot;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Joystick;

public class Elevator {

    static Spark elevator = new Spark(2);

    public static void elevControl(Joystick opStick) {
        elevator.set(opStick.getY());
    }


}