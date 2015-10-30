package org.usfirst.frc.team2220.robot;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.TalonSRX;

/*
 * Represents an individual swerve module with two PID controllers, one for speed and one for rotation
 */
public class SModule {
	
	CANTalon speedMotor;
	Encoder speedEnc;
	PIDController speed;
	
	CANTalon rotMotor;
	AnalogInput rotEnc; //Encoder rotEnc;
	//PIDController rotation;
	SPID rotation;
	
	/**
	 * Constructor for the module, declares PID Controls
	 * 
	 * @param sTalon Talon for speed control
	 * @param sEnc Encoder for speed control
	 * @param rTalon Talon for rotational control
	 * @param rEnc Encoder for rotational control
	 * 
	 */
	public SModule(CANTalon sTalon, Encoder sEnc, CANTalon rTalon, AnalogInput rEnc)
	{
		rotMotor = rTalon;
		rotEnc = rEnc;
		speedMotor = sTalon;
		speedEnc = sEnc;
	}
	
	public SModule(CANTalon rTalon, AnalogInput rEnc, CANTalon sTalon)
	{
		rotMotor = rTalon;
		rotEnc = rEnc;
		speedMotor = sTalon;
	}
	/**
	 * Constructor for testing only rotation
	 * @param rTalon Talon for rotational control
	 * @param rEnc Encoder for rotational control
	 */
	public SModule(CANTalon rTalon, AnalogInput rEnc)
	{
		rotMotor = rTalon;
		rotEnc = rEnc; //0.00088
		
	}
	
	/**
	 * Set the speed of the talon
	 * @param val desired speed of Speed Motor
	 */
	public void setSpeed(double val)
	{
		speedMotor.set(val);
	}
	
	public void setRotPID(double P, double I, double D)
	{
		rotation = new SPID(P, I, D, rotMotor, rotEnc);
	}
	
	public void setOff(double off)
	{
		rotation.setOffset(off);
	}
	
	public void runMotor(double goVal)
	{
		speedMotor.set(0.25 * goVal);
	}
	
	/*
	 * Updates each PID controller
	 * Should be constantly called
	 */
	public void update()
	{
		rotation.calculate();
	}
	
	/*
	 * Sets the setpoint
	 * Will eventually be scaled to degrees, right now 0 to 5
	 * @param degrees number of degrees, 0 to 360
	 */
	public void setRot(double degrees)
	{
		degrees = degrees; //math
		rotation.setSetpoint(degrees);
	}
	
	/*
	 * @return the setpoint
	 */
	public double getDegrees()
	{
		return rotation.getSetpoint();
	}
	/*
	 * @return the error
	 */
	public double getErr()
	{
		return rotation.getError();
	}
	
	
	
	
}
