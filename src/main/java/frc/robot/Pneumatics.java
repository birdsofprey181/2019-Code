package frc.robot;

// hatch solenoids by will

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

public class Pneumatics {

	public static boolean frontActive = false;
	public static boolean rearActive = false;

	public static Timer liftDelay = new Timer();

	static DoubleSolenoid frontSol = new DoubleSolenoid(2, 3);
	static DoubleSolenoid rearSol = new DoubleSolenoid(4, 5);
	static DoubleSolenoid hatchSol = new DoubleSolenoid(0, 1);
	//static DoubleSolenoid testSol = new DoubleSolenoid(moduleNumber, forwardChannel, reverseChannel)
	//static DoubleSolenoid testSol = new DoubleSolenoid(forwardChannel, reverseChannel)
    //static Joystick driveStick = new Joystick(0);
    //static Joystick opStick = new Joystick(1);

    public static void frontExtend() {
		frontSol.set(DoubleSolenoid.Value.kForward);	//moves solenoid forward
    }
    public static void frontRetract() {
		frontSol.set(DoubleSolenoid.Value.kReverse);	//moves solenoid backwards
	}
	public static void rearExtend() {
		rearSol.set(DoubleSolenoid.Value.kForward);
	}
	public static void rearRetract() {
		rearSol.set(DoubleSolenoid.Value.kReverse);
	}
	public static void pushHatch() {
		hatchSol.set(DoubleSolenoid.Value.kForward);
	}
	public static void pullHatch() {
		hatchSol.set(DoubleSolenoid.Value.kReverse);
	}
	public static void detatchHatch(Joystick driveStick) {
		if(driveStick.getRawButton(1) == true){
			pushHatch();
		}
		else{
			pullHatch();
		}
	}
	/*public static void liftFront(Joystick driveStick){
		if (driveStick.getRawButton(7) == true){
			frontExtend();
		}else{
			frontRetract();
		}
	}
	public static void liftRear(Joystick driveStick){
		if (driveStick.getRawButton(9) == true){
			rearExtend();
		}else{
			rearRetract();
		}
	}
	public static void toggleLift(Joystick driveStick){
		if(driveStick.getRawButtonPressed(7) == true){
			frontActive = true;
			// try{
				// TimeUnit.SECONDS.sleep(3);
			// }catch(InterruptedException wack){}
			rearActive = true;
		}
		if(driveStick.getRawButtonPressed(9) == true){
			frontActive = false;
		}
		if(driveStick.getRawButtonPressed(11) == true){
			rearActive = false;
		} 
		if (rearActive == true){
			rearExtend();
		} else if (rearActive == false){
			rearRetract();
		}
		if (frontActive == true){
			frontExtend();
		} else if (frontActive == false){
			frontRetract();
		}
	}
}