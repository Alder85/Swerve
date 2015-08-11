
package org.usfirst.frc.team2220.robot;


import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * this will do drive logic?
 */    
public class Robot extends SampleRobot{
	
    Joystick stick;
    SModule backleft;
    AnalogInput rot_bl;
    CANTalon motor_bl;
    SmartDashboard board;
    
    double rot_blVal;
    
    public Robot() {
        //3395
        motor_bl = new CANTalon(8);
        rot_bl = new AnalogInput(0);
        backleft = new SModule(motor_bl, rot_bl);
        board = new SmartDashboard();
        stick = new Joystick(0);
    } 

    /**
     * Nothing rn void 
     */
    public void autonomous() {
    	
    }

    /**
     * Start swerve tesgin
     */
    public void operatorControl() {
        while (isOperatorControl() && isEnabled()) {
    		/*
        	if(stick.getRawAxis(2) < -0.5)
        		backleft.setRot(0);
        	else
        		backleft.setRot(3.0);
        		*/
        	backleft.setRot(stick.getRawAxis(5) * 3);

        	board.putNumber("Err", backleft.getErr());
    		board.putNumber("AnalogValue", rot_bl.getAverageValue());
    		board.putNumber("MotorVal", motor_bl.get());
    		Timer.delay(0.005);	
    	}
    }
    /**
     * Runs the motors with cool steering
     */
    /*
    public void testEncoders() {
        //myRobot.setSafetyEnabled(true);
        //while (isOperatorControl() && isEnabled()) {
            //myRobot.arcadeDrive(stick); // drive with arcade style (use right stick)
        	
        	//getValue = 0 to ~39000
        	//getAccumulatedValue = continually adds the getValue
        	//getVoltage = 0 to 5 volts
        	//getAccumulatorCount = how many times the accumulator has counted
        	//pidGet = with no pid control, just the voltage
        	//getAverageValue = sample sizes the getValue
        	board.putNumber("AnalogVoltage", potatoe.getAverageVoltage());
        	board.putNumber("AnalogValue", potatoe.getAverageValue());
        	
        	digVal = digEncoder.get();
        	board.putNumber("DigVal", digVal);
        	
        	gyroRate = dubBoard.getRate();
        	board.putNumber("Gyro Rate ", gyroRate);
        	
        	gyroAngle = dubBoard.getAngle();
        	board.putNumber("Gyro Angle ", gyroAngle);
        	/*
        	if(analogVal > 2.0 && analogVal < 3.0)
        		motorRun.set(0.3);
        	else
        		motorRun.set(0);
        	///
        	
            Timer.delay(0.005);		// wait for a motor update time
        //}
    }
     */

    /**
     * Runs during test mode
     */
    public void test() {
    }
}
