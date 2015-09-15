
package org.usfirst.frc.team2220.robot;


import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Overall drive logic and control for the swerve drive
 */    
public class Robot extends SampleRobot{
	
    Joystick stick;
    SModule backleft;
    AnalogInput rot_bl;
    CANTalon motor_bl;
    SmartDashboard board;
    
    int dashboardCount;
    int dashUpdateRate;
    
    double rot_blVal;
    
    /*
     * Initialization of all motors, sensor etc
     */
    public Robot() {
        //3395
        motor_bl = new CANTalon(8);
        rot_bl = new AnalogInput(0);
        backleft = new SModule(motor_bl, rot_bl);
        board = new SmartDashboard();
        stick = new Joystick(0);
        dashboardCount = 0;
    } 

    /**
     * Nothing rn void 
     */
    public void autonomous() {
    	
    }

    /**
     * Start swerve testing
     */
    public void operatorControl() {
    	//backleft.setRot(3.2);
    	double stickVal;
        while (isOperatorControl() && isEnabled()) {
        	stickVal = (stick.getRawAxis(5) * -2.5) + 2.5;
        	if(stickVal > 4.75)
        		stickVal = 4.75;
        	backleft.setRot(stickVal);  //essentially the wheel follows the joystick
        	
        	backleft.update();
        	
        	
        	dashboardCount++;
        	if(dashboardCount % 100 == 0)
        	{
        		board.putNumber("Voltage", rot_bl.getAverageVoltage());  
	        	board.putNumber("Err", backleft.getErr());
	    		board.putNumber("AnalogValue", rot_bl.getAverageValue());
	    		board.putNumber("MotorVal", motor_bl.get());
	    		board.putNumber("DesiredVal", backleft.getDegrees());
	    		//In order:
	    		//Encoder Value 0 to ~4.7V
	    		//Error -250 to 250
	    		//Encoder Value 0 to ~3900
	    		//Value of the motor -1 to 1
	    		//Setpoint
        	}
    		Timer.delay(0.0005);	
    	}
    }
    

    /**
     * Runs during test mode
     */
    public void test() {
    }
}
