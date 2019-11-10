import java.util.Scanner;
import java.io.BufferedReader;

public class WEEK11PARTICIPATION_SHAFER
{
	private String name;
	private int idNumber;
	private int hours;
	private double rate;

	public PayRoll(String n, int id)
	{
		name = n;
		idNumber = id;
		hours = 0;
		rate = 0.0;
	}

	public String getName()
	{
		return name;
	}
	public int getidNumber()
	{
		return idNumber;
	}
	public int getHours()
	{
		return hours;
	}
	public double getRate()
	{
		return rate;
	}
	public void setName(String n)
	{
		name = n;
	}
	public void setidNumber(int id)
	{
		idNumber = id;
	}
	public void setHours(int h)
	{
		hours = h;
	}
	public void setRate(double r)
	{
		rate = r;
	}
	public double grossPay()
	{
		grossPay = rate * hours;
		return grossPay;
	}
}