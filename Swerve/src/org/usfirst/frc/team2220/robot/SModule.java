package org.usfirst.frc.team2220.robot;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.PIDController;

public class SModule {
	
	Talon speedMotor;
	Encoder speedEnc;
	PIDController speed;
	
	Talon rotMotor;
	AnalogInput rotEnc; //Encoder rotEnc;
	PIDController rotation;
	
	
	/**
	 * Constructor for the module, declares PID Controls
	 * 
	 * @param sTalon Talon for speed control
	 * @param sEnc Encoder for speed control
	 * @param rTalon Talon for rotational control
	 * @param rEnc Encoder for rotational controll
	 * 
	 */
	public SModule(Talon sTalon, Encoder sEnc, Talon rTalon, AnalogInput rEnc)
	{
		speedEnc = sEnc;
		speedMotor = sTalon;
		speed = new PIDController(0.0, 0.0, 0.0, speedEnc, speedMotor);
		speed.setContinuous(true);
		
		rotMotor = rTalon;
		rotEnc = rEnc;
		rotation = new PIDController(0.0, 0.0, 0.0, rotEnc, rotMotor);
	}
	
	/**
	 * 0 is straight forward, 360 is also straight forward
	 * @param degrees set the degrees relative to 0
	 */
	public void setRot(double degrees)
	{
	
	}
	
	/**
	 * Set the speed of the talon
	 * @param rps desired rotations per second
	 */
	public void setSpeed(double rps)
	{
	
	}
	
	
}
