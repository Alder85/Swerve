package org.usfirst.frc.team2220.robot;

import edu.wpi.first.wpilibj.*;

public class SPID {
	
	private double kP, kI, kD;
	private CANTalon rot;
	private AnalogInput rotEnc;
	private double setpoint;
	
	public SPID(double p, double i, double d, CANTalon tal, AnalogInput ai)
	{
		kP = p;
		kI = i;
		kD = d;
		rot = tal;
		rotEnc = ai;	
	}
	
	public void calculate()
	{
		double err = getError();
		double aP = kP * err;
		double aI = 0;
		double aD = 0;
		double out;
		out = aP + aI + aD;
		rot.set(out);
	}
	
	public void setSetpoint(double x)
	{
		setpoint = x;
	}
	
	public double getSetpoint()
	{
		return setpoint;
	}
	
	public double getError()
	{
		double out = rotEnc.getAverageVoltage() - setpoint;
		if(out > 2.5)
			out -= 5;
				//(setpoinf.5) - rotEnc.getAverageVoltage();
		
		return out * 100;
	}
	
	
	
}
