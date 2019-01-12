package frc.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;

public class PneumaticsTest {
    static DoubleSolenoid gripperSol = new DoubleSolenoid(0, 2, 3);
    static Joystick driveStick = new Joystick(0);
    static Joystick opStick = new Joystick(1);

    public static void startGrip() {
		gripperSol.set(DoubleSolenoid.Value.kReverse);	//moves solenoid forward
    }
    public static void unGrip() {
		gripperSol.set(DoubleSolenoid.Value.kForward);	//moves solenoid backwards
    }
    public static void grip() {
		if(driveStick.getRawButton(2) == true || opStick.getRawButton(2) == true) {	//if the "2" button pressed on the driver stick, run the program
			startGrip();	//runs the "unGrip" program
			System.out.println("Letting Go");
		}
		else if(driveStick.getRawButton(2) == false && opStick.getRawButton(2) == false) {	//if the "2" button released on the driver stick, run the program
			unGrip();	//runs the "startGrip" program
			System.out.println("Gripping");
		}
	}
}