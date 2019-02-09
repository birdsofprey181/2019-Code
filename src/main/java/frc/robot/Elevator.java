//Gwen, Elijah, Liam 02/09/19
package frc.robot;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Encoder;

public class Elevator {

    static Spark elevator = new Spark(2);
    static Encoder elevEncoder = new Encoder(0, 1, true, Encoder.EncodingType.k4X);

    public static void resetElevEncoder(){
        elevEncoder.reset();
    }

    public static void elevEncoderTest(){
        double elevDistance = elevEncoder.getDistance();
        
    }

    public static void elevControl(Joystick opStick) {
        elevator.set(opStick.getY());
    }


}