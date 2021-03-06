
package org.usfirst.frc.team4908.robot;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
    final String defaultAuto = "Default";
    final String customAuto = "My Auto";
    String autoSelected;
    SendableChooser chooser;

    Encoder rightEncoder;
    Encoder leftEncoder;
    
    CANTalon frontLeft;
	CANTalon frontRight;
	CANTalon backLeft;
	CANTalon backRight;
		
	Joystick stick1;
    Joystick stick2;
    
    RobotDrive drive;
    
    boolean isFirstTime;
    
    long startTime;
    long currentTime;
    
    double speedLeft;
    double speedRight;
    
    double maxLeft = 5;
    double maxRight = 5;
    
    public void robotInit() 
    {	
        frontLeft = new CANTalon(1);
    	frontRight = new CANTalon(2);
    	backLeft = new CANTalon(4);
    	backRight = new CANTalon(3);        
        
        stick1 = new Joystick(0);
        stick2 = new Joystick(1);
        
        leftEncoder = new Encoder(3, 2, true);
        leftEncoder.setDistancePerPulse((2*Math.PI) / 1440); // radians per pulse
        leftEncoder.setSamplesToAverage(64);
        rightEncoder = new Encoder(7, 6, false);
        rightEncoder.setDistancePerPulse((2*Math.PI) / 1440);
        rightEncoder.setSamplesToAverage(64);
        
    	drive = new RobotDrive(frontLeft, backLeft, frontRight, backRight);
    	
    	isFirstTime = true;
    }
    
	/**
	 * This autonomous (along with the chooser code above) shows how to select between different autonomous modes
	 * using the dashboard. The sendable chooser code works with the Java SmartDashboard. If you prefer the LabVIEW
	 * Dashboard, remove all of the chooser code and uncomment the getString line to get the auto name from the text box
	 * below the Gyro
	 *
	 * You can add additional auto modes by adding additional comparisons to the switch structure below with additional strings.
	 * If using the SendableChooser make sure to add them to the chooser code above as well.
	 */
    public void autonomousInit() 
    {
		autoSelected = SmartDashboard.getString("Auto Selector", defaultAuto);
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() 
    {

    	
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() 
    {
    	if(isFirstTime)
    	{
    		startTime = System.nanoTime();
    	}
        drive.arcadeDrive(1, 0);
        
        currentTime = System.nanoTime() - startTime;
        
        speedLeft = leftEncoder.getRate();
        speedRight = rightEncoder.getRate();
        
		if (speedLeft > maxLeft)
				maxLeft = speedLeft;
		if (speedRight > maxRight)
				maxRight = speedRight;
        
        System.out.println("Time: " + currentTime + "\t" + speedLeft + "\t" + speedRight + "\t" + maxLeft + "\t" + maxRight);
    
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() 
    {
    
    }
    
}
