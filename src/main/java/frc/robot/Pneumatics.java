package frc.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;

public class Pneumatics {

	static boolean frontActive = false;
	static boolean rearActive = false;

	static DoubleSolenoid frontSol = new DoubleSolenoid(2, 3);
	static DoubleSolenoid rearSol = new DoubleSolenoid(4, 5);
	static DoubleSolenoid shifterSol = new DoubleSolenoid(0, 1);
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
	public static void highGear() {
		shifterSol.set(DoubleSolenoid.Value.kForward);
	}
	public static void lowGear() {
		shifterSol.set(DoubleSolenoid.Value.kReverse);
	}
	public static void shiftGears(Joystick driveStick) {
		if(driveStick.getRawButton(1) == true){
			highGear();
		}
		else{
			lowGear();
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
	}*/
	public static void toggleLift(Joystick driveStick){
		if(driveStick.getRawButtonPressed(7) && (rearActive == false && frontActive == false)){
			rearExtend();
			rearActive = true;
			frontExtend();
			frontActive = true;
		}
		if(driveStick.getRawButtonPressed(8) && frontActive == true){
			frontRetract();
		}
		if(driveStick.getRawButtonPressed(9) && rearActive == true){
			rearRetract();
		}
	}
}