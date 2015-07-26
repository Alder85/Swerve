
package org.usfirst.frc.team2220.robot;


import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * this will do drive logic?
 */    
public class Robot extends SampleRobot{
	
    Joystick stick;
    SModule frontRight;
    AnalogInput potatoe;
    SmartDashboard board;
    double analogVal;
    
    public Robot() {
        stick = new Joystick(0);
        potatoe = new AnalogInput(0);
        board = new SmartDashboard();
    } 

    /**
     * Nothing rn
     */
    public void autonomous() {
        
    }

    /**
     * Runs the motors with cool steering
     */
    public void operatorControl() {
        //myRobot.setSafetyEnabled(true);
        while (isOperatorControl() && isEnabled()) {
            //myRobot.arcadeDrive(stick); // drive with arcade style (use right stick)
        	analogVal = potatoe.getAverageVoltage();
        	board.putNumber("AnalogVal", analogVal);
            Timer.delay(0.005);		// wait for a motor update time
        }
    }

    /**
     * Runs during test mode
     */
    public void test() {
    }
}
