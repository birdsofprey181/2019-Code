package frc.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
//import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public class Pneumatics {
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
		public static void liftFront(Joystick driveStick){
			if (driveStick.getRawButton(19) == true){
				frontExtend();
			}else{
				frontRetract();
			}
		}
		public static void liftRear(Joystick driveStick){
			if (driveStick.getRawButton(20) == true){
				rearExtend();
			}else{
				rearRetract();
			}
		}
}