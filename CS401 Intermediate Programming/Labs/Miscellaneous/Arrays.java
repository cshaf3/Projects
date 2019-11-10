import java.io.BufferedReader;
import java.util.*;

public class Arrays
{	
	public static double max(double[] data)
	{
		double max = data[0];
		for (int i = 1; i < data.length; i++)
		{
			if(data[i] > max)
			{
				max = data[i];
			}
		}
		return max;
	}
	public static double min(double[] data)
	{
		double min = data[0];
		for (int i = 1; i < data.length; i++)
		{
			if(data[i] < min)
			{
				min = data[i];
			}
		}
		return min;
	}
	public static double sum(double[] data)
	{
		double sum = 0;
		for (int i = 0; i < data.length; i++)
		{
			sum += data[i];
		}
		return sum;
	}
	public static double ave(double[] data)
	{
		double sum = 0;
		for (int i = 0; i < data.length; i++)
		{
			sum += data[i];
		}
		double ave = sum / data.length;
		return ave;
	}
}