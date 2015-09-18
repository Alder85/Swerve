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
	private double prevErr;
	private double offset;
	
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
	 * @WIP
	 */
	public void calculate()
	{
		double err = getError();
		double aP = kP * err;
		double aI = 0;
		double aD = 0;
		double out;
		out = aP + aI + aD;
		rot.set(out);
		
		prevErr = err;
	}
	
	/*
	 * Sets the setpoint
	 * @param x Desired setpoint
	 */
	public void setSetpoint(double x)
	{
		setpoint = x;
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
		if(out > 5)
			out -= 5;
		if(out < 0)
			out = (5 + out);
		out -= setpoint;
		if(out > 2.5)
			out -= 5;
				//(setpoinf.5) - rotEnc.getAverageVoltage();
		
		return out * 100;
	}
	
	/*
	 * Offset value -2.5 to 2.5
	 */
	public void setOffset(double in)
	{
		offset = in;
	}
	
	
	
}
