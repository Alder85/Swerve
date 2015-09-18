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
		/*
		speedEnc = sEnc;
		speedMotor = sTalon;
		speed = new PIDController(0.01, 0.1, 1.0, 0.01, speedEnc, speedMotor); //feed forward for continuous rotation
		speed.setContinuous(true);
		
		rotMotor = rTalon;
		rotEnc = rEnc;
		rotation = new PIDController(0.01, 0.1, 1.0, rotEnc, rotMotor);
		*/
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
		rotation = new SPID(0.00208, 0.0, 0.0, rotMotor, rotEnc);
		//rotation = new PIDController(0.0, 0.00, 0.0, rotEnc, rotMotor); //0.065
		//rotation.enable();
		//rotation.setInputRange(1.4, 1.5);
		//rotation.setOutputRange(-0.25, 0.25);
		//rotation.setAbsoluteTolerance(0.1);
	}
	
	public void setoff(double off)
	{
		rotation.setOffset(off);
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
	
	/**
	 * Set the speed of the talon
	 * @param rps desired rotations per second
	 */
	public void setSpeed(double rps)
	{
		speed.setSetpoint(rps);
	}
	
	
}
