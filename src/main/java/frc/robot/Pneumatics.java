package frc.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;

public class Pneumatics {

	public static boolean frontActive = false;
	public static boolean rearActive = false;

	public static int[] buttonMap = new int[2];

	static DoubleSolenoid frontSol = new DoubleSolenoid(2, 3);
	static DoubleSolenoid rearSol = new DoubleSolenoid(4, 5);

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
		if(driveStick.getRawButtonPressed(7) == true){
			rearActive = true;
			frontActive = true;
		}
		if(driveStick.getRawButtonPressed(8) == true){
			frontActive = false;
		}
		if(driveStick.getRawButtonPressed(9) == true){
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