package org.usfirst.frc.team2220.robot;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.TalonSRX;

public class SModule {
	
	CANTalon speedMotor;
	Encoder speedEnc;
	PIDController speed;
	
	CANTalon rotMotor;
	AnalogInput rotEnc; //Encoder rotEnc;
	PIDController rotation;
	
	
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
		speedEnc = sEnc;
		speedMotor = sTalon;
		speed = new PIDController(0.01, 0.1, 1.0, 0.01, speedEnc, speedMotor); //feed forward for continuous rotation
		speed.setContinuous(true);
		
		rotMotor = rTalon;
		rotEnc = rEnc;
		rotation = new PIDController(0.01, 0.1, 1.0, rotEnc, rotMotor);
	}
	
	/**
	 * Constructor for testing only rotation
	 * @param rTalon Talon for rotational control
	 * @param rEnc Encoder for rotational control
	 */
	public SModule(CANTalon rTalon, AnalogInput rEnc)
	{
		rotMotor = rTalon;
		rotEnc = rEnc;
		rotation = new PIDController(0.0, 0.00, 0.0, rotEnc, rotMotor); //0.065
		//rotation.enable();
		//rotation.setInputRange(1.4, 1.5);
		rotation.setOutputRange(-0.25, 0.25);
		rotation.setAbsoluteTolerance(0.1);
	}
	
	public void enable()
	{
		rotation.enable();
	}
	
	/**
o 0
	 */
	public void setRot(double degrees)
	{
		degrees = degrees; //math
		rotation.setSetpoint(degrees);
	}
	
	public double getDegrees()
	{
		return rotation.getSetpoint();
	}
	public double getErr()
	{
		return rotation.getError();
	}
	
	/**
	 * Set the speed of the talon
	 * @param rps desired rotations per second
	 */
	public void setSpeed(double rps)
	{
		speed.setSetpoint(rps);
	}
	
	
}
