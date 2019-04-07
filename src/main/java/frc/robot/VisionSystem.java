package frc.robot;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Joystick; 

public class VisionSystem {
    static NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    static NetworkTableEntry tx = table.getEntry("x");
    static NetworkTableEntry ty = table.getEntry("ty");
    static NetworkTableEntry ta = table.getEntry("ta");

    //read values periodically
    static double x = tx.getDouble(0.0);
    static double y = ty.getDouble(0.0);
    static double area = ta.getDouble(0.0);

    // measurements to find distance in inches and radians
    static double cameraHeight = 27; // CHANGE when limelight is mounted
    static double hatchHeight = 29; // rocket and cargo hatch heights
    static double rocketFaceHeight = 37; // front face of rocket
    static double cameraAngle = Math.PI * (1/18); // in radians, CHANGE when limelight is mounted

    //post to smart dashboard periodically
    // SmartDashboard.putNumber("LimelightX", x);
    // SmartDashboard.putNumber("LimelightY", y);
    // SmartDashboard.putNumber("LimelightArea", area);
    
    public static void pictureInPicture (){
        NetworkTableInstance.getDefault().getTable("limelight").getEntry("stream").setNumber(1);
    }

    public static void operateVisionTracking(Joystick driveStick) {
        if (driveStick.getRawButton(4)) { // button 4 aligns with hatch
            ledOn();
            driveToHatchTarget(x, y, driveStick);
            ledOff();
        } else if (driveStick.getRawButton(6)) { // buttton 6 aligns to rocket face
            ledOn();
            driveToRocketFaceTarget(x, y, driveStick);
            ledOff();
        }

    }

    public static void driveToHatchTarget(double tx, double y, Joystick driveStick) { // moves robot towards a cargo ship or rocket hatch target
        while (!driveStick.getRawButton(2)) {
            alignWithTarget(x);
            double distance = findDistanceHatch(y);
            double stopAtDistance = 40; //inches that limelight is from target, CHANGE when limelight is mounted
            // while(distance > stopAtDistance && !driveStick.getRawButton(2)) {
            //     Drivetrain.drive(.25, 0);
            // } 
        } 
    }
    public static void driveToRocketFaceTarget(double x, double y, Joystick driveStick) { // moves robot towards a rocket face target
        while (!driveStick.getRawButton(2)) {
            alignWithTarget(x);
            double distance = findDistanceRocketFace(y);
            double stopAtDistance = 40; //inches that limelight is from target, CHANGE when limelight is mounted
            // while(distance > stopAtDistance && !driveStick.getRawButton(2)) {
            //     Drivetrain.drive(.25, 0);
            // }
        }
    }
    public static void alignWithTarget(double x) { // points robot at target, leaving a "degree of freedom" on both sides of center
        //values of x range [-27,27]
        if (x < -1) { // target is to the left
            while(x < -1) {
                Drivetrain.drive(.25, .25); // turn right until aligned
            }
        } else if (x > 1) { // target to the right
            while(x > 1) {
                Drivetrain.drive(.25, -.25); // turn left until aligned
            }
        }
    }
    public static double findDistanceHatch(double y) { // INCHES, for cargo ship and rocket hatches
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
    public static double findDistanceRocketFace(double y) { // INCHES, for rocket face
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

    public static void PostToDashboard() {
        NetworkTableInstance.getDefault().getTable("limelight").getEntry("tv").getDouble(0);
    }

    public static void changeLEDStatus(Joystick driveStick){
        if (driveStick.getRawButton(1)){
            ledOn();
        } else {
            ledOff();
        }

    }
    public static void ledOn() {
        NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setNumber(3);
        NetworkTableInstance.getDefault().getTable("limelight").getEntry("camMode").setNumber(0);
    }
    public static void ledOff() {
        NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setNumber(1);
        NetworkTableInstance.getDefault().getTable("limelight").getEntry("camMode").setNumber(1);
    }
}