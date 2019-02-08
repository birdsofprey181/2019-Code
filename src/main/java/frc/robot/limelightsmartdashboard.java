package frc.robot;
import edu.wpi.first.wpilib.smartdashboard.Smartdashboard;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

@Override
NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
