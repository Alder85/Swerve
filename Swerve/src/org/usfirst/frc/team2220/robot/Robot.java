
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
    CANTalon speed_bl;
    
    SModule backright;
    AnalogInput rot_br;
    CANTalon motor_br;
    CANTalon speed_br;
    
    SModule frontleft;
    AnalogInput rot_fl;
    CANTalon motor_fl;
    CANTalon speed_fl;
    
    SModule frontright;
    AnalogInput rot_fr;
    CANTalon motor_fr;
    CANTalon speed_fr;
    
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
        speed_bl = new CANTalon(2);
        backleft = new SModule(motor_bl, rot_bl, speed_bl);
        backleft.setRotPID(-0.00308, 0/*.00002*/, 0.0);
        backleft.setOff(1.3);
        
        
        motor_br = new CANTalon(10);
        rot_br = new AnalogInput(1);
        speed_br = new CANTalon(6);
        backright = new SModule(motor_br, rot_br, speed_br);
        backright.setRotPID(-0.00308, 0.0000, 0.0);
        backright.setOff(-0.7);
        
        motor_fr = new CANTalon(9);
        rot_fr = new AnalogInput(2);
        speed_fr = new CANTalon(7);
        frontright = new SModule(motor_fr, rot_fr, speed_fr);
        frontright.setRotPID(0.00408, 0.0000, 0.0);
        frontright.setOff(1.8);
        
        motor_fl = new CANTalon(11);
        rot_fl = new AnalogInput(3);
        speed_fl = new CANTalon(3);
        frontleft = new SModule(motor_fl, rot_fl, speed_fl);
        frontleft.setRotPID(-0.00308, 0.0000/*2*/, 0.0);
        frontleft.setOff(1);
        
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
        	backleft.update();
        	backright.update();
        	frontleft.update();
        	backright.update();
        	/*
        	double goVal = stick.getRawAxis(1);
        	
        	backleft.runMotor(goVal);
        	backright.runMotor(goVal);
        	frontleft.runMotor(goVal);
        	frontright.runMotor(goVal);
        	
        	backleft.setRot(stickVal);  //essentially the wheel follows the joystick
        	backleft.update();
        	
        	backright.setRot(stickVal);
        	backright.update();
        	
        	frontleft.setRot(stickVal);
        	frontleft.update();
        	
        	frontright.setRot(stickVal);
        	frontright.update();
        	*/
        	
        	dashboardCount++;
        	if(dashboardCount % 100 == 0)
        	{
        		displayBR();
        		
        		
        		
        		/*//Front Right
        		
	    		*/
        		/*//Front Left
        		
	    		*/
	    		
        	}
    		Timer.delay(0.0005);	
    	}
    }
    public void displayBR()
    {
		board.putNumber("Voltage", rot_br.getAverageVoltage());  
    	board.putNumber("Err", backright.getErr());
		board.putNumber("AnalogValue", rot_br.getAverageValue());
		board.putNumber("MotorVal", motor_br.get());
		board.putNumber("DesiredVal", backright.getDegrees());
    }
    
    public void displayBL()
    {
		board.putNumber("Voltage", rot_bl.getAverageVoltage());  
    	board.putNumber("Err", backleft.getErr());
		board.putNumber("AnalogValue", rot_bl.getAverageValue());
		board.putNumber("MotorVal", motor_bl.get());
		board.putNumber("DesiredVal", backleft.getDegrees());
    }
    public void displayFR()
    {
    	board.putNumber("Voltage", rot_fr.getAverageVoltage());  
    	board.putNumber("Err", frontright.getErr());
		board.putNumber("AnalogValue", rot_fr.getAverageValue());
		board.putNumber("MotorVal", motor_fr.get());
		board.putNumber("DesiredVal", frontright.getDegrees());
    }
    public void displayFL()
    {
    	board.putNumber("Voltage", rot_fl.getAverageVoltage());  
    	board.putNumber("Err", frontleft.getErr());
		board.putNumber("AnalogValue", rot_fl.getAverageValue());
		board.putNumber("MotorVal", motor_fl.get());
		board.putNumber("DesiredVal", frontleft.getDegrees());
    }

    /**
     * Runs during test mode
     */
    public void test() {
    }
}
