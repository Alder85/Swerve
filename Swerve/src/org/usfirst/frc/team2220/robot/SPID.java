package org.usfirst.frc.team2220.robot;

import edu.wpi.first.wpilibj.*;

/*
 * SPID or Swerve PID is a really ghetto PID controller designed by Josh
 */
public class SPID {
	
	/*
	 * All the variables
	 */
	private final double kP, kI, kD;
	private CANTalon rot;
	private AnalogInput rotEnc;
	private double setpoint;
	private double accumErr;
	private double offset;
	private final double deadZone = 1;
	private boolean magnitude = true;
	private double oldSetpoint;
	/*
	 * Constructor to set constants
	 * @param p Proportional constant
	 * @param i Integral constant
	 * @param d Derivative constant
	 * @param tal CANTalon to be controlled
	 * @param ai Analog Encoder to be read to determine error
	 */
	public SPID(double p, double i, double d, CANTalon tal, AnalogInput ai)
	{
		kP = p;
		kI = i;
		kD = d;
		rot = tal;
		rotEnc = ai;	
	}
	
	/*
	 * Uses error to determine motor speed
	 * Currently only P is working
	 * @WIP I
	 */
	public void calculate()
	{
		double err = getError();
		if(err == 0)
		{
			rot.set(0);
			accumErr = 0;
			return;
		}
		double aP = kP * err;
		accumErr += err;
		double aI = kI * accumErr;
		double aD = 0;
		double out;
		out = aP;// + aI + aD;
		double maxVal = 0.5;
		if(out > maxVal)
			out = maxVal;
		if(out < -maxVal)
			out = -maxVal;
		rot.set(-out);
		
		
	}
	
	/*
	 * Sets the setpoint
	 * @param x Desired setpoint
	 */
	public void setSetpoint(double x)
	{
		setpoint = x;
	}
	
	public boolean getMag()
	{
		return magnitude;
	}
	
	/*
	 * Returns the setpoint for debugging
	 * @return the setpoint
	 */
	public double getSetpoint()
	{
		return setpoint;
	}
	
	/*
	 * Calculates, scales and returns the error
	 * @return the error
	 */
	public double getError()
	{
		double out = (rotEnc.getAverageVoltage() + offset);
		while(out > 5)
			out -= 5;
		while(out < 0)
			out += 5;
		out -= setpoint;
		while(out > 2.5)
			out -= 5;
		while(out < -2.5)
			out += 5;
		out *= 100;
		if(out < deadZone && out > -deadZone) //deadzone
			out = 0;
		return out;
	}
	
	/*
	 * Offset value -2.5 to 2.5
	 */
	public void setOffset(double in)
	{
		offset = in;
	}
	
	
	
}
