package frc.robot;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Joystick; 

public class VisionSystem {

    static private NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    static private NetworkTableEntry tx = table.getEntry("tx");
    static private NetworkTableEntry ty = table.getEntry("ty");
    static private NetworkTableEntry ta = table.getEntry("ta");
    static private NetworkTableEntry ledMode = table.getEntry("ledMode");

    static public double getY() {
        return ty.getDouble(0.0);
    }

    static public double getX() {
        return tx.getDouble(0.0);
    }

    static public double getA() {
        return ta.getDouble(0.0);
    }

    static public double getledMode() {
        return ledMode.getDouble(0.0);
    }

    static double testx = 10;
    static double testDistance = 24;
    static double deadZone = 1; // the amount of degrees on either side of the target that the limelight that is considered "on target"

    // measurements to find distance in inches and radians
    static double cameraHeight = 14; // CHANGE when limelight is mounted
    static double hatchHeight = 29; // rocket and cargo hatch heights
    static double rocketFaceHeight = 37; // front face of rocket
    static double cameraAngle = Math.PI * (10/180); // in radians, CHANGE when limelight is mounted

    //post to smart dashboard periodically
    // SmartDashboard.putNumber("LimelightX", x);
    // SmartDashboard.putNumber("LimelightY", y);
    // SmartDashboard.putNumber("LimelightArea", area);
    
    public static void pictureInPicture (){
        NetworkTableInstance.getDefault().getTable("limelight").getEntry("stream").setNumber(1);
    }

    public static void operateVisionTracking(Joystick driveStick) {
        printX();
        if (driveStick.getRawButton(4)) { // button 4 aligns with hatch
            ledOn();
            //test(driveStick);
            alignWithTarget();
            //driveToHatchTarget(driveStick);
            ledOff();
        } else if (driveStick.getRawButton(6)) { // buttton 6 aligns to rocket face
            ledOn();
            driveToRocketFaceTarget(driveStick);
            ledOff();
        }

    }
    public static void test(Joystick driveStick){
        printX();
        
    }
    // nice
    public static void driveToHatchTarget(Joystick driveStick) { // moves robot towards a cargo ship or rocket hatch target
        if (!driveStick.getRawButton(2)) {
            //Drivetrain.drive(0.0, .25);
            alignWithTarget();
            double distance = findDistanceHatch();
            double stopAtDistance = 40; //inches that limelight is from target, CHANGE when limelight is mounted
            // if (distance > stopAtDistance && !driveStick.getRawButton(2)) {
            //     Drivetrain.drive(.25, 0);
            // } 
        } 
    }
    public static void driveToRocketFaceTarget(Joystick driveStick) { // moves robot towards a rocket face target
        if (!driveStick.getRawButton(2)) {
            alignWithTarget();
            double distance = findDistanceRocketFace();
            double stopAtDistance = 40; //inches that limelight is from target, CHANGE when limelight is mounted
            // if (distance > stopAtDistance && !driveStick.getRawButton(2)) {
            //     Drivetrain.drive(.25, 0);
            // }
        }
    }
    public static void alignWithTarget() { // points robot at target, leaving a "degree of freedom" on both sides of center
        //values of x range [-27,27]
        if (getX() < -deadZone) { // target is to the left
            Drivetrain.drive(.25, .25); // turn right until aligned

        } else if (getX() > deadZone) { // target to the right
            Drivetrain.drive(.25, -.25); // turn left until aligned

        }
        //Drivetrain.drive(0, 0);
        System.out.println("Robot alligned");
    }
    public static double findDistanceHatch() { // INCHES, for cargo ship and rocket hatches
        double distance = 0;
        double a2 = Math.toRadians(getY());
        double heightDifference = hatchHeight - cameraHeight;
        double fullAngle = a2 + cameraAngle;
        double demoninator = Math.tan(fullAngle);
        if (fullAngle != 0){
            distance = heightDifference/demoninator;
        }
        return distance;
    }
    public static void printHatchDistance() {
        System.out.println("Hatch at " + findDistanceHatch() + " in");
    }
    public static double findDistanceRocketFace() { // INCHES, for rocket face
        double distance = 0;
        double a2 = Math.toRadians(getY());
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
    // hi fellow programmers. yall ugly
    // excuse me ma'am that is very offensive to us programmers :( 
    public static void printX() {
        System.out.println("x offset - " + getX());
    }
}