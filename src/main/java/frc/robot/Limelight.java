//Done by Evan Belcourt and Matthew Shelto 2/13/
package frc.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import java.lang.Number;
import edu.wpi.first.wpilibj.DriverStation; 
  


public class Limelight{
    // Variables for the limelight class
    public boolean targetFound;
    public double XAxis;
    public double YAxis;
    public double Area;
    boolean DriverStation;
    boolean isEnabled;
   
    public void update(){
        

            NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
            NetworkTableEntry targetFound = table.getEntry("targetFound");
            NetworkTableEntry XAxis = table.getEntry("XAxis");
            NetworkTableEntry YAxis = table.getEntry("YAxis");
            NetworkTableEntry Area = table.getEntry("Area");
                
            if (targetFound.getNumber(0).intValue() == 1){
                this.targetFound = true;
            }
            else if (targetFound.getNumber(0).intValue() == 0){
                this.targetFound = false;
            } 

            this.XAxis = XAxis.getDouble(0.0);
            this.YAxis = YAxis.getDouble(0.0);
            this.Area = Area.getDouble(0.0);
        }


public boolean gettargetFound(){
return this.targetFound;
}

public double getXAxis(){
return this.XAxis;
}
public double getYAxis(){
return this.YAxis;
}
public double getArea(){
return this.Area;
    }
}