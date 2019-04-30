//Gwen Miller 01/17/19

package frc.robot;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.VictorSP;
// import edu.wpi.first.wpilibj.Spark;

public class Drivetrain {

    //assigns pwm port to local motor variable
    static VictorSP leftMotor = new VictorSP(1);
    static VictorSP rightMotor = new VictorSP(0);

    //static CANSparkMax sparkMotor = new CANSparkMax(2, MotorType.kBrushless);
    //static CANSparkMax sparkMotor2 = new CANSparkMax(3, MotorType.kBrushless);

    // static DifferentialDrive sparkTrain = new DifferentialDrive(sparkMotor, sparkMotor2);
    
    //creates a drivetrain varible with the local motor variables, allows for easier control over motors
    static DifferentialDrive driveTrain = new DifferentialDrive(leftMotor, rightMotor);

    //methid to square a value but keep its pos/neg value
    public static double valSqu(double num){
        num = (num/Math.abs(num))*(num*num);
        return num;
    }

    //it stops the motors, thanks for reading comments
    public static void stop() {
        driveTrain.stopMotor();
    }

    //this one's a challenge, guess what it does?
    public static void drive(double forward, double turn) {
        driveTrain.arcadeDrive(valSqu(-forward), turn);
        //valSqu() is used to make the turning a tiny bit slower at low values, but more intense at higher values
    }

}