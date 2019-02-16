//Gwen, Elijah, Liam 02/09/19
package frc.robot;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Encoder;

public class Elevator {

    static Spark elevator = new Spark(2);
    static Encoder elevEncoder = new Encoder(0, 1, true, Encoder.EncodingType.k4X);
    static Double wantedHeight = 0.0;

    public static void resetElevEncoder(){
        elevEncoder.reset();
    }

    public static void encoderTest(){
        System.out.println(elevEncoder.getDistance());
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
        double topDist = 2177.0;
        double midDist = 2.5;
        double botDist = 0.0;
        if(opStick.getRawButton(8) == true) {
            elevEncoderRaise(elevDistance, topDist);
        }else if (opStick.getRawButton(10) == true) {
            elevEncoderRaise(elevDistance, midDist);
        }else if(opStick.getRawButton(12) == true){
            elevEncoderRaise(elevDistance, botDist);
        }
    }

    public static void elevControl(double up) {
        elevator.set(up);
        if(elevEncoder.getDistance() < 5.0 && elevEncoder.getDistance() > -5.0){
            resetElevEncoder();
        }
    }

    public static void elevBrake() {
        Double elevTolerance = 5.0; // tolerance + & -
        Double upBound = wantedHeight + elevTolerance;
        Double lowBound = wantedHeight - elevTolerance;
        if (elevEncoder.getDistance() < lowBound) {
            elevControl(2); // check polarity and values
        } else if( elevEncoder.getDistance() > upBound) {
            elevControl(-2);
        }
    }

}