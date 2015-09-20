
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
    
    SModule backright;
    AnalogInput rot_br;
    CANTalon motor_br;
    
    SModule frontleft;
    AnalogInput rot_fl;
    CANTalon motor_fl;
    
    SModule frontright;
    AnalogInput rot_fr;
    CANTalon motor_fr;
    
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
        backleft.setRotPID(0.00608, 0.00002, 0.0);
        backleft.setOff(-0.3);
        
        motor_br = new CANTalon(10);
        rot_br = new AnalogInput(1);
        backright = new SModule(motor_br, rot_br);
        backright.setRotPID(0.00508, 0.0000, 0.0);
        backright.setOff(-0.7);
        
        motor_fr = new CANTalon(9);
        rot_fr = new AnalogInput(2);
        frontright = new SModule(motor_fr, rot_fr);
        frontright.setRotPID(0.00608, 0.00002, 0.0);
        frontright.setOff(0);
        
        motor_fl = new CANTalon(11);
        rot_fl = new AnalogInput(3);
        frontleft = new SModule(motor_fl, rot_fl);
        frontleft.setRotPID(0.00308, 0.0000/*2*/, 0.0);
        frontleft.setOff(1.4);
        
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
        	/*
        	if(stick.getRawAxis(2) > 0)
        		stickVal = 1;
        	else
        		stickVal = 4;
        		*/
        	backleft.setRot(stickVal);  //essentially the wheel follows the joystick
        	backleft.update();
        	backright.setRot(stickVal);
        	backright.update();
        	frontleft.setRot(stickVal);
        	//frontleft.update();
        	//frontright.setRot(stickVal);
        	//frontright.update();
        	
        	
        	dashboardCount++;
        	if(dashboardCount % 100 == 0)
        	{
        		board.putNumber("Voltage", rot_bl.getAverageVoltage());  
	        	board.putNumber("Err", backleft.getErr());
	    		board.putNumber("AnalogValue", rot_bl.getAverageValue());
	    		board.putNumber("MotorVal", motor_bl.get());
	    		board.putNumber("DesiredVal", backleft.getDegrees());
	    		//board.putNumber("fr_voltage", rot_fr.getAverageVoltage());
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
