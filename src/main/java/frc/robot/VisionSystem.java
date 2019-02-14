package frc.robot;

/*
Vision tracking and alignment system - will

READ BEFORE RUNNING

as of 2-7-19, the limelight is not mounted, and some distances are dependent on measurements
that need to be taken once the limelight is finally secured and connected to the robot.
Anything that says "CHANGE when limelight is mounted" should be updated once the limelight is mounted along
with any speeds. As I am writing this code I am just speculating how fast we want the robot to go

because there are 2 different hieghts of vision targets, there are 2 methods; one for the cargo hold and
rocket hatch height (both under names related to "Hatch") and another for the front rocket face (under names
relating to "RocketFace"). If a method does not clarify, it is ambidextrous
*/

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Joystick;
import java.lang.Math; 

public class VisionSystem {
    NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    NetworkTableEntry tx = table.getEntry("x");
    NetworkTableEntry ty = table.getEntry("ty");
    NetworkTableEntry ta = table.getEntry("ta");

    //read values periodically
    double x = tx.getDouble(0.0);
    double y = ty.getDouble(0.0);
    double area = ta.getDouble(0.0);

    // measurements to find distance in inches and radians
    double cameraHeight = 12; // CHANGE when limelight is mounted
    double hatchHeight = 29; // rocket and cargo hatch heights
    double rocketFaceHeight = 37; // front face of rocket
    double cameraAngle = Math.PI * .25; // in radians, CHANGE when limelight is mounted

    //post to smart dashboard periodically
    // SmartDashboard.putNumber("LimelightX", x);
    // SmartDashboard.putNumber("LimelightY", y);
    // SmartDashboard.putNumber("LimelightArea", area);
    
    public void operateVisionTracking(Joystick driveStick) {
        if (driveStick.getRawButton(3)) { // button 3 aligns with hatch
            driveToHatchTarget(x, y, driveStick);
        }
        if (driveStick.getRawButton(5)) { // buttton 5 aligns to rocket face
            driveToRocketFaceTarget(x, y, driveStick);
        }
    }

    public void driveToHatchTarget(double tx, double y, Joystick driveStick) { // moves robot towards a cargo ship or rocket hatch target
        while (!driveStick.getRawButton(3)) { // hit 3 to stop the auto
            alignWithTarget(x);
            double distance = findDistanceHatch(y);
            double stopAtDistance = 12; //inches that limelight is from target, CHANGE when limelight is mounted
            while(distance > stopAtDistance) {
                Drivetrain.drive(.5, 0);
            }
        }  
    }
    public void driveToRocketFaceTarget(double x, double y, Joystick driveStick) { // moves robot towards a rocket face target
        while (!driveStick.getRawButton(5)) { // hit 5 to stop the auto
            alignWithTarget(x);
            double distance = findDistanceRocketFace(y);
            double stopAtDistance = 12; //inches that limelight is from target, CHANGE when limelight is mounted
            while(distance > stopAtDistance) {
                Drivetrain.drive(.5, 0);
            }
        }
    }
    public void alignWithTarget(double x) { // points robot at target, leaving a "degree of freedom" on both sides of center
        //values of x range [-27,27]
        if (x < -1) { // target is to the left
            while(x < -1) {
                Drivetrain.drive(0, .25); // turn right until aligned
            }
        } else if (x > 1) { // target to the right
            while(x > 1) {
                Drivetrain.drive(0, -.25); // turn left until aligned
            }
        }
    }
    public double findDistanceHatch(double y) { // INCHES, for cargo ship and rocket hatches
        double distance = 0;
        double a2 = Math.toRadians(y);
        double heightDifference = hatchHeight - cameraHeight;
        double fullAngle = a2 + cameraAngle;
        double demoninator = Math.tan(fullAngle);
        if (fullAngle != 0){
            distance = heightDifference/demoninator;
        }
        return distance;
    }
    public double findDistanceRocketFace(double y) { // INCHES, for rocket face
        double distance = 0;
        double a2 = Math.toRadians(y);
        double heightDifference = rocketFaceHeight - cameraHeight;
        double fullAngle = a2 + cameraAngle;
        double demoninator = Math.tan(fullAngle);
        if (fullAngle != 0){
            distance = heightDifference/demoninator;
        }
        return distance;
    }
}