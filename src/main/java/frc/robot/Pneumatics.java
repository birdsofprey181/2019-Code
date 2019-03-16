package frc.robot;

// hatch solenoids by will

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
import java.util.Timer;
import edu.wpi.first.wpilibj.smartdashboard.*;
//import java.util.concurrent.TimeUnit;

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
	public static void liftFront(Joystick driveStick){
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
		if(driveStick.getRawButtonPressed(7) == true){ // drops both
			frontActive = true;
			// try{
				// TimeUnit.SECONDS.sleep(3);
			// }catch(InterruptedException wack){}
			rearActive = true;
		}
		if(driveStick.getRawButtonPressed(9) == true){ // retracts front
			frontActive = false;
		}
		if(driveStick.getRawButtonPressed(11) == true){ // retract rear
			rearActive = false;
		} 
		if(driveStick.getRawButtonPressed(10)) { // drop front
			frontActive = true;
		}
		if(driveStick.getRawButtonPressed(12)) { // drop rear
			rearActive = true;
		}
		if (rearActive == true){
			rearExtend();
			SmartDashboard.putString("frontPneumatics", "Front Extended");
		} else if (rearActive == false){
			rearRetract();
			SmartDashboard.putString("frontPneumatics", "Front Retracted");
		}
		if (frontActive == true){
			frontExtend();
			SmartDashboard.putString("rearPneumatics", "Rear Active");
		} else if (frontActive == false){
			frontRetract();
			SmartDashboard.putString("rearPneumatics", "Rear Retracted");
		}

	}
	public static void dropRear(Joystick driveStick){
		if (driveStick.getRawButton(12) == true){
			rearExtend();
		}else{
			rearRetract();
		}
	}
	public static void dropFront(Joystick driveStick){
		if (driveStick.getRawButton(10) == true){
			frontExtend();
		}
		else{
			rearRetract();
		}
	}
}