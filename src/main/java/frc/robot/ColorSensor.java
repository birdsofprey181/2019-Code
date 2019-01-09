package frc.robot;

import edu.wpi.first.wpilibj.*;

public class ColorSensor{

    static Joystick opStick = new Joystick(1);
    static double whiteTape = 0.05;
    static double sensorOutput = 0.5;

    public static void activate(){
        if(opStick.getRawButton(1) == true){
            while(sensorOutput != whiteTape){
                //turn left
                //if nothing detected for a bit, turn right
            }
            //drive forward for a little
        }
    }
}