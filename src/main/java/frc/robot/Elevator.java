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

    public static void elevEncoderRaise(double elevDistance, double setDistance){
        if(elevDistance < setDistance) {
            elevEncoder.setReverseDirection(false);
            while(elevDistance < setDistance){
                elevControl(5);
                elevDistance = elevEncoder.getDistance();
            }
        }else if(elevDistance > setDistance){
            elevEncoder.setReverseDirection(true);
            while(elevDistance > setDistance){
                elevControl(-5);
                elevDistance = elevEncoder.getDistance();
            }
        }
    }
    //example code, not used in the final code
    public static void elevEncoderTest(Joystick opStick){
        double elevDistance = elevEncoder.getDistance();
        double topDist = 5.0;
        double midDist = 2.5;
        double botDist = 0.0;
        if(opStick.getRawButton(2) == true) {
            elevEncoderRaise(elevDistance, topDist);
        }else if (opStick.getRawButton(3) == true) {
            elevEncoderRaise(elevDistance, midDist);
        }else if(opStick.getRawButton(4) == true){
            elevEncoderRaise(elevDistance, botDist);
        }
    }

    public static void elevControl(double up) {
        elevator.set(up);
    }


}