//Gwen, Elijah, Liam 02/09/19
package frc.robot;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalInput;

public class Elevator {

    static Spark elevator = new Spark(2);
    static Encoder elevEncoder = new Encoder(0, 1, true, Encoder.EncodingType.k4X);
    static Counter wristCounter = new Counter(new DigitalInput(2));
    static Double elevTolerance = 5.0; // tolerance + & -
    static Double wantedHeight = 0.0;
    private static int position = 0;
    
    static Spark wrist = new Spark(3);

    //turns the mechanism counter into an encoder-ish
    //update as of DCMP: values slowly start to decrease, without any pattern
    public static void wristRead(Joystick opStick){
        int povValue = opStick.getPOV(0);
        if(povValue == 0){
            position += wristCounter.get();
        } else {
            position -= wristCounter.get();
        }
        System.out.println(position);
        wristCounter.reset();
    }

    //resets counter
    public static void wristReset(){
        wristCounter.reset();
    }

    //returns the meaning of life
    public static void wristMove(double up){
        wrist.set(up);
    }

    //translates pov into mechanism movements
    public static void controlWrist(Joystick opStick) {
        int povValue = opStick.getPOV(0);
        if (povValue == 0) { // pov stick is up
            wristMove(1.0); // hopefully polarities are right
        } else if (povValue == 180) { // pov stick is down
            wristMove(-1.0); 
        } else if (povValue == -1) { // pov stick is neutral (stoppping the wrist)
            wristMove(0.0);
        }
    }

    //do i really need to write comments for these methods?
    public static void resetElevEncoder(){
        elevEncoder.reset();
    }

    //a method to automove the elevator to a set distance
    //dont use this, it'll break the robot
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
    //utilizes the elevEncoderRaise method to take the current elevator position and move it to the preset distance
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
        Double upBound = wantedHeight + elevTolerance;
        Double lowBound = wantedHeight - elevTolerance;
        if (elevEncoder.getDistance() < lowBound) {
            elevControl(2); // check polarity and values
        } else if( elevEncoder.getDistance() > upBound) {
            elevControl(-2);
        }
    }

}