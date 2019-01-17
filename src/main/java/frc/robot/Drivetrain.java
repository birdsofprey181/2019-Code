//Gwen Miller 01/17/19

package frc.robot;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.VictorSP;

public class Drivetrain {

    static VictorSP leftMotor = new VictorSP(1);
    static VictorSP rightMotor = new VictorSP(0);

    static DifferentialDrive driveTrain = new DifferentialDrive(leftMotor, rightMotor);

    public static void stop() {
        driveTrain.stopMotor();
    }

    public static void drive(double forward, double turn) {
        driveTrain.arcadeDrive(forward, turn);
    }
}