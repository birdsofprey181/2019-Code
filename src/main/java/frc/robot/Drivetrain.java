//Gwen Miller 01/17/19

package frc.robot;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.VictorSP;
// import com.revrobotics.CANSparkMax;
// import com.revrobotics.CANSparkMaxLowLevel.MotorType;
// import edu.wpi.first.wpilibj.Spark;

public class Drivetrain {

    static VictorSP leftMotor = new VictorSP(1);
    static VictorSP rightMotor = new VictorSP(0);

    //static CANSparkMax sparkMotor = new CANSparkMax(2, MotorType.kBrushless);
    //static CANSparkMax sparkMotor2 = new CANSparkMax(3, MotorType.kBrushless);

    // static DifferentialDrive sparkTrain = new DifferentialDrive(sparkMotor, sparkMotor2);

    static DifferentialDrive driveTrain = new DifferentialDrive(leftMotor, rightMotor);

    public static void stop() {
        driveTrain.stopMotor();
    }

    public static void drive(double forward, double turn) {
        driveTrain.arcadeDrive(-forward, turn);
    }

}